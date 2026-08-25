package io.github.connortron110.scplockdown.data.advancements;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LockdownAdvancementsProvider extends ForgeAdvancementProvider {
	/**
	 * Constructs an advancement provider using the generators to write the
	 * advancements to a file.
	 *
	 * @param output             the target directory of the data generator
	 * @param registries         a future of a lookup for registries and their objects
	 * @param existingFileHelper a helper used to find whether a file exists
	 * @param subProviders       the generators used to create the advancements
	 */
	public LockdownAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper, List<AdvancementGenerator> subProviders) {
		super(output, registries, existingFileHelper, subProviders);
	}
}
