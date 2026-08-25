package io.github.connortron110.scplockdown.data.loot;

import io.github.connortron110.scplockdown.level.blocks.LockdownDoubleTallBlock;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.holders.StairSlabWallTriple;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Set;

public class LockdownBlockLootTables extends BlockLootSubProvider {
	protected LockdownBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	protected void generate() {
		dropSelf(SCPBlocks.SCP002_METAL);
		dropSelf(SCPBlocks.SCP002_FLESH_A);
		dropSelf(SCPBlocks.SCP002_FLESH_B);
		dropSelf(SCPBlocks.SCP002_FLESH_C);
		dropSelf(SCPBlocks.SCP002_FLESH_D);
		dropSelf(SCPBlocks.SCP002_COFFEE_TABLE);
		dropSelf(SCPBlocks.SCP002_PLANT_POT);
		dropSelf(SCPBlocks.SCP002_TV);
		add(SCPBlocks.SCP002_DOOR.get(), createDoorTable(SCPBlocks.SCP002_DOOR.get()));
		add(SCPBlocks.SCP002_LAMP.get(), createSinglePropConditionTable(SCPBlocks.SCP002_LAMP.get(), LockdownDoubleTallBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(SCPBlocks.SCP002_ARM_CHAIR);
		add(SCPBlocks.SCP002_TABLE.get(), LootTable.lootTable().withPool(applyExplosionCondition(SCPBlocks.SCP002_TABLE.get(), LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(SCPBlocks.SCP002_TABLE.get()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(SCPBlocks.SCP002_TABLE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HorizontalDirectionalBlock.FACING, Direction.NORTH).hasProperty(HorizontalDirectionalBlock.FACING, Direction.WEST)))))));

		dropSelf(SCPBlocks.SCP009);

		dropSelf(SCPBlocks.SCP012);

		dropSelf(SCPBlocks.SCP015_PIPE);
		dropSelf(SCPBlocks.SCP015_BLOCK);

		add(SCPBlocks.SCP019.get(), createSinglePropConditionTable(SCPBlocks.SCP019.get(), LockdownDoubleTallBlock.HALF, DoubleBlockHalf.LOWER));

		dropSelf(SCPBlocks.SCP035_GLASS_CASE);

		dropSelf(SCPBlocks.SCP124);

		dropSelf(SCPBlocks.SCP143_LOG);
		add(SCPBlocks.SCP143_LEAVES.get(), createLeavesDrops(SCPBlocks.SCP143_LEAVES.get(), SCPBlocks.SCP143_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F));
		dropSelf(SCPBlocks.SCP143_PLANKS);
		dropSelf(SCPBlocks.SCP143_SAPLING);

		add(SCPBlocks.SCP148_ORE.get(), block -> createOreDrop(block, SCPItems.SCP148_INGOT.asItem()));
		dropSelf(SCPBlocks.SCP148_BLOCK);

		dropSelf(SCPBlocks.SCP330);

		dropSelf(SCPBlocks.SCP458);

		dropSelf(SCPBlocks.SCP822);

		dropSelf(SCPBlocks.SCP902);

		dropSelf(SCPBlocks.SCP914);
		dropSelf(SCPBlocks.SCP914_METAL);
		dropSelf(SCPBlocks.SCP914_GEARS);

		//Functional Blocks\\

		dropSelf(SCPBlocks.CARD_READER);
		dropSelf(SCPBlocks.HEAVY_BUTTON);
		dropSelf(SCPBlocks.TOILET);
		dropSelf(SCPBlocks.CHAIR);
		dropSelf(SCPBlocks.OFFICE_CHAIR);
		dropSelf(SCPBlocks.TABLE);
		dropSelf(SCPBlocks.OPAQUE_TRAPDOOR);
		dropSelf(SCPBlocks.CEILING_LAMP);
		dropSelf(SCPBlocks.SMALL_LAMP);
		dropSelf(SCPBlocks.CEILING_LIGHT);

		add(SCPBlocks.WOOD_CRATE.get(), createNameableBlockEntityTable(SCPBlocks.WOOD_CRATE.get()));
		add(SCPBlocks.DARK_WOOD_CRATE.get(), createNameableBlockEntityTable(SCPBlocks.DARK_WOOD_CRATE.get()));

		dropSelf(SCPBlocks.PERSONAL_COMPUTER);
		dropSelf(SCPBlocks.COMPUTER);

		//Normal Blocks\\

		dropSelf(SCPBlocks.CEILING);
		dropSelf(SCPBlocks.CEILING_TILE);
		dropSelf(SCPBlocks.CEILING_GRATE);

		dropSelf(SCPBlocks.CONTAINMENT_FLOOR);
		dropSelf(SCPBlocks.FLOOR_A);
		dropSelf(SCPBlocks.FLOOR_B);
		dropSelf(SCPBlocks.MESH_FLOOR);

		dropSelf(SCPBlocks.PLASTER_WALL);
		dropSelf(SCPBlocks.VENT);
		dropSelf(SCPBlocks.VENTILATION);

		dropSelf(SCPBlocks.WALL_A);
		dropSelf(SCPBlocks.WALL_B);
		dropSelf(SCPBlocks.WALL_C);
		dropSelf(SCPBlocks.WALL_D);
		dropSelf(SCPBlocks.WALL_E);
		dropSelf(SCPBlocks.WALL_F);
		dropSelf(SCPBlocks.WALL_G);

		dropSelf(SCPBlocks.WHITE_WALL);
		dropSelf(SCPBlocks.OLD_WHITE_WALL);

		dropSelf(SCPBlocks.REINFORCED_IRON);
		dropSelf(SCPBlocks.STEEL);
		dropSelf(SCPBlocks.REINFORCED_WALL_A);
		dropSelf(SCPBlocks.REINFORCED_WALL_B);
		dropSelf(SCPBlocks.REINFORCED_WALL_C);

		dropSelf(SCPBlocks.EXTERIOR_BOTTOM);
		dropSelf(SCPBlocks.EXTERIOR_MIDDLE);
		dropSelf(SCPBlocks.EXTERIOR_TOP);

		dropSelf(SCPBlocks.MEDICAL_BOTTOM);
		dropSelf(SCPBlocks.MEDICAL_TOP);

		dropSelf(SCPBlocks.OFFICE_BOTTOM);
		dropSelf(SCPBlocks.OFFICE_TOP);

		dropSelf(SCPBlocks.PIPE_WALL);

		dropSelf(SCPBlocks.SUBLEVEL_WALL_A);
		dropSelf(SCPBlocks.SUBLEVEL_WALL_B);
		dropSelf(SCPBlocks.SUBLEVEL_WALL_C);
		dropSelf(SCPBlocks.REINFORCED_SUBLEVEL_WALL_A);
		dropSelf(SCPBlocks.REINFORCED_SUBLEVEL_WALL_B);
		dropSelf(SCPBlocks.REINFORCED_SUBLEVEL_WALL_C);
		dropSelf(SCPBlocks.METAL_SUBLEVEL);

		dropSelf(SCPBlocks.GRATE);
		dropWhenSilkTouch(SCPBlocks.BLAST_RESISTANT_GLASS.get());

		dropSelf(SCPBlocks.ARMORY_BOTTOM_A);
		dropSelf(SCPBlocks.ARMORY_TOP_A);
		dropSelf(SCPBlocks.ARMORY_BOTTOM_B);
		dropSelf(SCPBlocks.ARMORY_TOP_B);

		dropSelf(SCPBlocks.LABORATORY_BOTTOM_A);
		dropSelf(SCPBlocks.LABORATORY_TOP_A);
		dropSelf(SCPBlocks.LABORATORY_BOTTOM_B);
		dropSelf(SCPBlocks.LABORATORY_TOP_B);

		dropSelf(SCPBlocks.METAL_PANEL_A);
		dropSelf(SCPBlocks.METAL_PANEL_B);
		dropSelf(SCPBlocks.METAL_PANEL_C);

		dropSelf(SCPBlocks.REINFORCED_GRANITE);
		dropSelf(SCPBlocks.REINFORCED_QUARTZ);

		dropSelf(SCPBlocks.GREEN_PANEL_A);
		dropSelf(SCPBlocks.GREEN_PANEL_B);
		dropSelf(SCPBlocks.GREEN_PANEL_C);
		dropSelf(SCPBlocks.GREEN_PANEL_D);

		dropSelf(SCPBlocks.METAL_FLOOR_A);
		dropSelf(SCPBlocks.METAL_FLOOR_B);

		dropSelf(SCPBlocks.METAL_WALL_A);
		dropSelf(SCPBlocks.METAL_WALL_B);

		dropSelf(SCPBlocks.STEEL_FLOOR);

		dropSelf(SCPBlocks.HAZARD);

		dropSelfStairSlabWallTriple(SCPBlocks.REINFORCED_IRON_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.STEEL_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.SUBLEVEL_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.REINFORCED_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.METAL_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.METAL_WALL_A_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE);

		dropSelf(SCPBlocks.WALL_STAIRS);
		dropSelf(SCPBlocks.FLOOR_A_STAIRS);
		dropSelf(SCPBlocks.FLOOR_B_STAIRS);
		dropSelf(SCPBlocks.WHITE_STAIRS);
		dropSelf(SCPBlocks.REINFORCED_WALL_A_STAIRS);
		dropSelf(SCPBlocks.REINFORCED_WALL_B_STAIRS);
		dropSelf(SCPBlocks.REINFORCED_WALL_C_STAIRS);

		add(SCPBlocks.WALL_SLAB.get(), createSlabItemTable(SCPBlocks.WALL_SLAB.get()));
		add(SCPBlocks.FLOOR_A_SLAB.get(), createSlabItemTable(SCPBlocks.FLOOR_A_SLAB.get()));
		add(SCPBlocks.FLOOR_B_SLAB.get(), createSlabItemTable(SCPBlocks.FLOOR_B_SLAB.get()));
		add(SCPBlocks.WHITE_SLAB.get(), createSlabItemTable(SCPBlocks.WHITE_SLAB.get()));
		add(SCPBlocks.REINFORCED_WALL_A_SLAB.get(), createSlabItemTable(SCPBlocks.REINFORCED_WALL_A_SLAB.get()));
		add(SCPBlocks.REINFORCED_WALL_B_SLAB.get(), createSlabItemTable(SCPBlocks.REINFORCED_WALL_B_SLAB.get()));
		add(SCPBlocks.REINFORCED_WALL_C_SLAB.get(), createSlabItemTable(SCPBlocks.REINFORCED_WALL_C_SLAB.get()));

		dropSelf(SCPBlocks.REINFORCED_WALL_C_WALL);
		dropSelf(SCPBlocks.EXTERIOR_BOTTOM_WALL);
		dropSelf(SCPBlocks.EXTERIOR_MIDDLE_WALL);
		dropSelf(SCPBlocks.EXTERIOR_TOP_WALL);
		dropSelf(SCPBlocks.QUARTZ_WALL);
		dropSelf(SCPBlocks.PIPE_WALL_WALL);
		dropSelf(SCPBlocks.METAL_WALL_B_WALL);
		dropSelf(SCPBlocks.SMOOTH_QUARTZ_WALL);

		SCPBlocks.STRAIGHT_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.CORNER_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.JUNC3_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.TJUNC_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.JUNC4X_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.JUNC4_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.JUNC5_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));
		SCPBlocks.JUNC6_PIPES.getPairs().forEach(pair -> dropSelf(pair.getObject()));

		//Vanilla Extended Building Blocks
		dropSelf(SCPBlocks.SMOOTH_STONE_STAIRS);

		dropSelfStairSlabWallTriple(SCPBlocks.WHITE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.ORANGE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.MAGENTA_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.LIGHT_BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.YELLOW_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.LIME_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.PINK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.LIGHT_GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.CYAN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.PURPLE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.BROWN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.GREEN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.RED_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.BLACK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);
		dropSelfStairSlabWallTriple(SCPBlocks.TERRACOTTA_STAIR_SLAB_WALL_TRIPLE);

		dropSelf(SCPBlocks.STONE_WALL);
		dropSelf(SCPBlocks.POLISHED_GRANITE_WALL);
		dropSelf(SCPBlocks.POLISHED_DIORITE_WALL);
		dropSelf(SCPBlocks.POLISHED_ANDESITE_WALL);
	}

	protected void dropSelfStairSlabWallTriple(StairSlabWallTriple stairSlabWall) {
		dropSelf(stairSlabWall.getStairs());
		add(stairSlabWall.getSlab().get(), createSlabItemTable(stairSlabWall.getSlab().get()));
		dropSelf(stairSlabWall.getWall());
	}

	protected <B extends Block> void dropSelf(RegistryObject<B> registryObject) {
		dropSelf(registryObject.get());
	}

	@Nonnull
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return SCPBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}
}
