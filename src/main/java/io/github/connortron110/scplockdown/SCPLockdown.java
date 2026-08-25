package io.github.connortron110.scplockdown;

import com.mojang.logging.LogUtils;
import io.github.connortron110.scplockdown.registration.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SCPLockdown.MOD_ID)
public class SCPLockdown {
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String MOD_ID = "scplockdown";

	public static SCPLockdown instance;

	//public final RemappingWorldHook remappingWorldHook;

	public SCPLockdown(FMLJavaModLoadingContext context) {
		instance = this;
		IEventBus bus = context.getModEventBus();

		SCPEntities.ENTITY_TYPES.register(bus);
		SCPItems.ITEMS.register(bus);
		SCPBlocks.BLOCKS.register(bus);
		SCPCreativeTabs.TABS.register(bus);
		SCPBlockEntities.BLOCK_ENTITIES.register(bus);
		//SCPFluids.FLUIDS.register(bus);

		SCPMenuTypes.MENUS.register(bus);
		SCPRecipes.RECIPE_SERIALIZERS.register(bus);

		SCPSounds.SOUND_EVENTS.register(bus);
		SCPEffects.EFFECTS.register(bus);

		//  Stuff for config
		context.registerConfig(ModConfig.Type.COMMON, LockdownConfig.COMMON_SPEC);

		//FIXME Causes Client to brick world
		//remappingWorldHook = new RemappingWorldHook();
		//WorldPersistenceHooks.addHook(remappingWorldHook);
		//WorldPersistenceHooks.addHook(new RemappingWrappedFMLWrapperHook());
	}
}
