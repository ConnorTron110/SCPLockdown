package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ChairBlock extends AbstractChairBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			box(3, 7, 3, 13, 9, 13),
			box(3, 10, 12, 13, 20, 14),
			box(7, 9, 13, 9, 16, 15),
			box(7.001, 16.001, 14.001, 8.999, 17.999, 14.999),
			box(7.001, 9.001, 12.181, 8.999, 10.999, 14.999),
			box(4, 0, 4, 6, 7, 6),
			box(4, 0, 10, 6, 7, 12),
			box(10, 0, 10, 12, 7, 12),
			box(10, 0, 4, 12, 7, 6)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public ChairBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected float getSittingHeight() {
		return 0.5F;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get2DDataValue()];
	}
}
