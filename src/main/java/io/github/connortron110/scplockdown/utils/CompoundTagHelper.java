package io.github.connortron110.scplockdown.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

/**
 * This class helps with the editing and saving of tags.
 */
public class CompoundTagHelper<T> {

	private CompoundTag tag;
	private T tagOrigin;

	private CompoundTagHelper(CompoundTag tag, T tagOrigin) {
		this.tag = tag;
		this.tagOrigin = tagOrigin;
	}

	public void save() {
		if (tagOrigin instanceof ItemStack stack) {
			stack.setTag(tag);
		}
	}

	public static CompoundTagHelper<ItemStack> getTag(ItemStack stack) {
		return new CompoundTagHelper<>(stack.getOrCreateTag(), stack);
	}
}
