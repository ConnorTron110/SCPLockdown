package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Predicate;

public enum SCP008IllagerEnumVariants implements EntityEnumVariants, VanillaToVariant<SCP008IllagerEnumVariants> {
	PILLAGER("pillager", entity -> entity.getType().equals(EntityType.PILLAGER)),
	VINDICATOR("vindicator", entity -> entity.getType().equals(EntityType.VINDICATOR)),
	EVOKER("evoker", entity -> entity.getType().equals(EntityType.EVOKER)),
	ILLUSIONER("illusioner", entity -> entity.getType().equals(EntityType.ILLUSIONER));

	private final ResourceLocation textureLocation;
	private final Predicate<Entity> entityTest;

	SCP008IllagerEnumVariants(String id, Predicate<Entity> entityTest) {
		this.textureLocation = makeLocation(id);
		this.entityTest = entityTest;
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String type) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/008/" + type + ".png");
	}

	@Nullable
	@Override
	public SCP008IllagerEnumVariants getVariantFromEntity(Entity entity) {
		return Arrays.stream(values()).filter(value -> value.entityTest.test(entity)).findFirst().orElse(null);
	}
}
