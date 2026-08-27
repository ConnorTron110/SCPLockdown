package io.github.connortron110.scplockdown.events.tickingsound;

import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class SCP023HowlTickingSoundInstance extends AbstractTickableSoundInstance {

	private final SCP023Entity SCP023;

	public SCP023HowlTickingSoundInstance(SCP023Entity scp023) {
		super(SoundEvents.WOLF_HOWL, SoundSource.HOSTILE, SoundInstance.createUnseededRandom());
		this.SCP023 = scp023;
		this.volume = 2F;
		this.pitch = this.SCP023.getVoicePitch();
		this.x = this.SCP023.getX();
		this.y = this.SCP023.getY();
		this.z = this.SCP023.getZ();
	}

	@Override
	public void tick() {
		this.x = this.SCP023.getX();
		this.y = this.SCP023.getY();
		this.z = this.SCP023.getZ();
	}
}
