package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

/**
 * Direct copy of FollowMobGoal, however allows for custom follow predicate instead of being restricted to different mob type
 * and removes restriction of not applying to players
 */
public class FollowEntityGoal extends Goal {
	protected final Mob mob;
	protected Predicate<LivingEntity> followPredicate;
	protected LivingEntity followingMob;
	private final double speedModifier;
	private final PathNavigation navigation;
	private int timeToRecalcPath;
	private final float stopDistance;
	private float oldWaterCost;
	protected final float areaSize;

	public FollowEntityGoal(Mob mob, double speedModifier, float stopDistance, float areaSize, Predicate<LivingEntity> predicate) {
		this.mob = mob;
		this.followPredicate = predicate;
		this.speedModifier = speedModifier;
		this.navigation = mob.getNavigation();
		this.stopDistance = stopDistance;
		this.areaSize = areaSize;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		if (!(mob.getNavigation() instanceof GroundPathNavigation) && !(mob.getNavigation() instanceof FlyingPathNavigation)) {
			throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
		}
	}

	@Override
	public boolean canUse() {
		List<LivingEntity> list = this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(this.areaSize), this.followPredicate);
		if (!list.isEmpty()) {
			for (LivingEntity entity : list) {
				if (!entity.isInvisible()) {
					this.followingMob = entity;
					return true;
				}
			}
		}

		return false;
	}

	@Override   //Added !followingMob.isDeadOrDying()
	public boolean canContinueToUse() {
		return this.followingMob != null && !followingMob.isDeadOrDying() && !this.navigation.isDone() && this.mob.distanceToSqr(this.followingMob) > (double) (this.stopDistance * this.stopDistance);
	}

	@Override
	public void start() {
		this.timeToRecalcPath = 0;
		this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
		this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	@Override
	public void stop() {
		this.followingMob = null;
		this.navigation.stop();
		this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
	}

	@Override
	public void tick() {
		if (this.followingMob != null && !this.mob.isLeashed()) {
			this.mob.getLookControl().setLookAt(this.followingMob, 10.0F, (float) this.mob.getMaxHeadXRot());
			if (--this.timeToRecalcPath <= 0) {
				this.timeToRecalcPath = 10;
				double d0 = this.mob.getX() - this.followingMob.getX();
				double d1 = this.mob.getY() - this.followingMob.getY();
				double d2 = this.mob.getZ() - this.followingMob.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (!(d3 <= (double) (this.stopDistance * this.stopDistance))) {
					this.navigation.moveTo(this.followingMob, this.speedModifier);
				} else {
					this.navigation.stop();
//                    LookController lookcontroller = this.followingMob.getLookControl();
//                    if (d3 <= (double)this.stopDistance || lookcontroller.getWantedX() == this.mob.getX() && lookcontroller.getWantedY() == this.mob.getY() && lookcontroller.getWantedZ() == this.mob.getZ()) {
//                        double d4 = this.followingMob.getX() - this.mob.getX();
//                        double d5 = this.followingMob.getZ() - this.mob.getZ();
//                        this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ() - d5, this.speedModifier);
//                    }
				}
			}
		}
	}
}
