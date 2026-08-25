package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallLampBlock extends FaceAttachedHorizontalDirectionalBlock {

	private static final VoxelShape[] HORIZONTAL_SHAPES = Utils.makeHorizontalShapes(Shapes.join(box(7, 7, 13, 9, 9, 14.000000000000002), box(6, 6, 14, 10, 10, 16), BooleanOp.OR));
	private static final VoxelShape CEILING_SHAPE = Shapes.join(box(7, 13, 7, 9, 14.000000000000002, 9), box(6, 14, 6, 10, 16, 10), BooleanOp.OR);
	private static final VoxelShape FLOOR_SHAPE = Shapes.join(box(7, 1.9999999999999982, 7, 9, 3, 9), box(6, 0, 6, 10, 2, 10), BooleanOp.OR);

	public SmallLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACE)) {
			case CEILING -> CEILING_SHAPE;
			case FLOOR -> FLOOR_SHAPE;
			default -> HORIZONTAL_SHAPES[state.getValue(FACING).get2DDataValue()];
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FACE);
	}
}
