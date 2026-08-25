package io.github.connortron110.scplockdown.data.tags;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.SCPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class LockdownItemTagsProvider extends ItemTagsProvider {

	public LockdownItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> pBlockTags, ExistingFileHelper existingFileHelper) {
		super(pOutput, pLookupProvider, pBlockTags, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
		//Vanilla
		copy(BlockTags.LOGS, ItemTags.LOGS);
		copy(BlockTags.PLANKS, ItemTags.PLANKS);
		copy(BlockTags.LEAVES, ItemTags.LEAVES);
		copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

		//Forge
		copy(Tags.Blocks.ORES, Tags.Items.ORES);

		tag(Tags.Items.INGOTS).add(SCPItems.SCP148_INGOT.get());

		tag(SCPTags.Items.VIAL_008_REFILLABLE).add(SCPItems.PUTRID_FLESH.asItem());
	}
}
