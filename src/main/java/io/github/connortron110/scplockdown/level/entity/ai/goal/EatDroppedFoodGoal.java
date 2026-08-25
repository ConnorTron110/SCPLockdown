package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class EatDroppedFoodGoal extends Goal {

	private final int TIME_TO_EAT = 20;

	private final Mob mob;
	@Nullable
	private List<ItemEntity> nearItems;
	private int eatTime = TIME_TO_EAT;

	public EatDroppedFoodGoal(Mob mob) {
		this.mob = mob;
		setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		//Limit the y height for detection to match height dis-advantage
		nearItems = mob.level().getEntitiesOfClass(ItemEntity.class, new AABB(mob.blockPosition()).inflate(8, 3, 8)).stream().filter(itemEntity -> itemEntity.getItem().getItem().isEdible()).toList();
		return !nearItems.isEmpty();
	}

	@Override
	public boolean canContinueToUse() {
		return canUse() && eatTime != 0;
	}

	@Override
	public void start() {
		eatTime = TIME_TO_EAT;
		ItemEntity nearestFood = getNearestFood();
		if (nearestFood != null) moveAndLookToFood(nearestFood);
	}

	@Override
	public void stop() {
		this.mob.getNavigation().stop();
	}

	@Override
	public void tick() {
		ItemEntity nearestFood = getNearestFood();
		if (nearestFood == null) return;

		moveAndLookToFood(nearestFood);

		if (mob.distanceTo(nearestFood) < 1.75) {

			ItemStack foodStack = nearestFood.getItem();

			if (mob.level() instanceof ServerLevel serverWorld) {
				double particleY = (double) (-mob.getRandom().nextFloat()) * 0.6D - 0.2D;
				Vec3 vector3d1 = new Vec3(((double) mob.getRandom().nextFloat() - 0.5D) * 0.5D, particleY, 1.0D + ((double) mob.getRandom().nextFloat() - 0.5D) * 0.4D);
				vector3d1 = vector3d1.yRot(-mob.yBodyRot * ((float) Math.PI / 180F));
				vector3d1 = vector3d1.scale(0.5).add(mob.getX(), mob.getEyeY() + 1.0D, mob.getZ());
				serverWorld.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, foodStack), vector3d1.x, mob.getY(), vector3d1.z, 6, 0, mob.getEyeHeight(), 0, 0.01);
			}

			if (eatTime-- % 5 == 0 && eatTime > 0) {
				mob.playSound(SoundEvents.GENERIC_EAT, 0.05F + 0.1F * mob.getRandom().nextInt(2), (mob.getRandom().nextFloat() - mob.getRandom().nextFloat()) * 0.2f + 1.6F);
			} else if (eatTime == 0) {
				mob.playSound(SoundEvents.PLAYER_BURP, 0.05F, mob.getRandom().nextFloat() * 0.1F + 1.6F);
				foodStack.shrink(1);
				nearestFood.setItem(foodStack);
				eatTime = TIME_TO_EAT;
			}
		} else {
			eatTime = TIME_TO_EAT;
		}
	}

	@Nullable
	private ItemEntity getNearestFood() {
		return nearItems == null ? null : nearItems.stream().min(Comparator.comparingDouble(itemEntity -> itemEntity.distanceTo(mob))).orElse(null);
	}

	private void moveAndLookToFood(ItemEntity itemEntity) {
		this.mob.getNavigation().moveTo(itemEntity, 1.7D);
		this.mob.getLookControl().setLookAt(itemEntity, mob.getMaxHeadYRot(), mob.getMaxHeadXRot());
	}
}
