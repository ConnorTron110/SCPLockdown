package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.events.hooks.IDislikeBeingObserved;
import io.github.connortron110.scplockdown.level.effect.SCP023Effect;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

//TODO Needs to align better to the wiki
public class SCP023Entity extends Monster implements IRequirePersistence, IDislikeBeingObserved {

	boolean hasHowledForTarget = false;

	public SCP023Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.5D, 0.5F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
	}

	@Override
	protected void playStepSound(BlockPos pPos, BlockState pBlock) {
		this.playSound(SoundEvents.WOLF_STEP, 0.15F, 0.5F);
	}

	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
		return pDimensions.height * 0.825F;
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		super.setTarget(target);
		if (!hasHowledForTarget && target != null) {
			hasHowledForTarget = true;
			this.playSound(SoundEvents.WOLF_HOWL, this.getSoundVolume(), this.getVoicePitch());
		} else if (target == null) {
			hasHowledForTarget = false;
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.WOLF_GROWL;
	}

	@Override
	public float getVoicePitch() {
		return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F;
	}

	@Override
	public void updateBeingObserved(@Nullable LivingEntity observerEntity) {
		if (observerEntity != null && !observerEntity.isDeadOrDying() && !observerEntity.hasEffect(SCPEffects.SCP023_CURSE.get())) {
			observerEntity.addEffect(SCP023Effect.getDefaultInstance());
			observerEntity.sendSystemMessage(LockdownTextComponents.SCP023_LOOKED_AT);
		}
	}

	@Override
	public boolean mustLookAtFace() {
		return true;
	}

	@Override
	public int getObservationAngleTolerance() {
		return 5;
	}
}
