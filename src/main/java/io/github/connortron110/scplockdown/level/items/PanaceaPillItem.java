package io.github.connortron110.scplockdown.level.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PanaceaPillItem extends SCPItem {
	public PanaceaPillItem(Properties properties) {
		super(properties.food(new FoodProperties.Builder().alwaysEat().build()));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
		if (!pLevel.isClientSide) pEntityLiving.curePotionEffects(pStack);
		return super.finishUsingItem(pStack, pLevel, pEntityLiving);
	}

	@Override
	public int getUseDuration(ItemStack pStack) {
		return 15;
	}

}
