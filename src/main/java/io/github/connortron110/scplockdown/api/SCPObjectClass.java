package io.github.connortron110.scplockdown.api;

import net.minecraft.ChatFormatting;

public enum SCPObjectClass {
	SAFE("Safe", ChatFormatting.DARK_GREEN),
	EUCLID("Euclid", ChatFormatting.GOLD),
	KETER("Keter", ChatFormatting.DARK_RED);

	public final String name;
	public final ChatFormatting colour;

	SCPObjectClass(String name, ChatFormatting colour) {
		this.name = name;
		this.colour = colour;
	}
}
