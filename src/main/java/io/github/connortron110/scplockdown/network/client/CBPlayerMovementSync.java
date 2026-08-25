package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBPlayerMovementSync implements ISCPPacket {

	private final float aX;
	private final float aZ;

	public CBPlayerMovementSync(float aX, float aZ) {
		this.aX = aX;
		this.aZ = aZ;
	}

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
		context.enqueueWork(() -> {
			LocalPlayer player = Minecraft.getInstance().player;
			player.xxa = aX;
			player.zza = aZ;
			player.setDeltaMovement(player.getDeltaMovement().add(aX, 0, aZ));
		});
		return true;
	}
}
