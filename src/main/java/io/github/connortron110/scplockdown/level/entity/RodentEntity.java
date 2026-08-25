package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.level.entity.ai.goal.EatDroppedFoodGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RodentEntity extends PathfinderMob {
	public RodentEntity(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(2, new EatDroppedFoodGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.FOX_SNIFF;
	}

	@Override
	public float getVoicePitch() {
		return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.3F;
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	/**
	 * This is the correct method for eye height
	 */
	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
		return pDimensions.height * 0.7F;
	}
}
