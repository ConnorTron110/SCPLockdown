package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.level.blockentity.CardReaderBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.CardReaderBlock;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SBCardReaderScreen implements ISCPPacket {

	private final BlockPos ReaderPos;
	private final boolean ExitProgramming;
	private final int IndexToDelete;

	public SBCardReaderScreen(BlockPos cardReaderPos, boolean exitProgramming, int indexToDelete) {
		this.ReaderPos = cardReaderPos;
		this.ExitProgramming = exitProgramming;
		this.IndexToDelete = indexToDelete;
	}

	@SuppressWarnings("unused")
	public SBCardReaderScreen(FriendlyByteBuf buffer) {
		this(buffer.readBlockPos(), buffer.readBoolean(), buffer.readInt());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(ReaderPos);
		buffer.writeBoolean(ExitProgramming);
		buffer.writeInt(IndexToDelete);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			ServerPlayer sender = context.getSender();
			if (sender.level().getBlockEntity(ReaderPos) instanceof CardReaderBlockEntity cardReaderBE) {
				if (ExitProgramming) {
					sender.level().setBlockAndUpdate(ReaderPos, sender.level().getBlockState(ReaderPos).setValue(CardReaderBlock.PROG, false));
					return;
				}

				if (IndexToDelete >= 0) {
					cardReaderBE.removeEntry(IndexToDelete);
				}
			}
		});
		return true;
	}
}
