package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.events.hooks.IActionOnMining;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * Significant changes is that it no longer takes 10 mins to spread, instead its random
 */
public class SCP009Block extends LockdownBlock implements IActionOnMining {
	public SCP009Block(Properties properties) {
		super(properties);
	}

	@Override   //Changed from spreading Every 10 mins to a random chance (roughly 6-12 mins)
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(200) != 0) return;

		if (!canSpread(level, pos)) return;
		Direction freeSpaceDir = findWaterDirection(level, pos);
		if (freeSpaceDir == null) return;

		level.setBlockAndUpdate(pos.relative(freeSpaceDir), defaultBlockState());
		level.playSound(null, pos, SCPSounds.SCP009_SPREAD.get(), SoundSource.BLOCKS, 1, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
	}
/*
    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, EntityType<?> entityType) {
        return false;
    }

 */

	@Override
	public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
		if (pEntity instanceof LivingEntity living) {
			living.hurt(SCPDamageTypes.source(pLevel, SCPDamageTypes.SCP009FREEZE), 3);
			if (living.isDeadOrDying()) {
				List<BlockPos> posList = Utils.boundingBoxToPositions(living.getBoundingBox());
				posList.forEach(blockPos -> pLevel.setBlockAndUpdate(blockPos, defaultBlockState()));
			}
		}
	}

	//Temperature must be below 0.5 (what ever that means)
	private boolean canSpread(ServerLevel level, BlockPos pos) {
		return level.getBiome(pos).get().shouldFreeze(level, pos);
	}

	@Nullable
	private Direction findWaterDirection(ServerLevel level, BlockPos pos) {
		return Arrays.stream(Direction.values()).filter(dir -> level.getBlockState(pos.relative(dir)).getBlock().equals(Blocks.WATER)).findAny().orElse(null);
	}

	@Override
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
		return pAdjacentBlockState.is(this) || super.skipRendering(pState, pAdjacentBlockState, pSide);
	}

	@Override
	public void miningTick(Level level, BlockPos pos, Player player) {
		player.hurt(SCPDamageTypes.source(level, SCPDamageTypes.SCP009FREEZE), 3);
	}
}
