package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class KeycardItem extends Item {

	public static final String NAME_KEY = "cardname";
	public static final String COLOUR_KEY = "colour";
	public static final String SECRET_KEY = "key";

	public static final Lazy<List<ItemStack>> DEFAULT_CARDS = Lazy.of(() -> {
		ItemStack level1 = setDefaultTranslationKeycard("keycard.default.level.1", 0xE9E800, "LKDWN_LVL_1");
		ItemStack level2 = setDefaultTranslationKeycard("keycard.default.level.2", 0xE8BB00, "LKDWN_LVL_2");
		ItemStack level3 = setDefaultTranslationKeycard("keycard.default.level.3", 0xE78D00, "LKDWN_LVL_3");
		ItemStack level4 = setDefaultTranslationKeycard("keycard.default.level.4", 0xE65F00, "LKDWN_LVL_4");
		ItemStack level5 = setDefaultTranslationKeycard("keycard.default.level.5", 0xE53100, "LKDWN_LVL_5");
		return Arrays.asList(level1, level2, level3, level4, level5);
	});

	public KeycardItem(Properties properties) {
		super(properties);
	}

	/**
	 * Used to set default cards so they can have translatable names
	 */
	private static ItemStack setDefaultTranslationKeycard(String translationKey, int colour, String key) {
		Data data = new Data(Component.translatable(translationKey), colour, key);
		return data.setItemTag(SCPItems.KEYCARD.getDefaultInstance());
	}

	public static ItemStack setKeycardValues(ItemStack stack, String name, int colour, String key) {
		if (!isKeycard(stack) || key.isEmpty() || colour < 0) return stack;
		Data data = new Data(Component.literal(name), colour, key);
		return data.setItemTag(stack);
	}

	public static ItemStack setKey(ItemStack stack, String key) {
		if (!isKeycard(stack)) return stack;
		stack.getOrCreateTag().putString(SECRET_KEY, key);
		return stack;
	}

	public static ItemStack setColour(ItemStack stack, int colour) {
		if (!isKeycard(stack)) return stack;
		stack.getOrCreateTag().putInt(COLOUR_KEY, colour);
		return stack;
	}

	@Nullable
	public static String getKey(ItemStack stack) {
		return hasKey(stack) ? stack.getTag().getString(SECRET_KEY) : null;
	}

	public static int getColour(ItemStack stack) {
		return hasColour(stack) ? stack.getTag().getInt(COLOUR_KEY) : -1;
	}

	public static boolean hasKey(ItemStack stack) {
		if (!isKeycard(stack)) return false;
		return stack.getOrCreateTag().contains(SECRET_KEY);
	}

	public static boolean hasColour(ItemStack stack) {
		if (!isKeycard(stack)) return false;
		return stack.getOrCreateTag().contains(COLOUR_KEY);
	}

	public static boolean isKeycard(ItemStack stack) {
		return stack.getItem() instanceof KeycardItem;
	}

	/**
	 * Acts as a data holder
	 */
	public static class Data {
		public final Component Name;
		public final int Colour;
		public final String Key;

		public Data(Component name, int colour, String key) {
			this.Name = name.copy().setStyle(Style.EMPTY.withItalic(false));
			this.Colour = colour;
			this.Key = key;
		}

		public Data(String name, int colour, String key) {
			this(Component.literal(name), colour, key);
		}

		public Data(Component name, String colour, String key) {
			this(name, isColourStringValid(colour) ? Integer.parseInt(colour, 16) : 0xFFFFFF, key);
		}

		public Data(ItemStack stack) {
			if (!isKeycard(stack)) throw new IllegalArgumentException("Item is not a KeycardItem");
			this.Name = stack.getHoverName();
			this.Colour = stack.getOrCreateTag().getInt(COLOUR_KEY);
			this.Key = stack.getOrCreateTag().getString(SECRET_KEY);
		}

		public Data(CompoundTag tag) {
			this.Name = Component.literal(tag.getString(NAME_KEY));
			this.Colour = tag.getInt(COLOUR_KEY);
			this.Key = tag.getString(SECRET_KEY);
		}

		public ItemStack setItemTag(ItemStack stack) {
			if (!isKeycard(stack)) throw new IllegalArgumentException("Item is not a KeycardItem");
			stack.setHoverName(this.Name);
			stack.getOrCreateTag().putInt(COLOUR_KEY, this.Colour);
			stack.getOrCreateTag().putString(SECRET_KEY, this.Key);
			return stack;
		}

		/**
		 * Not used for the actual item, but instead serializes this data into a tag.
		 * Sadly does lose its translation (if any)
		 */
		public void setCompoundTag(CompoundTag tag) {
			tag.putString(NAME_KEY, this.Name.getString());
			tag.putInt(COLOUR_KEY, this.Colour);
			tag.putString(SECRET_KEY, this.Key);
		}

		public String getHexString() {
			return String.format("%06X", Colour & 0xFFFFFF);
		}

		public static boolean isColourStringValid(String s) {
			try {
				Integer.parseInt(s, 16);
			} catch (NumberFormatException e) {
				SCPLockdown.LOGGER.warn("Failed to parse colour string: {}\n{}", s, e);
				return false;
			}
			return true;
		}

		public static boolean hasData(ItemStack card) {
			return card.getOrCreateTag().contains(COLOUR_KEY) && card.getOrCreateTag().contains(SECRET_KEY);
		}
	}
}
