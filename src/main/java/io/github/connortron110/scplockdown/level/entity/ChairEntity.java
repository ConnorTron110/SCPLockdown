package io.github.connortron110.scplockdown.level.entity;

import io.github.connortron110.scplockdown.registration.SCPEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class ChairEntity extends Entity implements IEntityAdditionalSpawnData {

	private float height = 1F;

	public ChairEntity(EntityType<?> type, Level level) {
		super(type, level);
	}

	/**
	 * @param pos      the block position where you want the chair to spawn
	 * @param height   modifier to change height, 0 = 1 block high, - decreases | + increases
	 * @param rotation used to indicate where it should face, forcing the player to face the direction of the chair
	 */
	public ChairEntity(Level level, BlockPos pos, float height, float rotation) {
		this(SCPEntities.CHAIR.get(), level);
		this.height = height;
		//  Doesnt work >:(
		//refreshDimensions();

		setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
		setYRot(rotation);
	}

	@Nonnull
	@Override
	public EntityDimensions getDimensions(@Nonnull Pose pPose) {
		return getType().getDimensions().scale(1F, this.height);
	}

	@Override
	protected boolean canRide(@Nonnull Entity vehicle) {
		return false;
	}

	@Override
	public boolean hurt(@Nonnull DamageSource pSource, float pAmount) {
		return false;
	}

	@Nonnull
	@Override
	public InteractionResult interact(@Nonnull Player player, @Nonnull InteractionHand hand) {
		if (!level().isClientSide) {
			player.setYRot(this.getYRot());
			if (!player.startRiding(this)) {
				remove(RemovalReason.DISCARDED);
			}
			return InteractionResult.sidedSuccess(level().isClientSide);
		}
		return super.interact(player, hand);
	}

	@Override
	protected void removePassenger(@Nonnull Entity passenger) {
		super.removePassenger(passenger);
		remove(RemovalReason.DISCARDED);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	protected void readAdditionalSaveData(@Nonnull CompoundTag pCompound) {
	}

	@Override
	protected void addAdditionalSaveData(@Nonnull CompoundTag pCompound) {
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void writeSpawnData(FriendlyByteBuf buffer) {
		buffer.writeFloat(this.height);
	}

	@Override
	public void readSpawnData(FriendlyByteBuf additionalData) {
		this.height = additionalData.readFloat();
	}
}
