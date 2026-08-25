package io.github.connortron110.scplockdown.events.hooks;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public interface IDislikeBeingObserved {

	ImmutableList<EntityType<?>> DEFAULT_OBSERVERS = ImmutableList.<EntityType<?>>builder().add(EntityType.PLAYER).build();

	/**
	 * @param observerEntity null if entity is not being currently observed. non null otherwise
	 */
	void updateBeingObserved(@Nullable LivingEntity observerEntity);

	/**
	 * A list of Entities that 'trigger' observational checks
	 */
	default ImmutableList<EntityType<?>> getObservingEntities() {
		return DEFAULT_OBSERVERS;
	}

	/**
	 * If the observer must look at the face of the entity
	 */
	default boolean mustLookAtFace() {
		return false;
	}

	/**
	 * If the entity should be seen directly, or just has to be looked within its general direction
	 */
	default boolean mustObserveUnobstructed() {
		return true;
	}

	/**
	 * Determines how close the observer has to look at the entity before anything gets triggered
	 */
	default int getObservationAngleTolerance() {
		return 20;
	}

	/**
	 * Determines how far to check for entities that are looking at the entity
	 */
	default int getObservationRange() {
		return 32;
	}
}
