package io.github.connortron110.scplockdown.level.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.Level;

public class GuardEntity extends AbstractHumanEntity {
	public GuardEntity(EntityType<? extends AbstractHumanEntity> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
	}
}
