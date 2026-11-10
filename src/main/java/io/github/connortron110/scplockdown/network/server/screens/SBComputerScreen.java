package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SBComputerScreen(BlockPos Pos, String Url, String Display) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<SBComputerScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "sbcomputerscreen"));

	public static final StreamCodec<ByteBuf, SBComputerScreen> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			SBComputerScreen::Pos,
			ByteBufCodecs.STRING_UTF8,
			SBComputerScreen::Url,
			ByteBufCodecs.STRING_UTF8,
			SBComputerScreen::Display,
			SBComputerScreen::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
