package io.github.connortron110.scplockdown.level.inventory;

import io.github.connortron110.scplockdown.level.items.CardWriterItem;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.registration.SCPMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class CardWriterMenu extends AbstractContainerMenu {

	public static final int CARD_SLOT = 0;

	/**
	 * The actual Card Writer stack to save contents into
	 */
	private ItemStack CardWriter;
	/**
	 * The slot that the CardWriter was called from (prevents moving while using)
	 */
	private final int SelectedSlot;

	private final Slot CardSlot;

	private Runnable slotUpdateListener = () -> {
	};

	public final Container Container = new SimpleContainer(1) {
		@Override
		public void setChanged() {
			super.setChanged();
			CardWriterMenu.this.slotsChanged(this);
			CardWriterMenu.this.slotUpdateListener.run();
		}
	};

	public CardWriterMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf extraData) {
		this(pContainerId, pPlayerInventory, extraData.readItem(), extraData.readInt());
	}

	public CardWriterMenu(int pContainerId, Inventory pPlayerInventory, ItemStack cardWriter, int selectedSlot) {
		super(SCPMenuTypes.CARD_WRITER.get(), pContainerId);

		this.CardWriter = cardWriter;
		this.SelectedSlot = selectedSlot;

		this.CardSlot = this.addSlot(new Slot(this.Container, 0, 14, 33) {
			@Override
			public boolean mayPlace(@Nonnull ItemStack pStack) {
				return pStack.getItem() instanceof KeycardItem;
			}
		});

		//  Read the stored Card item
		CardSlot.set(CardWriterItem.getStoredItem(CardWriter));

		//  Surely there MUST be a better way of doing player inventories
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(pPlayerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(pPlayerInventory, k, 8 + k * 18, 142) {
				/**
				 * Prevent player from moving Card Writer stack when using it
				 */
				@Override
				public boolean mayPickup(Player pPlayer) {
					//  Account for Armor and offhand slots
					int containerSize = container.getContainerSize() - 5;
					return containerSize - index != 8 - SelectedSlot;
				}
			});
		}
	}

	public ItemStack getItemInCardSlot() {
		return CardSlot.getItem();
	}

	public void setItemInCardSlot(ItemStack pStack) {
		CardSlot.set(pStack);
	}

	@Override
	public void slotsChanged(Container pContainer) {
		super.slotsChanged(pContainer);
		CardWriterItem.setStoredItem(CardWriter, getItemInCardSlot());
	}

	@Override
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		//pPlayer.getInventory().getSelected()
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return true;
	}

	@Override
	public void removed(Player pPlayer) {
		super.removed(pPlayer);
		//  Store Item in Card slot onto the stack of the writer
		CardWriterItem.setStoredItem(CardWriter, getItemInCardSlot());
	}

	public void registerUpdateListener(Runnable pListener) {
		this.slotUpdateListener = pListener;
	}
}
