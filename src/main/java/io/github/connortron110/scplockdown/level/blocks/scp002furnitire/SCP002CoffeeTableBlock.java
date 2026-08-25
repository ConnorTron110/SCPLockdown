package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.LockdownBlock;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class SCP002CoffeeTableBlock extends LockdownBlock {

	public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	private static final VoxelShape[] SHAPE = Utils.makeHorizontalAxisShapes(Stream.of(
			Block.box(3, 8, 13, 13, 9, 14),
			Block.box(3, 3, 4, 4, 4, 12),
			Block.box(12, 3, 4, 13, 4, 12),
			Block.box(4, 4, 12, 12, 5, 13),
			Block.box(4, 4, 3, 12, 5, 4),
			Block.box(12, 0, 12, 13, 8, 13),
			Block.box(12, 0, 3, 13, 8, 4),
			Block.box(3, 0, 12, 4, 8, 13),
			Block.box(3, 0, 3, 4, 8, 4),
			Block.box(3, 8, 2, 13, 9, 3),
			Block.box(1, 8, 4, 15, 9, 12),
			Block.box(2, 8, 3, 14, 9, 4),
			Block.box(2, 8, 12, 14, 9, 13)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public SCP002CoffeeTableBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(HORIZONTAL_AXIS, Direction.Axis.X));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(HORIZONTAL_AXIS, context.getHorizontalDirection().getAxis());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE[state.getValue(HORIZONTAL_AXIS).equals(Direction.Axis.Z) ? 0 : 1];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(HORIZONTAL_AXIS);
	}
}
