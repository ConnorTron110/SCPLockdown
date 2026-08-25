package io.github.connortron110.scplockdown.level.effect;

import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SCP330Effect extends SCPEffect {
	public SCP330Effect(int color) {
		super(MobEffectCategory.HARMFUL, color, true, true);
	}

	@Override
	public void tick(LivingEntity living, int duration, int amplifier) {
		if (living.level().getGameTime() % 20 == 0) {
			living.hurt(SCPDamageTypes.source(living.level(), SCPDamageTypes.SCP330NOARMS), 2F);
		}
	}
}
