package io.github.connortron110.scplockdown.network;

import io.github.connortron110.scplockdown.client.camerashake.CameraShakeEvent;
import io.github.connortron110.scplockdown.client.gui.screen.CardReaderScreen;
import io.github.connortron110.scplockdown.client.gui.screen.ComputerScreen;
import io.github.connortron110.scplockdown.events.ClientEvents;
import io.github.connortron110.scplockdown.events.tickingsound.SCP023HowlTickingSoundInstance;
import io.github.connortron110.scplockdown.events.tickingsound.SCP914TickingSoundInstance;
import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.level.blocks.SCP330Block;
import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import io.github.connortron110.scplockdown.network.client.*;
import io.github.connortron110.scplockdown.network.client.debug.CBLureDebugSync;
import io.github.connortron110.scplockdown.network.client.screens.CBCardReaderScreen;
import io.github.connortron110.scplockdown.network.client.screens.CBComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

/**
 * Handles all client bound packets to avoid server from loading any client related functions.
 */
public abstract class ClientPacketHandler {

	public static void lureDebugSync(final CBLureDebugSync packet, final IPayloadContext context) {
		//LureDebugRenderer.addBlockLurePositions(blockLurePositions);
	}

	public static void handleCardReaderScreen(final CBCardReaderScreen packet, final IPayloadContext context) {
		Minecraft.getInstance().setScreen(new CardReaderScreen(packet.Pos(), packet.LinkedCards()));
	}

	public static void handleComputerScreen(final CBComputerScreen packet, final IPayloadContext context) {
		Minecraft.getInstance().setScreen(new ComputerScreen(packet.Pos()));
	}

	public static void handleCameraShake(final CBCameraShake packet, final IPayloadContext context) {
		CameraShakeEvent.shakeCamera(packet.Intensity(), packet.Ticks());
	}

	public static void handleConfusePlayerInput(final CBConfusePlayerInput packet, final IPayloadContext context) {
		ClientEvents.shouldConfuseInput = packet.ConfuseInput();
	}

	public static void handlePlayerMovementSync(final CBPlayerMovementSync packet, final IPayloadContext context) {
		LocalPlayer player = Minecraft.getInstance().player;
		player.xxa = packet.aX();
		player.zza = packet.aZ();
		player.setDeltaMovement(player.getDeltaMovement().add(packet.aX(), 0, packet.aZ()));
	}

	public static void handleRestrictPlayerInput(final CBRestrictPlayerInput packet, final IPayloadContext context) {
		ClientEvents.shouldCancelInput = packet.RestrictInput();
	}

	public static void handleSCP023Howl(final CBSCP023Howl packet, final IPayloadContext context) {
		Minecraft.getInstance().getSoundManager().play(new SCP023HowlTickingSoundInstance((SCP023Entity) Minecraft.getInstance().level.getEntity(packet.SCP023Id())));
	}

	public static void handleSCP330Sync(final CBSCP330Sync packet, final IPayloadContext context) {
		Optional<AbstractClientPlayer> targetPlayer = Minecraft.getInstance().level.players().stream().filter(player -> player.getUUID().equals(packet.PlayerUUID())).findFirst();
		if (targetPlayer.isPresent()) {
			targetPlayer.get().getPersistentData().putInt(SCP330Block.CANDIES_KEY, packet.CandiesTaken());
			targetPlayer.get().getPersistentData().putLong(SCP330Block.CANDIES_TIME_KEY, packet.CandiesTimeTaken());
		}
	}

	public static void handleSCP914Refining(final CBSCP914Refining packet, final IPayloadContext context) {
		if (Minecraft.getInstance().level.getBlockEntity(packet.SCP914Pos()) instanceof SCP914BlockEntity scp914BlockEntity) {
			scp914BlockEntity.CraftingTime = packet.CraftingTime();
			Minecraft.getInstance().getSoundManager().play(new SCP914TickingSoundInstance(scp914BlockEntity));
		}
	}
}
