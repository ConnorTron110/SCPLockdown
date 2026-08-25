package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class StraightPipeBlock extends AbstractPipeBlock {

	private static final VoxelShape SHAPE_X = Stream.of(
			Block.box(0, 0, 5, 16, 1, 11),
			Block.box(0, 5, 15, 16, 11, 16),
			Block.box(0, 2, 13, 16, 3, 14),
			Block.box(0, 3, 14, 16, 5, 15),
			Block.box(0, 1, 11, 16, 2, 13),
			Block.box(0, 15, 5, 16, 16, 11),
			Block.box(0, 5, 0, 16, 11, 1),
			Block.box(0, 14, 11, 16, 15, 13),
			Block.box(0, 14, 3, 16, 15, 5),
			Block.box(0, 1, 3, 16, 2, 5),
			Block.box(0, 3, 1, 16, 5, 2),
			Block.box(0, 11, 1, 16, 13, 2),
			Block.box(0, 11, 14, 16, 13, 15),
			Block.box(0, 13, 13, 16, 14, 14),
			Block.box(0, 13, 2, 16, 14, 3),
			Block.box(0, 2, 2, 16, 3, 3)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SHAPE_Y = Stream.of(
			Block.box(0, 0, 5, 1, 16, 11),
			Block.box(5, 0, 15, 11, 16, 16),
			Block.box(2, 0, 13, 3, 16, 14),
			Block.box(3, 0, 14, 5, 16, 15),
			Block.box(1, 0, 11, 2, 16, 13),
			Block.box(15, 0, 5, 16, 16, 11),
			Block.box(5, 0, 0, 11, 16, 1),
			Block.box(14, 0, 11, 15, 16, 13),
			Block.box(14, 0, 3, 15, 16, 5),
			Block.box(1, 0, 3, 2, 16, 5),
			Block.box(3, 0, 1, 5, 16, 2),
			Block.box(11, 0, 1, 13, 16, 2),
			Block.box(11, 0, 14, 13, 16, 15),
			Block.box(13, 0, 13, 14, 16, 14),
			Block.box(13, 0, 2, 14, 16, 3),
			Block.box(2, 0, 2, 3, 16, 3)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	private static final VoxelShape SHAPE_Z = Stream.of(
			Block.box(5, 0, 0, 11, 1, 16),
			Block.box(15, 5, 0, 16, 11, 16),
			Block.box(13, 2, 0, 14, 3, 16),
			Block.box(14, 3, 0, 15, 5, 16),
			Block.box(11, 1, 0, 13, 2, 16),
			Block.box(5, 15, 0, 11, 16, 16),
			Block.box(0, 5, 0, 1, 11, 16),
			Block.box(11, 14, 0, 13, 15, 16),
			Block.box(3, 14, 0, 5, 15, 16),
			Block.box(3, 1, 0, 5, 2, 16),
			Block.box(1, 3, 0, 2, 5, 16),
			Block.box(1, 11, 0, 2, 13, 16),
			Block.box(14, 11, 0, 15, 13, 16),
			Block.box(13, 13, 0, 14, 14, 16),
			Block.box(2, 13, 0, 3, 14, 16),
			Block.box(2, 2, 0, 3, 3, 16)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	public StraightPipeBlock(Properties properties) {
		super(properties);
	}

	@Override
	BlockState rotate(BlockState state) {
		return state.cycle(AXIS);
	}

	@Override
	Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper() {
		return Pair.of(SCPBlocks.STRAIGHT_PIPES, SCPBlocks.CORNER_PIPES);
	}

	@Override
	public VoxelShape shape(BlockState pState) {
		Direction.Axis axis = pState.getValue(AXIS);
		switch (axis) {
			case X:
			default:
				return SHAPE_X;
			case Y:
				return SHAPE_Y;
			case Z:
				return SHAPE_Z;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(AXIS, context.getNearestLookingDirection().getAxis());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
		Utils.createToolTip(tooltip, "straight-pipe", 1);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AXIS);
	}
}
