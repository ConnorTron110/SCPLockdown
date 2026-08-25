package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.crafting.SCP914Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SCPRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SCPLockdown.MOD_ID);

	public static final RegistryObject<RecipeSerializer<SCP914Recipe>> SCP914_REFINING_SERIALIZER = RECIPE_SERIALIZERS.register("scp914_refining", () -> SCP914Recipe.Serializer.INSTANCE);
}
