package io.github.connortron110.scplockdown.mixin.client;

import io.github.connortron110.scplockdown.api.SCPEntry;
import io.github.connortron110.scplockdown.api.SCPObjectClass;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.SCPs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

/**
 * Mixin for the base item class, to allow for modification of hover text
 */
@Mixin(Item.class)
public abstract class ClientMixinItem {

	@Unique
	private static final HashMap<SCPEntry, MutableComponent> SCP_TRANSLATIONS = new HashMap<>();
	@Unique
	private static final HashMap<SCPObjectClass, MutableComponent> OBJECT_CLASS_TRANSLATIONS = new HashMap<>();

	static {
		for (SCPObjectClass value : SCPObjectClass.values()) {
			OBJECT_CLASS_TRANSLATIONS.put(value, Component.translatable("tooltip.object_class." + value.name.toLowerCase()));
		}
	}

	/**
	 * This Inject is responsible for adding an SCPs name to the top of the hover text, it checks if this item is an SCP and if it has a name, if it does, it adds the translatable to the tooltip.
	 * It also adds the classification of this SCP to the tooltip
	 */
	@Inject(at = @At("HEAD"), method = "appendHoverText")
	protected void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag, CallbackInfo callbackInfo) {
		Item item = pStack.getItem() instanceof BlockItem ? ((BlockItem) pStack.getItem()).getBlock().asItem() : pStack.getItem();
		if (SCPItems.ITEMS.getRegister().getEntries().stream().anyMatch(itemRegistryObject -> itemRegistryObject.get().equals(item))) {
			//This is an item that we made, now to check if it is an SCP item (Has an entry)
			SCPEntry entry = SCPs.getEntryFromObject(item);
			if (entry != null) {
				//Name Translation
				if (!SCP_TRANSLATIONS.containsKey(entry)) {
					SCP_TRANSLATIONS.put(entry, Component.translatable("tooltip.scp" + entry.number + ".name"));
				}

				if (Screen.hasShiftDown() || pLevel == null) {
					pTooltip.add(SCP_TRANSLATIONS.get(entry).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY).withItalic(true)));
				}
				//Class Translation
				pTooltip.add(OBJECT_CLASS_TRANSLATIONS.get(entry.objectClass).withStyle(Style.EMPTY.withColor(entry.objectClass.colour)));
			}
		}
	}
}
