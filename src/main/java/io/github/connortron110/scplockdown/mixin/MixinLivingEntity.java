package io.github.connortron110.scplockdown.mixin;

import io.github.connortron110.scplockdown.events.hooks.IDislikeBeingObserved;
import io.github.connortron110.scplockdown.registration.SCPTags;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	@Shadow
	public abstract boolean addEffect(MobEffectInstance pEffectInstance);

	@Shadow
	public abstract ItemStack eat(Level pLevel, ItemStack pFood);

	@Inject(at = @At(value = "TAIL"), method = "baseTick")
	private void baseTick(CallbackInfo ci) {
		Entity thisEntity = ((Entity) (Object) this);
		thisEntity.level().getProfiler().push("livingEntityBaseTickMixinSCPLockdown");

		//Observation check
		//Get this running every 250ms to save computation (slightly)
		if (!thisEntity.level().isClientSide() && thisEntity.tickCount % 5 == 0 && thisEntity instanceof IDislikeBeingObserved dislikeBeingObserved) {
			SCP_Lockdown$runObservationCheck(dislikeBeingObserved);
		}

		//Is in fountain of youth
		if (thisEntity.getFluidHeight(SCPTags.Fluids.SCP006_FOUNTAIN_FLUID) > 0.0D) {
			addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 60 * 10, 3)); //  Decreased from 1h to 10m
			addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 60 * 10, 3));
		}

		thisEntity.level().getProfiler().pop();
	}

	@Unique
	private void SCP_Lockdown$runObservationCheck(IDislikeBeingObserved dislikeBeingObserved) {
		Entity thisEntity = ((Entity) (Object) this);
		int range = dislikeBeingObserved.getObservationRange();

		List<LivingEntity> nearbyEntities = thisEntity.level().getEntitiesOfClass(LivingEntity.class, new AABB(thisEntity.blockPosition()).inflate(range), EntitySelector.NO_CREATIVE_OR_SPECTATOR);
		nearbyEntities = nearbyEntities.stream().filter(living -> dislikeBeingObserved.getObservingEntities().stream().anyMatch(type -> type.equals(living.getType()))).toList();

		boolean isBeingObserved = false;
		for (LivingEntity nearEntity : nearbyEntities) {
			float angle = Utils.getLookingAtAngle(thisEntity, nearEntity, dislikeBeingObserved.mustLookAtFace());

			if (angle <= dislikeBeingObserved.getObservationAngleTolerance()) {
				if (dislikeBeingObserved.mustObserveUnobstructed() && Utils.isViewBetweenEntitiesObstructed(thisEntity, nearEntity, true)) {
					continue;
				}

				isBeingObserved = true;
				dislikeBeingObserved.updateBeingObserved(nearEntity);
			}
		}

		if (!isBeingObserved) {
			dislikeBeingObserved.updateBeingObserved(null);
		}
	}
}
