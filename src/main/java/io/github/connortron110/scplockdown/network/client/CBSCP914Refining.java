package io.github.connortron110.scplockdown.network.client;

import io.github.connortron110.scplockdown.events.tickingsound.SCP914TickingSoundInstance;
import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CBSCP914Refining implements ISCPPacket {

	private final BlockPos SCP914Pos;
	private final int CraftingTime;

	public CBSCP914Refining(BlockPos scp914Pos, int craftingTime) {
		this.SCP914Pos = scp914Pos;
		this.CraftingTime = craftingTime;
	}

	@SuppressWarnings("unused")
	public CBSCP914Refining(FriendlyByteBuf buffer) {
		this(BlockPos.of(buffer.readLong()), buffer.readInt());
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeLong(SCP914Pos.asLong());
		buffer.writeInt(CraftingTime);
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if (Minecraft.getInstance().level.getBlockEntity(SCP914Pos) instanceof SCP914BlockEntity scp914BlockEntity) {
				scp914BlockEntity.CraftingTime = CraftingTime;
				Minecraft.getInstance().getSoundManager().play(new SCP914TickingSoundInstance(scp914BlockEntity));
			}
		});
		return true;
	}
}
