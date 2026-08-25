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

public class ToiletBlock extends AbstractChairBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			box(5, 0, 3, 11, 5, 14),
			box(3, 5, 1, 13, 6, 16),
			box(3, 6, 1, 4, 8, 10),
			box(2.5, 15, 9.5, 13.4, 16, 16.4),
			box(12, 6, 1, 13, 8, 10),
			box(4, 6, 1, 12, 8, 2),
			box(3, 6, 10, 13, 15, 16),
			box(2.5, 8, 0.5, 13.5, 9, 2.5),
			box(4.5, 8, 8.5, 11.5, 9, 10),
			box(2.5, 8, 2.5, 4.5, 9, 10),
			box(11.5, 8, 2.5, 13.5, 9, 10),
			box(11, 13, 9, 13, 14, 10),
			box(6, 6, 6, 10, 6.1, 10)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public ToiletBlock(Properties properties) {
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
