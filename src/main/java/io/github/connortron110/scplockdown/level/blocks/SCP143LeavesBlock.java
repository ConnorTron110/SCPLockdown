package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.events.hooks.IActionOnMining;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.level.entity.SCP143PetalItemEntity;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SCP143LeavesBlock extends LockdownLeaves implements IActionOnMining {

	private static final VoxelShape COLLISION = Shapes.create(new AABB(BlockPos.ZERO).deflate(1 / 16D));

	public SCP143LeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isRandomlyTicking(BlockState pState) {
		return pState.getValue(DISTANCE) > 2 && !pState.getValue(PERSISTENT); //Only the furthest leaves will tick
	}

	@Override
	public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		super.randomTick(pState, pLevel, pPos, pRandom);
		//Make petal drop from leaf (Only works if not placed by player)
		if (!pLevel.isClientSide && pRandom.nextInt(1000) == 0) { // 1/1000 chance to drop
			if (pLevel.isEmptyBlock(pPos.below())) {
				//TODO Possible spawn in anywhere of the lower-half of the block, instead of just the center
				pLevel.playSound(null, pPos.below(), SoundEvents.NETHER_SPROUTS_PLACE, SoundSource.BLOCKS, 0.05F, 2.5F);
				SCP143PetalItemEntity itemEntity = new SCP143PetalItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() - 0.2D, pPos.getZ() + 0.5D, SCPItems.SCP143_PETALS.getDefaultInstance());
				itemEntity.setDeltaMovement(0, 0, 0);
				pLevel.addFreshEntity(itemEntity);
			}
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return COLLISION;   //  FIXME: (Minor) Causes lighting shadow issues
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.block();
	}

	@Override
	public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
		if (pEntity instanceof ItemEntity) return; //   Prevent Item entities from being destroyed
		pEntity.hurt(SCPDamageTypes.source(pLevel, (SCPDamageTypes.SCP143PETAL)), 2);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.getItemInHand(hand).isEmpty()) {
			player.hurt(SCPDamageTypes.source(level, (SCPDamageTypes.SCP143PETAL)), 2);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		return InteractionResult.FAIL;
	}

	@Override
	public void miningTick(Level level, BlockPos pos, Player player) {
		//  Crude implementation of hurting the player when mining
		player.hurt(SCPDamageTypes.source(level, (SCPDamageTypes.SCP143PETAL)), 2);
	}
}
