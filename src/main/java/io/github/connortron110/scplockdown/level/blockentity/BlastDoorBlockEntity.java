package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.level.blocks.BlastDoorBlock;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.CBCameraShake;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;

public class BlastDoorBlockEntity extends BlockEntity {

	private static final int TIME_TO_OPEN = 60; //In Ticks
	public static final int MAX_OPEN = 180;
	//0 = Closed, 80 = Open (80 because 16 pixels on a block, 16x5 means 5 moves per pixel which will be smooth enough)
	private int openProgress = 0;

	private boolean shouldShakeCamera = false;

	public BlastDoorBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.BLAST_DOOR.get(), pPos, pState);
	}

	public int getOpenProgress() {
		return openProgress;
	}

	/**
	 * To prevent noise spam due to the sound being long, the sound only allows you to play if the door is 87.5% open/closed
	 *
	 * @return true if a sound should play
	 */
	public boolean shouldPlaySound() {
		return getBlockState().getValue(SlidingDoorBlock.OPEN) ? openProgress < 5 : openProgress > MAX_OPEN - 5;
	}

	public void tick() {
		boolean isOpening = getBlockState().getValue(BlastDoorBlock.OPEN);

		openProgress += (isOpening ? MAX_OPEN : -MAX_OPEN) / TIME_TO_OPEN;
		openProgress = Mth.clamp(openProgress, 0, MAX_OPEN);

		if (shouldShakeCamera) {
			if (openProgress == MAX_OPEN || openProgress == 0) {
				shouldShakeCamera = false;
				if (!level.isClientSide) {
					SCPNetwork.NETWORK.send(PacketDistributor.NEAR.with(() -> PacketDistributor.TargetPoint.p(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 5, level.dimension()).get()), new CBCameraShake(0.5F, 20));
				}
			}
		} else {
			final int SHAKE_OPEN_THRESHOLD = 5;
			if (openProgress > SHAKE_OPEN_THRESHOLD && openProgress < MAX_OPEN - SHAKE_OPEN_THRESHOLD) {
				shouldShakeCamera = true;
			}
		}
	}

	private static final AABB RENDER_BOUNDS = new AABB(-2, 0, -2, 2, 3, 2);

	@Override
	public AABB getRenderBoundingBox() {
		return RENDER_BOUNDS.move(worldPosition);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putInt("DoorProgress", openProgress);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		openProgress = pTag.getInt("DoorProgress");
	}
}
