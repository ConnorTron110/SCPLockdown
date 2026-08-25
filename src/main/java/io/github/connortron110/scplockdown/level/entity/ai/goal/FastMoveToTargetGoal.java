package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;

import java.util.EnumSet;

//  Only runs server side
public class FastMoveToTargetGoal extends Goal {

	private final Monster monster;

	public FastMoveToTargetGoal(Monster monster) {
		setFlags(EnumSet.of(Flag.MOVE));
		this.monster = monster;
	}

	@Override
	public boolean canUse() {
		return monster.getTarget() != null && !monster.level().isClientSide;
	}

	@Override
	public void tick() {

	}
}
