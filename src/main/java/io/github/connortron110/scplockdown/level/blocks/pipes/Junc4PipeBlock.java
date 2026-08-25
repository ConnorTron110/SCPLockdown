package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.stream.Stream;

//Uses the same amount of states as the TJuncPipe
public class Junc4PipeBlock extends TJuncPipeBlock {

	private static final VoxelShapeHelper MAIN_SHAPE = new VoxelShapeHelper(Stream.of(
			Block.box(2, 2, 0, 3, 3, 2),
			Block.box(2, 13, 0, 3, 14, 2),
			Block.box(13, 13, 0, 14, 14, 2),
			Block.box(14, 11, 0, 15, 13, 1),
			Block.box(1, 11, 0, 2, 13, 1),
			Block.box(1, 3, 0, 2, 5, 1),
			Block.box(3, 1, 0, 5, 2, 3),
			Block.box(3, 14, 0, 5, 15, 2),
			Block.box(11, 14, 0, 13, 15, 2),
			Block.box(5, 15, 0, 11, 16, 1),
			Block.box(11, 1, 0, 13, 2, 3),
			Block.box(14, 3, 0, 15, 5, 1),
			Block.box(13, 2, 0, 14, 3, 2),
			Block.box(15, 5, 0, 16, 11, 1),
			Block.box(5, 0, 0, 11, 1, 5),
			Block.box(0, 2, 2, 3, 3, 3),
			Block.box(0, 13, 2, 3, 14, 3),
			Block.box(0, 13, 13, 3, 14, 14),
			Block.box(0, 11, 14, 5, 13, 15),
			Block.box(0, 11, 1, 2, 13, 2),
			Block.box(0, 3, 1, 2, 5, 2),
			Block.box(0, 1, 3, 5, 2, 5),
			Block.box(0, 14, 3, 2, 15, 5),
			Block.box(0, 14, 11, 2, 15, 13),
			Block.box(0, 5, 0, 1, 11, 1),
			Block.box(0, 1, 11, 8, 2, 13),
			Block.box(0, 3, 14, 8, 5, 15),
			Block.box(0, 2, 13, 8, 3, 14),
			Block.box(0, 5, 15, 8, 11, 16),
			Block.box(0, 0, 5, 8, 1, 11),
			Block.box(8, 2, 13, 16, 3, 14),
			Block.box(13, 13, 13, 16, 14, 14),
			Block.box(13, 13, 2, 16, 14, 3),
			Block.box(14, 11, 1, 16, 13, 2),
			Block.box(11, 11, 14, 16, 13, 15),
			Block.box(8, 3, 14, 16, 5, 15),
			Block.box(8, 1, 11, 16, 2, 13),
			Block.box(14, 14, 11, 16, 15, 13),
			Block.box(14, 14, 3, 16, 15, 5),
			Block.box(8, 5, 15, 16, 11, 16),
			Block.box(11, 1, 3, 16, 2, 5),
			Block.box(14, 3, 1, 16, 5, 2),
			Block.box(13, 2, 2, 16, 3, 3),
			Block.box(8, 0, 5, 16, 1, 11),
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
			Block.box(5, 11, 15, 11, 16, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] VERTICAL_SHAPE = MAIN_SHAPE.rotateShapeZ(90).rotateShapeY(270).createYVoxels();
	private static final VoxelShape[] X_SHAPE = MAIN_SHAPE.createXVoxels();
	private static final VoxelShape[] Z_SHAPE = MAIN_SHAPE.rotateShapeY(270).createZVoxels();

	public Junc4PipeBlock(Properties properties) {
		super(properties);
	}

	private Direction cycleXZ(Direction facing, Direction.Axis axis) {
		if (axis == Direction.Axis.Y) return Direction.NORTH; //Default to North if invalid
		else if (axis == Direction.Axis.Z) {
			return switch (facing) {
				default -> Direction.UP;
				case UP -> Direction.EAST;
				case EAST, DOWN -> Direction.DOWN; //Down stays on down as it should cycle to the different axis
			};
		} else {
			return switch (facing) {
				default -> Direction.NORTH;
				case NORTH -> Direction.UP;
				case UP -> Direction.SOUTH;
				case SOUTH -> Direction.DOWN;
			};
		}
	}

	@Override
	BlockState rotate(BlockState state) {
		//Easiest way to check if the block has an invalid state
		if (!isValidState(state)) {
			return defaultBlockState();
		}

		//Used as a read only
		final Direction.Axis axis = state.getValue(AXIS);
		final Direction facing = state.getValue(FACING);

		if ((facing == Direction.WEST && axis == Direction.Axis.Y) ||
				(facing == Direction.DOWN && axis == Direction.Axis.Z) ||
				(facing == Direction.SOUTH && axis == Direction.Axis.X)) {
			state = state.cycle(AXIS);
			if (axis == Direction.Axis.X) state = state.setValue(FACING, Direction.NORTH);
		} else {
			//No axis Change, rotate facing
			state = state.setValue(FACING, axis != Direction.Axis.Y ? cycleXZ(facing, axis) : state.cycle(FACING).getValue(FACING));
		}

		return state;
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.JUNC4_PIPES, SCPBlocks.JUNC5_PIPES);
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
}
