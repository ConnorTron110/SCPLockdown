package io.github.connortron110.scplockdown.network.server.screens;

import io.github.connortron110.scplockdown.level.inventory.CardWriterMenu;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SBCardWriterScreen implements ISCPPacket {

	private final int ContainerID;
	private final ItemStack CardStack;

	public SBCardWriterScreen(int containerId, ItemStack cardStack) {
		this.ContainerID = containerId;
		this.CardStack = cardStack;
	}

	@SuppressWarnings("unused")
	public SBCardWriterScreen(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readItem());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(ContainerID);
		buffer.writeItemStack(CardStack, false);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			ServerPlayer sender = context.getSender();
			AbstractContainerMenu container = sender.containerMenu;
			if (container.containerId == this.ContainerID && container instanceof CardWriterMenu cardWriterMenu) {
				cardWriterMenu.setItemInCardSlot(CardStack);
				cardWriterMenu.broadcastChanges();
			}
		});
		return true;
	}
}
