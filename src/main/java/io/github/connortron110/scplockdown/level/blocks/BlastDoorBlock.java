package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.blockentity.BlastDoorBlockEntity;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: ConnorTron110 <br>
 * File is structured in different segments. <br>
 * First being placement checks to see if placement in the world is valid. <br>
 * Second is block updates, misc block things and helper functions for the block to handle the multi-block side. <br>
 * Then past the DoorPart Enum class is the block shape helper as that is the most complicated part as we have to store 12 different shapes for one block that move dynamically.
 */
public class BlastDoorBlock extends Block implements EntityBlock {
	public static final EnumProperty<DoorPart> DOOR_PART = EnumProperty.create("door_part", DoorPart.class);
	public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

	public BlastDoorBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(DOOR_PART, DoorPart.LOWER_MID).setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(OPEN, false).setValue(POWERED, false));
	}

	//Placement checks\\

	/**
	 * Determines before the block is placed in the world, if the block can be placed.
	 *
	 * @return returning null does not consume item count.
	 */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		//Height Check (Has to be done here to be able to send player message)
		if (context.getLevel().getMaxBuildHeight() - context.getClickedPos().getY() < 3) {
			if (context.getLevel().getMaxBuildHeight() != context.getClickedPos().getY() && context.getPlayer() != null) //Avoids text flicker when at max height
				context.getPlayer().displayClientMessage(LockdownTextComponents.BLAST_DOOR_TOO_HIGH, true);
			return null;
		}

		return defaultBlockState().setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis());
	}

	/**
	 * The Lower Middle block has been placed,
	 */
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		List<Pair<DoorPart, BlockPos>> list = getAllPairedPartPositions(state, pos, DoorPart.LOWER_MID);
		list.forEach(pair -> level.setBlock(pair.getValue(), defaultBlockState().setValue(DOOR_PART, pair.getKey()).setValue(HORIZONTAL_AXIS, state.getValue(HORIZONTAL_AXIS)), Block.UPDATE_ALL));
	}

	/**
	 * Determines if the block can actually be placed where it is
	 */
	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		List<Pair<DoorPart, BlockPos>> partPositions = getAllPairedPartPositions(pState, pPos);

		//Check if area is empty (and not incorrect parts)
		for (Pair<DoorPart, BlockPos> pair : partPositions) {
			if (pLevel.getBlockState(pair.getValue()).is(SCPBlocks.BLAST_DOOR.get())) {
				if (pLevel.getBlockState(pair.getValue()).getValue(DOOR_PART).equals(pair.getKey())) {
					continue;
				} else {
					return false;
				}
			}

			if (!pLevel.getBlockState(pair.getValue()).canBeReplaced()) return false;
		}

		//TODO Possible add a check for when blocks around door are updated, we recheck if its stable?
		//Check for stable ground
		for (BlockPos lowestPos : partPositions.stream().map(Pair::getValue).sorted(Comparator.comparingInt(BlockPos::getY)).limit(3).collect(Collectors.toList())) {
			if (!pLevel.getBlockState(lowestPos.below()).isFaceSturdy(pLevel, pPos.below(), Direction.UP)) {
				return false;
			}
		}


		return true;
	}

	/**
	 * When the block gets removed in any way, we want to remove all paired positions
	 */
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onRemove(state, level, pos, newState, isMoving);

		//This method also gets called when state changes, so we check if the new state is still current block
		if (state.getBlock().equals(newState.getBlock())) return;

		getAllPairedPositions(state, pos, state.getValue(DOOR_PART)).stream()
				.filter(potentialPose -> level.getBlockState(potentialPose).getBlock().equals(SCPBlocks.BLAST_DOOR.get()))
				.forEach(posToDestroy -> level.destroyBlock(posToDestroy, false));
	}

	//End of placement checks\\

	/**
	 * Either a paired block has been removed without updating or redstone has been activated, either way sync up blocks.
	 * If synced block type is itself, don't update.
	 */
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		super.neighborChanged(state, level, pos, block, fromPos, isMoving);
		if (block.equals(SCPBlocks.BLAST_DOOR.get())) return;

		List<BlockPos> pairedPositions = getAllPairedPositions(state, pos);
		//Check if paried blocks are still there to avoid state clash crash //TODO Replace with canSurvive check
		for (BlockPos pairedPosition : pairedPositions) {
			if (!level.getBlockState(pairedPosition).getBlock().equals(SCPBlocks.BLAST_DOOR.get())) { //At least one block is invalid
				//We have to filter out positions that are not this block
				pairedPositions.stream()
						.filter(potentialPose -> level.getBlockState(potentialPose).getBlock().equals(SCPBlocks.BLAST_DOOR.get()))
						.forEach(posToDestroy -> level.destroyBlock(posToDestroy, false));
				return;
			}
		}

		boolean doesNeighborHaveSignal = pairedPositions.stream().anyMatch(level::hasNeighborSignal);

		//TODO add check to collect powered/open states to compare
		//Regardless of current state of block, we should update all
		syncPairedBlocks(level, state.setValue(POWERED, doesNeighborHaveSignal).setValue(OPEN, doesNeighborHaveSignal), pos);

		//Find Lower middle to get tile entity
		Optional<Pair<DoorPart, BlockPos>> optionalPos = getAllPairedPartPositions(state, pos).stream().filter(doorPartBlockPosPair -> doorPartBlockPosPair.getKey().equals(DoorPart.LOWER_MID)).findFirst();
		if (optionalPos.isPresent()) {
			BlockPos tileEntityPosition = optionalPos.get().getValue();
			if (level.getBlockEntity(tileEntityPosition) != null && level.getBlockEntity(tileEntityPosition).getType().equals(SCPBlockEntities.BLAST_DOOR.get())) {
				BlastDoorBlockEntity tileEntity = (BlastDoorBlockEntity) level.getBlockEntity(tileEntityPosition);
				if (tileEntity.shouldPlaySound()) {
					level.playSound(null, tileEntityPosition, getSound(state), SoundSource.BLOCKS, 5F, 0.95F);
				}
			}
		}
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(DOOR_PART).add(HORIZONTAL_AXIS).add(OPEN).add(POWERED);
	}

	//Helper Functions\\

	/**
	 * Gets the all positions of paired blocks and their expected part based on passed in state and pos
	 *
	 * @param state     The state of the block that wants paired block positions
	 * @param pos       The position of the block that wants paired block positions
	 * @param excluding An array of DoorParts to exclude from the returned list; can be empty to return all
	 * @return A list of block positions where paired blocks should be, including what part is expected
	 */
	private List<Pair<DoorPart, BlockPos>> getAllPairedPartPositions(BlockState state, BlockPos pos, DoorPart... excluding) {
		//Create list based on X orientation coords (center is 0,0,0)
		List<Pair<DoorPart, BlockPos.MutableBlockPos>> positionsPair = Arrays.stream(DoorPart.values()).map(part -> Pair.of(part, new BlockPos.MutableBlockPos(0, part.vOffset, part.hOffset))).collect(Collectors.toList());
		//Offset the door based on what state was passed in
		positionsPair.forEach(pair -> pair.getValue().move(0, -state.getValue(DOOR_PART).vOffset, -state.getValue(DOOR_PART).hOffset));

		//Rotate if necessary
		if (state.getValue(HORIZONTAL_AXIS) == Direction.Axis.Z) {
			positionsPair.forEach(pair -> pair.getValue().set(-pair.getValue().getZ(), pair.getValue().getY(), pair.getValue().getX())); //Rotate the door
		}

		//Remove DoorParts that are excluded
		if (excluding != null && excluding.length > 0) {
			Arrays.stream(excluding).forEach(part -> positionsPair.removeIf(pair -> pair.getKey().equals(part)));
		}

		//Move all positions and return moved map
		return positionsPair.stream().map(pair -> Pair.of(pair.getKey(), pair.getValue().move(pos).immutable())).collect(Collectors.toList());
	}

	/**
	 * Relies on {@link BlastDoorBlock#getAllPairedPartPositions} but then strips the pair away to only leave expected block positions.
	 * Mostly useful for getting paired positions to destroy
	 *
	 * @param state     The state of the block that wants paired block positions
	 * @param pos       The position of the block that wants paired block positions
	 * @param excluding An array of DoorParts to exclude from the returned list; can be empty to return all
	 * @return A list of all positions
	 */
	private List<BlockPos> getAllPairedPositions(BlockState state, BlockPos pos, DoorPart... excluding) {
		return getAllPairedPartPositions(state, pos, excluding).stream().map(Pair::getValue).collect(Collectors.toList());
	}

	/**
	 * Syncs all paired blocks based on the given state (Does not sync door part).
	 * Assumes all blocks exists and are correct, then changes their state without re-updating door.
	 * (Does update blocks around the door)
	 */
	private void syncPairedBlocks(Level level, BlockState state, BlockPos pos) {
		getAllPairedPositions(state, pos).forEach(posToSync ->
				level.setBlock(posToSync,
						level.getBlockState(posToSync).setValue(POWERED, state.getValue(POWERED)).setValue(OPEN, state.getValue(OPEN)),
						Block.UPDATE_ALL));
	}

	public SoundEvent getSound(BlockState state) {
		return !state.getValue(OPEN) ? SCPSounds.BLAST_DOOR_OPEN.get() : SCPSounds.BLAST_DOOR_CLOSE.get();
	}


	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		if (pState.getValue(DOOR_PART).equals(DoorPart.LOWER_MID)) {
			return SCPBlockEntities.BLAST_DOOR.get().create(pPos, pState);
		} else return null;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return (pLevel1, pPos, pState1, pBlockEntity) -> ((BlastDoorBlockEntity) pBlockEntity).tick();
	}

	/**
	 * Door parts based on their position when looking towards the positive axis (Looking South or East)
	 */
	public enum DoorPart implements StringRepresentable {
		UPPER_LEFT("ul", -1, 1),
		UPPER_MID("um", 0, 1),
		UPPER_RIGHT("ur", 1, 1),
		MID_LEFT("ml", -1, 0),
		CENTER("c", 0, 0),
		MID_RIGHT("mr", 1, 0),
		LOWER_LEFT("ll", -1, -1),
		LOWER_MID("lm", 0, -1),
		LOWER_RIGHT("lr", 1, -1);

		private final String name;
		//Offsets based on when looking towards +X Axis
		private final int hOffset;
		private final int vOffset;

		DoorPart(String name, int hOffset, int vOffset) {
			this.name = name;
			this.hOffset = hOffset;
			this.vOffset = vOffset;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}
	}

	//  TODO Optimize and create Actual Door Collision

	private static final VoxelShape Z_SHAPE = Stream.of(
			Block.box(-10, -16, 5, -8, 24, 11),
			Block.box(24, -16, 5, 26, 24, 11),
			Block.box(26, -16, 4, 27, 24, 12),
			Block.box(-11, -16, 4, -10, 24, 12),
			Block.box(-13, -16, 3, -11, 24, 13),
			Block.box(-16, -16, 1, -13, 24, 1),
			Block.box(29, -16, 1, 32, 24, 1),
			Block.box(29, -16, 15, 32, 24, 15),
			Block.box(-16, -16, 15, -13, 24, 15),
			Block.box(27, -16, 3, 29, 24, 13),
			Block.box(29, -16, 2, 30, 24, 14),
			Block.box(-14, -16, 2, -13, 24, 14),
			Block.box(-8, 16, 5, 24, 20, 11),
			Block.box(-8, 20, 2, 24, 24, 14),
			Block.box(-12, 20, 2, -8, 24, 5),
			Block.box(24, 20, 2, 28, 24, 5),
			Block.box(-12, 20, 11, -8, 24, 14),
			Block.box(24, 20, 11, 28, 24, 14),
			Block.box(-16, 24, -1, 32, 32, 17)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape X_SHAPE = Utils.rotateShapeY(Z_SHAPE, 90);

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		DoorPart part = pState.getValue(DOOR_PART);

		VoxelShape shape = pState.getValue(HORIZONTAL_AXIS).equals(Direction.Axis.Z) ? Z_SHAPE.move(part.hOffset, -part.vOffset, 0) : X_SHAPE.move(0, -part.vOffset, -part.hOffset);

		return shape;
	}
}
