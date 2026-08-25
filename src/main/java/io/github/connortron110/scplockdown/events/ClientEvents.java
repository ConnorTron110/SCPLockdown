package io.github.connortron110.scplockdown.events;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.blocks.SCP330Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SCPLockdown.MOD_ID)
public class ClientEvents {

	public static boolean shouldCancelInput = false; //Used by Lures to Cancel the players movement
	public static boolean shouldConfuseInput = false;

	@SubscribeEvent
	public static void mouseInputEvent(InputEvent event) {
		if (shouldCancelInput && event.isCancelable()) {
			if (Minecraft.getInstance().screen == null) {
				event.setCanceled(true);
			}
		}
	}

/*
    @SubscribeEvent
    public static void movementInputEvent(InputUpdateEvent event) {
        if (shouldCancelInput) {
            //Impulse actually controls the movement
            event.getMovementInput().leftImpulse = 0F;
            event.getMovementInput().forwardImpulse = 0F;
            event.getMovementInput().jumping = false;
            event.getMovementInput().shiftKeyDown = false;
        }

        if (shouldConfuseInput) {
            boolean jumping = event.getMovementInput().jumping;
            boolean shiftKeyDown = event.getMovementInput().shiftKeyDown;
            boolean up = event.getMovementInput().up;
            boolean down = event.getMovementInput().down;
            boolean left = event.getMovementInput().left;
            boolean right = event.getMovementInput().right;

            event.getMovementInput().shiftKeyDown = jumping;
            event.getMovementInput().jumping = shiftKeyDown;
            event.getMovementInput().down = up;
            event.getMovementInput().up = down;
            event.getMovementInput().right = left;
            event.getMovementInput().left = right;
        }
    }

 */

	@SubscribeEvent
	public static void renderHandEvent(RenderHandEvent event) {
		if (Minecraft.getInstance().player == null) return;
		if (SCP330Block.hasPlayerTakenTooManyCandies(Minecraft.getInstance().player)) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void mouseInputEvent(InputEvent.MouseButton.Pre event) {
		if (Minecraft.getInstance().player == null) return;
		if (Minecraft.getInstance().screen != null) return;
		if (SCP330Block.hasPlayerTakenTooManyCandies(Minecraft.getInstance().player)) {
			//  Determine if player is pressing the mouse, allow them if they are releasing
			if (event.getAction() == 1) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void renderPlayerEvent(RenderPlayerEvent.Pre event) {
		if (SCP330Block.hasPlayerTakenTooManyCandies(event.getEntity())) {
			event.getRenderer().getModel().rightArm.visible = false;
			event.getRenderer().getModel().leftArm.visible = false;
			event.getRenderer().getModel().rightSleeve.visible = false;
			event.getRenderer().getModel().leftSleeve.visible = false;
			//  TODO: Prevent item from rendering
		}
	}

	@SubscribeEvent
	public static void clientLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
		SCPLockdown.LOGGER.debug("Resetting all input modification events");
		shouldCancelInput = false;
		shouldConfuseInput = false;
	}
}
