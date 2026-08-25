package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;

public class LockdownSapling extends SaplingBlock {

	public LockdownSapling(AbstractTreeGrower treeGrower, Properties properties) {
		super(treeGrower, properties.noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	}

    /*
    @Override
    public abstract void grow(World worldIn, Random rand, BlockPos pos, IBlockState state);


    /**Schedule a tick here
    @Override
    public abstract void  onBlockAdded(World worldIn, BlockPos pos, IBlockState state);

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(canGrow(worldIn,pos,state,false))
            grow(worldIn,rand,pos,state);
        else worldIn.scheduleUpdate(pos,this,20*3);
    }

     */
}
