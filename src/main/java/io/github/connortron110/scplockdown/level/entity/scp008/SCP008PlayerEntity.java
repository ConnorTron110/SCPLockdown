package io.github.connortron110.scplockdown.level.entity.scp008;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SCP008PlayerEntity extends SCP008Entity {

	private static final String UUID_TAG_KEY = "AssociatedPlayerUUID";
	private static final EntityDataAccessor<Optional<UUID>> ASSOCIATED_PLAYER_UUID = SynchedEntityData.defineId(SCP008PlayerEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	public SCP008PlayerEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
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
