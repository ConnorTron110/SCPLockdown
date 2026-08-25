package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class CornerPipeBlock extends AbstractPipeBlock {

	private static final VoxelShape[] SHAPES_U = Utils.makeHorizontalShapes(Stream.of(
			Block.box(2, 13, 0, 3, 14, 3),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(13, 2, 0, 14, 3, 14),
			Block.box(11, 1, 0, 13, 2, 14),
			Block.box(11, 14, 0, 13, 15, 2),
			Block.box(3, 14, 0, 5, 15, 1),
			Block.box(1, 11, 0, 2, 13, 3),
			Block.box(14, 11, 0, 15, 13, 5),
			Block.box(14, 3, 0, 15, 5, 11),
			Block.box(15, 5, 0, 16, 11, 5),
			Block.box(1, 3, 0, 2, 5, 11),
			Block.box(3, 1, 0, 5, 2, 15),
			Block.box(2, 2, 0, 3, 3, 14),
			Block.box(5, 0, 0, 11, 1, 16),
			Block.box(0, 5, 0, 1, 11, 11),
			Block.box(2, 3, 13, 3, 16, 14),
			Block.box(13, 3, 13, 14, 16, 14),
			Block.box(13, 13, 2, 14, 16, 3),
			Block.box(11, 15, 1, 13, 16, 2),
			Block.box(11, 1, 14, 13, 16, 15),
			Block.box(3, 2, 14, 5, 16, 15),
			Block.box(1, 3, 11, 2, 16, 13),
			Block.box(14, 3, 11, 15, 16, 13),
			Block.box(14, 13, 3, 15, 16, 5),
			Block.box(5, 1, 15, 11, 16, 16),
			Block.box(15, 5, 5, 16, 16, 11),
			Block.box(1, 11, 3, 2, 16, 5),
			Block.box(3, 14, 1, 5, 16, 2),
			Block.box(2, 14, 2, 3, 16, 3),
			Block.box(5, 15, 0, 11, 16, 1),
			Block.box(0, 11, 5, 1, 16, 11)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] SHAPES_H = Utils.makeHorizontalShapes(Stream.of(
			Block.box(2, 2, 0, 3, 3, 3),
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 14),
			Block.box(14, 11, 0, 15, 13, 14),
			Block.box(1, 11, 0, 2, 13, 2),
			Block.box(1, 3, 0, 2, 5, 1),
			Block.box(3, 1, 0, 5, 2, 3),
			Block.box(3, 14, 0, 5, 15, 5),
			Block.box(11, 14, 0, 13, 15, 11),
			Block.box(5, 15, 0, 11, 16, 5),
			Block.box(11, 1, 0, 13, 2, 11),
			Block.box(14, 3, 0, 15, 5, 15),
			Block.box(13, 2, 0, 14, 3, 14),
			Block.box(15, 5, 0, 16, 11, 16),
			Block.box(5, 0, 0, 11, 1, 11),
			Block.box(0, 2, 13, 13, 3, 14),
			Block.box(0, 13, 13, 13, 14, 14),
			Block.box(0, 13, 2, 3, 14, 3),
			Block.box(0, 11, 1, 1, 13, 2),
			Block.box(0, 11, 14, 15, 13, 15),
			Block.box(0, 3, 14, 14, 5, 15),
			Block.box(0, 1, 11, 13, 2, 13),
			Block.box(0, 14, 11, 13, 15, 13),
			Block.box(0, 14, 3, 3, 15, 5),
			Block.box(0, 5, 15, 15, 11, 16),
			Block.box(0, 15, 5, 11, 16, 11),
			Block.box(0, 1, 3, 5, 2, 5),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 2, 2, 2, 3, 3),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 0, 5, 5, 1, 11)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] SHAPES_D = Utils.makeHorizontalShapes(Stream.of(
			Block.box(13, 2, 0, 14, 3, 3),
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(2, 13, 0, 3, 14, 14),
			Block.box(3, 14, 0, 5, 15, 14),
			Block.box(3, 1, 0, 5, 2, 2),
			Block.box(11, 1, 0, 13, 2, 1),
			Block.box(14, 3, 0, 15, 5, 3),
			Block.box(1, 3, 0, 2, 5, 5),
			Block.box(1, 11, 0, 2, 13, 11),
			Block.box(0, 5, 0, 1, 11, 5),
			Block.box(14, 11, 0, 15, 13, 11),
			Block.box(11, 14, 0, 13, 15, 15),
			Block.box(13, 13, 0, 14, 14, 14),
			Block.box(5, 15, 0, 11, 16, 16),
			Block.box(15, 5, 0, 16, 11, 11),
			Block.box(13, 0, 13, 14, 13, 14),
			Block.box(2, 0, 13, 3, 13, 14),
			Block.box(2, 0, 2, 3, 3, 3),
			Block.box(3, 0, 1, 5, 1, 2),
			Block.box(3, 0, 14, 5, 15, 15),
			Block.box(11, 0, 14, 13, 14, 15),
			Block.box(14, 0, 11, 15, 13, 13),
			Block.box(1, 0, 11, 2, 13, 13),
			Block.box(1, 0, 3, 2, 3, 5),
			Block.box(5, 0, 15, 11, 15, 16),
			Block.box(0, 0, 5, 1, 11, 11),
			Block.box(14, 0, 3, 15, 5, 5),
			Block.box(11, 0, 1, 13, 2, 2),
			Block.box(13, 0, 2, 14, 2, 3),
			Block.box(5, 0, 0, 11, 1, 1),
			Block.box(15, 0, 5, 16, 5, 11)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());


	//Axis Determines the "Up | Down | Horizontal" part of the corner (X Rotation)
	public static final EnumProperty<Direction> AXIS = DirectionProperty.create("axis", Direction.NORTH, Direction.UP, Direction.DOWN);
	//This determines the horizontal direction of the pipe (Y Rotation)
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public CornerPipeBlock(Properties properties) {
		super(properties);
	}

	@Override
	BlockState rotate(BlockState state) {
		state = state.setValue(FACING, state.getValue(FACING).getClockWise());
		if (state.getValue(FACING) == Direction.NORTH) {
			state = state.cycle(AXIS);
		}
		return state;
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.CORNER_PIPES, SCPBlocks.JUNC3_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		return switch (pState.getValue(AXIS)) {
			case UP -> SHAPES_U[pState.getValue(FACING).get2DDataValue()];
			case DOWN -> SHAPES_D[pState.getValue(FACING).get2DDataValue()];
			default -> SHAPES_H[pState.getValue(FACING).get2DDataValue()];
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS, FACING);
	}
}
