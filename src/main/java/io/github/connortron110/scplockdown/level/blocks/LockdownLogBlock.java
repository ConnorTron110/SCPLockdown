package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

//TODO Figure out if IPlantable has a different functionality
public class LockdownLogBlock extends RotatedPillarBlock implements IPlantable {
	public LockdownLogBlock(Properties properties) {
		super(properties.sound(SoundType.WOOD));
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		return defaultBlockState();
	}
}
