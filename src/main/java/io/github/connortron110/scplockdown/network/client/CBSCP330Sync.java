package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public record CBSCP330Sync(UUID PlayerUUID, int CandiesTaken, long CandiesTimeTaken) implements ISCPPacket {

	@SuppressWarnings("unused")
	public CBSCP330Sync(FriendlyByteBuf buffer) {
		this(buffer.readUUID(), buffer.readInt(), buffer.readLong());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(this.PlayerUUID);
		buffer.writeInt(this.CandiesTaken);
		buffer.writeLong(this.CandiesTimeTaken);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleSCP330Sync(this)));
		return true;
	}
}
