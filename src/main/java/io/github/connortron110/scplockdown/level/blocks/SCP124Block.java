package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

public class SCP124Block extends LockdownBlock {
	public SCP124Block(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		super.randomTick(pState, pLevel, pPos, pRandom);

		BlockPos posAbove = pPos.relative(Direction.UP);
		BlockState blockAbove = pLevel.getBlockState(posAbove);

		/*
		if (blockAbove instanceof IGrowable iGrowable) {
			iGrowable.performBonemeal(level, random, posAbove, blockAbove);
		}

		 */
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
		return true;
	}
}
