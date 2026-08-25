
package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SCPSounds {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SCPLockdown.MOD_ID);

	public static final RegistryObject<SoundEvent> KEYCARD_SUCCESS = register("keycard_success");
	public static final RegistryObject<SoundEvent> KEYCARD_FAIL = register("keycard_fail");
	public static final RegistryObject<SoundEvent> SLIDING_DOOR_OPEN = register("sliding_door_open");
	public static final RegistryObject<SoundEvent> SLIDING_DOOR_CLOSE = register("sliding_door_close");
	public static final RegistryObject<SoundEvent> MAGNETIZED_DOOR_OPEN = register("magnetized_door_open");
	public static final RegistryObject<SoundEvent> MAGNETIZED_DOOR_CLOSE = register("magnetized_door_close");
	public static final RegistryObject<SoundEvent> BLAST_DOOR_OPEN = register("blast_door_open");
	public static final RegistryObject<SoundEvent> BLAST_DOOR_CLOSE = register("blast_door_close");
	public static final RegistryObject<SoundEvent> CLOCK_TICKING = register("clockticking");

	public static final RegistryObject<SoundEvent> SCP009_SPREAD = register("scp009_spread");

	public static final RegistryObject<SoundEvent> SCP914_REFINING_START = register("scp914_refining_start");
	public static final RegistryObject<SoundEvent> SCP914_REFINING = register("scp914_refining");
	public static final RegistryObject<SoundEvent> SCP914_REFINING_STOP = register("scp914_refining_stop");


	private static RegistryObject<SoundEvent> register(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, name)));
	}
}
