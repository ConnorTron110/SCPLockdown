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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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

public class SlidingDoorBlock extends Block implements EntityBlock, IScrewdriverInteraction {
	public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	//  Looking towards the Positive axis direction, Hinge determines if the door opens LEFT or RIGHT
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final BooleanProperty SIGNAL_SENSITIVE = BooleanProperty.create("signal_sensitive");

	public SlidingDoorBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(HORIZONTAL_AXIS, Direction.Axis.X).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	//  Valid Placement Checks  \\

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		//  Check if block above can be replaced
		if (!context.getLevel().getBlockState(context.getClickedPos().relative(Direction.UP)).canBeReplaced(context))
			return null;

		//  Kinda Scuffed Reference with `Blocks.OAK_DOOR` But method is non-static so kinda annoying
		DoorHingeSide hinge = ((DoorBlock) Blocks.OAK_DOOR).getHinge(context);

		//  If player is looking towards the negative of the axis, then inverse the hinge
		if (context.getHorizontalDirection().getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
			hinge = (hinge == DoorHingeSide.LEFT ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT);
		}

		//  The state we are going to return with appropriate hinge and axis values
		BlockState ret = defaultBlockState().setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis()).setValue(HINGE, hinge).setValue(SIGNAL_SENSITIVE, true);

		//  Check to see if partner door is the same type
		Block doubleDoorCheck = context.getLevel().getBlockState(context.getClickedPos().relative(getDoubleDoorDirection(ret))).getBlock();
		if (!doubleDoorCheck.equals(this) && doubleDoorCheck instanceof SlidingDoorBlock) return null;

		return ret;
	}

	@Override   //  When block is updated by neighbours, check if still connected to upper / lower half
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
		if (getConnectedDirection(state) == direction) {
			return ((neighborState.is(this)) && (state.getValue(HALF) != neighborState.getValue(HALF)) && (state.getValue(HORIZONTAL_AXIS) == neighborState.getValue(HORIZONTAL_AXIS))) ? state : Blocks.AIR.defaultBlockState();
		} else return state;
	}

	@Override   //  Player place call, used to properly set up the door block and its other half
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
		if (!pLevel.isClientSide) {
			pLevel.setBlockAndUpdate(pPos.relative(Direction.UP), pState.setValue(HALF, DoubleBlockHalf.UPPER));
			pLevel.updateNeighborsAt(pPos, this);
			pLevel.updateNeighborsAt(pPos.relative(Direction.UP), this);
		}
	}

	@Override   //  Removes the other half when player is destroying this block
	public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
		super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
		//  As a cool workaround, the lower half of the block does not drop anything, but the top half will
		if (!pLevel.isClientSide && pPlayer.isCreative() && pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
			pLevel.setBlockAndUpdate(pPos.relative(Direction.UP), Blocks.AIR.defaultBlockState());
			pLevel.updateNeighborsAt(pPos.relative(Direction.UP), this);
		}
	}

	//  End of Placement checks  \\

	@Override
	public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
		super.neighborChanged(pState, pLevel, pPos, pNeighborBlock, pNeighborPos, pMovedByPiston);
		if (pLevel.isClientSide) return;

		//  If we are NOT signal sensitive, do nothing
		if (!pState.getValue(SIGNAL_SENSITIVE)) return;

		//  Check if THIS door (including its lower/upper half) if it has a signal
		boolean doesThisDoorHaveSignal = pLevel.hasNeighborSignal(pPos) || pLevel.hasNeighborSignal(pPos.relative(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
		//  Check if the door to the left/right acting as a double door has a signal
		boolean doesDoubleDoorOtherSideHaveSignal = pLevel.hasNeighborSignal(pPos.relative(getDoubleDoorDirection(pState))) || pLevel.hasNeighborSignal(pPos.relative(getDoubleDoorDirection(pState)).relative(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
		boolean shouldBePowered = doesThisDoorHaveSignal || doesDoubleDoorOtherSideHaveSignal;
		boolean shouldUpdate = shouldBePowered != pState.getValue(POWERED);

		//  Update ourselves, other pair can update themselves
		if (shouldUpdate) {
			pLevel.setBlockAndUpdate(pPos, pState.setValue(POWERED, shouldBePowered).setValue(OPEN, shouldBePowered));

			//  Play sound if required
			if (pLevel.getBlockEntity(pPos) != null && pLevel.getBlockEntity(pPos) instanceof SlidingDoorBlockEntity slidingDoorBlockEntity && slidingDoorBlockEntity.shouldPlaySound()) {
				pLevel.playSound(null, pPos, getSound(pState), SoundSource.BLOCKS, 1, 1);
			}
		}
	}

	/**
	 * @return The Direction relative to where the other door should be based on the hinge of the door
	 */
	private Direction getDoubleDoorDirection(BlockState state) {
		Direction ret = state.getValue(HORIZONTAL_AXIS) == Direction.Axis.X ? Direction.EAST : Direction.SOUTH; //  Get the Axis the door is on
		ret = state.getValue(HINGE) == DoorHingeSide.RIGHT ? ret.getCounterClockWise() : ret.getClockWise();    //  Facing the positive cardinal of the door, rotate left or right based on hinge ro get relative positon
		return ret;
	}

	/**
	 * @return The appropriate sound for this sliding door
	 */
	public SoundEvent getSound(BlockState state) {
		if (state.getBlock().equals(SCPBlocks.MAGNETIZED_DOOR.get())) {
			//  Magnetized
			return !state.getValue(OPEN) ? SCPSounds.MAGNETIZED_DOOR_OPEN.get() : SCPSounds.MAGNETIZED_DOOR_CLOSE.get();
		} else {
			//  Normal
			return !state.getValue(OPEN) ? SCPSounds.SLIDING_DOOR_OPEN.get() : SCPSounds.SLIDING_DOOR_CLOSE.get();
		}
	}

	/**
	 * Gets the BE from the door. BE is always at the bottom half.
	 */
	@Nullable
	private SlidingDoorBlockEntity getSlidingDoorEntity(BlockGetter level, BlockPos pos, BlockState state) {
		BlockPos positionOfBE = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
		if (level.getBlockEntity(positionOfBE) != null && level.getBlockEntity(positionOfBE) instanceof SlidingDoorBlockEntity) {
			return (SlidingDoorBlockEntity) level.getBlockEntity(positionOfBE);
		} else {
			//  BE somehow doesnt exist, invalid placement etc
			return null;
		}
	}

	/**
	 * @return The shape of the door of the current open/close cycle, or default to normal orientation
	 */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		SlidingDoorBlockEntity be = this.getSlidingDoorEntity(level, pos, state);
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
