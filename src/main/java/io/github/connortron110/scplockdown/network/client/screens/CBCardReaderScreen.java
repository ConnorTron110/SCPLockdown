package io.github.connortron110.scplockdown.network.client.screens;

import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.network.ClientPacketHandler;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CBCardReaderScreen implements ISCPPacket {

	public final BlockPos pos;
	public final List<KeycardItem.Data> linkedCards;

	public CBCardReaderScreen(BlockPos pos, List<KeycardItem.Data> linkedCards) {
		this.pos = pos;
		this.linkedCards = linkedCards;
	}

	@SuppressWarnings("unused")
	public CBCardReaderScreen(FriendlyByteBuf buffer) {
		this.pos = buffer.readBlockPos();
		this.linkedCards = buffer.readCollection(ArrayList::new, friendlyByteBuf -> new KeycardItem.Data(friendlyByteBuf.readUtf(), friendlyByteBuf.readInt(), friendlyByteBuf.readUtf()));
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(pos);
		buffer.writeCollection(linkedCards, (friendlyByteBuf, data) -> {
			friendlyByteBuf.writeUtf(data.Name.getString());
			friendlyByteBuf.writeInt(data.Colour);
			friendlyByteBuf.writeUtf(data.Key);
		});
	}

	@Override
	public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleCardReaderScreen(this)));
		return true;
	}
}
