package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//Remember: Effects =! Potions
public class SCPEffects {
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SCPLockdown.MOD_ID);

	public static final RegistryObject<ZombismEffect> SCP008_ZOMBISM = EFFECTS.register("zombism", () -> new ZombismEffect(0x8cc560));
	public static final RegistryObject<SCP023Effect> SCP023_CURSE = EFFECTS.register("scp023_curse", () -> new SCP023Effect(0x621313));
	public static final RegistryObject<SCP027Effect> SCP027_VERMIN = EFFECTS.register("scp027_vermin", () -> new SCP027Effect(0x63625b));
	public static final RegistryObject<SCP330Effect> SCP330_NOARMS = EFFECTS.register("scp330_noarms", () -> new SCP330Effect(0xB71021));
	public static final RegistryObject<SCP822Effect> SCP822_TOXIN = EFFECTS.register("scp822_toxin", () -> new SCP822Effect(0x427a44));
}
