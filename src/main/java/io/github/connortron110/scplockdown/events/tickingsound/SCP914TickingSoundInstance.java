package io.github.connortron110.scplockdown.events.tickingsound;

import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;

public class SCP914TickingSoundInstance extends AbstractTickableSoundInstance {

	/**
	 * Stopping the sound instantly is too jarring, instead, fade it out.
	 */
	private static final int TICKS_TO_STOP = 10;

	private final SCP914BlockEntity BlockEntity;
	private boolean IsStopping = false;
	private int StopCount = 0;

	public SCP914TickingSoundInstance(SCP914BlockEntity blockEntity) {
		super(SCPSounds.SCP914_REFINING.get(), SoundSource.BLOCKS, SoundInstance.createUnseededRandom());
		this.BlockEntity = blockEntity;
		this.looping = true;
		this.x = this.BlockEntity.getBlockPos().getX() + 0.5;
		this.y = this.BlockEntity.getBlockPos().getY() + 0.5;
		this.z = this.BlockEntity.getBlockPos().getZ() + 0.5;
	}

	@Override
	public void tick() {
		//	Check if SCP-914 still exists
		if (BlockEntity.isRemoved())
			stop();

		//	Gradually fade out
		if (this.IsStopping) {
			this.volume = Mth.lerp((float) StopCount++ / TICKS_TO_STOP, 1, 0);
			if (StopCount >= TICKS_TO_STOP)
				stop();
		}

		if (this.BlockEntity.getCurrentState() != SCP914BlockEntity.MachineState.PROCESSING && !this.IsStopping)
			this.IsStopping = true;
	}
}
