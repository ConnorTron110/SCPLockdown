package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SCP173MeleeAttackGoal extends MeleeAttackGoal {

	public SCP173MeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
		super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
	}

	/**
	 * Identical to super, but with cooldown removed
	 */
	@Override
	protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
		double d0 = this.getAttackReachSqr(pEnemy);
		if (pDistToEnemySqr <= d0) {
			this.resetAttackCooldown();
			this.mob.doHurtTarget(pEnemy);
		}
	}
}
