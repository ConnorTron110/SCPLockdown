package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.LockdownHorizontallyDependantBlock;
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

public class SCP002TableBlock extends LockdownHorizontallyDependantBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(15, 0, 15, 16, 15, 16),
			Block.box(0, 0, 15, 1, 15, 16),
			Block.box(1, 4, 15, 15, 5, 16),
			Block.box(7.5, 4, 0, 8.5, 5, 15),
			Block.box(0, 15, 0, 16, 16, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public SCP002TableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPES[pState.getValue(FACING).get2DDataValue()];
	}
}
