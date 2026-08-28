package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.CBSCP914Refining;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class SCP914BlockEntity extends BlockEntity {

	//	Constants
	/**
	 * The time taken for the machine to "start up" in ticks. Once this time has passed, the doors start to close and the actual crafting process takes place.
	 */
	private static final int START_UP_TIME = 20;
	private static final int TICKS_FOR_DOORS_TO_MOVE = SlidingDoorBlockEntity.TIME_TO_OPEN;
	/**
	 * Determines how large a single dimension of the Area an Input/Output can be.
	 */
	private static final int MAX_ENCLOSED_SIZE = 5;

	//	NBT Key Constants
	private static final String CURRENT_SETTING_KEY = "CurrentSetting";
	private static final String INPUT_DOOR_LOCATION_KEY = "InputDoorLocation";
	private static final String OUTPUT_DOOR_LOCATION_KEY = "OutputDoorLocation";

	//	Cosmetic Rotation
	/**
	 * Rotation of the main Knob in degrees, ranging from 0 to 180.
	 */
	public short KnobRotationDegrees = (short) RefiningSetting.ONEONE.toDegrees();
	/**
	 * Rotation of the key in degrees, ranging from 0 to 360.
	 */
	public short KeyRotationDegrees = 0;

	//	Machine state variables
	/**
	 * The setting of 914 as defined in {@link RefiningSetting}
	 */
	private RefiningSetting CurrentSetting = RefiningSetting.ONEONE;
	/**
	 * The state of 914 as defined in {@link MachineState}
	 */
	private MachineState CurrentState = MachineState.IDLE;
	/**
	 * Used to "start up" the machine. Allows for the doors to close before the actual crafting begins.
	 */
	private int StartUpDelay = 0;
	/**
	 * Used to actually count the crafting time, and is set once the doors have closed and ingredients have been identified.
	 */
	private int CraftingTime = 0;

	//	Input / Output Locations
	@Nullable private BlockPos InputDoorLocation;
	@Nullable private AABB InputBBox;
	@Nullable private BlockPos OutputDoorLocation;
	@Nullable private AABB OutputBBox;

	public SCP914BlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(SCPBlockEntities.SCP914.get(), pPos, pBlockState);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		if (pTag.contains(CURRENT_SETTING_KEY)) this.CurrentSetting = RefiningSetting.values()[pTag.getInt(CURRENT_SETTING_KEY)];
		if (pTag.contains(INPUT_DOOR_LOCATION_KEY)) this.InputDoorLocation = BlockPos.of(pTag.getLong(INPUT_DOOR_LOCATION_KEY));
		if (pTag.contains(OUTPUT_DOOR_LOCATION_KEY)) this.OutputDoorLocation = BlockPos.of(pTag.getLong(OUTPUT_DOOR_LOCATION_KEY));
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putInt(CURRENT_SETTING_KEY, this.CurrentSetting.ordinal());
		if (this.InputDoorLocation != null) pTag.putLong(INPUT_DOOR_LOCATION_KEY, this.InputDoorLocation.asLong());
		if (this.OutputDoorLocation != null) pTag.putLong(OUTPUT_DOOR_LOCATION_KEY, this.OutputDoorLocation.asLong());
	}

	/**
	 * Allows for servers and clients to reliably sync
	 */
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		saveAdditional(tag);
		return tag;
	}

	/**
	 * Attempt to turn the knob of 914, ideally changing its current refinement setting.
	 *
	 * @return True if the event is consumed, false otherwise
	 */
	public boolean tryKnob(Player player) {
		if (CurrentState != MachineState.IDLE) {
			player.displayClientMessage(LockdownTextComponents.SCP914_BUSY, true);
			return false;
		}

		CurrentSetting = CurrentSetting.cycleSetting();
		player.displayClientMessage(CurrentSetting.getSettingMessage(), true);
		return true;
	}

	/**
	 * Attempt to activate 914.
	 *
	 * @return True if the event is consumed, false otherwise
	 */
	public boolean tryKey(Player player) {
		if (!areDoorsValid()) {
			player.displayClientMessage(LockdownTextComponents.SCP914_LINK_REQUIRED, true);
			return false;
		}

		if (CurrentState != MachineState.IDLE) {
			player.displayClientMessage(LockdownTextComponents.SCP914_BUSY, true);
			return false;
		}

		KeyRotationDegrees = 20;
		this.level.playSound(null, worldPosition, SCPSounds.SCP914_REFINING_START.get(), SoundSource.BLOCKS, 1, 1);
		this.StartUpDelay = START_UP_TIME + TICKS_FOR_DOORS_TO_MOVE;
		this.CurrentState = MachineState.STARTING;

		return true;
	}

	public void tick() {
		//  Regardless of state, match the Knob Angle to the requested angle
		if (KnobRotationDegrees != CurrentSetting.toDegrees()) {
			if (CurrentSetting.toDegrees() - KnobRotationDegrees > 0)
				KnobRotationDegrees += (short) ((CurrentSetting.toDegrees() - KnobRotationDegrees > 9) ? 18 : 9);
			else KnobRotationDegrees -= (short) ((CurrentSetting.toDegrees() - KnobRotationDegrees < -9) ? 18 : 9);
		}

		//	Key Spin
		if (KeyRotationDegrees != 0) KeyRotationDegrees += 20;
		if (KeyRotationDegrees > 360) KeyRotationDegrees = 0;

		//	If idle, we are doing nothing, so just stop here
		if (CurrentState == MachineState.IDLE) return;

		if (CurrentState == MachineState.STARTING) {
			StartUpDelay--;

			if (StartUpDelay == TICKS_FOR_DOORS_TO_MOVE) {
				closeDoor(InputDoorLocation);
				closeDoor(OutputDoorLocation);
			}

			if (StartUpDelay == 0) {
				CurrentState = MachineState.PROCESSING;
				//	TODO: Items are now in the machine, we can set the time to craft properly now
				if (!level.isClientSide)
					SCPNetwork.NETWORK.send(PacketDistributor.DIMENSION.with(() -> level.dimension()), new CBSCP914Refining(getBlockPos()));
				CraftingTime = 235;
			}

			return;
		}

		if (CurrentState == MachineState.PROCESSING || CurrentState == MachineState.FINISHING) {
			CraftingTime--;

			if (CraftingTime == 70) {
				this.level.playSound(null, worldPosition, SCPSounds.SCP914_REFINING_STOP.get(), SoundSource.BLOCKS, 1, 1);
				CurrentState = MachineState.FINISHING;
			}

			if (CraftingTime == 0) {
				openDoor(InputDoorLocation);
				openDoor(OutputDoorLocation);
				CurrentState = MachineState.IDLE;
			}
		}
	}

	/**
	 * Opens a given door
	 */
	private void openDoor(BlockPos doorPos) {
		this.level.setBlockAndUpdate(doorPos, this.level.getBlockState(doorPos).setValue(SlidingDoorBlock.OPEN, true));
	}

	/**
	 * Closes a given door
	 */
	private void closeDoor(BlockPos doorPos) {
		this.level.setBlockAndUpdate(doorPos, this.level.getBlockState(doorPos).setValue(SlidingDoorBlock.OPEN, false));
	}

	/**
	 * Attempt to link a given door to 914.
	 *
	 * @param doorPos The position of the door we are linking, either top or bottom half.
	 * @return True if successfully linked. False otherwise.
	 */
	public boolean linkDoor(BlockPos doorPos) {
		//	First, test if the given position is actually a door
		if (!(level.getBlockState(doorPos).getBlock() instanceof SlidingDoorBlock)) return false;

		//	To ensure we get the correct position of the door with the block entity, we can use a function within SlidingDoorBlock to get the block entity itself
		SlidingDoorBlockEntity slidingDoorBlockEntity = SlidingDoorBlock.getSlidingDoorEntity(level, doorPos, level.getBlockState(doorPos));
		if (slidingDoorBlockEntity == null) return false;
		doorPos = slidingDoorBlockEntity.getBlockPos();

		//	Check if the door has already been linked
		if (slidingDoorBlockEntity.isSCP914Linked())
			return false;

		//	If we have no input door, link it to that first (Java cant reference primitives :( )
		if (this.InputDoorLocation == null) {
			//	Find AABB first. If it fails, there's no enclosed space, meaning we can discard
			this.InputBBox = tryFindEnclosedSpaceFromDoor(doorPos);
			if (this.InputBBox == null)
				return false;

			this.InputDoorLocation = doorPos;
			slidingDoorBlockEntity.linkSCP914(this.getBlockPos());
			openDoor(doorPos);
			return true;
		}

		//	If we have no output door, link it to that (same as above)
		if (this.OutputDoorLocation == null) {
			this.OutputBBox = tryFindEnclosedSpaceFromDoor(doorPos);
			if (this.OutputBBox == null)
				return false;

			this.OutputDoorLocation = doorPos;
			slidingDoorBlockEntity.linkSCP914(this.getBlockPos());
			openDoor(doorPos);
			return true;
		}

		//	If none of the checks above passed, what are we doing here?
		return false;
	}

	/**
	 * Validates the Input and output doors to see if they (still) exist.
	 * This function, unlike the #isXDoorValid checks the inworld configuration of the doors to update internal variables.
	 *
	 * @return True if the current door configuration is valid, false if ANY are invalid.
	 */
	public boolean areDoorsValid() {
		//	Force Input and Output to be validated at the same time
		boolean in = isInputDoorValid();
		boolean out = isOutputDoorValid();
		return in && out;
	}

	/**
	 * Checks if the Input Doors configuration is (still) valid.
	 *
	 * @return True if the Input Configuration is valid. False otherwise.
	 */
	public boolean isInputDoorValid() {
		//	If Location is null, we can safely discard of the AABB
		if (InputDoorLocation == null) {
			InputBBox = null;
			return false;
		}

		//	Since the Location is not null, first check if it is in fact a door
		if (!(this.getLevel().getBlockEntity(InputDoorLocation) instanceof SlidingDoorBlockEntity)) {
			//	Door is invalid
			InputDoorLocation = null;
			InputBBox = null;
			return false;
		}

		//	If the AABB is null, we can try to find the AABB from the doors location
		if (InputBBox == null) {
			InputBBox = tryFindEnclosedSpaceFromDoor(InputDoorLocation);
			//	If It's still null, then it failed, so unlink this door
			if (InputBBox == null) {
				((SlidingDoorBlockEntity) level.getBlockEntity(InputDoorLocation)).unlinkSCP914();
				InputDoorLocation = null;
				return false;
			}
		} else {
			//	Validate that the AABB is still enclosed
			if (!isAABBEnclosed(InputBBox)) {
				((SlidingDoorBlockEntity) level.getBlockEntity(InputDoorLocation)).unlinkSCP914();
				InputDoorLocation = null;
				InputBBox = null;
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if the Output Doors configuration is (still) valid.
	 *
	 * @return True if the Output Configuration is valid. False otherwise.
	 */
	public boolean isOutputDoorValid() {
		//	If Location is null, we can safely discard of the AABB
		if (OutputDoorLocation == null) {
			OutputBBox = null;
			return false;
		}

		//	Since the Location is not null, first check if it is in fact a door
		if (!(this.getLevel().getBlockEntity(OutputDoorLocation) instanceof SlidingDoorBlockEntity)) {
			//	Door is invalid
			OutputDoorLocation = null;
			OutputBBox = null;
			return false;
		}

		//	If the AABB is null, we can try to find the AABB from the doors location
		if (OutputBBox == null) {
			OutputBBox = tryFindEnclosedSpaceFromDoor(OutputDoorLocation);
			//	If It's still null, then it failed, so unlink this door
			if (OutputBBox == null) {
				((SlidingDoorBlockEntity) level.getBlockEntity(OutputDoorLocation)).unlinkSCP914();
				OutputDoorLocation = null;
				return false;
			}
		} else {
			//	Validate that the AABB is still enclosed
			if (!isAABBEnclosed(OutputBBox)) {
				((SlidingDoorBlockEntity) level.getBlockEntity(OutputDoorLocation)).unlinkSCP914();
				OutputDoorLocation = null;
				OutputBBox = null;
				return false;
			}
		}

		return true;
	}

	/**
	 * Tests to see if the given AABB has blocks surrounding it (Including sliding doors)
	 *
	 * @return True if the AABB is enclosed, False otherwise
	 */
	private boolean isAABBEnclosed(AABB aabb) {
		//  First collect all positions that border this AABB (not including corners)
		//  Expand this aabb in all cardinal directions and list positions that DON'T intersect with the main one
		List<AABB> boundingBoxes = List.of(new AABB[]{
				new AABB(aabb.minX, aabb.minY, aabb.minZ - 1, aabb.maxX, aabb.maxY, aabb.minZ), //  North
				new AABB(aabb.minX, aabb.minY, aabb.maxZ, aabb.maxX, aabb.maxY, aabb.maxZ + 1), //  South
				new AABB(aabb.maxX, aabb.minY, aabb.minZ, aabb.maxX + 1, aabb.maxY, aabb.maxZ), //  East
				new AABB(aabb.minX - 1, aabb.minY, aabb.minZ, aabb.minX, aabb.maxY, aabb.maxZ), //  West
				new AABB(aabb.minX, aabb.maxY, aabb.minZ, aabb.maxX, aabb.maxY + 1, aabb.maxZ), //  Up
				new AABB(aabb.minX, aabb.minY - 1, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ)  //  Down
		});

		for (AABB bb : boundingBoxes) {
			for (BlockPos pos : Utils.boundingBoxToPositions(bb)) {
				//	If the surrounding block is not a full block AND not a sliding door, then we fail the check
				if (!level.getBlockState(pos).getBlock().isCollisionShapeFullBlock(level.getBlockState(pos), level, pos) && !(level.getBlockState(pos).getBlock() instanceof SlidingDoorBlock))
					return false;
			}
		}

		return true;
	}

	/**
	 * Traverses the given directionToSearch to find the min and max positions, giving back an AABB.
	 * It's a really simple search that should cover most cases, but doesn't check for if it's a cube.
	 *
	 * @param startingPos Position the search starts at.
	 * @param directionToSearch Directions to navigate.
	 * @return AABB of the space.
	 */
	private AABB simpleSpaceSearch(BlockPos startingPos, List<Direction> directionToSearch) {
		AABB space = new AABB(startingPos);
		BlockPos currentPos;
		for (Direction dir : directionToSearch) {
			//	Changing direction makes Positive and Negatives clash, meaning our currentPos will be out of sync with the min/max of space
			//	Before we start to expand the space, depending on if this is a positive or negative direction, ensure we are on the correct min/max values
			if (dir.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
				currentPos = new BlockPos((int) (space.maxX - 1), (int) (space.maxY - 1), (int) (space.maxZ - 1));
			} else {
				currentPos = new BlockPos((int) (space.minX), (int) (space.minY), (int) (space.minZ));
			}

			//	Keep expanding in this direction until we're too big, or we've hit a wall
			while (!level.getBlockState(currentPos.relative(dir)).getBlock().isCollisionShapeFullBlock(level.getBlockState(currentPos.relative(dir)), level, currentPos.relative(dir))) {

				space = space.expandTowards(Vec3.atLowerCornerOf(dir.getNormal()));
				currentPos = currentPos.relative(dir);

				//	Check the space isn't too big
				if (space.getXsize() > MAX_ENCLOSED_SIZE || space.getYsize() > MAX_ENCLOSED_SIZE || space.getZsize() > MAX_ENCLOSED_SIZE)
					return null;
			}
		}

		return space;
	}

	/**
	 * Given a Sliding Doors position, attempt to find an enclosed space.
	 * Currently only supports square enclosed spaces.
	 *
	 * @param pos The door to check for an enclosed area.
	 * @return The Enclosed AABB for this door.
	 */
	@Nullable
	private AABB tryFindEnclosedSpaceFromDoor(BlockPos pos) {
		//	First, check we are actually on a SlidingDoor
		BlockState doorState = level.getBlockState(pos);
		if (!(doorState.getBlock() instanceof SlidingDoorBlock))
			return null;

		//	Sanity check to see if we are on the bottom of the door
		if (!(level.getBlockEntity(pos) instanceof SlidingDoorBlockEntity))
			pos = pos.below();

		//	Since we don't know what side the enclosed space is going to be, we need to search both sides.
		//	Sliding doors can also be on 2 Axis, X and Z, so account for that
		Direction.Axis doorAxis = doorState.getValue(SlidingDoorBlock.HORIZONTAL_AXIS);
		@Nullable AABB positiveSpace = null;
		@Nullable AABB negativeSpace = null;

		//	Positive Axis
		//	Check if the door is unobstructed
		if (level.isEmptyBlock(pos.relative(doorAxis, 1)) && level.isEmptyBlock(pos.above().relative(doorAxis, 1))) {
			//	Get all directions and filter out this Axis' Negative direction (to prevent going back on ourselves)
			List<Direction> positiveDirections = Stream.of(Direction.values()).filter(direction ->
					!doorAxis.test(direction) || (doorAxis.test(direction) && direction.getAxisDirection() != Direction.AxisDirection.NEGATIVE)
			).toList();
			positiveSpace = simpleSpaceSearch(pos.relative(doorAxis, 1), positiveDirections);
		}

		//	Negative Axis
		//	Check if the door is unobstructed
		if (level.isEmptyBlock(pos.relative(doorAxis, -1)) && level.isEmptyBlock(pos.above().relative(doorAxis, -1))) {
			//	Get all directions and filter out this Axis' Positive direction (to prevent going back on ourselves)
			List<Direction> negativeDirections = Stream.of(Direction.values()).filter(direction ->
					!doorAxis.test(direction) || (doorAxis.test(direction) && direction.getAxisDirection() != Direction.AxisDirection.POSITIVE)
			).toList();
			negativeSpace = simpleSpaceSearch(pos.relative(doorAxis, -1), negativeDirections);
		}

		//	Ensure both spaces are enclosed
		if (positiveSpace != null && !isAABBEnclosed(positiveSpace))
			positiveSpace = null;
		if (negativeSpace != null && !isAABBEnclosed(negativeSpace))
			negativeSpace = null;

		//	Return the smallest non-null space
		if (positiveSpace == null && negativeSpace == null)
			return null;
		if (positiveSpace == null)
			return negativeSpace;
		if (negativeSpace == null)
			return positiveSpace;
		return positiveSpace.getSize() < negativeSpace.getSize() ? positiveSpace : negativeSpace;
	}

	/**
	 * @return The current state of 914.
	 */
	public MachineState getCurrentState() {
		return CurrentState;
	}

	public enum MachineState {
		IDLE,
		STARTING,
		PROCESSING,
		FINISHING
	}

	/**
	 * The Setting of the machine on how it refines.
	 */
	public enum RefiningSetting {
		ROUGH,
		COARSE,
		ONEONE,
		FINE,
		VERYFINE;

		public RefiningSetting cycleSetting() {
			return switch (this) {
				case ROUGH -> COARSE;
				case COARSE -> ONEONE;
				case ONEONE -> FINE;
				case FINE -> VERYFINE;
				case VERYFINE -> ROUGH;
			};
		}

		public int toDegrees() {
			return switch (this) {
				case ROUGH -> 0;
				case COARSE -> 45;
				case FINE -> 135;
				case VERYFINE -> 180;
				default -> 90;
			};
		}

		public MutableComponent getSettingMessage() {
			return switch (this) {
				case ROUGH -> LockdownTextComponents.SCP914_SET_ROUGH;
				case COARSE -> LockdownTextComponents.SCP914_SET_COARSE;
				case ONEONE -> LockdownTextComponents.SCP914_SET_ONEONE;
				case FINE -> LockdownTextComponents.SCP914_SET_FINE;
				case VERYFINE -> LockdownTextComponents.SCP914_SET_VERYFINE;
			};
		}
	}

	private static final AABB RENDER_BOUNDS = new AABB(-1, 0, -1, 2, 1, 2);
	@Nullable
	private AABB RenderBounds = null;

	@Override
	public AABB getRenderBoundingBox() {
		//	Store AABB instead of calling it over and over to reduce memory use
		if (RenderBounds == null) RenderBounds = RENDER_BOUNDS.move(worldPosition);
		return RenderBounds;
	}

	/**
	 * Syncs clients to server
	 */
	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
