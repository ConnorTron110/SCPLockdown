package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.inventory.CardWriterMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SCPMenuTypes {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SCPLockdown.MOD_ID);

	public static final RegistryObject<MenuType<CardWriterMenu>> CARD_WRITER = register("card_writer", CardWriterMenu::new);

	private static <M extends AbstractContainerMenu> RegistryObject<MenuType<M>> register(String id, IContainerFactory<M> constructor) {
		return MENUS.register(id, () -> IForgeMenuType.create(constructor));
	}
}
