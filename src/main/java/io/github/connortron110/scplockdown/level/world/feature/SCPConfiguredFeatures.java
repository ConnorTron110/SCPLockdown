package io.github.connortron110.scplockdown.level.world.feature;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

//TODO Possible generalise this class to hold all features?
//  Its also possible this is registration of world features but im not super confident on that
public class SCPConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> SCP143_TREE = registerKey("scp143_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SCP148_ORE_KEY = registerKey("scp148_ore");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

		register(context, SCP143_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(SCPBlocks.SCP143_LOG.get()),
				new StraightTrunkPlacer(4, 2, 0),
				BlockStateProvider.simple(SCPBlocks.SCP143_LEAVES.get()),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 0)).ignoreVines().build());

		RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		List<OreConfiguration.TargetBlockState> overworldSCP148Ores = List.of(OreConfiguration.target(stoneReplaceables, SCPBlocks.SCP148_ORE.get().defaultBlockState()));
		register(context, SCP148_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSCP148Ores, 3));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}
}
