package io.github.connortron110.scplockdown.utils.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class NBTWrapper {

	private final CompoundTag NBT;
	private final Object object; //Used only to save to

	private NBTWrapper(CompoundTag nbt, Object object) {
		this.NBT = nbt;
		this.object = object;
	}

	/**
	 * Gets the value from the key, or puts value
	 *
	 * @param key          key to check
	 * @param defaultValue value to put if no key is found
	 */
	public byte getOrCreateKey(String key, byte defaultValue) {
		if (NBT.contains(key)) return NBT.getByte(key);
		else {
			setByte(key, defaultValue);
			return defaultValue;
		}
	}

	public int getOrCreateKey(String key, int defaultValue) {
		if (NBT.contains(key)) return NBT.getInt(key);
		else {
			setInt(key, defaultValue);
			return defaultValue;
		}
	}

	public boolean getOrCreateKey(String key, boolean defaultValue) {
		if (NBT.contains(key)) return NBT.getBoolean(key);
		else {
			setBoolean(key, defaultValue);
			return defaultValue;
		}
	}

	public NBTWrapper setByte(String key, byte value) {
		NBT.putByte(key, value);
		return this;
	}

	public NBTWrapper setInt(String key, int value) {
		NBT.putInt(key, value);
		return this;
	}

	public NBTWrapper setBoolean(String key, boolean value) {
		NBT.putBoolean(key, value);
		return this;
	}

	public boolean hasKey(String key) {
		return NBT.contains(key);
	}

	public void save() {
		if (object instanceof ItemStack) {
			ItemStack stack = (ItemStack) object;
			stack.setTag(NBT);
		}
	}

	public static NBTWrapper getNBT(ItemStack stack) {
		return new NBTWrapper(stack.getOrCreateTag(), stack);
	}
}
