package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;

import java.util.List;

public class CandyItem extends Item {

	public static final Lazy<List<ItemStack>> CANDIES = Lazy.of(() -> List.of(
			SCPItems.RED_CANDY.getDefaultInstance(),
			SCPItems.ORANGE_CANDY.getDefaultInstance(),
			SCPItems.YELLOW_CANDY.getDefaultInstance(),
			SCPItems.GREEN_CANDY.getDefaultInstance(),
			SCPItems.BLUE_CANDY.getDefaultInstance(),
			SCPItems.PURPLE_CANDY.getDefaultInstance()
	));

	public CandyItem(Properties pProperties) {
		super(pProperties.food(new FoodProperties.Builder().alwaysEat().fast().nutrition(1).build()));
	}
}
