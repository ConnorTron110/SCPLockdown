package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.level.blocks.*;
import io.github.connortron110.scplockdown.level.blocks.pipes.*;
import io.github.connortron110.scplockdown.level.blocks.scp002furnitire.*;
import io.github.connortron110.scplockdown.level.world.feature.tree.SCP143TreeGrower;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.registration.holders.StairSlabWallTriple;
import io.github.connortron110.scplockdown.utils.SCPDefaultColors;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.function.Supplier;

import static io.github.connortron110.scplockdown.registration.SCPCreativeTabs.TAB_BUILDING;
import static io.github.connortron110.scplockdown.registration.SCPCreativeTabs.TAB_FUNCTIONAL;

public class SCPBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SCPLockdown.MOD_ID);

	public static final RegistryObject<LockdownBlock> SCP002_METAL = iron("scp002_metal", TAB_FUNCTIONAL);
	public static final RegistryObject<SCP002FleshBlock> SCP002_FLESH_A = register("scp002_flesh_a", TAB_FUNCTIONAL, () -> new SCP002FleshBlock(BlockBehaviour.Properties.of()));
	public static final RegistryObject<SCP002FleshBlock> SCP002_FLESH_B = register("scp002_flesh_b", TAB_FUNCTIONAL, () -> new SCP002FleshBlock(BlockBehaviour.Properties.of()));
	public static final RegistryObject<SCP002FleshBlock> SCP002_FLESH_C = register("scp002_flesh_c", TAB_FUNCTIONAL, () -> new SCP002FleshBlock(BlockBehaviour.Properties.of()));
	public static final RegistryObject<SCP002FleshBlock> SCP002_FLESH_D = register("scp002_flesh_d", TAB_FUNCTIONAL, () -> new SCP002FleshBlock(BlockBehaviour.Properties.of()));
	public static final RegistryObject<SCP002CoffeeTableBlock> SCP002_COFFEE_TABLE = register("scp002_coffee_table", TAB_FUNCTIONAL, () -> new SCP002CoffeeTableBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
	public static final RegistryObject<SCP002PlantPotBlock> SCP002_PLANT_POT = register("scp002_plant_pot", TAB_FUNCTIONAL, () -> new SCP002PlantPotBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
	public static final RegistryObject<SCP002TVBlock> SCP002_TV = register("scp002_tv", TAB_FUNCTIONAL, () -> new SCP002TVBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
	public static final RegistryObject<DoorBlock> SCP002_DOOR = register("scp002_door", TAB_FUNCTIONAL, () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.CLAY), BlockSetType.JUNGLE));
	public static final RegistryObject<SCP002LampBlock> SCP002_LAMP = register("scp002_lamp", TAB_FUNCTIONAL, () -> new SCP002LampBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));
	public static final RegistryObject<SCP002ArmChairBlock> SCP002_ARM_CHAIR = register("scp002_arm_chair", TAB_FUNCTIONAL, () -> new SCP002ArmChairBlock(BlockBehaviour.Properties.copy(Blocks.CLAY).noOcclusion()));
	public static final RegistryObject<SCP002TableBlock> SCP002_TABLE = register("scp002_table", TAB_FUNCTIONAL, () -> new SCP002TableBlock(BlockBehaviour.Properties.copy(Blocks.CLAY)));

	public static final RegistryObject<SCP009Block> SCP009 = register("scp009", TAB_FUNCTIONAL, () -> new SCP009Block(BlockBehaviour.Properties.copy(Blocks.ICE).randomTicks()));

	public static final RegistryObject<SCP012Block> SCP012 = register("scp012", TAB_FUNCTIONAL, () -> new SCP012Block(BlockBehaviour.Properties.of().strength(2F).noOcclusion()));

	public static final RegistryObject<SCP015PipeBlock> SCP015_PIPE = register("scp015", TAB_FUNCTIONAL, () -> new SCP015PipeBlock(BlockBehaviour.Properties.of().strength(2F).noOcclusion()));
	public static final RegistryObject<LockdownBlock> SCP015_BLOCK = register("scp015_block", TAB_FUNCTIONAL, () -> new LockdownBlock(BlockBehaviour.Properties.of().strength(2F).noOcclusion()));

	public static final RegistryObject<SCP019Block> SCP019 = register("scp019", TAB_FUNCTIONAL, () -> new SCP019Block(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA).noOcclusion().randomTicks()));

	public static final RegistryObject<SCP035GlassCaseBlock> SCP035_GLASS_CASE = register("scp035_glass_case", TAB_FUNCTIONAL, () -> new SCP035GlassCaseBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(0.5F)));

	public static final RegistryObject<SCP124Block> SCP124 = register("scp124", TAB_FUNCTIONAL, () -> new SCP124Block(BlockBehaviour.Properties.copy(Blocks.FARMLAND).randomTicks()));

	public static final RegistryObject<LockdownLogBlock> SCP143_LOG = register("scp143_log", TAB_FUNCTIONAL, () -> new LockdownLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
	public static final RegistryObject<SCP143LeavesBlock> SCP143_LEAVES = register("scp143_leaves", TAB_FUNCTIONAL, () -> new SCP143LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<LockdownBlock> SCP143_PLANKS = register("scp143_planks", TAB_FUNCTIONAL, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
	public static final RegistryObject<LockdownSapling> SCP143_SAPLING = register("scp143_sapling", TAB_FUNCTIONAL, () -> new LockdownSapling(new SCP143TreeGrower(), BlockBehaviour.Properties.of()));

	public static final RegistryObject<DropExperienceBlock> SCP148_ORE = register("scp148_ore", TAB_FUNCTIONAL, () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F), BiasedToBottomInt.of(2, 5)));
	public static final RegistryObject<LockdownBlock> SCP148_BLOCK = register("scp148_block", TAB_FUNCTIONAL, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

	public static final RegistryObject<SCP330Block> SCP330 = register("scp330", TAB_FUNCTIONAL, () -> new SCP330Block(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA)));

	public static final RegistryObject<SCP458Block> SCP458 = register("scp458", TAB_FUNCTIONAL, () -> new SCP458Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CARPET)));

	public static final RegistryObject<SCP822Block> SCP822 = register("scp822", TAB_FUNCTIONAL, () -> new SCP822Block(BlockBehaviour.Properties.copy(Blocks.CACTUS)));

	//  TODO update the model to better reflect image in the wiki
	public static final RegistryObject<SCP902Block> SCP902 = register("scp902", TAB_FUNCTIONAL, () -> new SCP902Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

	public static final RegistryObject<SCP914Block> SCP914 = register("scp914", TAB_FUNCTIONAL, () -> new SCP914Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<LockdownBlock> SCP914_METAL = register("scp914_metal", TAB_FUNCTIONAL, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<LockdownBlock> SCP914_GEARS = register("scp914_gears", TAB_FUNCTIONAL, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

	//Functional Blocks\\

	public static final RegistryObject<CardReaderBlock> CARD_READER = register("card_reader", TAB_FUNCTIONAL, () -> new CardReaderBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<HeavyButtonBlock> HEAVY_BUTTON = register("heavy_button", TAB_FUNCTIONAL, () -> new HeavyButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	public static final RegistryObject<ToiletBlock> TOILET = register("toilet", TAB_FUNCTIONAL, () -> new ToiletBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
	public static final RegistryObject<ChairBlock> CHAIR = register("chair", TAB_FUNCTIONAL, () -> new ChairBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
	public static final RegistryObject<OfficeChairBlock> OFFICE_CHAIR = register("office_chair", TAB_FUNCTIONAL, () -> new OfficeChairBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));
	public static final RegistryObject<TableBlock> TABLE = register("table", TAB_FUNCTIONAL, () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> OPAQUE_TRAPDOOR = register("opaque_trapdoor", TAB_FUNCTIONAL, () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BlockSetType.CHERRY));
	public static final RegistryObject<CeilingLampBlock> CEILING_LAMP = register("ceiling_lamp", TAB_FUNCTIONAL, () -> new CeilingLampBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().lightLevel(state -> 15)));
	public static final RegistryObject<SmallLampBlock> SMALL_LAMP = register("small_lamp", TAB_FUNCTIONAL, () -> new SmallLampBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().lightLevel(state -> 15)));
	public static final RegistryObject<CeilingLightBlock> CEILING_LIGHT = register("ceiling_light", TAB_FUNCTIONAL, () -> new CeilingLightBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().lightLevel(state -> (((state.getValue(CeilingLightBlock.LIT) || state.getValue(CeilingLightBlock.LIGHT_MODE).equals(CeilingLightBlock.CeilingLightMode.ON)) && !state.getValue(CeilingLightBlock.LIGHT_MODE).equals(CeilingLightBlock.CeilingLightMode.OFF)) ? (state.getValue(CeilingLightBlock.LIGHT_MODE).equals(CeilingLightBlock.CeilingLightMode.DAMAGED) ? 5 : 15) : 0))));  //  This hurts my head

	public static final RegistryObject<CrateBlock> WOOD_CRATE = register("wood_crate", TAB_FUNCTIONAL, () -> new CrateBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));
	public static final RegistryObject<CrateBlock> DARK_WOOD_CRATE = register("dark_wood_crate", TAB_FUNCTIONAL, () -> new CrateBlock(BlockBehaviour.Properties.copy(Blocks.BARREL)));

	public static final RegistryObject<ComputerBlock> PERSONAL_COMPUTER = register("personal_computer", TAB_FUNCTIONAL, () -> new ComputerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final RegistryObject<ComputerBlock> COMPUTER = register("computer", TAB_FUNCTIONAL, () -> new ComputerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

	//TODO Do drops for multiblocks
	public static final RegistryObject<LockerBlock> LOCKER = registerWithISTER("locker", TAB_FUNCTIONAL, () -> new LockerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable()));
	public static final RegistryObject<SlidingDoorBlock> CONTAINMENT_DOOR = registerWithISTER("containment_door", TAB_FUNCTIONAL, () -> new SlidingDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable()));
	public static final RegistryObject<SlidingDoorBlock> SLIDING_DOOR = registerWithISTER("sliding_door", TAB_FUNCTIONAL, () -> new SlidingDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable()));
	public static final RegistryObject<SlidingDoorBlock> MAGNETIZED_DOOR = registerWithISTER("magnetized_door", TAB_FUNCTIONAL, () -> new SlidingDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable()));
	public static final RegistryObject<BlastDoorBlock> BLAST_DOOR = registerWithISTER("blast_door", TAB_FUNCTIONAL, () -> new BlastDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noLootTable()));

	//Building Blocks\\

	public static final RegistryObject<LockdownBlock> CEILING = stone("ceiling");
	public static final RegistryObject<LockdownBlock> CEILING_TILE = stone("ceiling_tile");
	public static final RegistryObject<LockdownBlock> CEILING_GRATE = stone("ceiling_grate");

	public static final RegistryObject<LockdownBlock> CONTAINMENT_FLOOR = stone("containment_floor");
	public static final RegistryObject<LockdownBlock> FLOOR_A = stone("floor_a");
	public static final RegistryObject<LockdownBlock> FLOOR_B = stone("floor_b");
	public static final RegistryObject<LockdownBlock> MESH_FLOOR = register("mesh_floor", () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

	public static final RegistryObject<LockdownBlock> PLASTER_WALL = stone("plaster_wall");
	public static final RegistryObject<LockdownBlock> VENT = stone("vent");
	public static final RegistryObject<LockdownBlock> VENTILATION = stone("ventilation");

	public static final RegistryObject<LockdownBlock> WALL_A = stone("wall_a");
	public static final RegistryObject<LockdownBlock> WALL_B = stone("wall_b");
	public static final RegistryObject<LockdownBlock> WALL_C = stone("wall_c");
	public static final RegistryObject<LockdownBlock> WALL_D = stone("wall_d");
	public static final RegistryObject<LockdownBlock> WALL_E = stone("wall_e");
	public static final RegistryObject<LockdownBlock> WALL_F = stone("wall_f");
	public static final RegistryObject<LockdownBlock> WALL_G = stone("wall_g");

	public static final RegistryObject<LockdownBlock> WHITE_WALL = stone("white_wall");
	public static final RegistryObject<LockdownBlock> OLD_WHITE_WALL = stone("old_white_wall");

	public static final RegistryObject<LockdownBlock> REINFORCED_IRON = iron("reinforced_iron");
	public static final RegistryObject<LockdownBlock> STEEL = iron("steel");
	public static final RegistryObject<LockdownBlock> REINFORCED_WALL_A = iron("reinforced_wall_a");
	public static final RegistryObject<LockdownBlock> REINFORCED_WALL_B = iron("reinforced_wall_b");
	public static final RegistryObject<LockdownBlock> REINFORCED_WALL_C = iron("reinforced_wall_c");

	public static final RegistryObject<LockdownBlock> EXTERIOR_BOTTOM = stone("exterior_bottom");
	public static final RegistryObject<LockdownBlock> EXTERIOR_MIDDLE = stone("exterior_middle");
	public static final RegistryObject<LockdownBlock> EXTERIOR_TOP = stone("exterior_top");

	public static final RegistryObject<LockdownBlock> MEDICAL_BOTTOM = stone("medical_bottom");
	public static final RegistryObject<LockdownBlock> MEDICAL_TOP = stone("medical_top");

	public static final RegistryObject<LockdownBlock> OFFICE_BOTTOM = stone("office_bottom");
	public static final RegistryObject<LockdownBlock> OFFICE_TOP = stone("office_top");

	public static final RegistryObject<LockdownBlock> PIPE_WALL = stone("pipe_wall");

	public static final RegistryObject<LockdownBlock> SUBLEVEL_WALL_A = stone("sublevel_wall_a");
	public static final RegistryObject<LockdownBlock> SUBLEVEL_WALL_B = stone("sublevel_wall_b");
	public static final RegistryObject<LockdownBlock> SUBLEVEL_WALL_C = stone("sublevel_wall_c");
	public static final RegistryObject<LockdownBlock> REINFORCED_SUBLEVEL_WALL_A = iron("reinforced_sublevel_wall_a");
	public static final RegistryObject<LockdownBlock> REINFORCED_SUBLEVEL_WALL_B = iron("reinforced_sublevel_wall_b");
	public static final RegistryObject<LockdownBlock> REINFORCED_SUBLEVEL_WALL_C = iron("reinforced_sublevel_wall_c");
	public static final RegistryObject<LockdownBlock> METAL_SUBLEVEL = iron("metal_sublevel");

	public static final RegistryObject<LockdownBlock> GRATE = iron("grate");
	public static final RegistryObject<GlassBlock> BLAST_RESISTANT_GLASS = register("blast_resistant_glass", () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(0.5F, 1200F)));

	public static final RegistryObject<LockdownBlock> ARMORY_BOTTOM_A = stone("armory_bottom_a");
	public static final RegistryObject<LockdownBlock> ARMORY_TOP_A = stone("armory_top_a");
	public static final RegistryObject<LockdownBlock> ARMORY_BOTTOM_B = stone("armory_bottom_b");
	public static final RegistryObject<LockdownBlock> ARMORY_TOP_B = stone("armory_top_b");

	public static final RegistryObject<LockdownBlock> LABORATORY_BOTTOM_A = stone("laboratory_bottom_a");
	public static final RegistryObject<LockdownBlock> LABORATORY_TOP_A = stone("laboratory_top_a");
	public static final RegistryObject<LockdownBlock> LABORATORY_BOTTOM_B = stone("laboratory_bottom_b");
	public static final RegistryObject<LockdownBlock> LABORATORY_TOP_B = stone("laboratory_top_b");

	public static final RegistryObject<LockdownBlock> METAL_PANEL_A = iron("metal_panel_a");
	public static final RegistryObject<LockdownBlock> METAL_PANEL_B = iron("metal_panel_b");
	public static final RegistryObject<LockdownBlock> METAL_PANEL_C = iron("metal_panel_c");

	public static final RegistryObject<LockdownBlock> REINFORCED_GRANITE = stone("reinforced_granite");
	public static final RegistryObject<LockdownBlock> REINFORCED_QUARTZ = stone("reinforced_quartz");

	public static final RegistryObject<LockdownBlock> GREEN_PANEL_A = stone("green_panel_a");
	public static final RegistryObject<LockdownBlock> GREEN_PANEL_B = stone("green_panel_b");
	public static final RegistryObject<LockdownBlock> GREEN_PANEL_C = stone("green_panel_c");
	public static final RegistryObject<LockdownBlock> GREEN_PANEL_D = stone("green_panel_d");

	public static final RegistryObject<LockdownBlock> METAL_FLOOR_A = iron("metal_floor_a");
	public static final RegistryObject<LockdownBlock> METAL_FLOOR_B = iron("metal_floor_b");

	public static final RegistryObject<LockdownBlock> METAL_WALL_A = iron("metal_wall_a");
	public static final RegistryObject<LockdownBlock> METAL_WALL_B = iron("metal_wall_b");

	public static final RegistryObject<LockdownBlock> STEEL_FLOOR = iron("steel_floor");

	public static final RegistryObject<LockdownBlock> HAZARD = iron("hazard");

	public static final StairSlabWallTriple REINFORCED_IRON_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("reinforced_iron", TAB_BUILDING, SCPBlocks.REINFORCED_IRON);
	public static final StairSlabWallTriple STEEL_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("steel", TAB_BUILDING, SCPBlocks.STEEL);
	public static final StairSlabWallTriple SUBLEVEL_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("sublevel", TAB_BUILDING, SCPBlocks.SUBLEVEL_WALL_A);
	public static final StairSlabWallTriple REINFORCED_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("reinforced_sublevel", TAB_BUILDING, SCPBlocks.REINFORCED_SUBLEVEL_WALL_A);
	public static final StairSlabWallTriple METAL_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("metal_sublevel", TAB_BUILDING, SCPBlocks.METAL_SUBLEVEL);
	public static final StairSlabWallTriple METAL_WALL_A_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("metal_wall_a", TAB_BUILDING, SCPBlocks.METAL_WALL_A);

	public static final RegistryObject<StairBlock> WALL_STAIRS = stairsWithSameMaterial("wall_stairs", TAB_BUILDING, WALL_A);
	public static final RegistryObject<StairBlock> FLOOR_A_STAIRS = stairsWithSameMaterial("floor_a_stairs", TAB_BUILDING, FLOOR_A);
	public static final RegistryObject<StairBlock> FLOOR_B_STAIRS = stairsWithSameMaterial("floor_b_stairs", TAB_BUILDING, FLOOR_B);
	public static final RegistryObject<StairBlock> WHITE_STAIRS = stairsWithSameMaterial("white_stairs", TAB_BUILDING, WHITE_WALL);
	public static final RegistryObject<StairBlock> REINFORCED_WALL_A_STAIRS = stairsWithSameMaterial("reinforced_wall_a_stairs", TAB_BUILDING, REINFORCED_WALL_A);
	public static final RegistryObject<StairBlock> REINFORCED_WALL_B_STAIRS = stairsWithSameMaterial("reinforced_wall_b_stairs", TAB_BUILDING, REINFORCED_WALL_B);
	public static final RegistryObject<StairBlock> REINFORCED_WALL_C_STAIRS = stairsWithSameMaterial("reinforced_wall_c_stairs", TAB_BUILDING, REINFORCED_WALL_C);

	public static final RegistryObject<SlabBlock> WALL_SLAB = slabWithSameMaterial("wall_slab", TAB_BUILDING, WALL_A);
	public static final RegistryObject<SlabBlock> FLOOR_A_SLAB = slabWithSameMaterial("floor_a_slab", TAB_BUILDING, FLOOR_A);
	public static final RegistryObject<SlabBlock> FLOOR_B_SLAB = slabWithSameMaterial("floor_b_slab", TAB_BUILDING, FLOOR_B);
	public static final RegistryObject<SlabBlock> WHITE_SLAB = slabWithSameMaterial("white_slab", TAB_BUILDING, WHITE_WALL);
	public static final RegistryObject<SlabBlock> REINFORCED_WALL_A_SLAB = slabWithSameMaterial("reinforced_wall_a_slab", TAB_BUILDING, REINFORCED_WALL_A);
	public static final RegistryObject<SlabBlock> REINFORCED_WALL_B_SLAB = slabWithSameMaterial("reinforced_wall_b_slab", TAB_BUILDING, REINFORCED_WALL_B);
	public static final RegistryObject<SlabBlock> REINFORCED_WALL_C_SLAB = slabWithSameMaterial("reinforced_wall_c_slab", TAB_BUILDING, REINFORCED_WALL_C);

	public static final RegistryObject<WallBlock> REINFORCED_WALL_C_WALL = wallWithSameMaterial("reinforced_wall_c_wall", TAB_BUILDING, REINFORCED_WALL_C);
	public static final RegistryObject<WallBlock> EXTERIOR_BOTTOM_WALL = wallWithSameMaterial("exterior_bottom_wall", TAB_BUILDING, EXTERIOR_BOTTOM);
	public static final RegistryObject<WallBlock> EXTERIOR_MIDDLE_WALL = wallWithSameMaterial("exterior_middle_wall", TAB_BUILDING, EXTERIOR_MIDDLE);
	public static final RegistryObject<WallBlock> EXTERIOR_TOP_WALL = wallWithSameMaterial("exterior_top_wall", TAB_BUILDING, EXTERIOR_TOP);
	public static final RegistryObject<WallBlock> QUARTZ_WALL = wallWithSameMaterial("quartz_wall", TAB_BUILDING, Blocks.QUARTZ_BLOCK);
	public static final RegistryObject<WallBlock> PIPE_WALL_WALL = wallWithSameMaterial("pipe_wall_wall", TAB_BUILDING, PIPE_WALL);
	public static final RegistryObject<WallBlock> METAL_WALL_B_WALL = wallWithSameMaterial("metal_wall_b_wall", TAB_BUILDING, METAL_WALL_B);
	public static final RegistryObject<WallBlock> SMOOTH_QUARTZ_WALL = wallWithSameMaterial("smooth_quartz_wall", TAB_BUILDING, Blocks.SMOOTH_QUARTZ);

	public static final ColourObjectsRegistry<StraightPipeBlock> STRAIGHT_PIPES = registerColourBlocks("straight_pipe", TAB_BUILDING, () -> new StraightPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<CornerPipeBlock> CORNER_PIPES = registerColourBlocks("corner_pipe", null, () -> new CornerPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<Junc3PipeBlock> JUNC3_PIPES = registerColourBlocks("junc3_pipe", null, () -> new Junc3PipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<TJuncPipeBlock> TJUNC_PIPES = registerColourBlocks("tjunc_pipe", null, () -> new TJuncPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<Junc4XPipeBlock> JUNC4X_PIPES = registerColourBlocks("4xjunc_pipe", null, () -> new Junc4XPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<Junc4PipeBlock> JUNC4_PIPES = registerColourBlocks("4junc_pipe", null, () -> new Junc4PipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<Junc5PipeBlock> JUNC5_PIPES = registerColourBlocks("5junc_pipe", null, () -> new Junc5PipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	public static final ColourObjectsRegistry<Junc6PipeBlock> JUNC6_PIPES = registerColourBlocks("6junc_pipe", null, () -> new Junc6PipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

	//Vanilla Extended Building Blocks
	public static final RegistryObject<StairBlock> SMOOTH_STONE_STAIRS = stairsWithSameMaterial("smooth_stone_stairs", TAB_BUILDING, Blocks.SMOOTH_STONE);

	public static final StairSlabWallTriple WHITE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("white_terracotta", TAB_BUILDING, Blocks.WHITE_TERRACOTTA);
	public static final StairSlabWallTriple ORANGE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("orange_terracotta", TAB_BUILDING, Blocks.ORANGE_TERRACOTTA);
	public static final StairSlabWallTriple MAGENTA_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("magenta_terracotta", TAB_BUILDING, Blocks.MAGENTA_TERRACOTTA);
	public static final StairSlabWallTriple LIGHT_BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("light_blue_terracotta", TAB_BUILDING, Blocks.LIGHT_BLUE_TERRACOTTA);
	public static final StairSlabWallTriple YELLOW_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("yellow_terracotta", TAB_BUILDING, Blocks.YELLOW_TERRACOTTA);
	public static final StairSlabWallTriple LIME_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("lime_terracotta", TAB_BUILDING, Blocks.LIME_TERRACOTTA);
	public static final StairSlabWallTriple PINK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("pink_terracotta", TAB_BUILDING, Blocks.PINK_TERRACOTTA);
	public static final StairSlabWallTriple GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("gray_terracotta", TAB_BUILDING, Blocks.GRAY_TERRACOTTA);
	public static final StairSlabWallTriple LIGHT_GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("light_gray_terracotta", TAB_BUILDING, Blocks.GRAY_TERRACOTTA);
	public static final StairSlabWallTriple CYAN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("cyan_terracotta", TAB_BUILDING, Blocks.CYAN_TERRACOTTA);
	public static final StairSlabWallTriple PURPLE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("purple_terracotta", TAB_BUILDING, Blocks.PURPLE_TERRACOTTA);
	public static final StairSlabWallTriple BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("blue_terracotta", TAB_BUILDING, Blocks.BLUE_TERRACOTTA);
	public static final StairSlabWallTriple BROWN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("brown_terracotta", TAB_BUILDING, Blocks.BROWN_TERRACOTTA);
	public static final StairSlabWallTriple GREEN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("green_terracotta", TAB_BUILDING, Blocks.GREEN_TERRACOTTA);
	public static final StairSlabWallTriple RED_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("red_terracotta", TAB_BUILDING, Blocks.RED_TERRACOTTA);
	public static final StairSlabWallTriple BLACK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("black_terracotta", TAB_BUILDING, Blocks.BLACK_TERRACOTTA);
	public static final StairSlabWallTriple TERRACOTTA_STAIR_SLAB_WALL_TRIPLE = registerStairSlabWallWithSameMaterial("terracotta", TAB_BUILDING, Blocks.TERRACOTTA);

	public static final RegistryObject<WallBlock> STONE_WALL = wallWithSameMaterial("stone_wall", TAB_BUILDING, Blocks.STONE);
	public static final RegistryObject<WallBlock> POLISHED_GRANITE_WALL = wallWithSameMaterial("polished_granite_wall", TAB_BUILDING, Blocks.POLISHED_GRANITE);
	public static final RegistryObject<WallBlock> POLISHED_DIORITE_WALL = wallWithSameMaterial("polished_diorite_wall", TAB_BUILDING, Blocks.POLISHED_DIORITE);
	public static final RegistryObject<WallBlock> POLISHED_ANDESITE_WALL = wallWithSameMaterial("polished_andesite_wall", TAB_BUILDING, Blocks.POLISHED_ANDESITE);

	/**
	 * Used to create multiple registries that consist of the colours in SCPDefaultColors.
	 *
	 * @param baseName Nae of the Registry object, then appended with _[Colour]
	 * @param tab      The Creative tab to put these items
	 * @param <B>      Any type of Block
	 * @return A Hashmap of all the blocks and its corresponding colour. //TODO Possble better method to organise and get these
	 */
	private static <B extends Block> ColourObjectsRegistry<B> registerColourBlocks(String baseName, @Nullable RegistryObject<CreativeModeTab> tab, Supplier<B> block) {
		ArrayList<Pair<SCPDefaultColors, RegistryObject<B>>> list1 = new ArrayList<>();
		for (SCPDefaultColors color : SCPDefaultColors.values()) {
			list1.add(new ImmutablePair<>(color, register(baseName + "_" + color.colorName, tab, block)));
		} //TODO Possible re-do

		return new ColourObjectsRegistry<>(list1);
	}

	private static StairSlabWallTriple registerStairSlabWallWithSameMaterial(String baseName, @Nullable RegistryObject<CreativeModeTab> tab, RegistryObject<? extends Block> block) {
		RegistryObject<StairBlock> stairsRegistry = stairsWithSameMaterial(baseName + "_stairs", tab, block);
		RegistryObject<SlabBlock> slabRegistry = slabWithSameMaterial(baseName + "_slab", tab, block);
		RegistryObject<WallBlock> wallRegistry = wallWithSameMaterial(baseName + "_wall", tab, block);
		return new StairSlabWallTriple(stairsRegistry, slabRegistry, wallRegistry);
	}

	private static StairSlabWallTriple registerStairSlabWallWithSameMaterial(String baseName, @Nullable RegistryObject<CreativeModeTab> tab, Block block) {
		RegistryObject<StairBlock> stairsRegistry = stairsWithSameMaterial(baseName + "_stairs", tab, block);
		RegistryObject<SlabBlock> slabRegistry = slabWithSameMaterial(baseName + "_slab", tab, block);
		RegistryObject<WallBlock> wallRegistry = wallWithSameMaterial(baseName + "_wall", tab, block);
		return new StairSlabWallTriple(stairsRegistry, slabRegistry, wallRegistry);
	}

	private static RegistryObject<WallBlock> wallWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, Block block) {
		return register(name, tab, () -> new WallBlock(BlockBehaviour.Properties.copy(block)));
	}

	private static RegistryObject<WallBlock> wallWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, RegistryObject<? extends Block> block) {
		return register(name, tab, () -> new WallBlock(BlockBehaviour.Properties.copy(block.get())));
	}

	private static RegistryObject<SlabBlock> slabWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, RegistryObject<? extends Block> block) {
		return register(name, tab, () -> new SlabBlock(BlockBehaviour.Properties.copy(block.get())));
	}

	private static RegistryObject<SlabBlock> slabWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, Block block) {
		return register(name, tab, () -> new SlabBlock(BlockBehaviour.Properties.copy(block)));
	}

	private static RegistryObject<StairBlock> stairsWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, RegistryObject<? extends Block> block) {
		return register(name, tab, () -> new StairBlock(() -> block.get().defaultBlockState(), BlockBehaviour.Properties.copy(block.get())));
	}

	private static RegistryObject<StairBlock> stairsWithSameMaterial(String name, @Nullable RegistryObject<CreativeModeTab> tab, Block block) {
		return register(name, tab, () -> new StairBlock(block::defaultBlockState, BlockBehaviour.Properties.copy(block)));
	}

	private static <B extends Block> RegistryObject<B> stone(String name, @Nullable RegistryObject<CreativeModeTab> tab) {
		return (RegistryObject<B>) register(name, tab, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	}

	private static <B extends Block> RegistryObject<B> iron(String name, @Nullable RegistryObject<CreativeModeTab> tab) {
		return (RegistryObject<B>) register(name, tab, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	}

	private static <B extends Block> RegistryObject<B> stone(String name) {
		return (RegistryObject<B>) register(name, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
	}

	private static <B extends Block> RegistryObject<B> iron(String name) {
		return (RegistryObject<B>) register(name, () -> new LockdownBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
	}

	private static <B extends Block> RegistryObject<B> registerWithISTER(String name, @Nullable RegistryObject<CreativeModeTab> tab, Supplier<B> block) {
		RegistryObject<B> blockReg = registerNoItem(name, block);
		//SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p.setISTER(DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> () -> ItemModelRenderer::new)))).tab(tab).build();
		SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p)).build();

		return blockReg;
	}

	/**
	 * Registers Block and puts BlockItem in Building Creative Tab
	 */
	private static <B extends Block> RegistryObject<B> register(String name, Supplier<B> block) {
		RegistryObject<B> blockReg = registerNoItem(name, block);
		//SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p)).tab(TAB_BUILDING).build();
		SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p)).build();
		return blockReg;
	}

	/**
	 * Registers Block and puts BlockItem in passed through tab.
	 *
	 * @param tab Null meaning it will not have a creative tab but can still be given to player via commands (like command block)
	 */
	private static <B extends Block> RegistryObject<B> register(String name, @Nullable RegistryObject<CreativeModeTab> tab, Supplier<B> block) {
		RegistryObject<B> blockReg = registerNoItem(name, block);
		//SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p)).tab(tab).build();
		SCPItems.ITEMS.register(name, p -> new BlockItem(blockReg.get(), p)).build();
		return blockReg;
	}

	/**
	 * Registers a Block without an item
	 */
	public static <B extends Block> RegistryObject<B> registerNoItem(String name, Supplier<B> block) {
		return BLOCKS.register(name, block);
	}
}
