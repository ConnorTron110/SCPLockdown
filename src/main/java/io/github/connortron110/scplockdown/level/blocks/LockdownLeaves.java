package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;

public class LockdownLeaves extends LeavesBlock {

	//private Block sapling;

	public LockdownLeaves(Properties properties) {
		super(properties.strength(1F, 0.5F).randomTicks().sound(SoundType.GRASS).noOcclusion().isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false));
		//sapling = associatedSapling;
	}

    /*

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.BIRCH;
    }

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Lists.newArrayList(new ItemStack(this));
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState sidestate=blockAccess.getBlockState(pos.offset(side));
        return sidestate.getBlock() != Blocks.AIR.getDefaultState();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public  Item getItemDropped(IBlockState state, Random rand, int fortune) {
        int drop=rand.nextInt(100);
        return drop < 4 ? Item.getItemFromBlock(sapling) : Items.AIR;
    }

     */
}
