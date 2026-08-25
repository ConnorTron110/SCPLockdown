package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.level.entity.variants.SCP019EnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class SCP019Entity extends Monster implements SCPEntityVariant<SCP019EnumVariants> {

	/**
	 * Life functionality is no longer exactly 25 mins, it's now a minimum of 5 Mins and a 1/100 chance every tick to die to add very small variance
	 */
	private static final int MAX_LIFE = 20 * 60 * 5;   //   5 mins in ticks

	public SCP019Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.5D, 0.5F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, living -> !(living instanceof SCP019Entity)));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
		//This can be added to the Entity Variant mixin (MixinEntity), but allows for specific entities to have random variants when spawned with an egg
		setVariant(this, SCP019EnumVariants.values()[level.getRandom().nextInt(SCP019EnumVariants.values().length)]);
		return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
	}

	@Override
	public void tick() {
		super.tick();
		if (tickCount >= MAX_LIFE && !isDeadOrDying() && random.nextInt(100) == 0) {
			setHealth(0);
		}
	}

	@Override
	public SCP019EnumVariants[] getEnumVariantValues() {
		return SCP019EnumVariants.values();
	}
}
