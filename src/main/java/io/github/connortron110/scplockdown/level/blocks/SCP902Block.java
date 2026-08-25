package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.registration.SCPSounds;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class SCP902Block extends LockdownHorizontalBlock {

	private static final VoxelShape[] SHAPES_CLOSED = VoxelShapeHelper.createHorizontalFacingVoxels(Stream.of(
			box(1, 1, 4, 15, 7, 12),
			box(1, 0, 4, 2, 1, 5),
			box(14, 0, 4, 15, 1, 5),
			box(1, 0, 11, 2, 1, 12),
			box(14, 0, 11, 15, 1, 12),
			box(1, 7, 4, 15, 8, 12)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] SHAPES_OPEN = VoxelShapeHelper.createHorizontalFacingVoxels(Stream.of(
			box(1, 1, 4, 15, 2, 12),
			box(14, 2, 5, 15, 7, 11),
			box(1, 2, 5, 2, 7, 11),
			box(1, 2, 11, 15, 7, 12),
			box(1, 2, 4, 15, 7, 5),
			box(1, 7, 12, 15, 15, 13),
			box(14, 0, 11, 15, 1, 12),
			box(1, 0, 11, 2, 1, 12),
			box(14, 0, 4, 15, 1, 5),
			box(1, 0, 4, 2, 1, 5)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

	public SCP902Block(Properties properties) {
		super(properties);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (state.getBlock().equals(oldState.getBlock())) return;
		level.playSound(null, pos, SCPSounds.CLOCK_TICKING.get(), SoundSource.BLOCKS, 2.5F, 1F);
		level.scheduleTick(pos, this, 40);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.playSound(null, pos, SCPSounds.CLOCK_TICKING.get(), SoundSource.BLOCKS, 2.5F, 1F);
		level.scheduleTick(pos, this, 40);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		pLevel.setBlockAndUpdate(pPos, pState.cycle(OPEN));
		if (pLevel.isClientSide && !pState.getValue(OPEN))
			pPlayer.displayClientMessage(LockdownTextComponents.SCP902_OPENED, true);
		return InteractionResult.CONSUME;
	}

	@Override
	public @org.jetbrains.annotations.Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(OPEN, false);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(OPEN));
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return pState.getValue(OPEN) ? SHAPES_OPEN[pState.getValue(FACING).get2DDataValue()] : SHAPES_CLOSED[pState.getValue(FACING).get2DDataValue()];
	}
}
