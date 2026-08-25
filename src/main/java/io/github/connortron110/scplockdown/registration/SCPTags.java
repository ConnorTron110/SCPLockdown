package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class SCPTags {
	public static class Entity {

		public static final TagKey<EntityType<?>> SCP008_INFECTABLE = createTag("scp008_infectable"); //Entities that can get infected and convert to instances of 008
		public static final TagKey<EntityType<?>> SCP008_ATTACKING = createTag("scp008_attacking"); //Entities that have this tag will have SCP-008-1 instances as targets (Mixin'd)
		public static final TagKey<EntityType<?>> SCP008_FLEEING = createTag("scp008_fleeing"); //Entities that have this tag will flee from SCP-008-1 instances (Mixin'd)

		public static final TagKey<EntityType<?>> SCP027_VERMIN_COMMON = createTag("scp027_vermin_common");
		public static final TagKey<EntityType<?>> SCP027_VERMIN_UNCOMMON = createTag("scp027_vermin_uncommon");
		public static final TagKey<EntityType<?>> SCP027_VERMIN_RARE = createTag("scp027_vermin_rare");
		public static final TagKey<EntityType<?>> SCP027_VERMIN_EPIC = createTag("scp027_vermin_epic");

		//Generic Identifying tags (Will expand on this)
		public static final TagKey<EntityType<?>> ANIMALS = createTag("animals"); //Entities that can be classified as animals

		public static boolean isVermin(EntityType<?> type) {
			return type.is(SCP027_VERMIN_COMMON) || type.is(SCP027_VERMIN_UNCOMMON) || type.is(SCP027_VERMIN_RARE) || type.is(SCP027_VERMIN_EPIC);
		}

		private static TagKey<EntityType<?>> createTag(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name));
		}
	}

	public static class Items {

		public static final TagKey<Item> VIAL_008_REFILLABLE = createTag("vial_008_refillable");

		private static TagKey<Item> createTag(String name) {
			return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name));
		}
	}

	public static class Fluids {

		public static final TagKey<Fluid> SCP006_FOUNTAIN_FLUID = createTag("scp006_fountain_fluid");

		private static TagKey<Fluid> createTag(String name) {
			return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name));
		}
	}
}
