package io.github.connortron110.scplockdown.network.client.debug;

import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CBLureDebugSync implements ISCPPacket {
	private final List<BlockPos> blockLurePositions;

	public CBLureDebugSync(List<BlockPos> positions) {
		this.blockLurePositions = positions;
	}

	@SuppressWarnings("unused")
	public CBLureDebugSync(FriendlyByteBuf buffer) {
		this(Arrays.stream(buffer.readLongArray(null)).mapToObj(BlockPos::of).collect(Collectors.toList()));
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeLongArray(blockLurePositions.stream().mapToLong(BlockPos::asLong).toArray());
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		//context.enqueueWork(() -> LureDebugRenderer.addBlockLurePositions(blockLurePositions));
		return true;
	}
}
