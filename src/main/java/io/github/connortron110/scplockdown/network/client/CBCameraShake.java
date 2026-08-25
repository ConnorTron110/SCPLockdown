package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.client.camerashake.CameraShakeEvent;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBCameraShake implements ISCPPacket {

	private final float intensity;
	private final int ticks;

	/**
	 * Causes a camera shake event to happen on the client this gets sent to
	 *
	 * @param intensity Controls how many degrees the camera can move in any direction (5 means +5, -5 Degrees off current view)
	 * @param ticks     Controls how long the shake lasts in ticks
	 */
	public CBCameraShake(float intensity, int ticks) {
		this.intensity = intensity;
		this.ticks = ticks;
	}

	@SuppressWarnings("unused")
	public CBCameraShake(FriendlyByteBuf buffer) {
		this(buffer.readFloat(), buffer.readInt());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeFloat(intensity);
		buffer.writeInt(ticks);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> CameraShakeEvent.shakeCamera(intensity, ticks));
		return true;
	}
}
