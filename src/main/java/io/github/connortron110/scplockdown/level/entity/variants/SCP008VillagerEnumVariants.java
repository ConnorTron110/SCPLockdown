package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerType;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Predicate;

public enum SCP008VillagerEnumVariants implements EntityEnumVariants, VanillaToVariant<SCP008VillagerEnumVariants> {
	TAIGA("taiga", entity -> testVillagerForType(entity, VillagerType.TAIGA)),
	SWAMP("swamp", entity -> testVillagerForType(entity, VillagerType.SWAMP)),
	SNOW("snow", entity -> testVillagerForType(entity, VillagerType.SNOW)),
	SAVANNA("savanna", entity -> testVillagerForType(entity, VillagerType.SAVANNA)),
	PLAINS("plains", entity -> testVillagerForType(entity, VillagerType.PLAINS)),
	JUNGLE("jungle", entity -> testVillagerForType(entity, VillagerType.JUNGLE)),
	DESERT("desert", entity -> testVillagerForType(entity, VillagerType.DESERT));

	private final ResourceLocation textureLocation;
	private final Predicate<Entity> entityTest;

	SCP008VillagerEnumVariants(String id, Predicate<Entity> entityTest) {
		this.textureLocation = makeLocation(id);
		this.entityTest = entityTest;
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String type) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/008/villager/" + type + ".png");
	}

	private static boolean testVillagerForType(Entity entity, VillagerType type) {
		return entity.getType().equals(EntityType.VILLAGER) && ((Villager) entity).getVillagerData().getType().equals(type);
	}

	@Nullable
	@Override
	public SCP008VillagerEnumVariants getVariantFromEntity(Entity entity) {
		return Arrays.stream(values()).filter(value -> value.entityTest.test(entity)).findFirst().orElse(null);
	}
}
