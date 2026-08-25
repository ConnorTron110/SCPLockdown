package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.level.blockentity.ComputerBlockEntity;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SBComputerScreen implements ISCPPacket {

	private final BlockPos pos;
	private final String url;
	private final String display;

	public SBComputerScreen(BlockPos pos, String url, String display) {
		this.pos = pos;
		this.url = url;
		this.display = display;
	}

	@SuppressWarnings("unused")
	public SBComputerScreen(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readUtf(), buffer.readUtf());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeUtf(url);
		buffer.writeUtf(display);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			BlockEntity tileEntity = context.getSender().level().getBlockEntity(pos);
			if (tileEntity instanceof ComputerBlockEntity) {
				((ComputerBlockEntity) tileEntity).setData(url, display);
			}
		});
		return true;
	}
}
