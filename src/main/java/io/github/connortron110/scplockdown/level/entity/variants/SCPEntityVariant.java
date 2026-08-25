package io.github.connortron110.scplockdown.level.entity.variants;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.List;

/**
 * Interface used on entities that have multiple variants. Mixin {@link io.github.connortron110.scplockdown.mixin.MixinEntity} handles all data related to variant swapping
 *
 * @param <E> Variant Enum must implement {@link EntityEnumVariants}
 */
public interface SCPEntityVariant<E extends Enum<? extends EntityEnumVariants>> {

	HashMap<Class<? extends Entity>, EntityDataAccessor<Integer>> DATA_PARAMETER = new HashMap<>();
	HashMap<Class<? extends Entity>, ImmutableList<Enum<? extends EntityEnumVariants>>> ENUMS_FOR_ENTITY = new HashMap<>();

	E[] getEnumVariantValues();

	default int getVariantID(Entity entity) {
		return entity.getEntityData().get(getDataParameter(entity.getClass()));
	}

	default void setVariant(Entity entity, E variant) {
		entity.getEntityData().set(getDataParameter(entity.getClass()), variant.ordinal());
	}

	default void setVariant(Entity entity, int variantID) {
		entity.getEntityData().set(getDataParameter(entity.getClass()), variantID);
	}

	@SuppressWarnings("unchecked")
	default E getVariantEnum(Entity entity) {
		List<E> enums = (List<E>) ENUMS_FOR_ENTITY.get(entity.getClass());
		if (getVariantID(entity) >= enums.size()) return enums.get(0);
		else return enums.get(getVariantID(entity));
	}

	default EntityDataAccessor<Integer> getDataParameter(Class<? extends Entity> clazz) {
		return DATA_PARAMETER.get(clazz);
	}

	static <E extends Enum<? extends EntityEnumVariants>> void createVariantDataParam(Class<? extends Entity> entityClass, E[] enumValues) {
		if (!DATA_PARAMETER.containsKey(entityClass)) {
			ENUMS_FOR_ENTITY.put(entityClass, ImmutableList.copyOf(enumValues));
			DATA_PARAMETER.put(entityClass, SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT));
		}
	}
}
