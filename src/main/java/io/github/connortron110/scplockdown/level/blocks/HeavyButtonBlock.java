package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class HeavyButtonBlock extends ButtonBlock {

	//TODO Can be optimized slightly
	private static final VoxelShape[] CEILING_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(4.5, 14, 2, 11.5, 16, 7),
			Block.box(4.5, 14, 9, 11.5, 16, 14),
			Block.box(4.5, 15, 7, 11.5, 16, 9),
			Block.box(5.5, 14, 7, 10.5, 15, 9)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());
	private static final VoxelShape[] HORIZONTAL_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(4.5, 9, 14, 11.5, 14, 16),
			Block.box(4.5, 2, 14, 11.5, 7, 16),
			Block.box(4.5, 7, 15, 11.5, 9, 16),
			Block.box(5.5, 7, 14, 10.5, 9, 15)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());
	private static final VoxelShape[] FLOOR_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(4.5, 0, 9, 11.5, 2, 14),
			Block.box(4.5, 0, 2, 11.5, 2, 7),
			Block.box(4.5, 0, 7, 11.5, 1, 9),
			Block.box(5.5, 1, 7, 10.5, 2, 9)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public HeavyButtonBlock(Properties properties) {
		super(properties, BlockSetType.STONE, 70, false);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		int i = state.getValue(FACING).get2DDataValue();
		return switch (state.getValue(FACE)) {
			case CEILING -> CEILING_SHAPES[i];
			case WALL -> HORIZONTAL_SHAPES[i];
			default -> FLOOR_SHAPES[i];
		};
	}
}
