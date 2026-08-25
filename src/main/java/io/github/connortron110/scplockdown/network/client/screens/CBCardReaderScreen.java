package io.github.connortron110.scplockdown.network.client.screens;

import io.github.connortron110.scplockdown.client.ClientScreenOpener;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.network.ISCPPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CBCardReaderScreen implements ISCPPacket {

	private final BlockPos pos;
	private final List<KeycardItem.Data> linkedCards;

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
		context.enqueueWork(() -> ClientScreenOpener.openCardReaderScreen(pos, linkedCards));
		return true;
	}
}
