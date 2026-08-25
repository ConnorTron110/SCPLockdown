package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.LockdownHorizontalBlock;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class SCP002TVBlock extends LockdownHorizontalBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(1, 1.5, 6, 15, 11.5, 9),
			Block.box(2, 2.5, 9, 14, 10.5, 10),
			Block.box(3.5, 0.5, 6.25, 12.5, 1.5, 8.75),
			Block.box(2, 0, 5.5, 14, 1, 9.5),
			Block.box(2, 2, 5.9, 14, 11, 6.9)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public SCP002TVBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPES[pState.getValue(FACING).get2DDataValue()];
	}
}
