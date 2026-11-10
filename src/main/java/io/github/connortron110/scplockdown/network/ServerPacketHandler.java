package io.github.connortron110.scplockdown.network;

import io.github.connortron110.scplockdown.level.blockentity.CardReaderBlockEntity;
import io.github.connortron110.scplockdown.level.blockentity.ComputerBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.CardReaderBlock;
import io.github.connortron110.scplockdown.level.inventory.CardWriterMenu;
import io.github.connortron110.scplockdown.network.server.screens.SBCardReaderScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBCardWriterScreen;
import io.github.connortron110.scplockdown.network.server.screens.SBComputerScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public abstract class ServerPacketHandler {
	public static void handleCardReaderScreen(final SBCardReaderScreen packet, final IPayloadContext context) {
		Player sender = context.player();
		if (sender.level().getBlockEntity(packet.ReaderPos()) instanceof CardReaderBlockEntity cardReaderBE) {
			if (packet.ExitProgramming()) {
				sender.level().setBlockAndUpdate(packet.ReaderPos(), sender.level().getBlockState(packet.ReaderPos()).setValue(CardReaderBlock.PROG, false));
				return;
			}

			if (packet.IndexToDelete() >= 0) {
				cardReaderBE.removeEntry(packet.IndexToDelete());
			}
		}
	}

	public static void handleCardWriterScreen(final SBCardWriterScreen packet, final IPayloadContext context) {
		Player sender = context.player();
		AbstractContainerMenu container = sender.containerMenu;
		if (container.containerId == packet.ContainerID() && container instanceof CardWriterMenu cardWriterMenu) {
			cardWriterMenu.setItemInCardSlot(packet.CardStack());
			cardWriterMenu.broadcastChanges();
		}
	}

	public static void handleComputerScreen(final SBComputerScreen packet, final IPayloadContext context) {
		BlockEntity tileEntity = context.player().level().getBlockEntity(packet.Pos());
		if (tileEntity instanceof ComputerBlockEntity) {
			((ComputerBlockEntity) tileEntity).setData(packet.Url(), packet.Display());
		}
	}
}
