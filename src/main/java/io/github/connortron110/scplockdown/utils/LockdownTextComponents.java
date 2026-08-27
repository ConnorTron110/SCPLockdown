package io.github.connortron110.scplockdown.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public abstract class LockdownTextComponents {
	public static final MutableComponent BLOCK_INVALID_PLACEMENT = Component.translatable("lockdownBlock.invalidPlacement").withStyle(ChatFormatting.RED);
	public static final MutableComponent BLAST_DOOR_TOO_HIGH = Component.translatable("blastdoor.tooHigh").withStyle(ChatFormatting.RED);

	public static final MutableComponent SCREWDRIVER_HAS_TAG = Component.translatable("scplockdown.screwdriver.hastag");
	public static final MutableComponent SCREWDRIVER_TAGS_CLEAR = Component.translatable("scplockdown.screwdriver.cleartags");
	public static final MutableComponent SCREWDRIVER_TAGS_EMPTY = Component.translatable("scplockdown.screwdriver.empty");

	public static final MutableComponent SCP023_LOOKED_AT = Component.translatable("scplockdown.scp023.stare").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_RED);

	public static final MutableComponent SCP049_SPEECH_1 = Component.translatable("scplockdown.scp049.speech1");
	public static final MutableComponent SCP049_SPEECH_2 = Component.translatable("scplockdown.scp049.speech2");
	public static final MutableComponent SCP049_SPEECH_3 = Component.translatable("scplockdown.scp049.speech3");
	public static final MutableComponent SCP049_SPEECH_4 = Component.translatable("scplockdown.scp049.speech4");
	public static final MutableComponent SCP049_SPEECH_5 = Component.translatable("scplockdown.scp049.speech5");
	public static final MutableComponent SCP049_SPEECH_6 = Component.translatable("scplockdown.scp049.speech6");
	public static final MutableComponent SCP049_ATTACKED_1 = Component.translatable("scplockdown.scp049.attacked1");
	public static final MutableComponent SCP049_ATTACKED_2 = Component.translatable("scplockdown.scp049.attacked2");

	public static final MutableComponent SCP902_OPENED = Component.translatable("scplockdown.scp902.opened").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY);

	public static final MutableComponent SCP914_LINK_REQUIRED = Component.translatable("scplockdown.scp914.link.required").withStyle(ChatFormatting.DARK_RED);
	public static final MutableComponent SCP914_LINK_INPUT = Component.translatable("scplockdown.scp914.link.input").withStyle(ChatFormatting.GRAY);
	public static final MutableComponent SCP914_LINK_OUTPUT = Component.translatable("scplockdown.scp914.link.output").withStyle(ChatFormatting.GRAY);
	public static final MutableComponent SCP914_LINK_SUCCESS = Component.translatable("scplockdown.scp914.link.success").withStyle(ChatFormatting.DARK_GREEN);
	public static final MutableComponent SCP914_LINK_EXISTS = Component.translatable("scplockdown.scp914.link.exists").withStyle(ChatFormatting.GOLD);
	public static final MutableComponent SCP914_BUSY = Component.translatable("scplockdown.scp914.busy").withStyle(ChatFormatting.GOLD);
	public static final MutableComponent SCP914_SET_ROUGH = Component.translatable("scplockdown.scp914.set.rough");
	public static final MutableComponent SCP914_SET_COARSE = Component.translatable("scplockdown.scp914.set.coarse");
	public static final MutableComponent SCP914_SET_ONEONE = Component.translatable("scplockdown.scp914.set.oneone");
	public static final MutableComponent SCP914_SET_FINE = Component.translatable("scplockdown.scp914.set.fine");
	public static final MutableComponent SCP914_SET_VERYFINE = Component.translatable("scplockdown.scp914.set.veryfine");
}
