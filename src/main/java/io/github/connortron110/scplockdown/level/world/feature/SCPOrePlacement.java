package io.github.connortron110.scplockdown.level.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * Pure copy of methods in {@link net.minecraft.data.worldgen.placement.OrePlacements} because they're private... might AT them in the future
 */
public class SCPOrePlacement {
	public static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier heightRange) {
		return List.of(count, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
	}

	public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
		return orePlacement(CountPlacement.of(count), heightRange);
	}

	public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
		return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
	}
}
