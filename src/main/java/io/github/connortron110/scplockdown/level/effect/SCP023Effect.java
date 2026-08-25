package io.github.connortron110.scplockdown.level.effect;

import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class SCP023Effect extends SCPEffect {
	public SCP023Effect(int color) {
		super(MobEffectCategory.HARMFUL, color, false, false);
	}

	@Override
	public void tick(LivingEntity living, int duration, int amplifier) {

	}

	@Override
	public void lastTick(LivingEntity living, int amplifier) {
		living.hurt(SCPDamageTypes.source(living.level(), SCPDamageTypes.SCP023EXPIRE), living.getMaxHealth());
	}

	public static MobEffectInstance getDefaultInstance() {
		return new MobEffectInstance(SCPEffects.SCP023_CURSE.get(), 60 * 60 * 5);
	}
}
