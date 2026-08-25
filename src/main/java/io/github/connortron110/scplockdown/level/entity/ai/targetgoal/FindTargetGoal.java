package io.github.connortron110.scplockdown.level.entity.ai.targetgoal;


import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

/**
 *
 */
public class FindTargetGoal extends Goal {

	private final Mob mob;

	public FindTargetGoal(Mob mob) {
		this.mob = mob;
	}

	@Override
	public boolean canUse() {
		return false;
	}
}
