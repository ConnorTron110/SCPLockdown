package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
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

public class Junc5PipeBlock extends AbstractPipeBlock {

	private static final VoxelShape[] MAIN_SHAPE = VoxelShapeHelper.createFacingVoxels(Stream.of(
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(14, 11, 0, 15, 13, 1),
			Block.box(1, 11, 0, 2, 13, 1),
			Block.box(1, 3, 0, 2, 5, 1),
			Block.box(3, 1, 0, 5, 2, 2),
			Block.box(3, 14, 0, 5, 15, 2),
			Block.box(11, 14, 0, 13, 15, 2),
			Block.box(5, 15, 0, 11, 16, 1),
			Block.box(11, 1, 0, 13, 2, 2),
			Block.box(14, 3, 0, 15, 5, 1),
			Block.box(13, 2, 0, 14, 3, 2),
			Block.box(15, 5, 0, 16, 11, 1),
			Block.box(5, 0, 0, 11, 1, 1),
			Block.box(0, 2, 2, 3, 3, 3),
			Block.box(0, 13, 2, 3, 14, 3),
			Block.box(0, 13, 13, 3, 14, 14),
			Block.box(0, 11, 14, 5, 13, 15),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 1, 3, 2, 2, 5),
			Block.box(0, 14, 3, 2, 15, 5),
			Block.box(0, 14, 11, 2, 15, 13),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 1, 11, 2, 2, 13),
			Block.box(0, 3, 14, 5, 5, 15),
			Block.box(0, 2, 13, 3, 3, 14),
			Block.box(0, 5, 15, 8, 11, 16),
			Block.box(0, 0, 5, 1, 1, 11),
			Block.box(13, 2, 13, 16, 3, 14),
			Block.box(13, 13, 13, 16, 14, 14),
			Block.box(13, 13, 2, 16, 14, 3),
			Block.box(14, 11, 1, 16, 13, 2),
			Block.box(11, 11, 14, 16, 13, 15),
			Block.box(11, 3, 14, 16, 5, 15),
			Block.box(14, 1, 11, 16, 2, 13),
			Block.box(14, 14, 11, 16, 15, 13),
			Block.box(14, 14, 3, 16, 15, 5),
			Block.box(8, 5, 15, 16, 11, 16),
			Block.box(14, 1, 3, 16, 2, 5),
			Block.box(14, 3, 1, 16, 5, 2),
			Block.box(13, 2, 2, 16, 3, 3),
			Block.box(15, 0, 5, 16, 1, 11),
			Block.box(2, 14, 13, 3, 16, 14),
			Block.box(2, 14, 2, 3, 16, 3),
			Block.box(13, 14, 2, 14, 16, 3),
			Block.box(14, 15, 3, 15, 16, 5),
			Block.box(1, 15, 3, 2, 16, 5),
			Block.box(1, 15, 11, 2, 16, 13),
			Block.box(3, 13, 14, 5, 16, 15),
			Block.box(3, 15, 1, 5, 16, 2),
			Block.box(11, 15, 1, 13, 16, 2),
			Block.box(0, 15, 5, 1, 16, 11),
			Block.box(11, 13, 14, 13, 16, 15),
			Block.box(14, 15, 11, 15, 16, 13),
			Block.box(13, 14, 13, 14, 16, 14),
			Block.box(15, 15, 5, 16, 16, 11),
			Block.box(5, 11, 15, 11, 16, 16),
			Block.box(2, 0, 13, 3, 2, 14),
			Block.box(2, 0, 2, 3, 2, 3),
			Block.box(13, 0, 2, 14, 2, 3),
			Block.box(14, 0, 3, 15, 1, 5),
			Block.box(1, 0, 3, 2, 1, 5),
			Block.box(1, 0, 11, 2, 1, 13),
			Block.box(3, 0, 14, 5, 3, 15),
			Block.box(3, 0, 1, 5, 1, 2),
			Block.box(11, 0, 1, 13, 1, 2),
			Block.box(11, 0, 14, 13, 3, 15),
			Block.box(14, 0, 11, 15, 1, 13),
			Block.box(13, 0, 13, 14, 2, 14),
			Block.box(5, 0, 15, 11, 5, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public Junc5PipeBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.SOUTH));
	}

	@Override
	BlockState rotate(BlockState state) {
		return state.cycle(FACING);
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.JUNC5_PIPES, SCPBlocks.JUNC6_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		return MAIN_SHAPE[pState.getValue(FACING).getAxis() == Direction.Axis.Y ? pState.getValue(FACING).get3DDataValue() : pState.getValue(FACING).getOpposite().get3DDataValue()];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
}
