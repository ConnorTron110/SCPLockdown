package io.github.connortron110.scplockdown.data.tags;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LockdownFluidTagsProvider extends FluidTagsProvider {

	public LockdownFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, completableFuture, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
//        this.tag(SCPTags.Fluids.SCP006_FOUNTAIN_FLUID).add(SCPFluids.SCP006_FOUNTAIN.getSource().get()).add(SCPFluids.SCP006_FOUNTAIN.getFlowing().get());
//        this.tag(FluidTags.WATER).add(SCPFluids.SCP006_FOUNTAIN.getSource().get()).add(SCPFluids.SCP006_FOUNTAIN.getFlowing().get());
	}
}
