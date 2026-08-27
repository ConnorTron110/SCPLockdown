package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.Objects;

public abstract class LockdownDoubleTallBlock extends LockdownBlock {

	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	public LockdownDoubleTallBlock(Properties properties) {
		super(properties);
		BlockState defaultState = Objects.requireNonNullElse(setAdditionalDefaultStates(), getStateDefinition().any());
		registerDefaultState(defaultState.setValue(HALF, DoubleBlockHalf.LOWER));
	}

	@Nullable
	protected abstract BlockState setAdditionalDefaultStates();

	//Placement Checks\\

	/**
	 * Determines before the block is placed in the world, if the block can be placed.
	 *
	 * @return returning null does not consume item count.
	 */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		//	Height check
		if ((pContext.getLevel().getMaxBuildHeight() - pContext.getClickedPos().getY() < 2)) {
			pContext.getPlayer().displayClientMessage(LockdownTextComponents.BLOCK_INVALID_PLACEMENT, true);
			return null;
		}

		//	Check if the block above can be replaced
		if (!pContext.getLevel().getBlockState(pContext.getClickedPos().relative(Direction.UP)).canBeReplaced(pContext))
			return null;

		return defaultBlockState();
	}

	/**
	 * Copy of {@link net.minecraft.world.level.block.DoublePlantBlock#canSurvive(BlockState, LevelReader, BlockPos)}
	 */
	@Override
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		//	Half Checks to see if paired block is still there
		if (isLowerHalf(pState)) {
			return super.canSurvive(pState, pLevel, pPos);
		} else {
			//	This is the upper half
			BlockState lowerState = pLevel.getBlockState(pPos.below());
			return lowerState.is(this) && isLowerHalf(lowerState);
		}
	}

	/**
	 * When block is placed by a player, set paired block state.
	 */
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		level.setBlockAndUpdate(isLowerHalf(state) ? pos.above() : pos.below(), state.cycle(HALF));
	}

	//End of Placement Checks\\

	/**
	 * Copy of {@link net.minecraft.world.level.block.DoublePlantBlock#playerWillDestroy(Level, BlockPos, BlockState, Player)}
	 */
	@Override
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide) {
			if (player.isCreative()) {
				preventCreativeDropFromBottomPart(level, pos, state);
			} else {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
		}

		super.playerWillDestroy(level, pos, state, player);
	}

	/**
	 * Copy of {@link net.minecraft.world.level.block.DoublePlantBlock#playerDestroy(Level, Player, BlockPos, BlockState, BlockEntity, ItemStack)}
	 */
	@Override
	public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
		super.playerDestroy(pLevel, pPlayer, pPos, Blocks.AIR.defaultBlockState(), pBlockEntity, pTool);
	}

	/**
	 * Copy of {@link net.minecraft.world.level.block.DoublePlantBlock#updateShape(BlockState, Direction, BlockState, LevelAccessor, BlockPos, BlockPos)}
	 */
	@Override
	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
		if (pDirection.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (pDirection == Direction.UP) || pNeighborState.is(this) && pNeighborState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && pDirection == Direction.DOWN && !canSurvive(pState, pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	/**
	 * Mostly a copy of {@link net.minecraft.world.level.block.DoublePlantBlock#preventCreativeDropFromBottomPart(Level, BlockPos, BlockState, Player)}
	 */
	protected void preventCreativeDropFromBottomPart(Level pLevel, BlockPos pPos, BlockState pState) {
		if (isUpperHalf(pState)) {
			BlockPos lowerPos = pPos.below();
			BlockState lowerState = pLevel.getBlockState(lowerPos);
			if (lowerState.is(pState.getBlock()) && isLowerHalf(lowerState)) {
				pLevel.destroyBlock(lowerPos, false);
			}
		}
	}

	/**
	 * Gets the position for the given 'halfPosToGet'. Requires that the given block pos is already a valid half position.
	 *
	 * @param level        World to check within
	 * @param pos          Position of at least one of the half blocks
	 * @param halfPosToGet Half that we want the position of
	 * @return Valid position of the given half (from the pair) OR Null if first position was invalid.
	 */
	@Nullable
	protected BlockPos getPosForHalf(Level level, BlockPos pos, DoubleBlockHalf halfPosToGet) {
		if (!level.getBlockState(pos).hasProperty(HALF)) return null;
		if (halfPosToGet == DoubleBlockHalf.LOWER) {
			return isLowerHalf(level.getBlockState(pos)) ? pos : pos.below();
		} else {
			return isUpperHalf(level.getBlockState(pos)) ? pos : pos.above();
		}
	}

	/**
	 * Determines if this state is the lower half
	 *
	 * @param state The state we want to check
	 * @return True if the state is the lower half. False otherwise
	 */
	protected boolean isLowerHalf(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER;
	}

	/**
	 * Determines if this state is the upper half
	 *
	 * @param state The state we want to check
	 * @return True if the state is the upper half. False otherwise
	 */
	protected boolean isUpperHalf(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.UPPER;
	}


	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(HALF));
	}
}
