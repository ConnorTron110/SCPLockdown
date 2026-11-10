package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record CBConfusePlayerInput(boolean ConfuseInput) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBConfusePlayerInput> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbconfuseplayerinput"));

	public static final StreamCodec<ByteBuf, CBConfusePlayerInput> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			CBConfusePlayerInput::ConfuseInput,
			CBConfusePlayerInput::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
