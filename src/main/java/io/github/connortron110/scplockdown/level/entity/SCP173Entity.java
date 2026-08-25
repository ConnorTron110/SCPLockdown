package io.github.connortron110.scplockdown.level.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

//  Final goal is to get entity to respect FOV, and to move without the player witnessing its momentum (IE Teleporting)
public class SCP173Entity extends Monster implements IRequirePersistence {
	protected SCP173Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		setCanPickUpLoot(false);
	}

	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
}
