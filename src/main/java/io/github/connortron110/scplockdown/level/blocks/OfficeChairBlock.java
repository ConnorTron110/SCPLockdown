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

public class OfficeChairBlock extends AbstractChairBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			box(7, 2, 7, 9, 7, 9),
			box(3, 7, 3, 13, 9, 13),
			box(3, 10, 12, 13, 20, 14),
			box(7, 9, 13, 9, 16, 15),
			box(7.001, 16.001, 14.001, 8.999, 17.999, 14.999),
			box(7.001, 9.001, 12.181, 8.999, 10.999, 14.999),
			box(1, 13, 4, 3, 14, 13),
			box(13, 13, 4, 15, 14, 13),
			box(2, 8, 5, 3, 12, 6),
			box(13, 8, 5, 14, 12, 6),
			box(2, 8, 12, 3, 12, 13),
			box(13, 8, 12, 14, 12, 13),
			box(3.0001, 7.0001, 5.0001, 3.9999, 8.4149, 5.9999),
			box(12.0001, 7.0001, 5.0001, 12.9999, 8.4149, 5.9999),
			box(3.0001, 7.0001, 12.0001, 3.9999, 8.4149, 12.9999),
			box(12.0001, 7.0001, 12.0001, 12.9999, 8.4149, 12.9999),
			box(2, 12, 5, 3, 13, 13),
			box(13, 12, 5, 14, 13, 13),
			box(3.6999999999999993, 0, 3.6999999999999993, 12.3, 2, 12.3)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public OfficeChairBlock(Properties properties) {
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
