package io.github.connortron110.scplockdown.level.effect;

import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * Neurotoxin effect caused by the 822 Cacti explosion
 */
public class SCP822Effect extends SCPEffect {
	public SCP822Effect(int color) {
		super(MobEffectCategory.HARMFUL, color, false, false);
	}

	@Override
	public void tick(LivingEntity living, int duration, int amplifier) {
		if (duration % 40 == 0) {
			living.hurt(SCPDamageTypes.source(living.level(), SCPDamageTypes.SCP822TOXIN), 2F);
		}
	}
}
