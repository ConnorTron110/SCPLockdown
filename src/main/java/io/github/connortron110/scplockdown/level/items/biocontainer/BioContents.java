package io.github.connortron110.scplockdown.level.items.biocontainer;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public enum BioContents {
	EMPTY("empty", IBioRefill.EMPTY, IBioUseOnEntity.EMPTY),
	SCP008("scp008", IBioRefill.SCP008, IBioUseOnEntity.SCP008);

	private final String type;
	private final IBioRefill refill;
	private final IBioUseOnEntity useOn;

	BioContents(String type, IBioRefill refill, IBioUseOnEntity useOn) {
		this.type = type;
		this.refill = refill;
		this.useOn = useOn;
	}

	public String getName() {
		return type;
	}

	public void useOnEntity(Level level, Player player, LivingEntity entity) {
		useOn.accept(level, player, entity);
	}

	public boolean canRefill(ItemStack bioContainer, ItemStack otherHandStack, Level level, Player player) {
		if (!AbstractBioContainerItem.isBioContainer(bioContainer))
			return false; //If item is not a bio container, lets not even test
		else if (AbstractBioContainerItem.getBioAmount(bioContainer) >= ((AbstractBioContainerItem) bioContainer.getItem()).getMaxContainerSize())
			return false; //If container is full, we cannot refill
		else return refill.canRefill(bioContainer, otherHandStack, level, player);
	}

	/**
	 * Tests the stack through all the IBioRefill Types to get the Content type the item relates to
	 *
	 * @param stack The Stack to test
	 * @return The BioContents that relate to the item, Returns Empty if the item does not fit in any category
	 */
	public static BioContents getContentTypeFromStack(ItemStack stack) {
		for (BioContents value : BioContents.values()) {
			//ITag<Item> tag = value.refill.getRefillItemTag();
			//if (tag != null && tag.contains(stack.getItem())) {
			//    return value;
			//}
		}

		return EMPTY;
	}
}
