package io.github.connortron110.scplockdown.level.world.feature;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class SCPBiomeModifiers {
	public static final ResourceKey<BiomeModifier> SCP148_ORE_KEY = registerKey("scp148_ore");

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

		context.register(SCP148_ORE_KEY, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
				HolderSet.direct(placedFeatures.getOrThrow(SCPPlacedFeatures.SCP148_ORE_KEY)),
				GenerationStep.Decoration.UNDERGROUND_ORES
		));
	}

	public static ResourceKey<BiomeModifier> registerKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name));
	}
}
