package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Causes a camera shake event to happen on the client this gets sent to
 *
 * @param Intensity Controls how many degrees the camera can move in any direction (5 means +5, -5 Degrees off current view)
 * @param Ticks     Controls how long the shake lasts in ticks
 */
public record CBCameraShake(float Intensity, int Ticks) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBCameraShake> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbcamerashake"));

	public static final StreamCodec<ByteBuf, CBCameraShake> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.FLOAT,
			CBCameraShake::Intensity,
			ByteBufCodecs.INT,
			CBCameraShake::Ticks,
			CBCameraShake::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
