package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class CeilingLampBlock extends CeilingDependantBlock {

	public static final VoxelShape SHAPE = Stream.of(
			box(7, 13, 7, 9, 16, 9),
			box(6, 11, 6, 10, 13, 10),
			box(5, 10, 5, 11, 11, 11),
			box(4, 9, 4, 5, 10, 12),
			box(11, 9, 4, 12, 10, 12),
			box(12, 8, 3, 13, 9, 13),
			box(13, 7, 2, 14, 8, 14),
			box(14, 6, 1, 15, 7, 15),
			box(3, 8, 3, 4, 9, 13),
			box(1, 6, 1, 2, 7, 15),
			box(5, 9, 4, 11, 10, 5),
			box(4, 8, 3, 12, 9, 4),
			box(3, 7, 2, 13, 8, 3),
			box(2, 6, 1, 14, 7, 2),
			box(5, 9, 11, 11, 10, 12),
			box(4, 8, 12, 12, 9, 13),
			box(3, 7, 13, 13, 8, 14),
			box(2, 6, 14, 14, 7, 15),
			box(7, 9, 7, 9, 10, 9),
			box(6, 5, 6, 10, 9, 10),
			box(2, 7, 2, 3, 8, 14)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public CeilingLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
