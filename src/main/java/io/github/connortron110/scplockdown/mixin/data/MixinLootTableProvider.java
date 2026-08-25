package io.github.connortron110.scplockdown.mixin.data;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaEntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataResolver;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Allows for our loot tables to have references to vanilla loot tables <br>
 * Currently only supports Entity loot tables but can and most-likely will be expanded upon
 */
@Mixin(LootTableProvider.class)
public class MixinLootTableProvider {

	@Unique
	private static final Marker SCP_Lockdown$MARKER = MarkerFactory.getMarker("MixinLootTableProvider");
	@Unique
	private static final VanillaEntityLoot SCP_Lockdown$VANILLA_ENTITY_LOOTTABLES = new VanillaEntityLoot() {
	};


	@ModifyArg(
			method = "run",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/storage/loot/ValidationContext;<init>" +
							"(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;" +
							"Lnet/minecraft/world/level/storage/loot/LootDataResolver;)V"),
			index = 1)
	private LootDataResolver modifyLootDataResolver(LootDataResolver resolver, @Local(ordinal = 0) final Map<ResourceLocation, LootTable> map) {
		return new LootDataResolver() {
			@Nullable
			public <T> T getElement(LootDataId<T> id) {
				T table = (T) (id.type() == LootDataType.TABLE ? map.get(id.location()) : null);

				//  If the reference is invalid, and we are trying to get an element from a minecraft table
				if (table == null && id.location().getNamespace().equals("minecraft")) {

					//  Generate Vanilla Loot tables if they are empty
					if (SCP_Lockdown$VANILLA_ENTITY_LOOTTABLES.map.isEmpty()) {
						SCP_Lockdown$VANILLA_ENTITY_LOOTTABLES.generate();
					}

					SCPLockdown.LOGGER.debug(SCP_Lockdown$MARKER, "Added \"unsafe\" reference call to entity table {}", id.location());

					for (Map.Entry<EntityType<?>, Map<ResourceLocation, LootTable.Builder>> value : SCP_Lockdown$VANILLA_ENTITY_LOOTTABLES.map.entrySet()) {
						if (value.getValue().containsKey(id.location())) {
							//  Retry get on vanilla generated data
							table = (T) value.getValue().get(id.location()).build();
						}
					}
				}

				return table;
			}
		};
	}
}
