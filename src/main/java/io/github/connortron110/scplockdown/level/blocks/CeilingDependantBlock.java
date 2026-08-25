package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A Block that requires a full block above it to be able to be placeable
 */
public class CeilingDependantBlock extends LockdownBlock {
	public CeilingDependantBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN, SupportType.RIGID);
	}
}
