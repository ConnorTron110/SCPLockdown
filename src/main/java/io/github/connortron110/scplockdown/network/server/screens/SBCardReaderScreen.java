package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SBCardReaderScreen(BlockPos ReaderPos, boolean ExitProgramming, int IndexToDelete) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<SBCardReaderScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "sbcardreaderscreen"));

	public static final StreamCodec<ByteBuf, SBCardReaderScreen> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			SBCardReaderScreen::ReaderPos,
			ByteBufCodecs.BOOL,
			SBCardReaderScreen::ExitProgramming,
			ByteBufCodecs.INT,
			SBCardReaderScreen::IndexToDelete,
			SBCardReaderScreen::new
	);

	@Override
	public @NotNull CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
