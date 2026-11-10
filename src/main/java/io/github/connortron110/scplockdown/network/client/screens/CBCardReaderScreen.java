package io.github.connortron110.scplockdown.network.client.screens;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record CBCardReaderScreen(BlockPos Pos, List<KeycardItem.Data> LinkedCards) implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<CBCardReaderScreen> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "cbcardreaderscreen"));

	//	TODO: Possibly optimize to use ByteBufCodecs.collection
	private static final StreamCodec<ByteBuf, List<KeycardItem.Data>> CARD_DATA_CODEC = new StreamCodec<>() {
		@Override
		public @NotNull List<KeycardItem.Data> decode(ByteBuf buff) {
			List<KeycardItem.Data> list = Lists.newArrayList();
			int size = buff.readInt();
			for (int i = 0; i < size; i++) {
				list.add(new KeycardItem.Data(ByteBufCodecs.STRING_UTF8.decode(buff), buff.readInt(), ByteBufCodecs.STRING_UTF8.decode(buff)));
			}
			return list;
		}

		@Override
		public void encode(@NotNull ByteBuf buff, @NotNull List<KeycardItem.Data> storedKeycards) {
			buff.writeInt(storedKeycards.size());
			for (KeycardItem.Data data : storedKeycards) {
				ByteBufCodecs.STRING_UTF8.encode(buff, data.Name.getString());
				buff.writeInt(data.Colour);
				ByteBufCodecs.STRING_UTF8.encode(buff, data.Key);
			}
		}
	};

	public static final StreamCodec<ByteBuf, CBCardReaderScreen> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC,
			CBCardReaderScreen::Pos,
			CARD_DATA_CODEC,
			CBCardReaderScreen::LinkedCards,
			CBCardReaderScreen::new
	);

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
