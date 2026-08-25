package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class SCP027Entity extends AbstractHumanEntity implements IRequirePersistence {
	public SCP027Entity(EntityType<? extends AbstractHumanEntity> type, Level level) {
		super(type, level);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		addEffect(new MobEffectInstance(SCPEffects.SCP027_VERMIN.get()));
		return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
	}
}
