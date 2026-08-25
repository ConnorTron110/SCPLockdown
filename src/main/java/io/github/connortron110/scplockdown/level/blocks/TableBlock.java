package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class TableBlock extends LockdownBlock {

	private static final VoxelShape SHAPE = Stream.of(
			box(0, 15, 0, 16, 16, 16),
			box(1, 14, 1, 15, 15, 2),
			box(1, 14, 14, 15, 15, 15),
			box(1, 14, 2, 2, 15, 14),
			box(14, 14, 2, 15, 15, 14),
			box(1, 0, 1, 2, 14, 2),
			box(1, 0, 14, 2, 14, 15),
			box(14, 0, 14, 15, 14, 15),
			box(14, 0, 1, 15, 14, 2)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public TableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
