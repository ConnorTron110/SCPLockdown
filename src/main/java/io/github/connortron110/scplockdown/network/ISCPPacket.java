package io.github.connortron110.scplockdown.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

//  Would be nice to have a required "decode" method however cant think of a way to do it safely
public interface ISCPPacket {
	void encode(FriendlyByteBuf buffer);

	boolean consume(Supplier<NetworkEvent.Context> contextSupplier);
}
