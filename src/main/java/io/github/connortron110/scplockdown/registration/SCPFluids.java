package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SCPFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SCPLockdown.MOD_ID);
/*
    public static final FluidRegistryHolder SCP006_FOUNTAIN = FluidRegistryHolder.registerFluid("scp006",
            properties(attributes(WATER_STILL_RL, WATER_FLOWING_RL).density(15).luminosity(2).sound(SoundEvents.BUCKET_EMPTY).overlay(WATER_OVERLAY_RL).color(0xFF80FFFA))
                    .slopeFindDistance(2).levelDecreasePerBlock(2));
 */
}
