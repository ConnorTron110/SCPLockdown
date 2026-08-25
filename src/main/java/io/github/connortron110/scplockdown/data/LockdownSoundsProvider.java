package io.github.connortron110.scplockdown.data;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

/**
 * Author ConnorTron110
 */
public class LockdownSoundsProvider extends SoundDefinitionsProvider {
	protected LockdownSoundsProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, SCPLockdown.MOD_ID, helper);
	}

	@Override
	public void registerSounds() {
		add(SCPSounds.KEYCARD_SUCCESS, new ModSoundDefinition().sound("keycard_success"));
		add(SCPSounds.KEYCARD_FAIL, new ModSoundDefinition().sound("keycard_fail"));
		add(SCPSounds.SLIDING_DOOR_OPEN, new ModSoundDefinition().sound("sliding_door_open", 3));
		add(SCPSounds.SLIDING_DOOR_CLOSE, new ModSoundDefinition().sound("sliding_door_close", 3));
		add(SCPSounds.MAGNETIZED_DOOR_OPEN, new ModSoundDefinition().sound("magnetized_door_open"));
		add(SCPSounds.MAGNETIZED_DOOR_CLOSE, new ModSoundDefinition().sound("magnetized_door_close"));
		add(SCPSounds.BLAST_DOOR_OPEN, new ModSoundDefinition().sound("blast_door_open", 3));
		add(SCPSounds.BLAST_DOOR_CLOSE, new ModSoundDefinition().sound("blast_door_close", 3));
		add(SCPSounds.CLOCK_TICKING, new ModSoundDefinition().sound("clockticking"));

		add(SCPSounds.SCP009_SPREAD, new ModSoundDefinition().sound("scp009_spread"));

		add(SCPSounds.SCP914_REFINING_START, new ModSoundDefinition().sound("scp914_refining_start"));
		add(SCPSounds.SCP914_REFINING, new ModSoundDefinition().sound("scp914_refining"));
		add(SCPSounds.SCP914_REFINING_STOP, new ModSoundDefinition().sound("scp914_refining_stop"));
	}

	private void add(RegistryObject<? extends SoundEvent> reg, ModSoundDefinition modSoundDefinition) {
		String path = reg.get().getLocation().getPath();
		add(path, modSoundDefinition.subtitle("subtitle." + path).build());
	}

	private static class ModSoundDefinition {
		private final SoundDefinition definition = definition();
		private final String scpID;

		public ModSoundDefinition(String scpID) {
			this.scpID = scpID;
		}

		public ModSoundDefinition(int scpID) {
			this(String.valueOf(scpID));
		}

		public ModSoundDefinition() {
			this("");
		}

		public ModSoundDefinition subtitle(String subtitle) {
			definition.subtitle(subtitle);
			return this;
		}

		/**
		 * Used for sounds with more than one variant,
		 * Sounds using this method should start with a 1
		 * The number is appended after name E.G. hurt1, hurt2 etc..
		 */
		public ModSoundDefinition sound(String name, int amount) {
			if (amount < 2) {
				SCPLockdown.LOGGER.warn("Invalid Amount on Sound name: " + name);
				SCPLockdown.LOGGER.warn("Adding one sound and proceeding");
				sound(name, false);
				return this;
			}

			for (int i = 1; i != amount + 1; i++) {
				sound(name + i, false);
			}

			return this;
		}

		public ModSoundDefinition sound(String name, boolean stream) {
			definition.with(LockdownSoundsProvider.sound(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, (scpID.isEmpty() ? name : "scp/" + scpID + "/" + name))).stream(stream));
			return this;
		}

		/**
		 * Alternative to number version however doesnt add numbers and adds list of Strings as-is provided
		 */
		public ModSoundDefinition sound(String... name) {
			Arrays.asList(name).forEach(s -> sound(s, false));
			return this;
		}

		public ModSoundDefinition sound(String name) {
			this.sound(name, false);
			return this;
		}

		public SoundDefinition build() {
			return definition;
		}

	}
}
