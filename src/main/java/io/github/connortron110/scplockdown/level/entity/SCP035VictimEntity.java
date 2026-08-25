package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class SCP035VictimEntity extends PathfinderMob {
	public SCP035VictimEntity(EntityType<? extends PathfinderMob> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.2D, 1.3D));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
		setItemSlot(EquipmentSlot.HEAD, SCPItems.SCP035_MASK.getDefaultInstance());
		return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
	}

	@Override
	protected boolean shouldDropLoot() {
		return false;
	}

	@Override
	protected void dropEquipment() {
		getArmorSlots().forEach(stack -> {
			if (!stack.isEmpty()) {
				spawnAtLocation(stack);
			}
		});
	}
}
