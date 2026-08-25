package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

/**
 * Most of the methods in this class are inspired from the vanilla chest, as it has practically all elements on what we want to use but we cant use the chest class as we don't want the "double" feature
 * TODO: Fix Container and Door functionality
 */
public class LockerBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
	private NonNullList<ItemStack> items = NonNullList.withSize(36, ItemStack.EMPTY);
	private final ChestLidController lockerDoorsController = new ChestLidController();
	private final ContainerOpenersCounter lockerOpenersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level pLevel, BlockPos pPos, BlockState pState) {
		}

		@Override
		protected void onClose(Level pLevel, BlockPos pPos, BlockState pState) {
		}

		@Override
		protected void openerCountChanged(Level pLevel, BlockPos pPos, BlockState pState, int pCount, int pOpenCount) {

		}

		@Override
		protected boolean isOwnContainer(Player player) {
			if (player.containerMenu instanceof ChestMenu chestMenu) {
				Container container = chestMenu.getContainer();
				return container == LockerBlockEntity.this;
			} else {
				return false;
			}
		}
	};

	public LockerBlockEntity(BlockPos pos, BlockState state) {
		super(SCPBlockEntities.LOCKER.get(), pos, state);
	}

	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		if (!this.trySaveLootTable(pTag)) {
			ContainerHelper.saveAllItems(pTag, this.items);
		}
	}

	public void load(CompoundTag pTag) {
		super.load(pTag);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(pTag)) {
			ContainerHelper.loadAllItems(pTag, this.items);
		}
	}

	public float getOpenNess(float pPartialTicks) {
		return this.lockerDoorsController.getOpenness(pPartialTicks);
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getContainerSize() {
		return 36;
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	protected void setItems(NonNullList<ItemStack> pItems) {
		this.items = pItems;
	}

	protected MutableComponent getDefaultName() {
		return Component.translatable("container.locker");
	}

	protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
		return ChestMenu.fourRows(pId, pPlayer);
	}

	private static final AABB RENDER_BOUNDS = new AABB(0, 0, 0, 1, 2, 1);

	@Override
	public AABB getRenderBoundingBox() {
		return RENDER_BOUNDS.move(worldPosition);
	}

	@Override   //  Syncs from Server to client
	public boolean triggerEvent(int pId, int pType) {
		if (pId == 1) {
			this.lockerDoorsController.shouldBeOpen(pType > 0);
			return true;
		} else {
			return super.triggerEvent(pId, pType);
		}
	}

	public void startOpen(Player pPlayer) {
		if (!this.remove && !pPlayer.isSpectator()) {
			this.lockerOpenersCounter.incrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public void stopOpen(Player pPlayer) {
		if (!this.remove && !pPlayer.isSpectator()) {
			this.lockerOpenersCounter.decrementOpeners(pPlayer, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void clearContent() {
		this.getItems().clear();
	}
}
