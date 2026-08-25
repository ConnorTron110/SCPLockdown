package io.github.connortron110.scplockdown.api;

import com.google.common.collect.ImmutableList;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.registration.builder.item.ItemRegistryObject;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Optional;

//TODO Structure Information
public class SCPEntry {
	public final String number;
	public final String name;   //  Currently only used in EN Lang data generation for name
	public final SCPObjectClass objectClass;
	public final ImmutableList<Item> associatedItems;

	private SCPEntry(String number, String name, SCPObjectClass objectClass, ArrayList<Item> items) {
		this.number = number;
		this.name = name;
		this.objectClass = objectClass;
		this.associatedItems = ImmutableList.copyOf(items);
	}

	public static class Builder {
		private final String number;
		private final String name;
		private final SCPObjectClass objectClass;
		private ArrayList<Item> associatedItems;

		private Builder(String number, String name, SCPObjectClass objectClass) {
			this.number = number;
			this.name = name;
			this.objectClass = objectClass;
			associatedItems = new ArrayList<>();
		}

		@SafeVarargs
		public final Builder addItems(ItemRegistryObject<? extends Item>... itemRegistryObjects) {
			for (ItemRegistryObject<? extends Item> itemRegistryObject : itemRegistryObjects) {
				associatedItems.add(itemRegistryObject.asItem());
			}
			return this;
		}

		@SafeVarargs
		public final Builder addItems(RegistryObject<? extends Item>... itemRegistryObjects) {
			for (RegistryObject<? extends Item> itemRegistryObject : itemRegistryObjects) {
				associatedItems.add(itemRegistryObject.get().asItem());
			}
			return this;
		}

		@SafeVarargs
		public final Builder addBlocks(RegistryObject<? extends Block>... blockRegistryObjects) {
			for (RegistryObject<? extends Block> blockRegistryObject : blockRegistryObjects) {
				associatedItems.add(blockRegistryObject.get().asItem());
			}
			return this;
		}

		@SafeVarargs
		public final <E extends Entity> Builder addEntities(RegistryObject<EntityType<E>>... entityRegistryObjects) {
			//  Currently only adds the eggs themselves
			for (RegistryObject<EntityType<E>> entityRegistryObject : entityRegistryObjects) {
				Optional.ofNullable(SCPEntities.getEggFromEntity(entityRegistryObject)).ifPresent(forgeSpawnEggItemItemRegistryObject -> associatedItems.add(forgeSpawnEggItemItemRegistryObject.asItem()));
			}
			return this;
		}

		public SCPEntry build() {
			return new SCPEntry(this.number, this.name, this.objectClass, associatedItems);
		}

		public static Builder builder(String number, String name, SCPObjectClass objectClass) {
			return new Builder(number, name, objectClass);
		}
	}
}
