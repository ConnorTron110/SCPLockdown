package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

/**
 * Yes, I know, long class name. <br>
 * When placed, a Paired block gets placed in the facing direction and gets swapped to the opposite face, that way both
 * blocks depend on each-other to exist. In-game blocks would be facing each-other when placed
 */
public class LockdownHorizontallyDependantBlock extends LockdownHorizontalBlock {
	public LockdownHorizontallyDependantBlock(Properties properties) {
		super(properties);
	}

	//Placement Checks\\

	/**
	 * Determines before the block is placed in the world, if the block can be placed.
	 *
	 * @return returning null does not consume item count.
	 */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction facing = context.getHorizontalDirection();
		BlockPos pairedPos = context.getClickedPos().relative(facing);

		if (context.getLevel().getWorldBorder().getDistanceToBorder(pairedPos.getX(), pairedPos.getZ()) == 0) {
			if (context.getPlayer() != null)
				context.getPlayer().displayClientMessage(LockdownTextComponents.BLOCK_INVALID_PLACEMENT, true);
			return null;
		}

		return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	/**
	 * Checks if the block can survive, given if its paired block is there or where it's meant to be is replaceable
	 */
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos pairedPos = pos.relative(state.getValue(FACING));
		//Checks to see if paired block is still there, or if the space is available to place paired block
		if (level.getBlockState(pairedPos).is(state.getBlock())) {
			if (level.getBlockState(pairedPos).getValue(FACING) != state.getValue(FACING).getOpposite()) return false;
		} else if (!level.getBlockState(pairedPos).canBeReplaced()) return false;

		//World Border Check
		if (level.getWorldBorder().getDistanceToBorder(pairedPos.getX(), pairedPos.getZ()) == 0) return false;

		return true;
	}

	/**
	 * When block is placed by a player, set paired block state.
	 */
	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		level.setBlock(pos.relative(state.getValue(FACING)), state.setValue(FACING, state.getValue(FACING).getOpposite()), Block.UPDATE_ALL);
	}

	/**
	 * Block has been removed, remove paired block
	 */
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onRemove(state, level, pos, newState, isMoving);

		//This method also gets called when state changes, so we check if the new state is still current block
		if (state.getBlock().equals(newState.getBlock())) return;

		level.destroyBlock(pos.relative(state.getValue(FACING)), true);
	}

	//End of Placement Checks\\

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}
}
