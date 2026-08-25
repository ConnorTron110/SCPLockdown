package io.github.connortron110.scplockdown.mixin;

import io.github.connortron110.scplockdown.level.effect.SCPEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * This Mixin is responsible for allowing for the extended needs/capabilities we need for {@link SCPEffect}
 */
@Mixin(MobEffectInstance.class)
public abstract class MixinMobEffectInstance {

	@Shadow
	@Final
	private MobEffect effect;
	@Shadow
	private int duration;
	@Shadow
	private int amplifier;

	@Shadow
	private boolean visible;

	@Shadow
	public abstract void applyEffect(LivingEntity pEntity);

	//Methods below handle the permanent hiding of effects (their particles) implementation for SCPEffects\\

	@Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/effect/MobEffect;IIZZZLnet/minecraft/world/effect/MobEffectInstance;Ljava/util/Optional;)V")
	public void initEffectInstance(MobEffect pEffect, int pDuration, int pAmplifier, boolean pAmbient, boolean pVisible, boolean pShowIcon, MobEffectInstance pHiddenEffect, Optional<MobEffectInstance.FactorData> pFactorData, CallbackInfo ci) {
		//  Override effect parameters based on effect registration
		if (isSCPEffect(pEffect)) {
			SCPEffect scpEffect = (SCPEffect) pEffect;
			duration = scpEffect.isDurationInfinite() ? -1 : duration;
			visible = scpEffect.isVisible();
		}
	}

	@Inject(at = @At("TAIL"), method = "setDetailsFrom")
	private void setDetailsFrom(MobEffectInstance pEffectInstance, CallbackInfo ci) {
		if (isSCPEffect(pEffectInstance.getEffect())) {
			SCPEffect scpEffect = (SCPEffect) pEffectInstance.getEffect();
			visible = scpEffect.isVisible();
			duration = scpEffect.isDurationInfinite() ? -1 : duration;
		}
	}

	//  Methods below handles "tick" and "lastTick" calls to SCPEffect methods  \\

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(LivingEntity living, Runnable pOnExpirationRunnable, CallbackInfoReturnable<Boolean> cir) {
		if (duration == 1 && isSCPEffect(effect)) {
			((SCPEffect) effect).lastTick(living, amplifier);
		}
	}


	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;applyEffect(Lnet/minecraft/world/entity/LivingEntity;)V"))
	private void applyEffect(MobEffectInstance instance, LivingEntity living) {
		if (isSCPEffect(effect)) ((SCPEffect) effect).tick(living, duration, amplifier);
		else applyEffect(living);
	}

	/**
	 * Avoid calling the {@link MobEffect#isDurationEffectTick(int, int)} when it is an SCP effect (duration is included in the tick hence avoiding an unnecessary check)
	 */
	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;isDurationEffectTick(II)Z"))
	private boolean SCPEffectDurationBypass(MobEffect effect, int duration, int amplifier) {
		return isSCPEffect(effect) || effect.isDurationEffectTick(duration, amplifier);
	}

	@Unique
	private boolean isSCPEffect(MobEffect effect) {
		return effect instanceof SCPEffect;
	}
}
