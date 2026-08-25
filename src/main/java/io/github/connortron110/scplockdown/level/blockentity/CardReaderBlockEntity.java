package io.github.connortron110.scplockdown.level.blockentity;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.level.blocks.CardReaderBlock;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.screens.CBCardReaderScreen;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CardReaderBlockEntity extends BlockEntity {

	private static final String TIME = "Time";
	private static final String LINKED_CARDS = "LinkedCards";

	private short TicksOpen = 0;
	private final List<KeycardItem.Data> LinkedCards = Lists.newArrayList();

	public CardReaderBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.CARD_READER.get(), pPos, pState);
	}

	@Override
	public void load(@Nonnull CompoundTag pTag) {
		super.load(pTag);
		TicksOpen = pTag.getShort(TIME);

		ListTag cardList = pTag.getList(LINKED_CARDS, Tag.TAG_COMPOUND);
		for (int i = 0; i < cardList.size(); i++) {
			LinkedCards.add(new KeycardItem.Data(cardList.getCompound(i)));
		}
	}

	@Override
	protected void saveAdditional(@Nonnull CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putShort(TIME, TicksOpen);

		ListTag cardsList = new ListTag();
		for (KeycardItem.Data cardData : this.LinkedCards) {
			CompoundTag tag = new CompoundTag();
			cardData.setCompoundTag(tag);
			cardsList.add(tag);
		}

		pTag.put(LINKED_CARDS, cardsList);
	}

	public void removeEntry(int index) {
		LinkedCards.remove(index);
	}

	public static final Component CARD_LINKED = Component.translatable("scplockdown.blockentity.cardreader.linked").withStyle(ChatFormatting.DARK_GREEN);
	public static final Component CARD_ALREADY_LINKED = Component.translatable("scplockdown.blockentity.cardreader.alreadylinked").withStyle(ChatFormatting.GOLD);
	public static final Component CARD_BLANK = Component.translatable("scplockdown.blockentity.cardreader.blank").withStyle(ChatFormatting.GOLD);
	public static final Component ACCESS_DENIED = Component.translatable("scplockdown.blockentity.cardreader.accessdenied").withStyle(ChatFormatting.DARK_RED);


	/**
	 * Links a card to this Card Reader
	 */
	public void linkCard(ItemStack stack, Player player) {
		if (level == null) return;

		//  Edge case for Blank Card
		if (!KeycardItem.Data.hasData(stack)) {
			level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_FAIL.get(), SoundSource.BLOCKS, 1f, 1f);
			player.displayClientMessage(CARD_BLANK, true);
			return;
		}

		//  Check if this is the first card entry
		if (LinkedCards.isEmpty()) {
			LinkedCards.add(new KeycardItem.Data(stack));
			level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_SUCCESS.get(), SoundSource.BLOCKS, 1f, 1f);
			player.displayClientMessage(CARD_LINKED, true);
			return;
		}

		KeycardItem.Data newCard = new KeycardItem.Data(stack);
		for (KeycardItem.Data cardData : LinkedCards) {
			if (cardData.Key.equals(newCard.Key)) {
				//  Card already exists
				level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_FAIL.get(), SoundSource.BLOCKS, 1f, 1f);
				player.displayClientMessage(CARD_ALREADY_LINKED, true);
				return;
			}
		}

		LinkedCards.add(newCard);
		level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_SUCCESS.get(), SoundSource.BLOCKS, 1f, 1f);
		player.displayClientMessage(CARD_LINKED, true);
	}

	public boolean tryActivate(@Nullable ItemStack card, boolean override) {
		if (level == null) return false;

		if (override) {
			level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CardReaderBlock.POWERED, true));
			level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_SUCCESS.get(), SoundSource.BLOCKS, 1f, 1f);
			TicksOpen = 60;
			return true;
		}

		if (card != null && KeycardItem.isKeycard(card)) {
			KeycardItem.Data cardData = new KeycardItem.Data(card);

			//  Check if this keycard is a part of the linked cards
			for (KeycardItem.Data storedData : LinkedCards) {
				if (cardData.Key.equals(storedData.Key)) {
					level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CardReaderBlock.POWERED, true));
					level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_SUCCESS.get(), SoundSource.BLOCKS, 1f, 1f);
					TicksOpen = 60;
					return true;
				}
			}
		}

		level.playSound(null, getBlockPos(), SCPSounds.KEYCARD_FAIL.get(), SoundSource.BLOCKS, 1f, 1f);
		return false;
	}

	public void tick() {
		if (level == null) return;

		//  If the reader has somehow come out of programming without any cards, put it back into programming
		if (!getBlockState().getValue(CardReaderBlock.PROG) && LinkedCards.isEmpty()) {
			level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CardReaderBlock.PROG, true));
		}

		if (TicksOpen > 0) {
			TicksOpen--;
			if (TicksOpen == 0) {
				if (!level.isClientSide) {
					level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CardReaderBlock.POWERED, false));
					level.updateNeighborsAt(getBlockPos().relative(getBlockState().getValue(CardReaderBlock.FACING).getOpposite()), getBlockState().getBlock());
				}
			}
		}
	}

	public void openProgrammingScreen(ServerPlayer player) {
		SCPNetwork.NETWORK.send(PacketDistributor.PLAYER.with(() -> player), new CBCardReaderScreen(getBlockPos(), LinkedCards));
	}
}
