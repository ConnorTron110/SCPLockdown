package io.github.connortron110.scplockdown.events.setup;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.gui.screen.inventory.CardWriterScreen;
import io.github.connortron110.scplockdown.level.blocks.pipes.*;
import io.github.connortron110.scplockdown.level.items.CardWriterItem;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.level.items.biocontainer.SyringeItem;
import io.github.connortron110.scplockdown.level.items.biocontainer.VialItem;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPCreativeTabs;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.SCPMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Set;

//@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

	@SubscribeEvent
	public static void registerBlockColours(RegisterColorHandlersEvent.Block event) {
		SCPBlocks.STRAIGHT_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.CORNER_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.JUNC3_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.TJUNC_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.JUNC4X_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.JUNC4_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.JUNC5_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
		SCPBlocks.JUNC6_PIPES.getPairs().forEach(pair -> event.register((state, reader, pos, index) -> pair.getColor().colorValue, pair.getRegistryObject().get()));
	}

	@SubscribeEvent
	public static void registerItemColours(RegisterColorHandlersEvent.Item event) {
		event.register((pStack, pTintIndex) -> {
			if (!CardWriterItem.hasCard(pStack)) return 0xFFFFFF;
			int colour = KeycardItem.getColour(CardWriterItem.getStoredItem(pStack));
			return colour >= 0 ? colour : 0xFFFFFF;
		}, SCPItems.CARD_WRITER);

		event.register((pStack, pTintIndex) -> {
			if (pTintIndex != 1) return 0xFFFFFF;
			return KeycardItem.getColour(pStack) >= 0 ? KeycardItem.getColour(pStack) : 0xFFFFFF;
		}, SCPItems.KEYCARD);

		SCPBlocks.STRAIGHT_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.CORNER_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.JUNC3_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.TJUNC_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.JUNC4X_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.JUNC4_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.JUNC5_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
		SCPBlocks.JUNC6_PIPES.getPairs().forEach(pair -> event.register((stack, index) -> pair.getColor().colorValue, pair.getRegistryObject().get().asItem()));
	}

	@SubscribeEvent
	public static void clientSetupEvent(FMLClientSetupEvent event) {
		//setBlockLayers();
		setItemModelOverrides();
		//setupEntityRenderers();

		event.enqueueWork(ClientSetupEvents::registerMenuScreens);
	}

	private static void registerMenuScreens() {
		MenuScreens.register(SCPMenuTypes.CARD_WRITER.get(), CardWriterScreen::new);
	}

	//  TODO: Replaced with JSON Stuff
    /*
    private static void setBlockLayers() {


        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP002_PLANT_POT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(SCPFluids.SCP006_FOUNTAIN.getSource().get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(SCPFluids.SCP006_FOUNTAIN.getFlowing().get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(SCPFluids.SCP006_FOUNTAIN.getBlock().get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP009.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP012.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP035_GLASS_CASE.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP143_LEAVES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(SCPBlocks.SCP143_SAPLING.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(SCPBlocks.MESH_FLOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(SCPBlocks.BLAST_RESISTANT_GLASS.get(), RenderType.cutout());
    }

     */

	private static void setItemModelOverrides() {
		ItemProperties.register(SCPItems.CARD_WRITER.asItem(), ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "card"), (stack, level, entity, seed) -> CardWriterItem.hasCard(stack) ? 1 : 0);

		ItemProperties.register(SCPItems.VIAL.asItem(), ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "contents"), (stack, level, entity, seed) -> ((VialItem) SCPItems.VIAL.asItem()).getBioContent(stack).ordinal());
		ItemProperties.register(SCPItems.VIAL.asItem(), ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cap"), (stack, level, entity, seed) -> ((VialItem) SCPItems.VIAL.asItem()).isCapOn(stack) ? 1F : 0F);
		ItemProperties.register(SCPItems.VIAL.asItem(), ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "amount"), (stack, level, entity, seed) -> ((VialItem) SCPItems.VIAL.asItem()).getBioAmount(stack));
		ItemProperties.register(SCPItems.SYRINGE.asItem(), ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "contents"), (stack, level, entity, seed) -> ((SyringeItem) SCPItems.SYRINGE.asItem()).getBioContent(stack).ordinal());
	}


	@SubscribeEvent
	public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
		Set<RegistryObject<? extends Block>> scpBlocks = Set.of(SCPBlocks.SCP002_METAL, SCPBlocks.SCP002_FLESH_A, SCPBlocks.SCP002_FLESH_B, SCPBlocks.SCP002_FLESH_C, SCPBlocks.SCP002_FLESH_D, SCPBlocks.SCP002_COFFEE_TABLE, SCPBlocks.SCP002_PLANT_POT, SCPBlocks.SCP002_TV, SCPBlocks.SCP002_DOOR, SCPBlocks.SCP002_LAMP, SCPBlocks.SCP002_ARM_CHAIR, SCPBlocks.SCP002_TABLE, SCPBlocks.SCP009, SCPBlocks.SCP012, SCPBlocks.SCP015_PIPE, SCPBlocks.SCP015_BLOCK, SCPBlocks.SCP019, SCPBlocks.SCP035_GLASS_CASE, SCPBlocks.SCP124, SCPBlocks.SCP143_LOG, SCPBlocks.SCP143_LEAVES, SCPBlocks.SCP143_PLANKS, SCPBlocks.SCP143_SAPLING, SCPBlocks.SCP148_ORE, SCPBlocks.SCP148_BLOCK, SCPBlocks.SCP330, SCPBlocks.SCP458, SCPBlocks.SCP822, SCPBlocks.SCP902);
		Set<RegistryObject<? extends Block>> functionalBlocks = Set.of(SCPBlocks.CARD_READER, SCPBlocks.HEAVY_BUTTON, SCPBlocks.TOILET, SCPBlocks.CHAIR, SCPBlocks.OFFICE_CHAIR, SCPBlocks.TABLE, SCPBlocks.OPAQUE_TRAPDOOR, SCPBlocks.CEILING_LAMP, SCPBlocks.SMALL_LAMP, SCPBlocks.CEILING_LIGHT, SCPBlocks.WOOD_CRATE, SCPBlocks.DARK_WOOD_CRATE, SCPBlocks.LOCKER, SCPBlocks.CONTAINMENT_DOOR, SCPBlocks.SLIDING_DOOR, SCPBlocks.MAGNETIZED_DOOR, SCPBlocks.BLAST_DOOR);

		List<Item> blockItems = SCPItems.ITEMS.getRegister().getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof BlockItem)
				.filter(item -> !(((BlockItem) item).getBlock() instanceof CornerPipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof TJuncPipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof Junc3PipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof Junc4PipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof Junc4XPipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof Junc5PipeBlock)
						&& !(((BlockItem) item).getBlock() instanceof Junc6PipeBlock)).toList();


		if (event.getTab() == SCPCreativeTabs.TAB_SCP_ITEMS.get()) {
			//  Because I REALLY want the configured cards next to the blank card, we have this
			List<ItemStack> items = SCPItems.ITEMS.getRegister().getEntries().stream().map(RegistryObject::get)
					.filter(item -> !(item instanceof SpawnEggItem) && !(item instanceof BlockItem)).map(Item::getDefaultInstance).toList();

			List<ItemStack> orderedItems = Lists.newArrayList();

			for (ItemStack item : items) {
				if (item.getItem() instanceof KeycardItem) {
					//  Insert Blank first then configured
					orderedItems.add(item);
					orderedItems.addAll(KeycardItem.DEFAULT_CARDS.get());
					continue;
				}

				//  Insert this item
				orderedItems.add(item);
			}

			event.acceptAll(orderedItems);
		}

		if (event.getTab() == SCPCreativeTabs.TAB_BUILDING.get()) {
			event.acceptAll(blockItems.stream().filter(item -> !scpBlocks.stream().map(registryObject -> registryObject.get().asItem()).toList().contains(item) && !functionalBlocks.stream().map(registryObject -> registryObject.get().asItem()).toList().contains(item)).map(Item::getDefaultInstance).toList());
		}

		if (event.getTab() == SCPCreativeTabs.TAB_FUNCTIONAL.get()) {
			event.acceptAll(scpBlocks.stream().map(registryObject -> registryObject.get().asItem().getDefaultInstance()).toList());
			event.acceptAll(functionalBlocks.stream().map(registryObject -> registryObject.get().asItem().getDefaultInstance()).toList());
		}

		if (event.getTab() == SCPCreativeTabs.TAB_ENTITIES.get()) {
			event.acceptAll(SCPItems.ITEMS.getRegister().getEntries().stream().map(RegistryObject::get).filter(item -> item instanceof SpawnEggItem).map(Item::getDefaultInstance).toList());
		}
	}
}
