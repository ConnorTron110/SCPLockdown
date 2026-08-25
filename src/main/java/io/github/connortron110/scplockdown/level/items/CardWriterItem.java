package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.level.inventory.CardWriterMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class CardWriterItem extends Item {

	public static final String ITEM_STORAGE_KEY = "InsertedCard";

	public CardWriterItem(Properties pProperties) {
		super(pProperties.stacksTo(1));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		ItemStack stack = pPlayer.getItemInHand(pUsedHand);
		if (!pLevel.isClientSide) {
			if (pPlayer instanceof ServerPlayer serverPlayer) {
				NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer1) ->
						new CardWriterMenu(pContainerId, pPlayerInventory, pPlayer.getItemInHand(pUsedHand), pPlayer.getInventory().selected),
						Component.translatable("scplockdown.gui.menu.cardwriter")), friendlyByteBuf -> {
					friendlyByteBuf.writeItem(pPlayer.getItemInHand(pUsedHand));
					friendlyByteBuf.writeInt(pPlayer.getInventory().selected);
				});
			}
		}
		return InteractionResultHolder.pass(stack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}

	public static boolean hasCard(ItemStack cardWriter) {
		return !getStoredItem(cardWriter).isEmpty();
	}

	public static void setStoredItem(ItemStack cardWriterStack, ItemStack stackToStore) {
		if (!(cardWriterStack.getItem() instanceof CardWriterItem)) return;
		CompoundTag baseTag = cardWriterStack.getOrCreateTag();
		baseTag.remove(ITEM_STORAGE_KEY);   //  Remove Current (If Any)
		baseTag.put(ITEM_STORAGE_KEY, stackToStore.serializeNBT());
	}

	public static ItemStack getStoredItem(ItemStack cardWriterStack) {
		if (!(cardWriterStack.getItem() instanceof CardWriterItem)) return ItemStack.EMPTY;
		CompoundTag baseTag = cardWriterStack.getOrCreateTag();
		if (!baseTag.contains(ITEM_STORAGE_KEY)) return ItemStack.EMPTY;
		CompoundTag itemTag = baseTag.getCompound(ITEM_STORAGE_KEY);
		return ItemStack.of(itemTag);
	}
}
