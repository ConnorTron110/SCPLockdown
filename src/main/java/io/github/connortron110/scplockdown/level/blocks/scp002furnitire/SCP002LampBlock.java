package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.LockdownDoubleTallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class SCP002LampBlock extends LockdownDoubleTallBlock {

	private static final VoxelShape SHAPE_TOP = Stream.of(
			Block.box(5.5, 6, 9.5, 9.5, 7, 10.5),
			Block.box(9.5, 6, 6.5, 10.5, 7, 10.5),
			Block.box(6.5, 6, 5.5, 10.5, 7, 6.5),
			Block.box(5.5, 6, 5.5, 6.5, 7, 9.5),
			Block.box(6.5, 8, 7, 7.5, 9, 9),
			Block.box(7, 8, 8.5, 9, 9, 9.5),
			Block.box(8.5, 8, 7, 9.5, 9, 9),
			Block.box(6, 7, 6, 9, 8, 7),
			Block.box(9, 7, 6, 10, 8, 9),
			Block.box(6, 7, 7, 7, 8, 10),
			Block.box(7, 7, 9, 10, 8, 10),
			Block.box(7, 8, 6.5, 9, 9, 7.5),
			Block.box(7.5, 6, 7.5, 8.5, 8, 8.5),
			Block.box(7.5, 0, 7.5, 8.5, 6, 8.5)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
	private static final VoxelShape SHAPE_BOTTOM = Shapes.join(Block.box(5, 0, 5, 11, 1, 11), Block.box(7.5, 1, 7.5, 8.5, 16, 8.5), BooleanOp.OR);

	public SCP002LampBlock(Properties properties) {
		super(properties.lightLevel(state -> state.getValue(HALF) == DoubleBlockHalf.UPPER ? 1 : 0));
	}

	@Override
	protected @Nullable BlockState setAdditionalDefaultStates() {
		return null;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return pState.getValue(HALF) == DoubleBlockHalf.UPPER ? SHAPE_TOP : SHAPE_BOTTOM;
	}
}
