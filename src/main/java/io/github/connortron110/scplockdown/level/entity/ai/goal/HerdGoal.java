package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Goal Process goes as follows. <br>
 * canUse > start > tick > canContinueToUse (Based on return next line) <br>
 * If false > stop <br>
 * If True > isInterruptable > tick > canContinueToUse (Loop) <br>
 * <p>
 * Goal gets replaced if isInterruptable returns true and priority on replacement is higher than current.
 */
public class HerdGoal<E extends Mob, T extends LivingEntity> extends Goal {

	protected final E mob;
	protected final Class<T> herdType;
	protected final double speedModifier;
	protected double wantedX;
	protected double wantedY;
	protected double wantedZ;

	/**
	 * @param mob           The mob instance holding this goal
	 * @param herdType      Any class that extends this type, will be herd to
	 * @param speedModifier the speed to move at to get to go to the herd
	 */
	public HerdGoal(E mob, Class<T> herdType, double speedModifier) {
		this.mob = mob;
		this.herdType = herdType;
		this.speedModifier = speedModifier;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	/**
	 * Called when the goal gets triggered to see if the goal should start ticking
	 * <p>
	 * Called every tick when a goal with similar flags is not being ran
	 */
	@Override
	public boolean canUse() {

		//Random int from 500 to determine if it should happen 1/500 chance to happen every time can use gets called
		if (this.mob.getRandom().nextInt(2000) != 0) {
			return false;
		}

		List<Entity> entities = mob.level().getEntities(mob, new AABB(mob.blockPosition()).inflate(20, 10, 20)).stream()
				.filter(herdType::isInstance).collect(Collectors.toList());

		if (entities.isEmpty()) return false;

		this.wantedX = 0;
		this.wantedY = 0;
		this.wantedZ = 0;

		for (Entity entity : entities) {
			this.wantedX += entity.getX();
			this.wantedY += entity.getY();
			this.wantedZ += entity.getZ();
		}

		this.wantedX = this.wantedX / entities.size();
		this.wantedY = this.wantedY / entities.size();
		this.wantedZ = this.wantedZ / entities.size();

		return true;
	}

	/**
	 * Called after every tick to see if the next tick should be called
	 */
	@Override
	public boolean canContinueToUse() {
		//SCPLockdown.LOGGER.debug("canContinueToUse");
		return !this.mob.getNavigation().isDone();
	}

	/**
	 * Called before the first tick when canUse returns true
	 */
	@Override
	public void start() {
		//SCPLockdown.LOGGER.debug("start");
		this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}

	/**
	 * Called when the goal stops
	 */
	@Override
	public void stop() {
		//SCPLockdown.LOGGER.debug("stop");
		this.mob.getNavigation().stop();
	}

	/**
	 * Is called every tick when the goal is permitted to run by canContinueToUse
	 */
	@Override
	public void tick() {
		//Can remove this method, just keeping it here for the comment above for better understanding of Goals
	}

	/**
	 * Gets called every tick after the first tick and canContinueToUse check <br>
	 * If true: goals that share the same flags can override this goal and stop it from executing, does call (stop) <br>
	 * If false: goals that share the same flags that this goal uses are not permitted to run. <br>
	 * <p>
	 * Goals with a higher priority will not run if this returns true
	 * <p>
	 * Called by all other goals that want to run, however there is no way to tell what goal wants to run next, so we have to get smart about it
	 *
	 * @return true if the goal should stop in favour for another goal with the same/similar flags
	 */
	@Override
	public boolean isInterruptable() {
		return true; //Can remove this method, just keeping it here for the comment above for better understanding of Goals
	}
}
