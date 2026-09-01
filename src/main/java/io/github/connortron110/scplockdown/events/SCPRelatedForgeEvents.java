package io.github.connortron110.scplockdown.events;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.items.SCP035MaskItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * All events here are dedicated to SCP Related events, this will grow as new scp's are added. <br>
 * All events are to be treated as common events
 */
@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SCPRelatedForgeEvents {
	@SubscribeEvent
	public static void scp035(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SCP035MaskItem) {
			if (!entity.level().isClientSide) {

				//	TODO: decrease health overtime to simulate decay

				//	Changes expression
				if (SCP035MaskItem.shouldChange(entity.getItemBySlot(EquipmentSlot.HEAD))) {
					SCP035MaskItem.changeExpression(entity.getItemBySlot(EquipmentSlot.HEAD));
				}
			}
		}
	}
}
