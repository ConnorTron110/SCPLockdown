package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public record SBCardWriterScreen(int ContainerID, ItemStack CardStack) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<SBCardWriterScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "sbcardwriterscreen"));

	public static final StreamCodec<RegistryFriendlyByteBuf, SBCardWriterScreen> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			SBCardWriterScreen::ContainerID,
			ItemStack.STREAM_CODEC,
			SBCardWriterScreen::CardStack,
			SBCardWriterScreen::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
