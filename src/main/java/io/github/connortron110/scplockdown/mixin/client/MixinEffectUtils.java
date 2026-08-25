/*package io.github.connortron110.scplockdown.mixin.client;

import io.github.connortron110.scplockdown.level.effect.SCPEffect;
import net.minecraft.world.effect.MobEffectUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectUtil.class)
public class MixinEffectUtils {
    @Inject(method = "formatDuration", at = @At("HEAD"), cancellable = true)
    private static void formatDuration(EffectInstance pEffect, float pDurationFactor, CallbackInfoReturnable<String> cir) {
        if (pEffect.getEffect() instanceof SCPEffect scpEffect && scpEffect.isDurationInfinite()) {
            cir.setReturnValue("\u221e");   //Do NOT remove the unicode format, if you replace it, it won't work
        }
    }
}

 */
