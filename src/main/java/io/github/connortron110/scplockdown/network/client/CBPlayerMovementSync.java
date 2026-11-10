package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record CBPlayerMovementSync(float aX, float aZ) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBPlayerMovementSync> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbplayermovementsync"));

	public static final StreamCodec<ByteBuf, CBPlayerMovementSync> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.FLOAT,
			CBPlayerMovementSync::aX,
			ByteBufCodecs.FLOAT,
			CBPlayerMovementSync::aZ,
			CBPlayerMovementSync::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
