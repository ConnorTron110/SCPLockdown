package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record CBSCP914Refining(BlockPos SCP914Pos, int CraftingTime) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBSCP914Refining> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbscp914refining"));

	public static final StreamCodec<ByteBuf, CBSCP914Refining> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			CBSCP914Refining::SCP914Pos,
			ByteBufCodecs.INT,
			CBSCP914Refining::CraftingTime,
			CBSCP914Refining::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
