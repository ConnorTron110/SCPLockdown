package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SCPCreativeTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SCPLockdown.MOD_ID);

	public static final RegistryObject<CreativeModeTab> TAB_SCP_ITEMS = TABS.register("lockdown_items", () -> CreativeModeTab.builder()
			.icon(SCPItems.SCREWDRIVER::getDefaultInstance)
			.title(Component.translatable(("itemGroup.lockdown_items")))
			.build());

	public static final RegistryObject<CreativeModeTab> TAB_BUILDING = TABS.register("lockdown_building_blocks", () -> CreativeModeTab.builder()
			.icon(SCPBlocks.FLOOR_A.get().asItem()::getDefaultInstance)
			.title(Component.translatable("itemGroup.lockdown_building_blocks"))
			.build());

    /*
    public static final ItemGroup TAB_BUILDING = new ItemGroup("lockdown_building_blocks") {

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);
            //Super Jank but couldn't figure out how to do the comparator properly
            NonNullList<ItemStack> copy = NonNullList.create();
            copy.addAll(items.stream().filter(stack -> !(stack.getItem() instanceof BlockItem)).collect(Collectors.toList()));
            copy.addAll(items.stream().filter(stack -> stack.getItem() instanceof BlockItem
                            && !(((BlockItem) stack.getItem()).getBlock() instanceof StairsBlock)
                            && !(((BlockItem) stack.getItem()).getBlock() instanceof SlabBlock)
                            && !(((BlockItem) stack.getItem()).getBlock() instanceof WallBlock))
                    .collect(Collectors.toList()));
            copy.addAll(items.stream().filter(stack -> stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof StairsBlock).collect(Collectors.toList()));
            copy.addAll(items.stream().filter(stack -> stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof SlabBlock).collect(Collectors.toList()));
            copy.addAll(items.stream().filter(stack -> stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof WallBlock).collect(Collectors.toList()));
            items.removeAll(copy);
            items.addAll(copy);
            copy.clear();
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(SCPBlocks.FLOOR_A.get());
        }
    };

     */

	public static final RegistryObject<CreativeModeTab> TAB_FUNCTIONAL = TABS.register("lockdown_functional_blocks", () -> CreativeModeTab.builder()
			.icon(SCPBlocks.TOILET.get().asItem()::getDefaultInstance)
			.title(Component.translatable("itemGroup.lockdown_functional_blocks"))
			.build());

	public static final RegistryObject<CreativeModeTab> TAB_ENTITIES = TABS.register("lockdown_entities", () -> CreativeModeTab.builder()
			.icon(SCPEntities.getEggFromEntity(SCPEntities.SCP939)::getDefaultInstance)
			.title(Component.translatable("itemGroup.lockdown_entities"))
			.build());
}
