package io.github.connortron110.scplockdown.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.throwables.MixinError;

@Mixin(NaturalSpawner.class)
public interface NaturalSpawnerAccessor {
	@Invoker("getRandomPosWithin")
	static BlockPos invokeGetRandomPosWithin(Level pLevel, LevelChunk pChunk) {
		throw new MixinError("SCP:Lockdown: Failed to apply WorldEntitySpawner Static Invokers");
	}
}