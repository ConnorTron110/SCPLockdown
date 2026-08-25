package io.github.connortron110.scplockdown.data;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.data.advancements.LockdownAdvancementsProvider;
import io.github.connortron110.scplockdown.data.advancements.LockdownSCPEncounterAdvancements;
import io.github.connortron110.scplockdown.data.client.LockdownBlocksStateProvider;
import io.github.connortron110.scplockdown.data.client.LockdownItemModelProvider;
import io.github.connortron110.scplockdown.data.loot.LockdownLootTableProvider;
import io.github.connortron110.scplockdown.data.recipes.LockdownRecipeProvider;
import io.github.connortron110.scplockdown.data.tags.*;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.level.world.feature.SCPBiomeModifiers;
import io.github.connortron110.scplockdown.level.world.feature.SCPConfiguredFeatures;
import io.github.connortron110.scplockdown.level.world.feature.SCPPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput packOutput = gen.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		gen.addProvider(event.includeClient(), new LockdownBlocksStateProvider(packOutput, existingFileHelper));
		gen.addProvider(event.includeClient(), new LockdownItemModelProvider(packOutput, existingFileHelper));

		gen.addProvider(event.includeClient(), new LockdownLangProvider(packOutput));
		gen.addProvider(event.includeClient(), new LockdownSoundsProvider(packOutput, existingFileHelper));

		//  We must generate data tags for damage types before we can do things with tags
		RegistrySetBuilder builder = new RegistrySetBuilder()
				.add(Registries.DAMAGE_TYPE, SCPDamageTypes::damageTypesData)
				.add(Registries.CONFIGURED_FEATURE, SCPConfiguredFeatures::bootstrap)
				.add(Registries.PLACED_FEATURE, SCPPlacedFeatures::bootstrap)
				.add(ForgeRegistries.Keys.BIOME_MODIFIERS, SCPBiomeModifiers::bootstrap);
		DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, Set.of(SCPLockdown.MOD_ID));
		event.getGenerator().addProvider(event.includeServer(), datapackBuiltinEntriesProvider);

		//  Tags
		LockdownBlockTagsProvider blockTags = gen.addProvider(event.includeServer(), new LockdownBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
		gen.addProvider(event.includeServer(), new LockdownItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
		gen.addProvider(event.includeServer(), new LockdownEntityTagsProvider(packOutput, lookupProvider, existingFileHelper));
		gen.addProvider(event.includeServer(), new LockdownFluidTagsProvider(packOutput, lookupProvider, existingFileHelper));
		gen.addProvider(event.includeServer(), new LockdownDamageTypeTagsProvider(packOutput, datapackBuiltinEntriesProvider.getRegistryProvider(), existingFileHelper));

		gen.addProvider(event.includeServer(), LockdownLootTableProvider.create(packOutput));
		gen.addProvider(event.includeServer(), new LockdownRecipeProvider(packOutput));

		//  Advancements TODO: Some wack problem with some lookup
		gen.addProvider(event.includeServer(), new LockdownAdvancementsProvider(packOutput, lookupProvider, existingFileHelper, List.of(new LockdownSCPEncounterAdvancements())));
	}
}
