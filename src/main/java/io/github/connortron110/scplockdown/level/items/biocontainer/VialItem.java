package io.github.connortron110.scplockdown.level.items.biocontainer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class VialItem extends AbstractBioContainerItem {

	public static final String CAP_KEY = "CapOn"; //Cap is mostly cosmetic, I think it's a nice feature :)

	public VialItem(Properties pProperties) {
		super(pProperties);
	}

	public ItemStack setCap(ItemStack stack, boolean capOn) {
		CompoundTag nbt = stack.getOrCreateTag();
		nbt.putBoolean(CAP_KEY, capOn);
		stack.setTag(nbt);
		return stack;
	}

	/**
	 * @return If Cap should be on the vial
	 */
	public boolean isCapOn(ItemStack stack) {
		return stack.getOrCreateTag().getBoolean(CAP_KEY);
	}

	@Override
	public int getMaxContainerSize() {
		return 3;
	}

	@Override
	public ItemStack getDefaultInstance() {
		return setCap(clearContainer(new ItemStack(this)), true);
	}
}
