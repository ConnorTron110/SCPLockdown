package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Used to stop player movement input from being registered, making the player unable to move.
 */
public record CBRestrictPlayerInput(boolean RestrictInput) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBRestrictPlayerInput> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbrestrictplayerinput"));

	public static final StreamCodec<ByteBuf, CBRestrictPlayerInput> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.BOOL,
			CBRestrictPlayerInput::RestrictInput,
			CBRestrictPlayerInput::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
