package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record CBSCP023Howl(int SCP023Id) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBSCP023Howl> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbscp023howl"));

	public static final StreamCodec<ByteBuf, CBSCP023Howl> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			CBSCP023Howl::SCP023Id,
			CBSCP023Howl::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
