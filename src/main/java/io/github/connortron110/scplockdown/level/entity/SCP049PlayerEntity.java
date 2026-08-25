package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.level.entity.ai.goal.HerdGoal;
import io.github.connortron110.scplockdown.level.entity.ai.goal.HurtByTargetAlertTypeGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SCP049PlayerEntity extends Monster implements IRequirePersistence {

	private static final String UUID_TAG_KEY = "AssociatedPlayerUUID";
	private static final EntityDataAccessor<Optional<UUID>> ASSOCIATED_PLAYER_UUID = SynchedEntityData.defineId(SCP049PlayerEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	public SCP049PlayerEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);

		setCanPickUpLoot(false); //Disables 008 instances from picking up anything

		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);

		//Makes mob ignorant to sources of damage however does avoid them if safer path exists
		this.setPathfindingMalus(BlockPathTypes.LAVA, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_CAUTIOUS, 1.0F);
	}

	/**
	 * This is overridden in the more special case classes, such as the brute and 682 for more unique attacks
	 */
	@Override
	protected void registerGoals() {
		registerBaseGoals();
	}

	private void registerBaseGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(1, new BreakDoorGoal(this, difficulty -> true));
		this.goalSelector.addGoal(2, new HerdGoal<>(this, SCP049Entity.class, 0.8D));   //  Herd to SCP-049
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D, 0.5F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

		//Hurt > Player > Humanoid > Golems > Animal
		this.targetSelector.addGoal(1, (new HurtByTargetAlertTypeGoal<>(this, SCP049PlayerEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DClassEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ScientistEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GuardEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Raider.class, true)); //Covers all Raiders
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Animal.class, true)); //Should Cover All Animals
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(ASSOCIATED_PLAYER_UUID, Optional.empty());
	}

	@Nullable
	public UUID getPlayerUUID() {
		return entityData.get(ASSOCIATED_PLAYER_UUID).orElse(null);
	}

	public void setPlayerUUID(UUID uuid) {
		entityData.set(ASSOCIATED_PLAYER_UUID, Optional.of(uuid));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		if (pCompound.hasUUID(UUID_TAG_KEY)) {
			entityData.set(ASSOCIATED_PLAYER_UUID, Optional.of(pCompound.getUUID(UUID_TAG_KEY)));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		entityData.get(ASSOCIATED_PLAYER_UUID).ifPresent(uuid -> pCompound.putUUID(UUID_TAG_KEY, uuid));
	}
}
