package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.annotation.Nullable;
import java.util.List;

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
		//	TODO: Save and load of AABB area of input and output
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
			return false;
		}

		CurrentSetting = CurrentSetting.cycleSetting();
		return true;
	}

	/**
	 * Attempt to activate 914.
	 *
	 * @return True if the event is consumed, false otherwise
	 */
	public boolean tryKey(Player player) {
		if (!areDoorsValid()) {
			return false;
		}

		if (CurrentState != MachineState.IDLE) {
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

		if (KeyRotationDegrees != 0) KeyRotationDegrees += 20;
		if (KeyRotationDegrees > 360) KeyRotationDegrees = 0;

		if (CurrentState == MachineState.IDLE) return; //  Stop here to prevent processing further

		if (CurrentState == MachineState.STARTING) {
			StartUpDelay--;

			if (StartUpDelay == TICKS_FOR_DOORS_TO_MOVE) {
				setDoorOpen(InputDoorLocation, false);
				setDoorOpen(OutputDoorLocation, false);
			}

			if (StartUpDelay == 0) {
				CurrentState = MachineState.PROCESSING;
				//	TOOD, Items are now in the machine, we can set the time to craft properly now
				CraftingTime = 140;
				this.level.playSound(null, worldPosition, SCPSounds.SCP914_REFINING.get(), SoundSource.BLOCKS, 1, 1);
			}

			//  Check Doors if they are closed
			return;
		}

		if (CurrentState == MachineState.PROCESSING) {
			CraftingTime--;

			if (CraftingTime == 70) {
				this.level.playSound(null, worldPosition, SCPSounds.SCP914_REFINING_STOP.get(), SoundSource.BLOCKS, 1, 1);
			}

			if (CraftingTime == 0) {
				setDoorOpen(InputDoorLocation, true);
				setDoorOpen(OutputDoorLocation, true);
				CurrentState = MachineState.IDLE;
			}
		}

	}

	private void setDoorOpen(BlockPos doorPos, boolean doorOpen) {
		this.level.setBlock(doorPos, this.level.getBlockState(doorPos).setValue(SlidingDoorBlock.OPEN, doorOpen), Block.UPDATE_NONE);
		this.level.setBlock(doorPos.above(), this.level.getBlockState(doorPos.above()).setValue(SlidingDoorBlock.OPEN, doorOpen), Block.UPDATE_NONE);
	}

	private void configureDoor(BlockPos doorPos, boolean isNormalDoor) {
		if (!(this.level.getBlockEntity(doorPos) instanceof SlidingDoorBlockEntity)) return;
		this.level.setBlock(doorPos, this.level.getBlockState(doorPos).setValue(SlidingDoorBlock.SIGNAL_SENSITIVE, isNormalDoor), Block.UPDATE_NONE);
		this.level.setBlock(doorPos.above(), this.level.getBlockState(doorPos.above()).setValue(SlidingDoorBlock.SIGNAL_SENSITIVE, isNormalDoor), Block.UPDATE_NONE);
	}

	/**
	 * Attempt to link a given door to 914.
	 *
	 * @param doorPos The position of the door we are linking
	 * @return True if successfully linked. False otherwise.
	 */
	public boolean linkDoor(BlockPos doorPos) {
		//	Test if the position is valid (it points to a block entity of sliding door (the bottom half))
		if (!(this.level.getBlockEntity(doorPos) instanceof SlidingDoorBlockEntity)) {
			return false;
		}

		//	If we have no input door, link it to that first
		if (this.InputDoorLocation == null) {
			this.InputDoorLocation = doorPos;
			this.InputBBox = new AABB(doorPos);
			slidingDoorBlockEntity.linkSCP914(this.getBlockPos());
			setDoorOpen(doorPos, true);
			return true;
		}

		//	If we have no output door, link it to that
		if (this.OutputDoorLocation == null) {
			this.OutputDoorLocation = doorPos;
			this.OutputBBox = new AABB(doorPos);
			slidingDoorBlockEntity.linkSCP914(this.getBlockPos());
			setDoorOpen(doorPos, true);
			return true;
		}

		return false;
	}

	/**
	 * Validates the Input and output doors to see if they (still) exist.
	 * This function, unlike the #isXDoorValid checks the inworld configuration of the doors to update internal variables.
	 *
	 * @return True if the current door configuration is valid, false if ANY are invalid.
	 */
	public boolean areDoorsValid() {
		return isInputDoorValid() && isOutputDoorValid();
	}

	public boolean isInputDoorValid() {
		return isDoorValid(InputDoorLocation, InputBBox);
	}

	public boolean isOutputDoorValid() {
		return isDoorValid(OutputDoorLocation, OutputBBox);
	}

	private boolean isDoorValid(BlockPos doorPos, AABB doorBBox) {
		//	First check if either value is null (for some reason)

		//	If Pos is null, we can safely discard of the AABB
		if (doorPos == null) {
			doorBBox = null;
			return false;
		}

		//	Since the door pos is not null, first check if it is in fact a door
		if (!(this.getLevel().getBlockEntity(doorPos) instanceof SlidingDoorBlockEntity)) {
			//	Door is invalid
			doorPos = null;
			doorBBox = null;
			return false;
		}

		//	If the AABB is null, try to find the area given the doors location
		if (doorBBox == null) {
			//	TODO, for now area is pos
			doorBBox = new AABB(doorPos);
			return true;    //	Return early to avoid check of what we just did
		}

		//	TODO: Validate if the AABB is still enclosed

		return true;
	}

	@Override
	public void setRemoved() {
		if (InputDoorLocation != null && level.getBlockEntity(InputDoorLocation) instanceof SlidingDoorBlockEntity inputDoorBlockEntity)
			inputDoorBlockEntity.unlinkSCP914();
		if (OutputDoorLocation != null && level.getBlockEntity(OutputDoorLocation) instanceof SlidingDoorBlockEntity outputDoorBlockEntity)
			outputDoorBlockEntity.unlinkSCP914();
		super.setRemoved();
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
				if (level.getBlockState(pos).getBlock().isCollisionShapeFullBlock(level.getBlockState(pos), level, pos) || level.getBlockState(pos).getBlock() instanceof SlidingDoorBlock) {
					//  OK
				} else {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Given a doors position, attempt to find the enclosed space
	 *
	 * @param pos
	 * @return
	 */
	@Nullable
	private AABB tryFindEnclosedSpacePos(BlockPos pos) {
		return null;
	}

	private enum MachineState {
		MISSING_DOORS,
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
