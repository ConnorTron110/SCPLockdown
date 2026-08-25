package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.level.entity.SCP143PetalItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SCP143PetalItem extends SCPItem {

	public SCP143PetalItem(Properties pProperties) {
		super(pProperties);
	}

	@Nullable
	@Override
	public Entity createEntity(Level world, Entity location, ItemStack itemstack) {
		SCP143PetalItemEntity entity = new SCP143PetalItemEntity(world, location.getX(), location.getY(), location.getZ(), itemstack);
		entity.setPickUpDelay(80);
		entity.setDeltaMovement(location.getDeltaMovement());
		return entity;
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack) {
		return true;
	}
}
