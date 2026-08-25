package io.github.connortron110.scplockdown.registration.builder;

import net.minecraft.world.flag.FeatureElement;

@FunctionalInterface
public interface Builder<T extends FeatureElement> {
	WrappedRegistryObject<T> build();
}
