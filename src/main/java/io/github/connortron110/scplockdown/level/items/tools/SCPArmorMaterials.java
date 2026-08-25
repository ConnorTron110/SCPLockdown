package io.github.connortron110.scplockdown.level.items.tools;

import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;

import java.util.function.Supplier;

//Clone of ArmorMaterial but with our materials :3
public enum SCPArmorMaterials implements ArmorMaterial {
	SCP143("scp143", 5, new int[]{2, 4, 2, 1}, 0, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> Ingredient.of(SCPItems.SCP143_INGOT)),
	SCP148("scp148", 5, new int[]{2, 6, 5, 2}, 0, SoundEvents.ARMOR_EQUIP_IRON, 0.5F, 0.0F, () -> Ingredient.of(SCPItems.SCP148_INGOT));

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredient;

	SCPArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
		this.name = pName;
		this.durabilityMultiplier = pDurabilityMultiplier;
		this.slotProtections = pSlotProtections;
		this.enchantmentValue = pEnchantmentValue;
		this.sound = pSound;
		this.toughness = pToughness;
		this.knockbackResistance = pKnockbackResistance;
		this.repairIngredient = Lazy.of(pRepairIngredient);
	}

	public int getDurabilityForType(ArmorItem.Type pSlot) {
		return HEALTH_PER_SLOT[pSlot.ordinal()] * this.durabilityMultiplier;
	}

	public int getDefenseForType(ArmorItem.Type pSlot) {
		return this.slotProtections[pSlot.ordinal()];
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public SoundEvent getEquipSound() {
		return this.sound;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	/**
	 * Gets the percentage of knockback resistance provided by armor of the material.
	 */
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
