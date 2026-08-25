package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.events.hooks.IDislikeBeingObserved;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SCP053Entity extends PathfinderMob implements IRequirePersistence, IDislikeBeingObserved {
	public SCP053Entity(EntityType<? extends PathfinderMob> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.getMsgId().equals("player")) {
			//  If player is creative, handle like normal damage to allow creative players to kill entity
			if (source.isCreativePlayer()) return super.hurt(source, amount);

			amount = 0F;
			if (source.getEntity() instanceof Player player) {
				player.hurt(SCPDamageTypes.source(level(), SCPDamageTypes.SCP053HEARTATTACK), Float.MAX_VALUE);
			}
		}

		return super.hurt(source, amount);
	}

	@Override
	public void updateBeingObserved(@Nullable LivingEntity observerEntity) {
		if (observerEntity != null && !observerEntity.isDeadOrDying()) {
			//  TODO Have a clean way to make player "Rage" to compel them to kill this entity
			//  IDEA: make it so when this entity is looked, at, a skeleton is spawned in its place only by the POV of the affected player
			//  Then have it shoot the player, dealing real damage, if the player dies, they have died from a heart attack
			//  This disappears after a while when the player is no longer looking at the entity and taking any active damage
		}
	}

	@Override
	public int getObservationAngleTolerance() {
		return 6;
	}
}
