package io.github.connortron110.scplockdown.level.effect;

import com.google.common.collect.ImmutableMap;
import io.github.connortron110.scplockdown.mixin.NaturalSpawnerAccessor;
import io.github.connortron110.scplockdown.registration.SCPTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SCP027Effect extends SCPEffect {

	private static final Lazy<ImmutableMap<EntityType<?>, Rarity>> VERMIN_TYPES = Lazy.of(() -> {
		ImmutableMap.Builder<EntityType<?>, Rarity> builder = ImmutableMap.builder();

		ForgeRegistries.ENTITY_TYPES.forEach(type -> {
			if (type.is(SCPTags.Entity.SCP027_VERMIN_COMMON)) {
				builder.put(type, Rarity.COMMON);
			} else if (type.is(SCPTags.Entity.SCP027_VERMIN_UNCOMMON)) {
				builder.put(type, Rarity.UNCOMMON);
			} else if (type.is(SCPTags.Entity.SCP027_VERMIN_RARE)) {
				builder.put(type, Rarity.RARE);
			} else if (type.is(SCPTags.Entity.SCP027_VERMIN_EPIC)) {
				builder.put(type, Rarity.EPIC);
			}
		});

		return builder.build();
	});

	public SCP027Effect(int color) {
		super(MobEffectCategory.NEUTRAL, color, false, true);
	}

	@Override
	public void tick(LivingEntity living, int duration, int amplifier) {
		Level level = living.level();
		//if (living instanceof PlayerEntity player) if (player.isCreative() || player.isSpectator()) return; //Prevents vermin from spawning if in creative or spectator
		if (!level.isClientSide) {
			if (living.isDeadOrDying()) {
				living.removeEffect(this);
				List<? extends Player> players = level.players().stream().filter(EntitySelector.NO_CREATIVE_OR_SPECTATOR).toList();
				if (players.isEmpty()) return;
				Player player = players.get(level.random.nextInt(players.size()));
				player.addEffect(new MobEffectInstance(this));
			}

			//Seems like high odds, but spawning like normal spawning, that way vermin can reach caves etc
			//However each gen attempt is not guaranteed to be valid or on ground, therefore there are 32 attempts before classed as fail
			if (level.random.nextInt(100) != 0) return;

			//Checks if there are vermins in the area, if max has been reached, do not spawn
			if (level.getEntities(living, new AABB(living.blockPosition()).inflate(16)).stream().filter(entity -> SCPTags.Entity.isVermin(entity.getType())).toList().size() >= 16)
				return;

			//Select random rarity
			Rarity rarity;
			int rarityPossibility = level.random.nextInt(100);
			if (rarityPossibility > 20) rarity = Rarity.COMMON;
			else if (rarityPossibility > 10) rarity = Rarity.UNCOMMON;
			else if (rarityPossibility > 1) rarity = Rarity.RARE;
			else rarity = Rarity.EPIC;

			//Type Selection from rarity
			List<? extends EntityType<?>> selectedEntities = VERMIN_TYPES.get().entrySet().stream().filter(entry -> entry.getValue() == rarity).map(Map.Entry::getKey).toList();
			if (selectedEntities.isEmpty()) return;
			EntityType<?> typeToSpawn = selectedEntities.get(level.random.nextInt(selectedEntities.size()));

			//Selection of location
			//24 blocks away in either x or z guarantees that the entity spawning is far away enough (ish) to not be witnessed spawning
			List<LevelChunk> chunksToSpawnIn = new ArrayList<>();
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					LevelChunk chunk = level.getChunkAt(new BlockPos((int) (living.getX() + (24 * x)), 0, (int) (living.getZ() + (24 * z))));
					chunksToSpawnIn.add(chunk);
				}
			}
			BlockPos posToSpawn = NaturalSpawnerAccessor.invokeGetRandomPosWithin(level, chunksToSpawnIn.get(level.random.nextInt(chunksToSpawnIn.size())));

			//first pos is not guaranteed to work, so loop through a few iterations
			int spawnAttempts = 32;
			while (spawnAttempts-- >= 0) {
				if (!isValidSpawnLocation(level, posToSpawn, typeToSpawn)) {
					posToSpawn = NaturalSpawnerAccessor.invokeGetRandomPosWithin(level, chunksToSpawnIn.get(level.random.nextInt(chunksToSpawnIn.size())));
				} else {
					break;
				}
			}

			if (!isValidSpawnLocation(level, posToSpawn, typeToSpawn)) return;  //Checks one last time

			//Spawning of entity
			Entity entityToSpawn = typeToSpawn.create(level);
			entityToSpawn.moveTo(posToSpawn, level.random.nextFloat() * 360.0F, 0.0F);
			level.addFreshEntity(entityToSpawn);
		}
	}

	private boolean isValidSpawnLocation(Level level, BlockPos posToSpawn, EntityType<?> typeToSpawn) {
		if (!level.noCollision(typeToSpawn.getAABB((double) posToSpawn.getX() + 0.5D, posToSpawn.getY(), (double) posToSpawn.getZ() + 0.5D)))
			return false;
		else if (!NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, level, posToSpawn, typeToSpawn))
			return false;
		else if (!NaturalSpawner.isValidEmptySpawnBlock(level, posToSpawn, level.getBlockState(posToSpawn), level.getFluidState(posToSpawn), typeToSpawn))
			return false;
		else if (!level.getBlockState(posToSpawn.below()).isFaceSturdy(level, posToSpawn.below(), Direction.UP))
			return false;
		else return true;
	}

	//Inspiration from fortnite lol
	private enum Rarity {
		COMMON,
		UNCOMMON,
		RARE,
		EPIC
	}
}
