package io.github.connortron110.scplockdown.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;


/**
 * Used by recipe provides for this mod, {@link LockdownRecipeProvider} searches for any classes that use this interface and invokes its recipe building call. <br>
 * Also gives helper methods to classes to allow for utilizing common methods and or patterns in recipes
 */
public interface IRecipeHelperProvider {
	void buildRecipes(Consumer<FinishedRecipe> consumer);
}
