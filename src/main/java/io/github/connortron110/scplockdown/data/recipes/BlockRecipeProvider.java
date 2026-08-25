package io.github.connortron110.scplockdown.data.recipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;
import static net.minecraft.advancements.critereon.ItemPredicate.Builder.item;

public class BlockRecipeProvider implements IRecipeHelperProvider {
	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SCPBlocks.CARD_READER.get())
				.define('I', Items.IRON_INGOT)
				.define('D', Items.REDSTONE)
				.define('T', Items.REDSTONE_TORCH)
				.define('A', Blocks.POLISHED_ANDESITE)
				.pattern("IIA")
				.pattern("IDT")
				.pattern("IIA")
				.unlockedBy("has_iron_ingot", hasItems(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SCPBlocks.HEAVY_BUTTON.get(), 3)
				.define('I', Items.IRON_INGOT)
				.define('G', Blocks.GREEN_STAINED_GLASS)
				.define('B', ItemTags.BUTTONS)
				.define('R', Blocks.RED_STAINED_GLASS)
				.pattern("IGI")
				.pattern("IBI")
				.pattern("IRI")
				.unlockedBy("has_iron_ingot", hasItems(Items.IRON_INGOT))
				.save(consumer);

		// TODO: 26/05/2023 Toilet

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.CHAIR.get())
				.define('C', Items.GRAY_CARPET)
				.define('S', Items.STICK)
				.define('L', ItemTags.LOGS)
				.pattern("C  ")
				.pattern("SLS")
				.pattern("S S")
				.unlockedBy("has_logs", hasItems(item().of(ItemTags.LOGS).build()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.OFFICE_CHAIR.get())
				.define('C', Items.LIME_CARPET)
				.define('S', Items.STICK)
				.define('L', ItemTags.LOGS)
				.pattern("C  ")
				.pattern("SLS")
				.pattern("S S")
				.unlockedBy("has_logs", hasItems(item().of(ItemTags.LOGS).build()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.TABLE.get(), 2)
				.define('Q', Items.QUARTZ_SLAB)
				.define('I', Items.IRON_INGOT)
				.pattern("QQQ")
				.pattern("I I")
				.pattern("I I")
				.unlockedBy("has_iron_ingot", hasItems(Items.IRON_INGOT))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, SCPBlocks.OPAQUE_TRAPDOOR.get(), 2)
				.requires(Items.IRON_TRAPDOOR)
				.requires(Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.unlockedBy("has_iron_trapdoor", hasItems(Items.IRON_TRAPDOOR))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.CEILING_LAMP.get())
				.define('S', Items.STRING)
				.define('C', Items.COBBLESTONE_SLAB)
				.define('G', Items.GLOWSTONE)
				.define('L', Items.REDSTONE_LAMP)
				.pattern(" S ")
				.pattern("CGC")
				.pattern(" L ")
				.unlockedBy("has_glowstone", hasItems(Items.GLOWSTONE))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, SCPBlocks.SMALL_LAMP.get())
				.requires(Items.GLOWSTONE_DUST)
				.requires(Items.STONE_BUTTON)
				.unlockedBy("has_glowstone_dust", hasItems(Items.GLOWSTONE_DUST))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.CEILING_LIGHT.get(), 4)
				.define('R', Items.REDSTONE)
				.define('D', Items.DAYLIGHT_DETECTOR)
				.define('L', Items.REDSTONE_LAMP)
				.pattern("R")
				.pattern("D")
				.pattern("L")
				.unlockedBy("has_redstone", hasItems(Items.REDSTONE))
				.save(consumer);

		//Assumed QOL recipe
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.CEILING.get(), 8)
				.define('S', Items.STONE)
				.define('I', Items.INK_SAC)
				.pattern("SSS")
				.pattern("SIS")
				.pattern("SSS")
				.unlockedBy("has_stone", hasItems(Items.STONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.CEILING_TILE.get(), 6)
				.define('W', Items.WHITE_WOOL)
				.define('S', Items.STONE_SLAB)
				.pattern("WWW")
				.pattern("SSS")
				.pattern("WWW")
				.unlockedBy("has_wool", hasItems(Items.WHITE_WOOL))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.CEILING_GRATE.get())
				.requires(SCPBlocks.CEILING_TILE.get())
				.requires(Items.IRON_BARS)
				.unlockedBy("has_ceiling_tile", hasItems(SCPBlocks.CEILING_TILE.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.CONTAINMENT_FLOOR.get())
				.define('Q', Items.QUARTZ_BLOCK)
				.pattern("QQ")
				.pattern("QQ")
				.unlockedBy("has_quartz_block", hasItems(Items.QUARTZ_BLOCK))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.FLOOR_A.get())
				.define('C', SCPBlocks.CONTAINMENT_FLOOR.get())
				.define('B', Items.BONE_MEAL)
				.pattern("CCC")
				.pattern("CBC")
				.pattern("CCC")
				.unlockedBy("has_containment_floor", hasItems(SCPBlocks.CONTAINMENT_FLOOR.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.FLOOR_B.get())
				.define('C', SCPBlocks.CONTAINMENT_FLOOR.get())
				.define('I', Items.INK_SAC)
				.pattern("CCC")
				.pattern("CIC")
				.pattern("CCC")
				.unlockedBy("has_containment_floor", hasItems(SCPBlocks.CONTAINMENT_FLOOR.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.MESH_FLOOR.get(), 4)
				.define('I', Items.IRON_BARS)
				.pattern("II")
				.pattern("II")
				.unlockedBy("has_iron_bars", hasItems(Items.IRON_BARS))
				.save(consumer);

		// TODO: 26/05/2023 Plaster Wall

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.VENT.get())
				.define('I', Items.IRON_BARS)
				.define('C', SCPBlocks.CEILING.get())
				.pattern("ICI")
				.unlockedBy("has_ceiling", hasItems(SCPBlocks.CEILING.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.VENTILATION.get(), 4)
				.define('I', Items.IRON_INGOT)
				.pattern("III")
				.pattern("I I")
				.pattern("III")
				.unlockedBy("has_iron_ingot", hasItems(Items.IRON_INGOT))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_A.get(), 8)
				.define('Q', Items.QUARTZ)
				.define('W', Items.WHITE_CONCRETE)
				.pattern("QQQ")
				.pattern("QWQ")
				.pattern("QQQ")
				.unlockedBy("has_quartz", hasItems(Items.QUARTZ))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_B.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('I', Items.INK_SAC)
				.pattern("AIA")
				.pattern("AAA")
				.pattern("AAA")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_C.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('G', Items.GRAY_DYE)
				.pattern("AAA")
				.pattern("AAA")
				.pattern("AGA")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_D.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('G', Items.GRAY_DYE)
				.pattern("AGA")
				.pattern("AAA")
				.pattern("AAA")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_E.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('I', Items.INK_SAC)
				.pattern("AAA")
				.pattern("AAA")
				.pattern("AIA")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_F.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('G', Items.GRAY_DYE)
				.define('I', Items.INK_SAC)
				.pattern("AGA")
				.pattern("IAI")
				.pattern("AAA")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		//Original has 7 instead of 8 as result
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WALL_G.get(), 8)
				.define('A', SCPBlocks.WALL_A.get())
				.define('I', Items.INK_SAC)
				.pattern("AAA")
				.pattern("AAA")
				.pattern("IAI")
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.WHITE_WALL.get())
				.requires(SCPBlocks.WALL_A.get())
				.requires(Items.BONE_MEAL)
				.unlockedBy("has_wall_a", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(SCPBlocks.WHITE_WALL.get()), RecipeCategory.BUILDING_BLOCKS, SCPBlocks.OLD_WHITE_WALL.get(), 10.0F, 200)
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WALL_A.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.REINFORCED_IRON.get())
				.define('I', Items.IRON_INGOT)
				.define('B', Items.IRON_BLOCK)
				.pattern("III")
				.pattern("IBI")
				.pattern("III")
				.unlockedBy("has_iron_ingot", hasItems(Items.IRON_INGOT))
				.save(consumer);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(SCPBlocks.REINFORCED_IRON.get()), RecipeCategory.BUILDING_BLOCKS, SCPBlocks.STEEL.get(), 20.0F, 200)
				.unlockedBy("has_reinforced_iron", hasItems(SCPBlocks.REINFORCED_IRON.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.REINFORCED_WALL_A.get(), 5)
				.define('A', SCPBlocks.WALL_A.get())
				.define('R', SCPBlocks.REINFORCED_IRON.get())
				.pattern(" A ")
				.pattern("ARA")
				.pattern(" A ")
				.unlockedBy("has_reinforced_iron", hasItems(SCPBlocks.REINFORCED_IRON.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.REINFORCED_WALL_B.get(), 5)
				.define('B', SCPBlocks.WALL_B.get())
				.define('R', SCPBlocks.REINFORCED_IRON.get())
				.pattern(" B ")
				.pattern("BRB")
				.pattern(" B ")
				.unlockedBy("has_reinforced_iron", hasItems(SCPBlocks.REINFORCED_IRON.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.REINFORCED_WALL_C.get(), 5)
				.define('C', SCPBlocks.WALL_C.get())
				.define('R', SCPBlocks.REINFORCED_IRON.get())
				.pattern(" C ")
				.pattern("CRC")
				.pattern(" C ")
				.unlockedBy("has_reinforced_iron", hasItems(SCPBlocks.REINFORCED_IRON.get()))
				.save(consumer);

		//Inconsistencies in amount with exterior bottom recipe (From 7 to 8)
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.EXTERIOR_BOTTOM.get(), 8)
				.define('A', Items.POLISHED_ANDESITE)
				.define('I', Items.INK_SAC)
				.define('G', Items.GRAY_DYE)
				.pattern("AIA")
				.pattern("AAA")
				.pattern("AGA")
				.unlockedBy("has_polished_andesite", hasItems(Items.POLISHED_ANDESITE))
				.save(consumer);

		//Inconsistencies in amount with exterior bottom recipe (From 7 to 8)
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.EXTERIOR_MIDDLE.get(), 8)
				.define('S', Items.STONE_BRICKS)
				.define('B', Items.BONE_MEAL)
				.define('I', Items.INK_SAC)
				.pattern("SBS")
				.pattern("SSS")
				.pattern("SIS")
				.unlockedBy("has_stone_bricks", hasItems(Items.STONE_BRICKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.EXTERIOR_TOP.get(), 8)
				.define('S', Items.STONE_BRICKS)
				.define('B', Items.BONE_MEAL)
				.pattern("SBS")
				.pattern("SSS")
				.pattern("SSS")
				.unlockedBy("has_stone_bricks", hasItems(Items.STONE_BRICKS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.MEDICAL_BOTTOM.get(), 8)
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('G', Items.GREEN_DYE)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("WGW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.MEDICAL_TOP.get(), 8)
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('G', Items.GREEN_DYE)
				.pattern("WGW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.OFFICE_BOTTOM.get(), 8)
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('O', Items.ORANGE_DYE)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("WOW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.OFFICE_TOP.get(), 8)
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('O', Items.ORANGE_DYE)
				.pattern("WOW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		// TODO: 26/05/2023 Pipe Wall

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.SUBLEVEL_WALL_A.get())
				.define('G', Items.ORANGE_DYE)
				.define('S', Items.STONE_BRICKS)
				.pattern("G")
				.pattern("S")
				.unlockedBy("has_stone_bricks", hasItems(Items.STONE_BRICKS))
				.save(consumer);

		// TODO: 26/05/2023 Sublevel Wall B
		// TODO: 26/05/2023 Sublevel Wall C
		// TODO: 26/05/2023 Reinforced Sublevel Wall A
		// TODO: 26/05/2023 Reinforced Sublevel Wall B
		// TODO: 26/05/2023 Reinforced Sublevel Wall C

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.GRATE.get())
				.define('I', Items.IRON_NUGGET)
				.define('B', Items.IRON_BARS)
				.pattern("III")
				.pattern("IBI")
				.pattern("III")
				.unlockedBy("has_iron_bars", hasItems(Items.IRON_BARS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, SCPBlocks.BLAST_RESISTANT_GLASS.get(), 4)
				.define('G', Items.GLASS)
				.define('O', Items.OBSIDIAN)
				.pattern("OGO")
				.pattern("GGG")
				.pattern("OGO")
				.unlockedBy("has_glass", hasItems(Items.GLASS))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.ARMORY_BOTTOM_A.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('R', Items.RED_DYE)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("WRW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.ARMORY_TOP_A.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('R', Items.RED_DYE)
				.pattern("WRW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.ARMORY_BOTTOM_B.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('R', Items.RED_DYE)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("RWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.ARMORY_TOP_B.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('R', Items.RED_DYE)
				.pattern("RWW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.LABORATORY_BOTTOM_A.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('L', Items.LAPIS_LAZULI)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("WLW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.LABORATORY_TOP_A.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('L', Items.LAPIS_LAZULI)
				.pattern("WLW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.LABORATORY_BOTTOM_B.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('L', Items.LAPIS_LAZULI)
				.pattern("WWW")
				.pattern("WWW")
				.pattern("LWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.LABORATORY_TOP_B.get())
				.define('W', SCPBlocks.WHITE_WALL.get())
				.define('L', Items.LAPIS_LAZULI)
				.pattern("LWW")
				.pattern("WWW")
				.pattern("WWW")
				.unlockedBy("has_white_wall", hasItems(SCPBlocks.WHITE_WALL.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.METAL_PANEL_A.get())
				.define('R', SCPBlocks.REINFORCED_IRON.get())
				.define('I', Items.IRON_NUGGET)
				.pattern("IRI")
				.pattern("RIR")
				.pattern("IRI")
				.unlockedBy("has_reinforced_iron", hasItems(SCPBlocks.REINFORCED_IRON.get()))
				.save(consumer);

		// TODO: 26/05/2023 Metal Panel B
		// TODO: 26/05/2023 Metal Panel C

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.METAL_SUBLEVEL.get())
				.define('S', SCPBlocks.SUBLEVEL_WALL_A.get())
				.define('I', Items.IRON_NUGGET)
				.pattern("ISI")
				.pattern("SIS")
				.pattern("ISI")
				.unlockedBy("has_sublevel_wall_a", hasItems(SCPBlocks.SUBLEVEL_WALL_A.get()))
				.save(consumer);

		// TODO: 26/05/2023 Reinforced Granite
		// TODO: 26/05/2023 Reinforced Quartz
		// TODO: 26/05/2023 Green Panel A
		// TODO: 26/05/2023 Green Panel B
		// TODO: 26/05/2023 Green Panel C
		// TODO: 26/05/2023 Green Panel D
		// TODO: 26/05/2023 Metal Floor A
		// TODO: 26/05/2023 Metal Floor B
		// TODO: 26/05/2023 Metal Wall A
		// TODO: 26/05/2023 Metal Wall B
		// TODO: 26/05/2023 Steel Floor

		//stairs(SCPBlocks.SUBLEVEL_WALL_A.get(), SCPBlocks.SUBLEVEL_STAIRS.get(), 4).save(consumer);
		stairs(SCPBlocks.WALL_A.get(), SCPBlocks.WALL_STAIRS.get(), 4).save(consumer); //Assumed recipe
		stairs(SCPBlocks.FLOOR_A.get(), SCPBlocks.FLOOR_A_STAIRS.get(), 4).save(consumer);
		stairs(SCPBlocks.FLOOR_B.get(), SCPBlocks.FLOOR_B_STAIRS.get(), 4).save(consumer);
		stairs(SCPBlocks.WHITE_WALL.get(), SCPBlocks.WHITE_STAIRS.get(), 4).save(consumer); //Assumed recipe
		stairs(SCPBlocks.REINFORCED_WALL_A.get(), SCPBlocks.REINFORCED_WALL_A_STAIRS.get(), 4).save(consumer); //Assumed recipe
		stairs(SCPBlocks.REINFORCED_WALL_B.get(), SCPBlocks.REINFORCED_WALL_B_STAIRS.get(), 4).save(consumer); //Assumed recipe
		stairs(SCPBlocks.REINFORCED_WALL_C.get(), SCPBlocks.REINFORCED_WALL_C_STAIRS.get(), 4).save(consumer); //Assumed recipe

		//All Slabs are assumed recipes
		//slab(SCPBlocks.SUBLEVEL_WALL_A.get(), SCPBlocks.SUBLEVEL_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.WALL_A.get(), SCPBlocks.WALL_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.FLOOR_A.get(), SCPBlocks.FLOOR_A_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.FLOOR_B.get(), SCPBlocks.FLOOR_B_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.WHITE_WALL.get(), SCPBlocks.WHITE_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.REINFORCED_WALL_A.get(), SCPBlocks.REINFORCED_WALL_A_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.REINFORCED_WALL_B.get(), SCPBlocks.REINFORCED_WALL_B_SLAB.get(), 6).save(consumer);
		slab(SCPBlocks.REINFORCED_WALL_C.get(), SCPBlocks.REINFORCED_WALL_C_SLAB.get(), 6).save(consumer);

		// TODO: 29/05/2023 All Wall recipes

		// TODO: 26/05/2023 All Straight Pipe Recipes
		// TODO: 26/05/2023 Wood Crate
		// TODO: 26/05/2023 Dark Wood Crate

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCPBlocks.PERSONAL_COMPUTER.get())
				.define('I', Items.IRON_BLOCK)
				.define('G', Items.BLACK_STAINED_GLASS_PANE)
				.define('A', Items.POLISHED_ANDESITE)
				.define('R', Items.REDSTONE)
				.pattern("III")
				.pattern("IGI")
				.pattern("ARA")
				.unlockedBy("has_redstone", hasItems(Items.REDSTONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCPBlocks.COMPUTER.get())
				.define('P', SCPBlocks.PERSONAL_COMPUTER.get())
				.define('A', Items.POLISHED_ANDESITE)
				.pattern("P")
				.pattern("A")
				.unlockedBy("has_personal_computer", hasItems(SCPBlocks.PERSONAL_COMPUTER.get()))
				.save(consumer);

		// TODO: 26/05/2023 Locker Recipes

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SCPBlocks.CONTAINMENT_DOOR.get())
				.define('I', SCPBlocks.REINFORCED_IRON.get())
				.define('R', Items.REDSTONE)
				.pattern("II")
				.pattern("IR")
				.pattern("II")
				.unlockedBy("has_redstone", hasItems(Items.REDSTONE))
				.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, SCPBlocks.SLIDING_DOOR.get())
				.define('I', Items.IRON_INGOT)
				.define('R', Items.REDSTONE)
				.pattern("II")
				.pattern("IR")
				.pattern("II")
				.unlockedBy("has_redstone", hasItems(Items.REDSTONE))
				.save(consumer);

		// TODO: 26/05/2023 Magnetised Door (Magnetized Block not added)
	}

	private ShapedRecipeBuilder slab(Block block, Block result, int amount) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, amount)
				.define('B', block)
				.pattern("BBB")
				.unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(block).getPath(), hasItems(block));
	}

	private ShapedRecipeBuilder stairs(Block block, Block result, int amount) {
		return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, amount)
				.define('B', block)
				.pattern("B  ")
				.pattern("BB ")
				.pattern("BBB")
				.unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey(block).getPath(), hasItems(block));
	}
}
