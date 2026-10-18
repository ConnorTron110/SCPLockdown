package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBSCP023Howl implements ISCPPacket {

	public final int SCP023Id;

	public CBSCP023Howl(SCP023Entity scp023) {
		this.SCP023Id = scp023.getId();
	}

	@SuppressWarnings("unused")
	public CBSCP023Howl(FriendlyByteBuf buffer) {
		this.SCP023Id = buffer.readInt();
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(SCP023Id);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleSCP023Howl(this)));
		return true;
	}
}
