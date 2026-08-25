package io.github.connortron110.scplockdown.level.world.feature.tree;

import io.github.connortron110.scplockdown.level.world.feature.SCPConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class SCP143TreeGrower extends AbstractTreeGrower {
	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
		return SCPConfiguredFeatures.SCP143_TREE;
	}
}
