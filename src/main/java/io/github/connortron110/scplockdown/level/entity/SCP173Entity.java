package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.level.entity.ai.goal.SCP173MeleeAttackGoal;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.registration.SCPTags;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fluids.FluidType;

import java.util.List;
import java.util.stream.Collectors;

public class SCP173Entity extends Monster implements IRequirePersistence {

	/**
	 * SCP-173 technically only cares about if their current target is looking at them, but in RP situations, it would be better to check other entities nearby to see if they are looking too.
	 * This determines the radius around the SCP that other entities are check if they are looking. Currently, at 16 to cover 2 chunks worth of entities.
	 */
	private static final int MUTI_ENTITY_CHECK_RANGE = 16;
	private static final int TICKS_UNTIL_NEXT_CHECK = 2;
	/**
	 * Temporary ish value that determines how far away an entity needs to be looking in order for SCP-173 to be able to move
	 */
	private static final int VIEWING_ANGLE = 75;

	private int MoveCheckTicks = 0;
	private boolean CanMove = false;

	public SCP173Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		setCanPickUpLoot(false);
		this.moveControl = new SCP173MoveControl();
		this.lookControl = new SCP173LookControl();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SCPSounds.SCP173_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource pDamageSource) {
		if (isEntityHoldingPickaxeInMainHand(pDamageSource.getEntity())) {
			return SCPSounds.SCP173_HURT.get();
		} else return super.getHurtSound(pDamageSource);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SCP173MeleeAttackGoal(this, 1F, false));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1, 0.000001F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, livingEntity -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingEntity) && livingEntity.getType().is(SCPTags.Entity.SCP173_TARGET)));
	}

	@Override
	protected BodyRotationControl createBodyControl() {
		return new SCP173BodyRotationControl();
	}

	private boolean isEntityHoldingPickaxeInMainHand(Entity entity) {
		if (entity instanceof LivingEntity living) {
			return living.getItemInHand(InteractionHand.MAIN_HAND).is(ItemTags.PICKAXES);
		} else return false;
	}

	/**
	 * When SCP-173 is hurt by anything, reduce the damage taken, other than from pickaxes and explosions.
	 */
	@Override
	public boolean hurt(DamageSource pSource, float pAmount) {
		if (isEntityHoldingPickaxeInMainHand(pSource.getEntity())) {
			return super.hurt(pSource, pAmount * 1.5F);
		}

		//	Explosions still nerfed, but can still hurt more than other sources
		if (pSource.is(DamageTypeTags.IS_EXPLOSION)) {
			return super.hurt(pSource, pAmount * 0.1F);
		}

		//	Technically, other sources will kill, just will take forever
		return super.hurt(pSource, pAmount * 0.01F);
	}

	/**
	 * Falling and cactus should not bother stone... maybe.
	 */
	@Override
	public boolean isInvulnerableTo(DamageSource pSource) {
		return super.isInvulnerableTo(pSource) || pSource.is(DamageTypes.CACTUS) || pSource.is(DamageTypes.FALL);
	}

	/**
	 * Prevents knockback.
	 */
	@Override
	public void knockback(double pStrength, double pX, double pZ) {
	}

	/**
	 * Make SCP-173 immovable when alive (does not prevent moving from knockback)
	 */
	@Override
	public boolean canBeCollidedWith() {
		return this.isAlive();
	}

	/**
	 * Prevent SCP-173 from drowning.
	 */
	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	/**
	 * Used to track how often we should recalculate if SCP-173 can move.
	 */
	@Override
	public void tick() {
		super.tick();
		if (MoveCheckTicks++ >= TICKS_UNTIL_NEXT_CHECK) {
			MoveCheckTicks = 0;
			updateCanMove();
		}
	}

	private void updateCanMove() {
		List<Entity> entitiesAround173 = level().getEntities(this, AABB.ofSize(position(), MUTI_ENTITY_CHECK_RANGE, MUTI_ENTITY_CHECK_RANGE, MUTI_ENTITY_CHECK_RANGE), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

		//	Remove other 173 instances... TODO: maybe extend to a tag based system?
		entitiesAround173 = entitiesAround173.stream().filter(e -> !(e instanceof SCP173Entity)).collect(Collectors.toList());

		//	Remove the target from this list to avoid checking twice
		if (getTarget() != null) {
			entitiesAround173.remove(getTarget());
		}

		//	First, check nearby entities
		for (Entity entity : entitiesAround173) {
			if (!Utils.isViewBetweenEntitiesObstructed(this, entity, true) && Utils.getLookingAtAngle(this, entity, false) < VIEWING_ANGLE) {
				this.CanMove = false;
				return;
			}
		}

		//	Now check with target entity
		if (getTarget() != null) {
			if (!Utils.isViewBetweenEntitiesObstructed(this, getTarget(), true) && Utils.getLookingAtAngle(this, getTarget(), false) < VIEWING_ANGLE) {
				this.CanMove = false;
				return;
			}
		}

		//	If no entity is looking at SCP-173, we can move
		this.CanMove = true;
	}

	@Override
	public boolean doHurtTarget(Entity pEntity) {
		if (this.CanMove) {
			boolean didHurt = super.doHurtTarget(pEntity);
			if (didHurt) {
				level().playSound(null, getOnPos(), SCPSounds.NECK_SNAP.get(), SoundSource.MASTER, 1, 1);
			}
			return didHurt;
		} else return false;
	}

	/**
	 * Controls how 173 moves, currently teleports along the path a tick at a time.
	 */
	class SCP173MoveControl extends MoveControl {

		/**
		 * Keep a count up to some arbitrary value on every step we take, as to not spam the sound when we move.
		 */
		private int moveSoundCount = 0;

		public SCP173MoveControl() {
			super(SCP173Entity.this);
		}

		public void tick() {
			if (SCP173Entity.this.CanMove) {
				if (isPathFinding()) {
					//	If the wanted position is 3 or more blocks away from where we want to go, disallow it as it's typically trying to default to (0, 0, 0)
					if (Mth.sqrt((float) SCP173Entity.this.distanceToSqr(wantedX, wantedY, wantedZ)) >= 3) {
						return;
					}

					if (moveSoundCount == 0) {
						SCP173Entity.this.level().playSound(null, SCP173Entity.this.getOnPos(), SCPSounds.SCP173_STEP.get(), SoundSource.HOSTILE, 2, 1);
					}

					//	Playing the sound after 4-5 ticks is the sweet spot
					if (moveSoundCount++ >= 5) {
						moveSoundCount = 0;
					}

					SCP173Entity.this.moveTo(wantedX, wantedY, wantedZ, SCP173Entity.this.getYRot(), SCP173Entity.this.getXRot());
					SCP173Entity.this.setYBodyRot(SCP173Entity.this.getYRot());
				}
			} else {
				//	Reset move sound so that the first move after we've been "let go" plays a sound.
				moveSoundCount = 0;
			}
		}
	}

	/**
	 * Look at things instantly to add to the supernatural feeling, but only allow updates when not looked at.
	 */
	public class SCP173LookControl extends LookControl {
		public SCP173LookControl() {
			super(SCP173Entity.this);
		}

		public void tick() {
			if (SCP173Entity.this.CanMove) {
				this.getYRotD().ifPresent((rot) -> {
					this.mob.yHeadRot = rot;
					this.mob.yBodyRot = rot;
				});
				this.getXRotD().ifPresent(this.mob::setXRot);
			}
		}
	}

	/**
	 * Keep body rotation synced with head at all times.
	 * Mostly used on clients as body rotation is weird.
	 */
	public class SCP173BodyRotationControl extends BodyRotationControl {

		public SCP173BodyRotationControl() {
			super(SCP173Entity.this);
		}

		@Override
		public void clientTick() {
			SCP173Entity.this.yBodyRot = SCP173Entity.this.yHeadRot;
		}
	}
}
