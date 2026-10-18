package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record CBPlayerMovementSync(float aX, float aZ) implements ISCPPacket {

	@SuppressWarnings("unused")
	public CBPlayerMovementSync(FriendlyByteBuf buffer) {
		this(buffer.readFloat(), buffer.readFloat());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeFloat(aX);
		buffer.writeFloat(aZ);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handlePlayerMovementSync(this)));
		return true;
	}
}
