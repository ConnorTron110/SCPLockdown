package io.github.connortron110.scplockdown.network;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.network.client.*;
import io.github.connortron110.scplockdown.network.client.debug.CBLureDebugSync;
import io.github.connortron110.scplockdown.network.client.screens.CBCardReaderScreen;
import io.github.connortron110.scplockdown.network.client.screens.CBComputerScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBCardReaderScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBCardWriterScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBComputerScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = SCPLockdown.MOD_ID)
public class SCPNetwork {

	private static final String NETWORK_VERSION = "0.0.1";

	@SubscribeEvent
	public static void registerPackets(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(NETWORK_VERSION);

		//	Client Bound
		registrar.playToClient(CBLureDebugSync.TYPE, CBLureDebugSync.STREAM_CODEC, ClientPacketHandler::lureDebugSync);
		registrar.playToClient(CBCardReaderScreen.TYPE, CBCardReaderScreen.STREAM_CODEC, ClientPacketHandler::handleCardReaderScreen);
		registrar.playToClient(CBComputerScreen.TYPE, CBComputerScreen.STREAM_CODEC, ClientPacketHandler::handleComputerScreen);
		registrar.playToClient(CBCameraShake.TYPE, CBCameraShake.STREAM_CODEC, ClientPacketHandler::handleCameraShake);
		registrar.playToClient(CBConfusePlayerInput.TYPE, CBConfusePlayerInput.STREAM_CODEC, ClientPacketHandler::handleConfusePlayerInput);
		registrar.playToClient(CBPlayerMovementSync.TYPE, CBPlayerMovementSync.STREAM_CODEC, ClientPacketHandler::handlePlayerMovementSync);
		registrar.playToClient(CBRestrictPlayerInput.TYPE, CBRestrictPlayerInput.STREAM_CODEC, ClientPacketHandler::handleRestrictPlayerInput);
		registrar.playToClient(CBSCP023Howl.TYPE, CBSCP023Howl.STREAM_CODEC, ClientPacketHandler::handleSCP023Howl);
		registrar.playToClient(CBSCP330Sync.TYPE, CBSCP330Sync.STREAM_CODEC, ClientPacketHandler::handleSCP330Sync);
		registrar.playToClient(CBSCP914Refining.TYPE, CBSCP914Refining.STREAM_CODEC, ClientPacketHandler::handleSCP914Refining);

		//	Server Bound
		registrar.playToServer(SBCardReaderScreen.TYPE, SBCardReaderScreen.STREAM_CODEC, ServerPacketHandler::handleCardReaderScreen);
		registrar.playToServer(SBCardWriterScreen.TYPE, SBCardWriterScreen.STREAM_CODEC, ServerPacketHandler::handleCardWriterScreen);
		registrar.playToServer(SBComputerScreen.TYPE, SBComputerScreen.STREAM_CODEC, ServerPacketHandler::handleComputerScreen);
	}
}
