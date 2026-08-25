package io.github.connortron110.scplockdown.data.recipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

import static net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance.hasItems;

/**
 * All recipes related to an SCP and its blocks/items
 */
public class SCPRecipeProvider implements IRecipeHelperProvider {
	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		scp143Recipes(consumer);
		scp148Recipes(consumer);
	}

	// TODO: 26/05/2023 Redo this section

	private void scp143Recipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, SCPBlocks.SCP143_PLANKS.get(), 4)  //  Planks Recipe
				.requires(SCPBlocks.SCP143_LOG.get()).unlockedBy("has_tree_trunk", hasItems(SCPBlocks.SCP143_LOG.get())).save(consumer);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(SCPBlocks.SCP143_LOG.get()), //Processed Log Smelting
				RecipeCategory.MISC, SCPItems.SCP143_PROCESSED_LOG, 0, 100).unlockedBy("has_tree_trunk", hasItems(SCPBlocks.SCP143_LOG.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCPItems.SCP143_INGOT)  //  Sturdy Material Recipe
				.define('P', SCPItems.SCP143_PETALS).define('L', SCPItems.SCP143_PROCESSED_LOG)
				.pattern("PPP")
				.pattern("PLP")
				.pattern("PPP").unlockedBy("has_petals", hasItems(SCPItems.SCP143_PETALS)).save(consumer);

		createSwordRecipe(SCPItems.SCP143_SWORD, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);     //  Sturdy SWORD
		createAxeRecipe(SCPItems.SCP143_AXE, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);         //  Sturdy AXE
		createPickaxeRecipe(SCPItems.SCP143_PICKAXE, SCPItems.SCP143_INGOT, "has_143_ingot", consumer); //  Sturdy PICKAXE
		createShovelRecipe(SCPItems.SCP143_SHOVEL, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);   //  Sturdy SHOVEL
		createHoeRecipe(SCPItems.SCP143_HOE, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);         //  Sturdy HOE

		createHelmetRecipe(SCPItems.SCP143_HELMET, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);           //  Sturdy Helmet
		createChestplateRecipe(SCPItems.SCP143_CHESTPLATE, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);   //  Sturdy Chestplate
		createLeggingsRecipe(SCPItems.SCP143_LEGGINGS, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);       //  Sturdy Leggings
		createBootsRecipe(SCPItems.SCP143_BOOTS, SCPItems.SCP143_INGOT, "has_143_ingot", consumer);             //  Sturdy Boots
	}

	private void scp148Recipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCPBlocks.SCP148_BLOCK.get()) //  Ingot to Block Recipe
				.requires(SCPItems.SCP148_INGOT.get(), 9).unlockedBy("has_telekill_ingot", hasItems(SCPItems.SCP148_INGOT.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCPItems.SCP148_INGOT.get(), 9)    //  Block to Ingot Recipe
				.requires(SCPBlocks.SCP148_BLOCK.get()).unlockedBy("has_telekill_block", hasItems(SCPBlocks.SCP148_BLOCK.get())).save(consumer);

		createSwordRecipe(SCPItems.SCP148_SWORD, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);        //  Telekill SWORD
		createAxeRecipe(SCPItems.SCP148_AXE, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);            //  Telekill AXE
		createPickaxeRecipe(SCPItems.SCP148_PICKAXE, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);    //  Telekill PICKAXE
		createShovelRecipe(SCPItems.SCP148_SHOVEL, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);      //  Telekill SHOVEL
		createHoeRecipe(SCPItems.SCP148_HOE, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);            //  Telekill HOE

		createHelmetRecipe(SCPItems.SCP148_HELMET, SCPItems.SCP148_INGOT, "has_telekill_ingot", consumer);      //  Telekill Helmet
	}


	private void createSwordRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('I', ingredient).define('S', Items.STICK).pattern("I").pattern("I").pattern("S").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createAxeRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('I', ingredient).define('S', Items.STICK).pattern("II").pattern("SI").pattern("S ").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createPickaxeRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('I', ingredient).define('S', Items.STICK).pattern("III").pattern(" S ").pattern(" S ").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createShovelRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('I', ingredient).define('S', Items.STICK).pattern("II").pattern("S ").pattern("S ").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createHoeRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result).define('I', ingredient).define('S', Items.STICK).pattern("I").pattern("S").pattern("S").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createHelmetRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('I', ingredient).pattern("III").pattern("I I").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createChestplateRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('I', ingredient).pattern("I I").pattern("III").pattern("III").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createLeggingsRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('I', ingredient).pattern("III").pattern("I I").pattern("I I").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}

	private void createBootsRecipe(ItemLike result, ItemLike ingredient, String unlockedBy, Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result).define('I', ingredient).pattern("I I").pattern("I I").unlockedBy(unlockedBy, hasItems(ingredient)).save(consumer);
	}
}
