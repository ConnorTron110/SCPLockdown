package io.github.connortron110.scplockdown.level.crafting;

import com.google.gson.JsonObject;
import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.List;

public class SCP914Recipe implements Recipe<RecipeWrapper> {
	private final NonNullList<Ingredient> inputItems;
	private final ItemStack output;
	private final ResourceLocation id;

	public SCP914Recipe(NonNullList<Ingredient> inputItems, ItemStack outputItem, ResourceLocation id) {
		this.inputItems = inputItems;
		this.output = outputItem;
		this.id = id;
	}

	/**
	 * Does the container contents match this recipe
	 */
	@Override
	public boolean matches(RecipeWrapper pContainer, Level pLevel) {
		if (pLevel.isClientSide) return false;  //  Return on client to allow server to overrule recipes

		return false;
	}

	@Override
	public ItemStack assemble(RecipeWrapper pContainer, RegistryAccess pRegistryAccess) {
		return this.output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return false;
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
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	@Override
	public RecipeType<?> getType() {
		return null;
	}

	public static class Type implements RecipeType<SCP914Recipe> {
		public static final Type INSTANCE = new Type();
		public static final String ID = "scp914";
	}

	/**
	 * Each JSON associated to this ID gets fed into here to create new Recipe Instances for each JSON file
	 */
	public static class Serializer implements RecipeSerializer<SCP914Recipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp914");

		/**
		 * Each JSON file gets read here to be created into a recipe
		 */
		@Override
		public SCP914Recipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
			return null;
		}

		@Nullable
		@Override
		public SCP914Recipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
			return null;
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, SCP914Recipe pRecipe) {

		}
	}
}
