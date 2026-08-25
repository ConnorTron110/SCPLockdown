package io.github.connortron110.scplockdown.level.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * SCP-148. Causes dizziness and speech impairment - @link io.github.connortron110.scplockdown.events.CapabilityController#playerTicks}
 */
public class SCP148ArmorItem extends ArmorItem {
	public SCP148ArmorItem(ArmorMaterial pMaterial, ArmorItem.Type pSlot, Item.Properties pProperties) {
		super(pMaterial, pSlot, pProperties);
	}

	public static boolean isWearingTelekill(LivingEntity entityLiving) {
		for (ItemStack itemStack : entityLiving.getArmorSlots()) {
			if (itemStack.getItem() instanceof SCP148ArmorItem) return true;
		}
		return false;
	}
}
