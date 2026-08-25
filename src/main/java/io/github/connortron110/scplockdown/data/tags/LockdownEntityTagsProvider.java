package io.github.connortron110.scplockdown.data.tags;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.registration.SCPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class LockdownEntityTagsProvider extends EntityTypeTagsProvider {

	public LockdownEntityTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper) {
		super(packOutput, completableFuture, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(SCPTags.Entity.SCP008_INFECTABLE)
				//.add(SCPEntities.DCLASS.get())
				//.add(SCPEntities.SCIENTIST.get())
				.add(SCPEntities.GUARD.get())
				.add(EntityType.VILLAGER)
				.add(EntityType.PILLAGER)
				.add(EntityType.VINDICATOR)
				.add(EntityType.EVOKER)
				.add(EntityType.ILLUSIONER)
				.add(EntityType.ENDERMAN)
				.add(EntityType.PIGLIN)
				.add(EntityType.PIGLIN_BRUTE);

		tag(SCPTags.Entity.SCP008_ATTACKING)
				.addTag(EntityTypeTags.RAIDERS)
				.add(EntityType.PIGLIN)
				.add(EntityType.PIGLIN_BRUTE)
				.add(SCPEntities.GUARD.get());

		tag(SCPTags.Entity.SCP008_FLEEING)
				.addTag(SCPTags.Entity.ANIMALS)
				.add(EntityType.VILLAGER); //This technically does nothing as Villagers have slightly more complex AI (See common setup events)
		//.add(SCPEntities.DCLASS.get())
		//.add(SCPEntities.SCIENTIST.get());

		tag(SCPTags.Entity.SCP027_VERMIN_COMMON)
				.add(SCPEntities.MOUSE.get());
		tag(SCPTags.Entity.SCP027_VERMIN_UNCOMMON)
				.add(SCPEntities.RAT.get())
				.add(EntityType.SPIDER);
		tag(SCPTags.Entity.SCP027_VERMIN_RARE)
				.add(EntityType.CAVE_SPIDER)
				.add(EntityType.SILVERFISH);
		tag(SCPTags.Entity.SCP027_VERMIN_EPIC)  //TODO add Shadowspider
				.add(EntityType.ENDERMITE);

		//Generification tags to help with further tags and quickly identifying different types of entities
		//These will never have to be touched again as its mostly Vanilla things
		tag(SCPTags.Entity.ANIMALS) //TODO Decide if Commented out entities should be in this tag (if not at least separate tags)
				//.add(EntityType.BAT)
				//.add(EntityType.BEE)
				.add(EntityType.CAT)
				.add(EntityType.CHICKEN)
				.add(EntityType.COW)
				.add(EntityType.DONKEY)
				.add(EntityType.FOX)
				//.add(EntityType.HOGLIN)
				.add(EntityType.HORSE)
				.add(EntityType.LLAMA)
				.add(EntityType.MULE)
				.add(EntityType.MOOSHROOM)
				.add(EntityType.OCELOT)
				.add(EntityType.PANDA)
				//.add(EntityType.PARROT)
				.add(EntityType.PIG)
				.add(EntityType.POLAR_BEAR)
				.add(EntityType.RABBIT)
				.add(EntityType.SHEEP)
				//.add(EntityType.SKELETON_HORSE)
				.add(EntityType.TRADER_LLAMA)
				//.add(EntityType.TURTLE)
				.add(EntityType.WOLF);
	}
}
