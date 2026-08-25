package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

public class SCP822Block extends LockdownBlock {

	private static final float EXPLOSION_INTENSITY = 3F;   //  The explosions "range"
	private static final int ACTIVATION_DISTANCE = 3;   //  The distance in blocks on which the cactus will detonate
	private static final int TOXIN_DURATION = 120 * 20;  //  The duration in ticks

	private static final VoxelShape SHAPE = Stream.of(
			box(5, 0, 5, 11, 3, 11),
			box(7.5, 2.75, 7.5, 8.5, 3.75, 8.5),
			box(7, 3, 5.5, 9, 4, 7.5),
			box(7, 3, 8.5, 9, 4, 10.5),
			box(8.5, 3, 7, 10.5, 3.9, 9),
			box(6, 3, 6, 10, 3.5, 10),
			box(5.5, 3, 7, 7.5, 3.9, 9)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


	public SCP822Block(Properties properties) {
		super(properties);
	}

	/**
	 * Schedules a tick for this block on the given level at the given pos.
	 */
	private void scheduleTick(Level level, BlockPos pos) {
		if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
			level.scheduleTick(pos, this, 20);
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		//  An entity is inside the block, this can happen if they run and step inside the block before the tick, to mask that were checking every second, when a player is inside this block, then it blows up
		if (entity instanceof Player player && (player.isCreative() || player.isSpectator())) return;
		if (level instanceof ServerLevel serverLevel) {
			//  Easier to call the tick method instead of redoing a majority of the work
			this.tick(state, serverLevel, pos, serverLevel.random);
		}
	}

	@Override
	public boolean dropFromExplosion(Explosion explosion) {
		return false;
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		scheduleTick(level, pos);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		AABB box = new AABB(pos).inflate(ACTIVATION_DISTANCE);

		List<LivingEntity> entitiesNearby = level.getEntitiesOfClass(LivingEntity.class, box, EntitySelector.LIVING_ENTITY_STILL_ALIVE).stream()
				.filter(entity -> entity.distanceToSqr(Vec3.atCenterOf(pos)) <= Math.pow(ACTIVATION_DISTANCE, 2))   //  Filter for entities in a circular radius
				//  Filter for entities that if they are a player, that they are NOT invulnerable
				.filter(entity -> {
					if (!(entity instanceof Player player)) return true;
					return !player.isCreative() && !player.isSpectator();
				})
				.toList();

		if (!entitiesNearby.isEmpty()) {
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
			level.explode(null, SCPDamageTypes.source(level, SCPDamageTypes.SCP822EXPLODE), null, pos.getX() + 0.5D, pos.getY() + 0.125D, pos.getZ() + 0.5D, EXPLOSION_INTENSITY, false, Level.ExplosionInteraction.BLOCK);

			entitiesNearby.forEach(entity -> entity.addEffect(new MobEffectInstance(SCPEffects.SCP822_TOXIN.get(), TOXIN_DURATION, 0)));

			return; //  Prevent reschedule of tick
		}

		//  Reschedule the tick
		scheduleTick(level, pos);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}
}
