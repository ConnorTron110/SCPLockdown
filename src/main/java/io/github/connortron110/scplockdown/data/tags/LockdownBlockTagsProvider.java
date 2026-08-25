package io.github.connortron110.scplockdown.data.tags;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LockdownBlockTagsProvider extends BlockTagsProvider {

	public LockdownBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
		//Vanilla Tags
		tag(BlockTags.LOGS).add(SCPBlocks.SCP143_LOG.get());
		tag(BlockTags.PLANKS).add(SCPBlocks.SCP143_PLANKS.get());
		tag(BlockTags.LEAVES).add(SCPBlocks.SCP143_LEAVES.get());
		tag(BlockTags.SAPLINGS).add(SCPBlocks.SCP143_SAPLING.get());
		tag(BlockTags.WALLS)
				.add(SCPBlocks.REINFORCED_IRON_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.STEEL_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.SUBLEVEL_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.REINFORCED_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.METAL_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.METAL_WALL_A_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.REINFORCED_WALL_C_WALL.get())
				.add(SCPBlocks.EXTERIOR_BOTTOM_WALL.get())
				.add(SCPBlocks.EXTERIOR_MIDDLE_WALL.get())
				.add(SCPBlocks.EXTERIOR_TOP_WALL.get())
				.add(SCPBlocks.QUARTZ_WALL.get())
				.add(SCPBlocks.PIPE_WALL_WALL.get())
				.add(SCPBlocks.METAL_WALL_B_WALL.get())
				.add(SCPBlocks.SMOOTH_QUARTZ_WALL.get())
				.add(SCPBlocks.STONE_WALL.get())
				.add(SCPBlocks.WHITE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.ORANGE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.MAGENTA_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.LIGHT_BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.YELLOW_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.LIME_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.PINK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.LIGHT_GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.CYAN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.PURPLE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.BROWN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.GREEN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.RED_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.BLACK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.TERRACOTTA_STAIR_SLAB_WALL_TRIPLE.getWall().get())
				.add(SCPBlocks.POLISHED_GRANITE_WALL.get())
				.add(SCPBlocks.POLISHED_DIORITE_WALL.get())
				.add(SCPBlocks.POLISHED_ANDESITE_WALL.get());

		//Forge Tags
		tag(Tags.Blocks.ORES).add(SCPBlocks.SCP148_ORE.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(SCPBlocks.SCP148_ORE.get()).add(SCPBlocks.SCP148_BLOCK.get());
		tag(BlockTags.NEEDS_DIAMOND_TOOL).add(SCPBlocks.SCP148_ORE.get());
	}
}
