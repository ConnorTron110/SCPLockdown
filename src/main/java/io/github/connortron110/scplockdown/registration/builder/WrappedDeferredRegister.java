package io.github.connortron110.scplockdown.registration.builder;

import net.minecraft.world.flag.FeatureElement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

public abstract class WrappedDeferredRegister<T extends FeatureElement> {
	protected final DeferredRegister<T> register;
	private final String modId;

	public WrappedDeferredRegister(IForgeRegistry<T> registry, String modId) {
		this.register = DeferredRegister.create(registry, modId);
		this.modId = modId;
	}

	public DeferredRegister<T> getRegister() {
		return register;
	}

	public void register(IEventBus bus) {
		this.register.register(bus);
	}
}
