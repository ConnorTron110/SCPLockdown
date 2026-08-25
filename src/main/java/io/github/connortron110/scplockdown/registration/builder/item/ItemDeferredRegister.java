package io.github.connortron110.scplockdown.registration.builder.item;

import io.github.connortron110.scplockdown.registration.builder.WrappedDeferredRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ItemDeferredRegister extends WrappedDeferredRegister<Item> {

	@Nullable
	private CreativeModeTab defaultItemGroup;

	private ItemDeferredRegister(String modId) {
		super(ForgeRegistries.ITEMS, modId);
	}

	public ItemDeferredRegister setDefaultItemGroup(@Nullable CreativeModeTab defaultItemGroup) {
		this.defaultItemGroup = defaultItemGroup;
		return this;
	}

	@Nullable
	public CreativeModeTab getDefaultItemGroup() {
		return defaultItemGroup;
	}

	public <I extends Item> ItemBuilder<I> register(String name, Function<Item.Properties, I> builder) {
		return new ItemBuilder<>(this, name, builder);
	}


	public static ItemDeferredRegister create(String modId) {
		return new ItemDeferredRegister(modId);
	}
}
