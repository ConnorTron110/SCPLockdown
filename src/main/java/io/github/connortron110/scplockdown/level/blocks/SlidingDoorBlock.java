package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.level.blockentity.SlidingDoorBlockEntity;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SlidingDoorBlock extends LockdownDoubleTallBlock implements EntityBlock, IScrewdriverInteraction {
	public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;	//	Looking towards the Positive axis direction, Hinge determines if the door opens LEFT or RIGHT
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final BooleanProperty SIGNAL_SENSITIVE = BooleanProperty.create("signal_sensitive");

	public SlidingDoorBlock(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	protected BlockState setAdditionalDefaultStates() {
		return this.getStateDefinition().any().setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false);
	}

	/**
	 * Returns the state of the door we are about to place.
	 *
	 * @return Blockstate of the block we want to place, null if an invalid placement.
	 */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		//	Placement checks for double tall
		BlockState state = super.getStateForPlacement(pContext);
		if (state == null)
			return null;

		//	Kinda Scuffed Reference with `Blocks.OAK_DOOR` But method is non-static so kinda annoying, but it makes our lives a bit easier
		DoorHingeSide hinge = ((DoorBlock) Blocks.OAK_DOOR).getHinge(pContext);

		//	DoorBlock#getHinge() Only accounts for the positive axis directions, so if the player is looking in the negative axis, the door's placement is flipped, so account for that here
		if (pContext.getHorizontalDirection().getAxisDirection() == Direction.AxisDirection.NEGATIVE)
			hinge = (hinge == DoorHingeSide.LEFT ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT);

		//	The state we are going to return with appropriate hinge and axis values
		state = state.setValue(HORIZONTAL_AXIS, pContext.getHorizontalDirection().getAxis()).setValue(HINGE, hinge).setValue(SIGNAL_SENSITIVE, true);

		//	If we are making a double door, make sure the other side (if it exists) is the same type of sliding door
		Block doubleDoorCheck = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(getDoubleDoorDirection(state))).getBlock();
		if (!doubleDoorCheck.equals(this) && doubleDoorCheck instanceof SlidingDoorBlock) return null;

		return state;
	}

	/**
	 * Called when an adjacent block gets updated and forces updates to its neighbours.
	 * This is responsible for Setting the door open and or syncing its state to the other half.
	 * Still don't know what the full difference between this and {@link updateShape} fully is.
	 */
	@Override
	public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
		super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);
		if (pLevel.isClientSide) return;

		//	First, check if the other half is actually there before doing anything
		if (!canSurvive(pState, pLevel, pPos)) return;

		//	Check if the signal sensitive is out of sync, if so, sync it
		if (pState.getValue(SIGNAL_SENSITIVE) != pLevel.getBlockState(pPos.relative(getConnectedDirection(pState))).getValue(SIGNAL_SENSITIVE)) {
			pLevel.setBlock(pPos, pState.setValue(SIGNAL_SENSITIVE, pLevel.getBlockState(pPos.relative(getConnectedDirection(pState))).getValue(SIGNAL_SENSITIVE)), Block.UPDATE_NONE);

			//	Recall this to properly sync other states
			this.neighborChanged(pLevel.getBlockState(pPos), pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);
			return;
		}

		//	If we are NOT signal sensitive, just blindly copy the opposite side, without causing another update.
		if (!pState.getValue(SIGNAL_SENSITIVE)) {
			BlockState otherHalfState = pLevel.getBlockState(pPos.relative(getConnectedDirection(pState)));
			pLevel.setBlock(pPos, otherHalfState.setValue(HALF, pState.getValue(HALF)), Block.UPDATE_NONE);
			return;
		}

		//	Check if THIS door (including its lower/upper half) if it has a signal
		boolean doesThisDoorHaveSignal = pLevel.hasNeighborSignal(pPos) || pLevel.hasNeighborSignal(pPos.relative(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
		//	Check if the door to the left/right acting as a double door has a signal
		boolean doesDoubleDoorOtherSideHaveSignal = pLevel.hasNeighborSignal(pPos.relative(getDoubleDoorDirection(pState))) || pLevel.hasNeighborSignal(pPos.relative(getDoubleDoorDirection(pState)).relative(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
		boolean shouldBePowered = doesThisDoorHaveSignal || doesDoubleDoorOtherSideHaveSignal;
		boolean shouldUpdate = shouldBePowered != pState.getValue(POWERED);

		//	Update ourselves, other pair can update themselves
		if (shouldUpdate) {
			pLevel.setBlockAndUpdate(pPos, pState.setValue(POWERED, shouldBePowered).setValue(OPEN, shouldBePowered));

			//	Play sound if required
			if (pLevel.getBlockEntity(pPos) != null && pLevel.getBlockEntity(pPos) instanceof SlidingDoorBlockEntity slidingDoorBlockEntity && slidingDoorBlockEntity.shouldPlaySound()) {
				pLevel.playSound(null, pPos, getSound(pState), SoundSource.BLOCKS, 1, 1);
			}
		}
	}

	/**
	 * @return The Direction relative to where the other door should be based on the hinge of the door
	 */
	private Direction getDoubleDoorDirection(BlockState state) {
		Direction ret = state.getValue(HORIZONTAL_AXIS) == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;	//	Get the Axis the door is on
		ret = state.getValue(HINGE) == DoorHingeSide.RIGHT ? ret.getCounterClockWise() : ret.getClockWise();	//	Facing the positive cardinal of the door, rotate left or right based on hinge ro get relative positon
		return ret;
	}

	/**
	 * @return The appropriate sound for this sliding door
	 */
	public SoundEvent getSound(BlockState state) {
		if (state.getBlock().equals(SCPBlocks.MAGNETIZED_DOOR.get())) {
			//	Magnetized
			return !state.getValue(OPEN) ? SCPSounds.MAGNETIZED_DOOR_OPEN.get() : SCPSounds.MAGNETIZED_DOOR_CLOSE.get();
		} else {
			//	Normal
			return !state.getValue(OPEN) ? SCPSounds.SLIDING_DOOR_OPEN.get() : SCPSounds.SLIDING_DOOR_CLOSE.get();
		}
	}

	/**
	 * Gets the BE from the door. BE is always at the bottom half.
	 */
	@Nullable
	public static SlidingDoorBlockEntity getSlidingDoorEntity(BlockGetter level, BlockPos pos, BlockState state) {
		BlockPos positionOfBE = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
		if (level.getBlockEntity(positionOfBE) != null && level.getBlockEntity(positionOfBE) instanceof SlidingDoorBlockEntity) {
			return (SlidingDoorBlockEntity) level.getBlockEntity(positionOfBE);
		} else {
			//	BE somehow doesn't exist, invalid placement etc
			return null;
		}
	}

	/**
	 * @return The shape of the door of the current open/close cycle, or default to normal orientation
	 */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		SlidingDoorBlockEntity be = getSlidingDoorEntity(level, pos, state);
		if (be != null) {
			return be.getShape();
		} else return Block.box(0, 0, 7, 16, 16, 9);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState pState) {
		return PushReaction.BLOCK;
	}

	public static Direction getConnectedDirection(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_AXIS, OPEN, HINGE, POWERED, HALF, SIGNAL_SENSITIVE);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return SCPBlockEntities.SLIDING_DOOR.get().create(pPos, pState);
		} else return null;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return (pLevel1, pPos, pState1, pBlockEntity) -> ((SlidingDoorBlockEntity) pBlockEntity).tick();
	}

	@Override
	public void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver) {
		if (screwdriver.getOrCreateTag().contains(SCP914Block.DOOR_LINK_KEY)) {
			BlockPos scp914Pos = BlockPos.of(screwdriver.getOrCreateTag().getLong(SCP914Block.DOOR_LINK_KEY));
			if (level.getBlockEntity(scp914Pos) instanceof SCP914BlockEntity blockEntity) {
				if (blockEntity.linkDoor(getSlidingDoorEntity(level, pos, state).getBlockPos())) {
					player.displayClientMessage(LockdownTextComponents.SCP914_LINK_SUCCESS, true);
					screwdriver.getOrCreateTag().remove(SCP914Block.DOOR_LINK_KEY);
				}
			}
		}
	}
}
