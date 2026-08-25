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
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		//Check if block can survive in current position
		if (!canSurvive(defaultBlockState(), context.getLevel(), context.getClickedPos())) {
			if (context.getLevel().getMaxBuildHeight() != context.getClickedPos().getY() && context.getPlayer() != null) //Avoids text flicker when at max height
				context.getPlayer().displayClientMessage(LockdownTextComponents.BLOCK_INVALID_PLACEMENT, true);
			return null;
		}

		return defaultBlockState();
	}

	/**
	 * Checks if the block can survive, given if its paired block is there or where it's meant to be is replaceable
	 */
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		//Half Checks to see if paired block is still there, or if the space is available to place paired block
		BlockState paredState = level.getBlockState(isLowerHalf(state) ? pos.above() : pos.below());    //Get the opposite half's state
		if (paredState.is(state.getBlock())) {   //If the pared block is the same
			if (state.getValue(HALF) == paredState.getValue(HALF))
				return false;   //If both are the same half, then its an invalid placement
		} else if (!paredState.canBeReplaced())
			return false; //If the pared state is not this block and is NOT replaceable, its invalid

		//Height Check(s)
		if ((level.getMaxBuildHeight() - pos.getY() < 2) && isLowerHalf(state)) return false;
		if (pos.getY() == 0 && isUpperHalf(state)) return false;

		return true;
	}

	/**
	 * When block is placed by a player, set paired block state.
	 */
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		level.setBlock(isLowerHalf(state) ? pos.above() : pos.below(), state.cycle(HALF), Block.UPDATE_ALL);
	}

	//End of Placement Checks\\

	@Override
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide) {
			if (player.isCreative()) {
				preventCreativeDropFromBottomPart(level, pos, state, player);
			} else {
				dropResources(state, level, pos, null, player, player.getMainHandItem());
			}
		}

		super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
		super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, tool);
	}

	@Override   //  Pulled from DoublePlantBlock
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.is(this) && neighborState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	protected void preventCreativeDropFromBottomPart(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
		if (isUpperHalf(pState)) {
			BlockPos lowerPos = pPos.below();
			BlockState lowerState = pLevel.getBlockState(lowerPos);
			if (lowerState.getBlock() == pState.getBlock() && isLowerHalf(lowerState)) {
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

	protected boolean isLowerHalf(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER;
	}

	protected boolean isUpperHalf(BlockState state) {
		return !isLowerHalf(state);
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
