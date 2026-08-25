package io.github.connortron110.scplockdown.level.items;

import net.minecraft.world.item.Item;

//Possible that we don't need this class as a base
public class SCPItem extends Item { //TODO Are Rechargables as a base class really necessarily?
	//private boolean rechargable;

	public SCPItem(Properties pProperties) {
		super(pProperties);
	}


/*
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return SCPLockdown.MOD_ID +super.getUnlocalizedName(stack);
    }

    public boolean isRechargable()
    {
        return rechargable;
    }

    public void setRechargable(boolean rechargeable)
    {
        this.rechargable = rechargeable;
    }

    public static void dechargeItem(ItemStack itemStack, PlayerEntity player)
    {
        if(itemStack.getItemDamage() <= itemStack.getMaxDamage() - 2 && Utils.isPlayerInSurvivalMode(player) && !player.world.isRemote)
            itemStack.damageItem(1, player);
    }

    public static boolean isItemUncharged(ItemStack itemStack)
    {
        return itemStack.getItemDamage() > itemStack.getMaxDamage() - 2;
    }

    public boolean shouldItemRecharge(ItemStack itemStack)
    {
        return false;
    }

 */

}
