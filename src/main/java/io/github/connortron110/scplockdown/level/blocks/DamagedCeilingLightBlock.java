package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DamagedCeilingLightBlock extends LockdownHorizontalBlock {

	public static final BooleanProperty LIGHT = BooleanProperty.create("light");

	public DamagedCeilingLightBlock(Properties properties) {
		super(properties.randomTicks());
	}

	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
		if (pOldState.getBlock() != this) {
			pLevel.scheduleTick(pPos, this, pLevel.random.nextInt(50));
		}
	}

	@Override
	public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		pLevel.setBlockAndUpdate(pPos, pState.cycle(LIGHT));
		pLevel.scheduleTick(pPos, this, pRandom.nextInt(10, 120));
	}

	@Override   //  Also used in CeilingDependantBlock Class
	public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
		return pLevel.getBlockState(pPos.above()).isFaceSturdy(pLevel, pPos.above(), Direction.DOWN, SupportType.RIGID);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return CeilingLightBlock.SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(LIGHT));
	}
}
