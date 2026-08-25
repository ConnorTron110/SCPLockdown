package io.github.connortron110.scplockdown.level.blocks.scp002furnitire;

import io.github.connortron110.scplockdown.level.blocks.LockdownBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class SCP002PlantPotBlock extends LockdownBlock {

	private static final VoxelShape SHAPE = Stream.of(
			Block.box(5, 0, 5, 11, 4, 11),
			Block.box(5, 4, 5, 6, 6, 11),
			Block.box(10, 4, 5, 11, 6, 11),
			Block.box(6, 4, 5, 10, 6, 6),
			Block.box(6, 4, 10, 10, 6, 11)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public SCP002PlantPotBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE;
	}
}
