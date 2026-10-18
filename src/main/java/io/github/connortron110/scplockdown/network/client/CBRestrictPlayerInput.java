package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Used to stop player movement input from being registered, making the player unable to move.
 */
public record CBRestrictPlayerInput(boolean restrictInput) implements ISCPPacket {

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
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleRestrictPlayerInput(this)));
		return true;
	}
}
