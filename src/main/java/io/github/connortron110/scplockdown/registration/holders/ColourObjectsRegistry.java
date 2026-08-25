package io.github.connortron110.scplockdown.registration.holders;

import com.google.common.collect.ImmutableSet;
import io.github.connortron110.scplockdown.utils.SCPDefaultColors;
import net.minecraft.world.flag.FeatureElement;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * This class acts as a list to contain registries and their respective colours.
 * This class's purpose is to hold all blocks that relate to SCPDefaultColours and has some helper methods here and there
 */
public class ColourObjectsRegistry<T extends FeatureElement> {
	private final ImmutableSet<ColorObjectPair<T>> registryObjects;

	public ColourObjectsRegistry(ArrayList<Pair<SCPDefaultColors, RegistryObject<T>>> objects) {
		ImmutableSet.Builder<ColorObjectPair<T>> builder = ImmutableSet.builder();
		for (Pair<SCPDefaultColors, RegistryObject<T>> object : objects) {
			builder.add(new ColorObjectPair<>(object.getKey(), object.getValue()));
		}

		this.registryObjects = builder.build();
	}

	public ImmutableSet<ColorObjectPair<T>> getPairs() {
		return registryObjects;
	}

	public T getObjectFromColour(SCPDefaultColors colour) {
		return getPairs().stream().filter(pair -> pair.getColor() == colour).findFirst().get().getObject();
	}

	@Nullable
	public SCPDefaultColors getColourFromObject(Object object) {
		SCPDefaultColors colour = null;
		for (ColorObjectPair<T> pair : getPairs()) {
			if (pair.getObject().equals(object)) {
				colour = pair.getColor();
				break;
			}
		}
		return colour;
	}

    /*
    public static class Builder<T extends IForgeRegistryEntry<? super T>> {
        private HashSet<MutablePair<SCPDefaultColors, T>> set;

        public Builder() {
            set = new HashSet<>();
        }

        public Builder<T> put(SCPDefaultColors color, T object) {
            set.add(MutablePair.of(color, object));
            return this;
        }
    }

     */

	public static class ColorObjectPair<T extends FeatureElement> extends Pair<SCPDefaultColors, RegistryObject<T>> {

		private final SCPDefaultColors color;
		private final RegistryObject<T> registryObject;

		public ColorObjectPair(SCPDefaultColors color, RegistryObject<T> registryObject) {
			this.color = color;
			this.registryObject = registryObject;
		}

		public SCPDefaultColors getColor() {
			return getLeft();
		}

		public RegistryObject<T> getRegistryObject() {
			return getRight();
		}

		public T getObject() {
			return getRegistryObject().get();
		}

		public boolean containsObject(T object) {
			return registryObject.get().equals(object);
		}

		@Override
		public SCPDefaultColors getLeft() {
			return color;
		}

		@Override
		public RegistryObject<T> getRight() {
			return registryObject;
		}

		@Override //Should not have to be used
		public RegistryObject<T> setValue(RegistryObject<T> value) {
			return null;
		}
	}
}
