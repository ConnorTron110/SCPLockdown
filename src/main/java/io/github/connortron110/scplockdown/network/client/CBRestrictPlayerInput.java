package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.events.ClientEvents;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Used to stop player movement input from being registered, making the player unable to move.
 */
public class CBRestrictPlayerInput implements ISCPPacket {

	private final boolean restrictInput;

	public CBRestrictPlayerInput(boolean restrictInput) {
		this.restrictInput = restrictInput;
	}

	@SuppressWarnings("unused")
	public CBRestrictPlayerInput(FriendlyByteBuf buffer) {
		this(buffer.readBoolean());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBoolean(restrictInput);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> ClientEvents.shouldCancelInput = restrictInput);
		return true;
	}
}
