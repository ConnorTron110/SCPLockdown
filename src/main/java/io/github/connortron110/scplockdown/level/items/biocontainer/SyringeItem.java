package io.github.connortron110.scplockdown.level.items.biocontainer;

import net.minecraft.world.item.ItemStack;

public class SyringeItem extends AbstractBioContainerItem {
	public SyringeItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public int getMaxContainerSize() {
		return 1;
	}

	@Override
	public ItemStack getDefaultInstance() {
		return clearContainer(new ItemStack(this));
	}
}
