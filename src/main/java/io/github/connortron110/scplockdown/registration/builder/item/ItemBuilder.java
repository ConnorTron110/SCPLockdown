package io.github.connortron110.scplockdown.registration.builder.item;

import io.github.connortron110.scplockdown.registration.builder.Builder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ItemBuilder<I extends Item> implements Builder<I> {
	private final ItemDeferredRegister register;
	private final String name;
	private final Function<Item.Properties, I> builder;

	protected final Item.Properties properties = new Item.Properties();

	// private static final HashMap<CreativeModeTab, List<Item>> TABS = new HashMap<>();

	public ItemBuilder(ItemDeferredRegister register, String name, Function<Item.Properties, I> builder) {
		this.register = register;
		this.name = name;
		this.builder = builder;
/*
        if (register.getDefaultItemGroup() != null) {
            if (!TABS.containsKey(register.getDefaultItemGroup())) {
                TABS.put(register.getDefaultItemGroup(), Lists.newArrayList());
            }

            List<Item> itemList = TABS.get(register.getDefaultItemGroup());

            itemList.add()

            properties.tab(register.getDefaultItemGroup());
        }

 */
	}

	public ItemBuilder<I> tab(CreativeModeTab itemGroup) {
		//properties.tab(itemGroup);
		return this;
	}

	public ItemBuilder<I> stacksTo(int maxStackSize) {
		properties.stacksTo(maxStackSize);
		return this;
	}

	@Override
	public ItemRegistryObject<I> build() {
		return new ItemRegistryObject<>(register.getRegister().register(name, () -> builder.apply(properties)));
	}

	//public static HashMap<CreativeModeTab, List<Item>> getTABS() {
	//   return TABS;
	//}
}
