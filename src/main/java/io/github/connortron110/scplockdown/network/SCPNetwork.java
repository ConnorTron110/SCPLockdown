package io.github.connortron110.scplockdown.network;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.network.client.*;
import io.github.connortron110.scplockdown.network.client.debug.CBLureDebugSync;
import io.github.connortron110.scplockdown.network.client.screens.CBCardReaderScreen;
import io.github.connortron110.scplockdown.network.client.screens.CBComputerScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBCardReaderScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBCardWriterScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBComputerScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.lang.reflect.InvocationTargetException;

@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCPNetwork {

	private static final String NETWORK_VERSION = "0.0.1";
	public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "network")
			, () -> NETWORK_VERSION, NETWORK_VERSION::equals, NETWORK_VERSION::equals);

	@SubscribeEvent
	public static void networkSetup(FMLCommonSetupEvent event) {
		registerPacket(CBPlayerMovementSync.class, NetworkDirection.PLAY_TO_CLIENT);
		registerPacket(CBRestrictPlayerInput.class, NetworkDirection.PLAY_TO_CLIENT);
		registerPacket(CBConfusePlayerInput.class, NetworkDirection.PLAY_TO_CLIENT);

		registerPacket(CBLureDebugSync.class, NetworkDirection.PLAY_TO_CLIENT);

		registerPacket(CBComputerScreen.class, NetworkDirection.PLAY_TO_CLIENT);
		registerPacket(SBComputerScreen.class, NetworkDirection.PLAY_TO_SERVER);

		registerPacket(CBCardReaderScreen.class, NetworkDirection.PLAY_TO_CLIENT);
		registerPacket(SBCardReaderScreen.class, NetworkDirection.PLAY_TO_SERVER);

		registerPacket(SBCardWriterScreen.class, NetworkDirection.PLAY_TO_SERVER);

		registerPacket(CBCameraShake.class, NetworkDirection.PLAY_TO_CLIENT);

		registerPacket(CBSCP023Howl.class, NetworkDirection.PLAY_TO_CLIENT);
		registerPacket(CBSCP330Sync.class, NetworkDirection.PLAY_TO_CLIENT);
	}

	private static int id = 0;

	private static <MSG extends ISCPPacket> void registerPacket(Class<MSG> packetClass, NetworkDirection networkDirection) {
		SimpleChannel.MessageBuilder<MSG> builder = NETWORK.messageBuilder(packetClass, id++, networkDirection);
		builder.encoder(ISCPPacket::encode);
		builder.decoder(packetBuffer -> {
			try {
				return packetClass.getConstructor(FriendlyByteBuf.class).newInstance(packetBuffer);
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
			         InvocationTargetException e) {
				//  In Theory these should NEVER catch, and if it does, that should indicate faulty system and or installation
				SCPLockdown.LOGGER.error("An Error occurred on {} Packet", packetClass.getName());
				SCPLockdown.LOGGER.error("Either there is no decoder or some unexpected error happened");
				throw new RuntimeException(e);
			}
		});
		builder.consumerNetworkThread(ISCPPacket::consume);
		builder.add();
	}
}
