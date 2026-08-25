package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.entity.ChairEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public abstract class AbstractChairBlock extends LockdownHorizontalBlock {
	public AbstractChairBlock(Properties properties) {
		super(properties);
	}

	protected abstract float getSittingHeight();

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		boolean flag = level.getEntitiesOfClass(ChairEntity.class, new AABB(pos)).isEmpty();
		if (flag && !level.getBlockState(pos.above()).isSuffocating(level, pos.above())) {
			if (!level.isClientSide) {
				ChairEntity chair = new ChairEntity(player.level(), pos, getSittingHeight(), state.getValue(FACING).toYRot());
				level.addFreshEntity(chair);
				chair.interact(player, hand);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		return InteractionResult.PASS;
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onRemove(state, level, pos, newState, isMoving);
		Optional<ChairEntity> foundChair = level.getEntitiesOfClass(ChairEntity.class, new AABB(pos)).stream().findFirst();
		if (foundChair.isPresent()) {
			foundChair.get().ejectPassengers();
			foundChair.get().remove(Entity.RemovalReason.DISCARDED); //Just in case it has no player
		}
	}
}
