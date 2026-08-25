package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class Junc4XPipeBlock extends AbstractPipeBlock {

	private static final VoxelShape SHAPE_X = Stream.of(
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(13, 2, 0, 14, 3, 2),
			Block.box(11, 1, 0, 13, 2, 1),
			Block.box(11, 14, 0, 13, 15, 1),
			Block.box(3, 14, 0, 5, 15, 1),
			Block.box(1, 11, 0, 2, 13, 3),
			Block.box(14, 11, 0, 15, 13, 3),
			Block.box(14, 3, 0, 15, 5, 3),
			Block.box(15, 5, 0, 16, 11, 5),
			Block.box(1, 3, 0, 2, 5, 3),
			Block.box(3, 1, 0, 5, 2, 1),
			Block.box(5, 15, 0, 11, 16, 1),
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(5, 0, 0, 11, 1, 1),
			Block.box(0, 5, 0, 1, 11, 5),
			Block.box(2, 13, 2, 3, 16, 3),
			Block.box(13, 13, 2, 14, 16, 3),
			Block.box(13, 13, 13, 14, 16, 14),
			Block.box(11, 14, 14, 13, 16, 15),
			Block.box(11, 14, 1, 13, 16, 2),
			Block.box(3, 14, 1, 5, 16, 2),
			Block.box(1, 11, 3, 2, 16, 5),
			Block.box(14, 11, 3, 15, 16, 5),
			Block.box(14, 11, 11, 15, 16, 13),
			Block.box(15, 8, 5, 16, 16, 11),
			Block.box(1, 11, 11, 2, 16, 13),
			Block.box(3, 14, 14, 5, 16, 15),
			Block.box(2, 13, 13, 3, 16, 14),
			Block.box(0, 8, 5, 1, 16, 11),
			Block.box(2, 0, 13, 3, 3, 14),
			Block.box(13, 0, 13, 14, 3, 14),
			Block.box(13, 0, 2, 14, 3, 3),
			Block.box(11, 0, 1, 13, 2, 2),
			Block.box(11, 0, 14, 13, 2, 15),
			Block.box(3, 0, 14, 5, 2, 15),
			Block.box(1, 0, 11, 2, 5, 13),
			Block.box(14, 0, 11, 15, 5, 13),
			Block.box(14, 0, 3, 15, 5, 5),
			Block.box(15, 0, 5, 16, 8, 11),
			Block.box(1, 0, 3, 2, 5, 5),
			Block.box(3, 0, 1, 5, 2, 2),
			Block.box(2, 0, 2, 3, 3, 3),
			Block.box(0, 0, 5, 1, 8, 11),
			Block.box(2, 2, 14, 3, 3, 16),
			Block.box(13, 2, 14, 14, 3, 16),
			Block.box(13, 13, 14, 14, 14, 16),
			Block.box(11, 14, 15, 13, 15, 16),
			Block.box(11, 1, 15, 13, 2, 16),
			Block.box(3, 1, 15, 5, 2, 16),
			Block.box(1, 3, 13, 2, 5, 16),
			Block.box(14, 3, 13, 15, 5, 16),
			Block.box(14, 11, 13, 15, 13, 16),
			Block.box(15, 5, 11, 16, 11, 16),
			Block.box(1, 11, 13, 2, 13, 16),
			Block.box(3, 14, 15, 5, 15, 16),
			Block.box(5, 0, 15, 11, 1, 16),
			Block.box(2, 13, 14, 3, 14, 16),
			Block.box(5, 15, 15, 11, 16, 16),
			Block.box(0, 5, 11, 1, 11, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SHAPE_Y = Stream.of(
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(14, 11, 0, 15, 13, 1),
			Block.box(1, 11, 0, 2, 13, 1),
			Block.box(1, 3, 0, 2, 5, 1),
			Block.box(3, 1, 0, 5, 2, 3),
			Block.box(3, 14, 0, 5, 15, 3),
			Block.box(11, 14, 0, 13, 15, 3),
			Block.box(5, 15, 0, 11, 16, 5),
			Block.box(11, 1, 0, 13, 2, 3),
			Block.box(14, 3, 0, 15, 5, 1),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(13, 2, 0, 14, 3, 2),
			Block.box(15, 5, 0, 16, 11, 1),
			Block.box(5, 0, 0, 11, 1, 5),
			Block.box(0, 2, 2, 3, 3, 3),
			Block.box(0, 13, 2, 3, 14, 3),
			Block.box(0, 13, 13, 3, 14, 14),
			Block.box(0, 11, 14, 2, 13, 15),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 1, 3, 5, 2, 5),
			Block.box(0, 14, 3, 5, 15, 5),
			Block.box(0, 14, 11, 5, 15, 13),
			Block.box(0, 15, 5, 8, 16, 11),
			Block.box(0, 1, 11, 5, 2, 13),
			Block.box(0, 3, 14, 2, 5, 15),
			Block.box(0, 2, 13, 3, 3, 14),
			Block.box(0, 0, 5, 8, 1, 11),
			Block.box(13, 2, 13, 16, 3, 14),
			Block.box(13, 13, 13, 16, 14, 14),
			Block.box(13, 13, 2, 16, 14, 3),
			Block.box(14, 11, 1, 16, 13, 2),
			Block.box(14, 11, 14, 16, 13, 15),
			Block.box(14, 3, 14, 16, 5, 15),
			Block.box(11, 1, 11, 16, 2, 13),
			Block.box(11, 14, 11, 16, 15, 13),
			Block.box(11, 14, 3, 16, 15, 5),
			Block.box(8, 15, 5, 16, 16, 11),
			Block.box(11, 1, 3, 16, 2, 5),
			Block.box(14, 3, 1, 16, 5, 2),
			Block.box(13, 2, 2, 16, 3, 3),
			Block.box(8, 0, 5, 16, 1, 11),
			Block.box(13, 2, 14, 14, 3, 16),
			Block.box(13, 13, 14, 14, 14, 16),
			Block.box(2, 13, 14, 3, 14, 16),
			Block.box(1, 11, 15, 2, 13, 16),
			Block.box(14, 11, 15, 15, 13, 16),
			Block.box(14, 3, 15, 15, 5, 16),
			Block.box(11, 1, 13, 13, 2, 16),
			Block.box(11, 14, 13, 13, 15, 16),
			Block.box(3, 14, 13, 5, 15, 16),
			Block.box(5, 15, 11, 11, 16, 16),
			Block.box(3, 1, 13, 5, 2, 16),
			Block.box(1, 3, 15, 2, 5, 16),
			Block.box(15, 5, 15, 16, 11, 16),
			Block.box(2, 2, 14, 3, 3, 16),
			Block.box(0, 5, 15, 1, 11, 16),
			Block.box(5, 0, 11, 11, 1, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SHAPE_Z = Stream.of(
			Block.box(0, 13, 13, 2, 14, 14),
			Block.box(0, 13, 2, 2, 14, 3),
			Block.box(0, 2, 2, 2, 3, 3),
			Block.box(0, 1, 3, 1, 2, 5),
			Block.box(0, 14, 3, 1, 15, 5),
			Block.box(0, 14, 11, 1, 15, 13),
			Block.box(0, 11, 14, 3, 13, 15),
			Block.box(0, 11, 1, 3, 13, 2),
			Block.box(0, 3, 1, 3, 5, 2),
			Block.box(0, 5, 0, 5, 11, 1),
			Block.box(0, 3, 14, 3, 5, 15),
			Block.box(0, 1, 11, 1, 2, 13),
			Block.box(0, 15, 5, 1, 16, 11),
			Block.box(0, 2, 13, 2, 3, 14),
			Block.box(0, 0, 5, 1, 1, 11),
			Block.box(0, 5, 15, 5, 11, 16),
			Block.box(2, 13, 13, 3, 16, 14),
			Block.box(2, 13, 2, 3, 16, 3),
			Block.box(13, 13, 2, 14, 16, 3),
			Block.box(14, 14, 3, 15, 16, 5),
			Block.box(1, 14, 3, 2, 16, 5),
			Block.box(1, 14, 11, 2, 16, 13),
			Block.box(3, 11, 14, 5, 16, 15),
			Block.box(3, 11, 1, 5, 16, 2),
			Block.box(11, 11, 1, 13, 16, 2),
			Block.box(5, 8, 0, 11, 16, 1),
			Block.box(11, 11, 14, 13, 16, 15),
			Block.box(14, 14, 11, 15, 16, 13),
			Block.box(13, 13, 13, 14, 16, 14),
			Block.box(5, 8, 15, 11, 16, 16),
			Block.box(13, 0, 13, 14, 3, 14),
			Block.box(13, 0, 2, 14, 3, 3),
			Block.box(2, 0, 2, 3, 3, 3),
			Block.box(1, 0, 3, 2, 2, 5),
			Block.box(14, 0, 3, 15, 2, 5),
			Block.box(14, 0, 11, 15, 2, 13),
			Block.box(11, 0, 14, 13, 5, 15),
			Block.box(11, 0, 1, 13, 5, 2),
			Block.box(3, 0, 1, 5, 5, 2),
			Block.box(5, 0, 0, 11, 8, 1),
			Block.box(3, 0, 14, 5, 5, 15),
			Block.box(1, 0, 11, 2, 2, 13),
			Block.box(2, 0, 13, 3, 3, 14),
			Block.box(5, 0, 15, 11, 8, 16),
			Block.box(14, 2, 13, 16, 3, 14),
			Block.box(14, 2, 2, 16, 3, 3),
			Block.box(14, 13, 2, 16, 14, 3),
			Block.box(15, 14, 3, 16, 15, 5),
			Block.box(15, 1, 3, 16, 2, 5),
			Block.box(15, 1, 11, 16, 2, 13),
			Block.box(13, 3, 14, 16, 5, 15),
			Block.box(13, 3, 1, 16, 5, 2),
			Block.box(13, 11, 1, 16, 13, 2),
			Block.box(11, 5, 0, 16, 11, 1),
			Block.box(13, 11, 14, 16, 13, 15),
			Block.box(15, 14, 11, 16, 15, 13),
			Block.box(15, 0, 5, 16, 1, 11),
			Block.box(14, 13, 13, 16, 14, 14),
			Block.box(15, 15, 5, 16, 16, 11),
			Block.box(11, 5, 15, 16, 11, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	//The axis that this is using is the one the pipe is not going
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	public Junc4XPipeBlock(Properties properties) {
		super(properties);
	}

	@Override
	BlockState rotate(BlockState state) {
		return state.cycle(AXIS);
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.JUNC4X_PIPES, SCPBlocks.JUNC4_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		return switch (pState.getValue(AXIS)) {
			default -> SHAPE_X;
			case Y -> SHAPE_Y;
			case Z -> SHAPE_Z;
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}
}
