package io.github.connortron110.scplockdown.utils;

/**
 * Use instead of {@link net.minecraft.world.item.DyeColor} CC 15/09 - Why is there preference to use this one??
 */
public enum SCPDefaultColors {
	WHITE("white", 16383998),
	ORANGE("orange", 16351261),
	MAGENTA("magenta", 13061821),
	LIGHT_BLUE("light_blue", 3847130),
	YELLOW("yellow", 16701501),
	LIME("lime", 8439583),
	PINK("pink", 15961002),
	GRAY("gray", 4673362),
	SILVER("silver", 10329495),
	CYAN("cyan", 1481884),
	PURPLE("purple", 8991416),
	BLUE("blue", 3949738),
	BROWN("brown", 8606770),
	GREEN("green", 6192150),
	RED("red", 11546150),
	BLACK("black", 1908001);

	public final String colorName;
	public final int colorValue;

	SCPDefaultColors(String name, int value) {
		colorName = name;
		colorValue = value;
	}

	/**
	 * Capitalizes the first character and removes _ in literal names
	 */
	public String getDisplayName() {
		String[] s = this.colorName.split("_");
		for (int i = 0; i < s.length; i++) {
			s[i] = Character.toUpperCase(s[i].charAt(0)) + s[i].substring(1);
		}
		return String.join(" ", s);
	}
}
