package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.events.lure.interfaces.IBlockLurable;

public class SCP012Block extends LockdownHorizontalBlock implements IBlockLurable {

	public SCP012Block(Properties properties) {
		super(properties);
	}

	@Override
	public void handleLure() {

	}

	@Override
	public void untrapEntity() {

	}

    /*
    @Override
    public void handleTrappedBlockEvent(World world, Entity trappedEntity, BlockPos pos, int ticks) {
        trappedEntity.hurt(SCPDamageSources.SCP012CURSE, 0.5F);
    }

    @Override
    public boolean doesDirectionMatterToLure() {
        return true;
    }

    @Override
    public boolean mustLookAtFirstToLure() {
        return true;
    }

     */
}
