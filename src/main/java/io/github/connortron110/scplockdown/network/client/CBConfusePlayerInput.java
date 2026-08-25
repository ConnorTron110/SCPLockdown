package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.events.ClientEvents;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBConfusePlayerInput implements ISCPPacket {

	private final boolean confuseInput;

	public CBConfusePlayerInput(boolean confuseInput) {
		this.confuseInput = confuseInput;
	}

	@SuppressWarnings("unused")
	public CBConfusePlayerInput(FriendlyByteBuf buffer) {
		this(buffer.readBoolean());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBoolean(confuseInput);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> ClientEvents.shouldConfuseInput = confuseInput);
		return true;
	}
}
