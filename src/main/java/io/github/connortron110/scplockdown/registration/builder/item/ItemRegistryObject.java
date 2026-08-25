package io.github.connortron110.scplockdown.registration.builder.item;

import io.github.connortron110.scplockdown.registration.builder.WrappedRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ItemRegistryObject<I extends Item> extends WrappedRegistryObject<I> implements ItemLike {
	public ItemRegistryObject(RegistryObject<I> object) {
		super(object);
	}

	public ItemStack getDefaultInstance() {
		return get().getDefaultInstance();
	}

	@Override
	public @NotNull Item asItem() {
		return get();
	}
}
