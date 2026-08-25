package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.world.level.block.Block;

public class LockdownBlock extends Block {

	public LockdownBlock(Properties properties) {
		super(properties);
	}

/* //TODO Cleanup
    public SCPBlock(Material material) {
        super(material);
        setResistance(General.BLOCKCREEPERESISTANCE);
        setHardness(1.5f);
    }


    @Override
    public boolean isToolEffective(String type, IBlockState state) {
        if(getClass()==this.getClass())
            return type.equals("pickaxe");
        return super.isToolEffective(type, state);
    }

    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public String getUnlocalizedName() {
        return SCPLockdown.MOD_ID +super.getUnlocalizedName();
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        if(this.hasTileEntity(state)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            return tileEntity != null && tileEntity.receiveClientEvent(id, param);
        }
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(hasTileEntity(state))
        {
            TileEntity tileEntity=worldIn.getTileEntity(pos);
            Utils.dropTileItems(tileEntity);
        }
    }

    public SCPBlock setSoundType(SoundType type)
    {
        super.setSoundType(type);
        return this;
    }

 */
}
