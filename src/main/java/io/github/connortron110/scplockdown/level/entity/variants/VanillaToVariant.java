package io.github.connortron110.scplockdown.level.entity.variants;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public interface VanillaToVariant<E extends Enum<?>> {
	@Nullable
	E getVariantFromEntity(Entity entity);
}
