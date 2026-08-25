package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.level.blocks.CrateBlock;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrateBlockEntity extends RandomizableContainerBlockEntity {
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private int openCount;

	public CrateBlockEntity(BlockPos pos, BlockState state) {
		super(SCPBlockEntities.CRATE.get(), pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		if (!this.trySaveLootTable(pTag)) {
			ContainerHelper.saveAllItems(pTag, this.items);
		}
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(pTag)) {
			ContainerHelper.loadAllItems(pTag, this.items);
		}

	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getContainerSize() {
		return 27;
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	protected void setItems(NonNullList<ItemStack> pItems) {
		this.items = pItems;
	}

	protected Component getDefaultName() {
		return Component.translatable("container.crate");
	}

	protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
		return ChestMenu.threeRows(pId, pPlayer, this);
	}

	public void startOpen(Player player) {
		if (!player.isSpectator()) {
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
		this.level.scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
	}

	public void recheckOpen() {
		int i = this.worldPosition.getX();
		int j = this.worldPosition.getY();
		int k = this.worldPosition.getZ();
		this.openCount = ChestBlockEntity.getOpenCount(this.level, this.worldPosition);
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

	public void stopOpen(Player pPlayer) {
		if (!pPlayer.isSpectator()) {
			--this.openCount;
		}

	}

	private void updateBlockState(BlockState pState, boolean pOpen) {
		this.level.setBlock(this.getBlockPos(), pState.setValue(CrateBlock.OPEN, pOpen), 3);
	}

	private void playSound(BlockState pState, SoundEvent pSound) {
		double d0 = (double) this.worldPosition.getX() + 0.5D;
		double d1 = (double) this.worldPosition.getY() + 0.5D;
		double d2 = (double) this.worldPosition.getZ() + 0.5D;
		this.level.playSound(null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}
}
