package io.github.connortron110.scplockdown.level.items.biocontainer;

import io.github.connortron110.scplockdown.level.effect.ZombismEffect;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Used by the syringes to do logic when a syringe is used on an entity
 */
public interface IBioUseOnEntity {
	/**
	 * When a bio container like a syringe is used on an entity with the intent to effect the entity in some way
	 *
	 * @param level  the level the interaction is taking place
	 * @param player the player applying the effect
	 * @param living the entity the effect is going to be applied to
	 */
	void accept(Level level, Player player, LivingEntity living);

	IBioUseOnEntity EMPTY = (level, player, living) -> {
	}; //Empty does nothing
	IBioUseOnEntity SCP008 = (level, player, living) -> {
		if (!living.hasEffect(SCPEffects.SCP008_ZOMBISM.get())) {
			if (ZombismEffect.canBeInfected(living)) {
				living.addEffect(ZombismEffect.getDefaultInstance());
			} else {
				player.sendSystemMessage(Component.literal("REPLACE FOR TRANSLATION").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)); //TODO replace for translation and add entity as an argument
				player.sendSystemMessage(Component.literal("XYZ Is immune to the effects of SCP-008"));
			}
		}
	};
}
