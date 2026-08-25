/*package io.github.connortron110.scplockdown.level.tileentity;

import io.github.connortron110.scplockdown.level.blocks.CrateBlock;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

public class CrateTileEntity extends BaseContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
    private int openCount;

    private CrateTileEntity(TileEntityType<?> type) {
        super(type);
    }

    public CrateTileEntity() {
        this(SCPBlockEntities.CRATE.get());
    }


    public CompoundNBT save(CompoundNBT pCompound) {
        super.save(pCompound);
        if (!this.trySaveLootTable(pCompound)) {
            ItemStackHelper.saveAllItems(pCompound, this.items);
        }

        return pCompound;
    }

    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_230337_2_)) {
            ItemStackHelper.loadAllItems(p_230337_2_, this.items);
        }

    }

    /**
     * Returns the number of slots in the inventory.
     *//*
    public int getContainerSize() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.crate");
    }

    protected Container createMenu(int pId, PlayerInventory pPlayer) {
        return ChestContainer.threeRows(pId, pPlayer, this);
    }

    public void startOpen(PlayerEntity pPlayer) {
        if (!pPlayer.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            BlockState blockstate = this.getBlockState();
            boolean flag = blockstate.getValue(CrateBlock.OPEN);
            if (!flag) {
                this.playSound(blockstate, SoundEvents.BARREL_OPEN);
                this.updateBlockState(blockstate, true);
            }

            this.scheduleRecheck();
        }

    }

    private void scheduleRecheck() {
        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }

    public void recheckOpen() {
        int i = this.worldPosition.getX();
        int j = this.worldPosition.getY();
        int k = this.worldPosition.getZ();
        this.openCount = ChestTileEntity.getOpenCount(this.level, this, i, j, k);
        if (this.openCount > 0) {
            this.scheduleRecheck();
        } else {
            BlockState blockstate = this.getBlockState();
            if (!(blockstate.getBlock() instanceof CrateBlock)) {
                this.setRemoved();
                return;
            }

            boolean flag = blockstate.getValue(CrateBlock.OPEN);
            if (flag) {
                this.playSound(blockstate, SoundEvents.BARREL_CLOSE);
                this.updateBlockState(blockstate, false);
            }
        }

    }

    public void stopOpen(PlayerEntity pPlayer) {
        if (!pPlayer.isSpectator()) {
            --this.openCount;
        }

    }

    private void updateBlockState(BlockState pState, boolean pOpen) {
        this.level.setBlock(this.getBlockPos(), pState.setValue(CrateBlock.OPEN, pOpen), 3);
    }

    private void playSound(BlockState pState, SoundEvent pSound) {
        double d0 = (double)this.worldPosition.getX() + 0.5D;
        double d1 = (double)this.worldPosition.getY() + 0.5D;
        double d2 = (double)this.worldPosition.getZ() + 0.5D;
        this.level.playSound(null, d0, d1, d2, pSound, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}

*/