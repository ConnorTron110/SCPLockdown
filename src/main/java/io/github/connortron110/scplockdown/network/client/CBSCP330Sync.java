package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.level.blocks.SCP330Block;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class CBSCP330Sync implements ISCPPacket {

	private final UUID PlayerUUID;
	private final int CandiesTaken;
	private final long CandiesTimeTaken;

	public CBSCP330Sync(UUID playerUUID, int candiesTaken, long CandiesTimeTaken) {
		this.PlayerUUID = playerUUID;
		this.CandiesTaken = candiesTaken;
		this.CandiesTimeTaken = CandiesTimeTaken;
	}

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
		context.enqueueWork(() -> {
			Optional<AbstractClientPlayer> targetPlayer = Minecraft.getInstance().level.players().stream().filter(player -> player.getUUID().equals(PlayerUUID)).findFirst();
			if (targetPlayer.isPresent()) {
				targetPlayer.get().getPersistentData().putInt(SCP330Block.CANDIES_KEY, this.CandiesTaken);
				targetPlayer.get().getPersistentData().putLong(SCP330Block.CANDIES_TIME_KEY, this.CandiesTimeTaken);
			}
		});
		return true;
	}
}
