package io.github.connortron110.scplockdown.events.remapping;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.builder.item.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * Used for block renames during early stages of development
 */
@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenameRemapping {

	private static final HashMap<String, Lazy<Block>> BLOCK_REMAP = new HashMap<>();
	private static final HashMap<String, Lazy<Block>> BLOCK_ITEM_REMAP = new HashMap<>();
	private static final HashMap<String, Lazy<Item>> ITEM_REMAP = new HashMap<>();

	static {
		alphaRemaps();
	}

	public static void alphaRemaps() {
		//  25w25a
		addBlockMapping("damaged_ceiling_light", SCPBlocks.CEILING_LIGHT);
	}

	private static void addItemMapping(String name, ItemRegistryObject<? extends Item> registryObject) {
		ITEM_REMAP.put(name, Lazy.of(registryObject::get));
	}

	private static void addBlockMapping(String name, RegistryObject<? extends Block> registryObject) {
		BLOCK_REMAP.put(name, Lazy.of(registryObject::get));
		BLOCK_ITEM_REMAP.put(name, Lazy.of(registryObject::get));
	}
    /*

    @SubscribeEvent
    public static void onMissingBlock(RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> blockMapping : event.getMappings(SCPLockdown.MOD_ID)) {
            if (BLOCK_REMAP.containsKey(blockMapping.key.getPath().toLowerCase())) {
                blockMapping.remap(BLOCK_REMAP.get(blockMapping.key.getPath()).get());
            }
        }
    }

    @SubscribeEvent
    public static void onMissingItem(RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> itemMapping : event.getMappings(SCPLockdown.MOD_ID)) {
            if (ITEM_REMAP.containsKey(itemMapping.key.getPath().toLowerCase())) {
                itemMapping.remap(ITEM_REMAP.get(itemMapping.key.getPath()).get());
            }
            if (BLOCK_ITEM_REMAP.containsKey(itemMapping.key.getPath().toLowerCase())) {
                itemMapping.remap(BLOCK_ITEM_REMAP.get(itemMapping.key.getPath()).get().asItem());
            }
        }
    }

     */
}