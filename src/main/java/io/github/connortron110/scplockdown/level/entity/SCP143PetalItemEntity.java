package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.registration.SCPEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

//The entire class is a fix from the item not actually getting dropped onto the ground
public class SCP143PetalItemEntity extends ItemEntity {

	public SCP143PetalItemEntity(EntityType<? extends SCP143PetalItemEntity> type, Level level) {
		super(type, level);
	}

	public SCP143PetalItemEntity(Level worldIn, double x, double y, double z, ItemStack stack) {
		this(SCPEntities.PETAL_ITEM.get(), worldIn);
		this.setPos(x, y, z);
		this.setYRot(this.random.nextFloat() * 360.0F);
		this.setDeltaMovement(this.random.nextDouble() * 0.2D - 0.1D, 0.2D, this.random.nextDouble() * 0.2D - 0.1D);
		this.setItem(stack);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		List<Entity> list = level().getEntities((Entity) null, getBoundingBox(), EntitySelector.NO_CREATIVE_OR_SPECTATOR);
		for (Entity entity : list) {
			if (entity instanceof LivingEntity) { //Check if its alive, if not, no point hurting it
				if (!inRange(getDeltaMovement().x) && !inRange(getDeltaMovement().y) && !inRange(getDeltaMovement().z)) {
					//entity.hurt(SCPDamageSources.SCP143PETAL, 1F);
				}
			}
		}
	}

	private boolean inRange(double value) {
		return value < 0.02D && value > -0.02D;
	}

    /*
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

     */
}
