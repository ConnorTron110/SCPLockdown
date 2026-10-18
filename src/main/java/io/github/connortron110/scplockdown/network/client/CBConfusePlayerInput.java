package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record CBConfusePlayerInput(boolean confuseInput) implements ISCPPacket {

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
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleConfusePlayerInput(this)));
		return true;
	}
}
