package io.github.connortron110.scplockdown.level.blocks;

import com.google.common.collect.ImmutableMap;
import io.github.connortron110.scplockdown.events.hooks.IActionOnMining;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SCP015PipeBlock extends LockdownBlock implements IActionOnMining {
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;

	private final ImmutableMap<BlockState, VoxelShape> SHAPE_STATE_MAP;

	public SCP015PipeBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(UP, false).setValue(DOWN, false).setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
		SHAPE_STATE_MAP = makeShapes();
	}

	private ImmutableMap<BlockState, VoxelShape> makeShapes() {
		VoxelShape center = Block.box(5, 5, 5, 11, 11, 11);

		VoxelShape shapeUp = Block.box(5, 11, 5, 11, 16, 11);
		VoxelShape shapeDown = Block.box(5, 0, 5, 11, 11, 11);

		VoxelShape shapeNorth = Block.box(5, 5, 0, 11, 11, 5);
		VoxelShape shapeSouth = Block.box(11, 5, 5, 16, 11, 11);
		VoxelShape shapeEast = Block.box(5, 5, 11, 11, 11, 16);
		VoxelShape shapeWest = Block.box(0, 5, 5, 5, 11, 11);

		ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

		//O(2^6) (64)
		for (boolean up : UP.getPossibleValues()) {
			for (boolean down : DOWN.getPossibleValues()) {
				for (boolean north : NORTH.getPossibleValues()) {
					for (boolean east : EAST.getPossibleValues()) {
						for (boolean south : SOUTH.getPossibleValues()) {
							for (boolean west : WEST.getPossibleValues()) {
								VoxelShape shape = center;

								if (up) shape = Shapes.join(shape, shapeUp, BooleanOp.OR);
								if (down) shape = Shapes.join(shape, shapeDown, BooleanOp.OR);
								if (north) shape = Shapes.join(shape, shapeNorth, BooleanOp.OR);
								if (east) shape = Shapes.join(shape, shapeSouth, BooleanOp.OR);
								if (south) shape = Shapes.join(shape, shapeEast, BooleanOp.OR);
								if (west) shape = Shapes.join(shape, shapeWest, BooleanOp.OR);

								BlockState blockstate = this.defaultBlockState().setValue(UP, up).setValue(DOWN, down).setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
								builder.put(blockstate, shape);
							}
						}
					}
				}
			}
		}

		return builder.build();
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE_STATE_MAP.get(pState);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return stateForPos(defaultBlockState(), pContext.getLevel(), pContext.getClickedPos());
	}

	@Override
	public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
		return stateForPos(pState, pLevel, pPos);
	}

	private BlockState stateForPos(BlockState state, LevelAccessor level, BlockPos pos) {
		return state
				.setValue(UP, shouldConnect(level, pos, Direction.UP))
				.setValue(DOWN, shouldConnect(level, pos, Direction.DOWN))
				.setValue(NORTH, shouldConnect(level, pos, Direction.NORTH))
				.setValue(EAST, shouldConnect(level, pos, Direction.EAST))
				.setValue(SOUTH, shouldConnect(level, pos, Direction.SOUTH))
				.setValue(WEST, shouldConnect(level, pos, Direction.WEST));
	}

	private boolean shouldConnect(LevelAccessor level, BlockPos pos, Direction dir) {
		Block block = level.getBlockState(pos.relative(dir)).getBlock();
		return block.equals(this) || block == SCPBlocks.SCP015_BLOCK.get();
	}

	@Override
	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
	}

	@Override
	public void miningTick(Level level, BlockPos pos, Player player) {
		if (!player.isCreative()) {
			player.hurt(SCPDamageTypes.source(level, SCPDamageTypes.SCP015DEFENCE), 2);

			player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 7 * 20, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.WITHER, 5 * 20, 0, true, false));
			player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 14 * 20, 0, true, false));
		}
	}
}
