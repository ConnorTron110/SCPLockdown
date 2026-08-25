package io.github.connortron110.scplockdown.data.recipes;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LockdownRecipeProvider extends RecipeProvider implements IConditionBuilder {

	private static final ImmutableList<RegistryObject<?>> EXCLUDED_OBJECTS = new ImmutableList.Builder<RegistryObject<?>>()
			.addAll(SCPBlocks.CORNER_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.JUNC3_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.TJUNC_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.JUNC4X_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.JUNC4_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.JUNC5_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.addAll(SCPBlocks.JUNC6_PIPES.getPairs().stream().map(ColourObjectsRegistry.ColorObjectPair::getRegistryObject).collect(Collectors.toList()))
			.build();

	private static final ImmutableList<IRecipeHelperProvider> PROVIDERS = new ImmutableList.Builder<IRecipeHelperProvider>().add(
			new BlockRecipeProvider(),
			new SCPRecipeProvider()
	).build();

	private static final Marker MARKER = MarkerFactory.getMarker("LockdownRecipeProvider");

	public LockdownRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	/**
	 * Runs the actual generation of recipes, and also checks for any missing recipes
	 */
	@Nonnull
	@Override
	public CompletableFuture<?> run(@Nonnull CachedOutput pOutput) {
		//  Check for any missing recipes and report
		SCPLockdown.LOGGER.info(MARKER, "Generating Recipes and report missing");

		//  Collect all items and Blocks
		Set<ResourceLocation> existingBlocksAndItems = new HashSet<>();
		existingBlocksAndItems.addAll(SCPItems.ITEMS.getRegister().getEntries().stream().map(RegistryObject::getId).toList());
		existingBlocksAndItems.addAll(SCPBlocks.BLOCKS.getEntries().stream().map(RegistryObject::getId).toList());

		//  Remove excluded items from the
		EXCLUDED_OBJECTS.forEach(registryObject -> existingBlocksAndItems.remove(registryObject.getId()));

		//  Taken from super method
		Set<ResourceLocation> genRecipeIDs = Sets.newHashSet();
		List<CompletableFuture<?>> generatedData = new ArrayList<>();
		this.buildRecipes((finishedRecipe) -> {

			//  Remove the Generated Recipe
			existingBlocksAndItems.remove(finishedRecipe.getId());

			if (!genRecipeIDs.add(finishedRecipe.getId())) {
				throw new IllegalStateException("Duplicate recipe " + finishedRecipe.getId());
			} else {
				generatedData.add(DataProvider.saveStable(pOutput, finishedRecipe.serializeRecipe(), this.recipePathProvider.json(finishedRecipe.getId())));
				JsonObject jsonobject = finishedRecipe.serializeAdvancement();
				if (jsonobject != null) {
					var saveAdvancementFuture = saveAdvancement(pOutput, finishedRecipe, jsonobject);
					if (saveAdvancementFuture != null)
						generatedData.add(saveAdvancementFuture);
				}
			}
		});

		//  Report individual and total count of recipes missing
		AtomicInteger missing = new AtomicInteger();
		existingBlocksAndItems.forEach(id -> {
			missing.getAndIncrement();
			SCPLockdown.LOGGER.warn(MARKER, "Missing Recipe for {}", id);
		});

		if (missing.get() > 0) {
			SCPLockdown.LOGGER.warn(MARKER, "Missing {} total amount of Recipes", missing);
		}

		return CompletableFuture.allOf(generatedData.toArray(CompletableFuture[]::new));
	}

	@Override
	protected void buildRecipes(@Nonnull Consumer<FinishedRecipe> pWriter) {
		PROVIDERS.forEach(provider -> provider.buildRecipes(pWriter));
	}
}

