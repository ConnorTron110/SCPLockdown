package io.github.connortron110.scplockdown.level.items.tools;

import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

//Basically a copy of ItemTier but with our tiers :3
public enum SCPItemTiers implements Tier {
	SCP143(3, 1600, 16F, 6F, 0, () -> Ingredient.of(SCPItems.SCP143_INGOT));

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Lazy<Ingredient> repairIngredient;

	SCPItemTiers(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
		this.level = pLevel;
		this.uses = pUses;
		this.speed = pSpeed;
		this.damage = pDamage;
		this.enchantmentValue = pEnchantmentValue;
		this.repairIngredient = Lazy.of(pRepairIngredient);
	}

	@Override
	public int getUses() {
		return this.uses;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public @NotNull Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
