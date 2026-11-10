package io.github.connortron110.scplockdown.network.client.debug;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record CBLureDebugSync(List<BlockPos> BlockLurePositions) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBLureDebugSync> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbluredebugsync"));

	private static final StreamCodec<ByteBuf, List<BlockPos>> BLOCK_POS_LIST_CODEC = new StreamCodec<>() {
		@Override
		public @NotNull List<BlockPos> decode(ByteBuf buff) {
			List<BlockPos> list = Lists.newArrayList();
			int size = buff.readInt();
			for (int i = 0; i < size; i++) {
				list.add(BlockPos.of(buff.readLong()));
			}
			return list;
		}

		@Override
		public void encode(@NotNull ByteBuf buff, @NotNull List<BlockPos> blockPos) {
			buff.writeInt(blockPos.size());
			for (BlockPos positions : blockPos) {
				buff.writeLong(positions.asLong());
			}
		}
	};

	public static final StreamCodec<ByteBuf, CBLureDebugSync> STREAM_CODEC = StreamCodec.composite(
			BLOCK_POS_LIST_CODEC,
			CBLureDebugSync::BlockLurePositions,
			CBLureDebugSync::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
