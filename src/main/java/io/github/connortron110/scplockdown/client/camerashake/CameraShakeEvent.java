package io.github.connortron110.scplockdown.client.camerashake;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//	TODO Update this for location and distance based instead of just instantaneous
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SCPLockdown.MOD_ID)
public class CameraShakeEvent {

	//  Runtime variables that get updated
	//  Shake intensity has a rapid fall off starting about when 1/5 of the tick time remains, the fall off is exponential and non-linear
	private static float InitialIntensity = 0F;
	private static float ShakeIntensity = 0F;
	private static int TicksToStop = 0;
	private static int TicksCounted = 0;

	public static void shakeCamera(float intensity, int ticks) {
		//  Only add a new shake if the new intensity is higher than the current one (or the old one has expired)
		if (ShakeIntensity > intensity) return;

		TicksCounted = 0;
		TicksToStop = ticks;
		InitialIntensity = intensity;
		ShakeIntensity = InitialIntensity;
	}

	/**
	 * This event doesn't straight up override where the player is actually looking, but instead what the camera sees.
	 */
	@SubscribeEvent
	public static void cameraSetup(ViewportEvent.ComputeCameraAngles event) {
		if (ShakeIntensity > 0F) {
			RandomSource rand = event.getCamera().getEntity().level().random;
			event.setPitch(event.getPitch() + ((rand.nextFloat() - 0.5F) * ShakeIntensity));
			event.setYaw(event.getYaw() + ((rand.nextFloat() - 0.5F) * ShakeIntensity));
			event.setRoll(event.getRoll() + ((rand.nextFloat() - 0.5F) * ShakeIntensity));
		}
	}

	/**
	 * This event is used to control the camera shake intensity fall off.
	 * <a href="https://www.desmos.com/calculator/5whlpesjag">This graph will explain it well.</a>
	 */
	@SubscribeEvent
	public static void playerTicker(TickEvent.PlayerTickEvent event) {
		//  Prevent ticks from different phases, sides AND from other players other than this player
		if (event.phase.equals(TickEvent.Phase.END) || event.side.isServer() || !(event.player instanceof LocalPlayer))
			return;

		if (ShakeIntensity > 0F) {
			ShakeIntensity = InitialIntensity * (1 - ((float) Math.pow(((float) TicksCounted++ / TicksToStop), 2)));
		}
	}
}
