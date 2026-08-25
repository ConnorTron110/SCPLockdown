package io.github.connortron110.scplockdown.network.client.screens;

import io.github.connortron110.scplockdown.client.ClientScreenOpener;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBComputerScreen implements ISCPPacket {

	private final BlockPos pos;

	public CBComputerScreen(BlockPos pos) {
		this.pos = pos;
	}

	@SuppressWarnings("unused")
	public CBComputerScreen(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(pos);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> ClientScreenOpener.openComputerScreen(pos));
		return true;
	}
}
