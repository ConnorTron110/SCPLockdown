package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.ScientistEnumVariants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class ScientistEntity extends AbstractHumanEntity implements SCPEntityVariant<ScientistEnumVariants> {

	public ScientistEntity(EntityType<? extends ScientistEntity> type, Level level) {
		super(type, level);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		//This can be added to the Entity Variant mixin (MixinEntity), but allows for specific entities to have random variants when spawned with an egg
		setVariant(this, ScientistEnumVariants.values()[level.getRandom().nextInt(ScientistEnumVariants.values().length)]);
		return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
	}

	@Override
	public ScientistEnumVariants[] getEnumVariantValues() {
		return ScientistEnumVariants.values();
	}
}
