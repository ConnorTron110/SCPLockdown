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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

public class TJuncPipeBlock extends AbstractPipeBlock {

	private static final VoxelShapeHelper MAIN_SHAPE = new VoxelShapeHelper(Stream.of(
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
			Block.box(13, 2, 0, 14, 3, 2),
			Block.box(15, 5, 0, 16, 11, 1),
			Block.box(5, 0, 0, 11, 1, 5),
			Block.box(0, 2, 2, 3, 3, 3),
			Block.box(0, 13, 2, 3, 14, 3),
			Block.box(0, 13, 13, 8, 14, 14),
			Block.box(0, 11, 14, 8, 13, 15),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 1, 3, 5, 2, 5),
			Block.box(0, 14, 3, 5, 15, 5),
			Block.box(0, 14, 11, 8, 15, 13),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 15, 5, 8, 16, 11),
			Block.box(0, 1, 11, 8, 2, 13),
			Block.box(0, 3, 14, 8, 5, 15),
			Block.box(0, 2, 13, 8, 3, 14),
			Block.box(0, 5, 15, 8, 11, 16),
			Block.box(0, 0, 5, 8, 1, 11),
			Block.box(8, 2, 13, 16, 3, 14),
			Block.box(8, 13, 13, 16, 14, 14),
			Block.box(13, 13, 2, 16, 14, 3),
			Block.box(14, 11, 1, 16, 13, 2),
			Block.box(8, 11, 14, 16, 13, 15),
			Block.box(8, 3, 14, 16, 5, 15),
			Block.box(8, 1, 11, 16, 2, 13),
			Block.box(8, 14, 11, 16, 15, 13),
			Block.box(11, 14, 3, 16, 15, 5),
			Block.box(8, 5, 15, 16, 11, 16),
			Block.box(8, 15, 5, 16, 16, 11),
			Block.box(11, 1, 3, 16, 2, 5),
			Block.box(14, 3, 1, 16, 5, 2),
			Block.box(13, 2, 2, 16, 3, 3),
			Block.box(8, 0, 5, 16, 1, 11)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] VERTICAL_SHAPE = MAIN_SHAPE.rotateShapeZ(90).createYVoxels();
	private static final VoxelShape[] X_SHAPE = MAIN_SHAPE.createXVoxels();
	private static final VoxelShape[] Z_SHAPE = MAIN_SHAPE.rotateShapeY(270).createZVoxels();

	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	public TJuncPipeBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(AXIS, Direction.Axis.X));
	}

	public static boolean isValidState(BlockState state) {
		if (state.getBlock() instanceof TJuncPipeBlock) {
			return state.getValue(FACING).getAxis() != state.getValue(AXIS);
		}

		return false;
	}

	private BlockState horizontalAxisCycle(BlockState state) {
		return state.setValue(AXIS, state.getValue(AXIS) == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
	}

	@Override
	BlockState rotate(BlockState state) {
		//Easiest way to check if the block has an invalid state
		if (!isValidState(state)) {
			return defaultBlockState();
		}

		Direction.Axis axis = state.getValue(AXIS);
		Direction facing = state.getValue(FACING);

		if (!((facing == Direction.UP || facing == Direction.DOWN) && axis == Direction.Axis.Z)) {
			state = state.cycle(FACING);
		}

		if (facing == Direction.WEST && axis == Direction.Axis.Y) {
			state = state.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.DOWN);
		} else if (facing == Direction.UP && axis == Direction.Axis.X) {
			state = state.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.NORTH);
		} else if (axis != Direction.Axis.Y) {
			if (!((facing == Direction.DOWN && axis == Direction.Axis.X) || (facing == Direction.WEST && axis == Direction.Axis.Z))) {
				state = horizontalAxisCycle(state);
			}
		}

		return state;
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.TJUNC_PIPES, SCPBlocks.JUNC4X_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		if (!isValidState(pState)) {
			return Shapes.block();
		}

		Direction facing = pState.getValue(FACING);

		return switch (pState.getValue(AXIS)) {
			case Y -> VERTICAL_SHAPE[VoxelShapeHelper.getYIndex(facing)];
			case X -> X_SHAPE[VoxelShapeHelper.getXZIndex(facing)];
			default -> Z_SHAPE[VoxelShapeHelper.getXZIndex(facing)];
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, AXIS);
	}
}
