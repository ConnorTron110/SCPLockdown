package io.github.connortron110.scplockdown.network.client.screens;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record CBComputerScreen(BlockPos Pos) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBComputerScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbcomputerscreen"));

	public static final StreamCodec<ByteBuf, CBComputerScreen> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			CBComputerScreen::Pos,
			CBComputerScreen::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
