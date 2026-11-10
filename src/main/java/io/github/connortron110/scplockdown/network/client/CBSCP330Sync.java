package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record CBSCP330Sync(UUID PlayerUUID, int CandiesTaken, long CandiesTimeTaken) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBSCP330Sync> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbscp330sync"));

	public static final StreamCodec<ByteBuf, CBSCP330Sync> STREAM_CODEC = StreamCodec.composite(
			UUIDUtil.STREAM_CODEC,
			CBSCP330Sync::PlayerUUID,
			ByteBufCodecs.INT,
			CBSCP330Sync::CandiesTaken,
			ByteBufCodecs.VAR_LONG,
			CBSCP330Sync::CandiesTimeTaken,
			CBSCP330Sync::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
