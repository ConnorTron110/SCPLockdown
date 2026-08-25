package io.github.connortron110.scplockdown.mixin;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.entity.ai.goal.FollowEntityGoal;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import io.github.connortron110.scplockdown.registration.SCPTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Currently only used to add the fleeing/attacking goals for SCP-008-1 to entities that have the tag
 */
@Mixin(Mob.class)
public abstract class MixinMobEntity {

	@Shadow
	protected abstract void registerGoals();

	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;registerGoals()V"))
	private void registerGoalsRedirect(Mob mob) {
		registerGoals();
		registerSharedGoals(mob);
	}

	/**
	 * Used to register commonly shared goals. <br>
	 * Currently, includes goals to avoid or attack SCP-008-1 Instances
	 */
	@Unique
	private void registerSharedGoals(Mob mob) {
		EntityType<?> type = mob.getType();
		if (type.is(SCPTags.Entity.SCP008_ATTACKING)) {
			mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>((Mob) (Object) this, SCP008Entity.class, true));
			SCPLockdown.LOGGER.debug("Added SCP-008-1 Targeting goal to {}", mob.getType().getDescription().getString());
		} else if (mob instanceof PathfinderMob pathfinderMob && type.is(SCPTags.Entity.SCP008_FLEEING) && !(mob instanceof AbstractVillager)) {
			mob.goalSelector.addGoal(1, new AvoidEntityGoal<>(pathfinderMob, SCP008Entity.class, 8F, 1D, 1.2D));
			SCPLockdown.LOGGER.debug("Added SCP-008-1 Avoid goal to {}", mob.getType().getDescription().getString());
		}

		if (SCPTags.Entity.isVermin(type)) {
			mob.goalSelector.addGoal(0, new FollowEntityGoal(mob, 1.25, 1.5F, 32, entity -> entity.hasEffect(SCPEffects.SCP027_VERMIN.get())));
		}
	}
}
