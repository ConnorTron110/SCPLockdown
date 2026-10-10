package io.github.connortron110.scplockdown.level.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.apache.commons.compress.utils.Lists;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Individual recipe for SCP-914
 */
public class SCP914Recipe implements Recipe<RecipeWrapper> {
	/**
	 * Input items for this recipe
	 */
	private final NonNullList<Ingredient> inputItems;
	/**
	 * What this recipe outputs
	 */
	private final ItemStack output;
	/**
	 * Setting required on SCP-914 to craft this recipe
	 */
	private final SCP914BlockEntity.RefiningSetting setting;
	/**
	 * Time in ticks it takes for SCP-914 to craft this recipe
	 */
	private final int craftingTime;
	/**
	 * ID of this recipe
	 */
	private final ResourceLocation id;

	public SCP914Recipe(NonNullList<Ingredient> inputItems, ItemStack outputItem, SCP914BlockEntity.RefiningSetting setting, int craftingTime, ResourceLocation id) {
		this.inputItems = inputItems;
		this.output = outputItem;
		this.setting = setting;
		this.craftingTime = craftingTime;
		this.id = id;
	}

	/**
	 * Does the container contents match this recipe (Only tests item requirements)
	 */
	@Override
	public boolean matches(RecipeWrapper pContainer, Level pLevel) {
		if (pLevel.isClientSide) return false;	//	Return on client to allow server to overrule recipes

		//	Create a pseudo container to remove items once we tested them
		List<ItemStack> containerCopy = Lists.newArrayList();
		for (int i = 0; i < pContainer.getContainerSize(); i++) {
			containerCopy.add(pContainer.getItem(i).copy());
		}

		//	Test each ingredient and remove from copy if successful
		for (Ingredient inputItem : inputItems) {
			boolean isIngredientSatisfied = false;

			//	Try to find a matching item in the container copy
			for (ItemStack containerStack : containerCopy) {
				if (containerStack.isEmpty()) continue;

				//	Does the current item match this ingredient?
				if (inputItem.test(containerStack)) {
					isIngredientSatisfied = true;

					//	Currently ingredients only support one value at a time, so only shrink by one
					containerStack.shrink(1);
					break;
				}
			}

			if (!isIngredientSatisfied) return false;
		}

		return true;
	}

	public SCP914BlockEntity.RefiningSetting getSetting() {
		return this.setting;
	}

	public int getCraftingTime() {
		return this.craftingTime;
	}

	@Override
	public ItemStack assemble(RecipeWrapper pContainer, RegistryAccess pRegistryAccess) {
		return this.output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
		return this.output.copy();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.inputItems;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<SCP914Recipe> {
		public static final Type INSTANCE = new Type();
	}

	/**
	 * Each JSON associated to this ID gets fed into here to create new Recipe Instances for each JSON file
	 */
	public static class Serializer implements RecipeSerializer<SCP914Recipe> {
		public static final Serializer INSTANCE = new Serializer();

		/**
		 * Each JSON file gets read here to be created into a recipe
		 */
		@Override
		public SCP914Recipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

			//	Setting refine_setting
			SCP914BlockEntity.RefiningSetting setting = SCP914BlockEntity.RefiningSetting.fromString(GsonHelper.getAsString(pSerializedRecipe, "refine_setting"));

			//	Time refine_time
			int craftingTicks = GsonHelper.getAsInt(pSerializedRecipe, "refine_time");

			JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(ingredients.size(), Ingredient.EMPTY);
			for (int i = 0; i < inputs.size(); i++) {
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
			}

			ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

			return new SCP914Recipe(inputs, output, setting, craftingTicks, pRecipeId);
		}

		@Nullable
		@Override
		public SCP914Recipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
			SCP914BlockEntity.RefiningSetting setting = pBuffer.readEnum(SCP914BlockEntity.RefiningSetting.class);

			int craftingTime = pBuffer.readInt();

			ItemStack output = pBuffer.readItem();

			NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			inputs.replaceAll(ignored -> Ingredient.fromNetwork(pBuffer));

			return new SCP914Recipe(inputs, output, setting, craftingTime, pRecipeId);
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, SCP914Recipe pRecipe) {
			pBuffer.writeEnum(pRecipe.setting);

			pBuffer.writeInt(pRecipe.craftingTime);

			pBuffer.writeItemStack(pRecipe.output, false);

			pBuffer.writeInt(pRecipe.getIngredients().size());
			for (Ingredient ingredient : pRecipe.getIngredients()) {
				ingredient.toNetwork(pBuffer);
			}
		}
	}
}
