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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class Junc3PipeBlock extends AbstractPipeBlock {

	private static final VoxelShape[] SHAPE_UP = Utils.makeHorizontalShapes(Stream.of(
			Block.box(2, 13, 13, 3, 16, 14),
			Block.box(13, 3, 13, 14, 16, 14),
			Block.box(13, 13, 2, 14, 16, 3),
			Block.box(11, 15, 1, 13, 16, 2),
			Block.box(11, 5, 14, 13, 16, 15),
			Block.box(3, 11, 14, 5, 16, 15),
			Block.box(1, 15, 11, 2, 16, 13),
			Block.box(14, 5, 11, 15, 16, 13),
			Block.box(14, 13, 3, 15, 16, 5),
			Block.box(5, 5, 15, 11, 16, 16),
			Block.box(15, 11, 5, 16, 16, 11),
			Block.box(1, 14, 3, 2, 16, 5),
			Block.box(3, 14, 1, 5, 16, 2),
			Block.box(2, 13, 2, 3, 16, 3),
			Block.box(5, 15, 0, 11, 16, 1),
			Block.box(0, 15, 5, 1, 16, 11),
			Block.box(0, 2, 13, 13, 3, 14),
			Block.box(0, 13, 13, 2, 14, 14),
			Block.box(0, 13, 2, 2, 14, 3),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 11, 14, 3, 13, 15),
			Block.box(0, 3, 14, 13, 5, 15),
			Block.box(0, 1, 11, 13, 2, 13),
			Block.box(0, 14, 11, 2, 15, 13),
			Block.box(0, 14, 3, 1, 15, 5),
			Block.box(0, 5, 15, 5, 11, 16),
			Block.box(0, 1, 3, 5, 2, 5),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 2, 2, 3, 3, 3),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 0, 5, 11, 1, 11),
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(14, 11, 0, 15, 13, 5),
			Block.box(1, 11, 0, 2, 13, 1),
			Block.box(1, 3, 0, 2, 5, 1),
			Block.box(3, 1, 0, 5, 2, 3),
			Block.box(3, 14, 0, 5, 15, 1),
			Block.box(11, 14, 0, 13, 15, 2),
			Block.box(11, 1, 0, 13, 2, 11),
			Block.box(14, 3, 0, 15, 5, 13),
			Block.box(13, 2, 0, 14, 3, 14),
			Block.box(15, 5, 0, 16, 11, 11),
			Block.box(5, 0, 0, 11, 1, 5)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] SHAPE_DOWN = Utils.makeHorizontalShapes(Stream.of(
			Block.box(2, 13, 0, 3, 14, 3),
			Block.box(13, 13, 0, 14, 14, 13),
			Block.box(13, 2, 0, 14, 3, 3),
			Block.box(11, 1, 0, 13, 2, 1),
			Block.box(11, 14, 0, 13, 15, 11),
			Block.box(3, 14, 0, 5, 15, 5),
			Block.box(1, 11, 0, 2, 13, 1),
			Block.box(14, 11, 0, 15, 13, 11),
			Block.box(14, 3, 0, 15, 5, 3),
			Block.box(5, 15, 0, 11, 16, 11),
			Block.box(15, 5, 0, 16, 11, 5),
			Block.box(1, 3, 0, 2, 5, 2),
			Block.box(3, 1, 0, 5, 2, 2),
			Block.box(2, 2, 0, 3, 3, 3),
			Block.box(5, 0, 0, 11, 1, 1),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 13, 13, 13, 14, 14),
			Block.box(0, 13, 2, 2, 14, 3),
			Block.box(0, 2, 2, 2, 3, 3),
			Block.box(0, 1, 3, 2, 2, 5),
			Block.box(0, 14, 3, 3, 15, 5),
			Block.box(0, 14, 11, 13, 15, 13),
			Block.box(0, 11, 14, 13, 13, 15),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 3, 1, 1, 5, 2),
			Block.box(0, 15, 5, 5, 16, 11),
			Block.box(0, 3, 14, 5, 5, 15),
			Block.box(0, 1, 11, 2, 2, 13),
			Block.box(0, 2, 13, 3, 3, 14),
			Block.box(0, 0, 5, 1, 1, 11),
			Block.box(0, 5, 15, 11, 11, 16),
			Block.box(2, 0, 13, 3, 2, 14),
			Block.box(2, 0, 2, 3, 2, 3),
			Block.box(13, 0, 2, 14, 2, 3),
			Block.box(14, 0, 3, 15, 5, 5),
			Block.box(1, 0, 3, 2, 1, 5),
			Block.box(1, 0, 11, 2, 1, 13),
			Block.box(3, 0, 14, 5, 3, 15),
			Block.box(3, 0, 1, 5, 1, 2),
			Block.box(11, 0, 1, 13, 2, 2),
			Block.box(11, 0, 14, 13, 11, 15),
			Block.box(14, 0, 11, 15, 13, 13),
			Block.box(13, 0, 13, 14, 14, 14),
			Block.box(15, 0, 5, 16, 11, 11),
			Block.box(5, 0, 15, 11, 5, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public static final DirectionProperty AXIS = DirectionProperty.create("axis", Direction.Plane.VERTICAL);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public Junc3PipeBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(AXIS, Direction.UP));
	}

	@Override
	BlockState rotate(BlockState state) {
		if (state.getValue(FACING) == Direction.WEST) {
			state = state.cycle(AXIS);
		}

		return state.setValue(FACING, state.getValue(FACING).getClockWise());
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.JUNC3_PIPES, SCPBlocks.TJUNC_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		return pState.getValue(AXIS) == Direction.UP ? SHAPE_UP[pState.getValue(FACING).get2DDataValue()] : SHAPE_DOWN[pState.getValue(FACING).get2DDataValue()];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS, FACING);
	}
}
