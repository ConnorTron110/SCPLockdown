package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.AbstractChairBlock;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class SCP002ArmChairBlock extends AbstractChairBlock {

	private static final VoxelShape[] SHAPES = Utils.makeHorizontalShapes(Stream.of(
			Block.box(13, 0, 1, 16, 15, 14),
			Block.box(3, 0, 2, 13, 10, 11),
			Block.box(1, 0, 11, 15, 16, 16),
			Block.box(1, 16, 11, 15, 19, 16),
			Block.box(0, 0, 1, 3, 15, 14)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public SCP002ArmChairBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected float getSittingHeight() {
		return 0.6F;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get2DDataValue()];
	}
}
