package io.github.connortron110.scplockdown.registration.builder;

import net.minecraft.world.flag.FeatureElement;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class WrappedRegistryObject<T extends FeatureElement> implements Supplier<T> {

	protected final RegistryObject<T> registryObject;

	public WrappedRegistryObject(RegistryObject<T> object) {
		registryObject = object;
	}

	@Override
	public T get() {
		return registryObject.get();
	}
}
