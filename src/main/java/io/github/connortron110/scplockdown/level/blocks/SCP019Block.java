package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.events.hooks.IActionOnMining;
import io.github.connortron110.scplockdown.level.entity.SCP019Entity;
import io.github.connortron110.scplockdown.level.entity.variants.SCP019EnumVariants;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

/**
 * No longer has a tile entity, instead relies on randomness (from ten minutes to approx 6-12 on avg)
 */
public class SCP019Block extends LockdownDoubleTallBlock implements IActionOnMining {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

	private static final VoxelShape[] SHAPE_TOP = Utils.makeHorizontalAxisShapes(Stream.of(
			Block.box(2, 9, 7.5, 5, 12, 8.5),
			Block.box(11, 9, 7.5, 14, 12, 8.5),
			Block.box(2.5, 4, 7.5, 3.5, 9, 8.5),
			Block.box(12.5, 4, 7.5, 13.5, 9, 8.5),
			Block.box(3, 1.5, 7, 4, 4.5, 9),
			Block.box(12, 1.5, 7, 13, 4.5, 9),
			Block.box(3.5, 8, 3.5, 12.5, 9, 12.5),
			Block.box(4.5, 7, 4.5, 11.5, 8, 11.5),
			Block.box(5, 6, 5, 11, 7, 11),
			Block.box(5.5, 2, 5.5, 10.5, 6, 10.5),
			Block.box(4, 1, 4, 12, 2, 12),
			Block.box(3.5, 0, 3.5, 12.5, 1, 12.5)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape SHAPE_BOTTOM = Stream.of(
			Block.box(3.5, 3, 3.5, 12.5, 8, 12.5),
			Block.box(3, 8, 3, 13, 16, 13),
			Block.box(4.5, 0, 4.5, 11.5, 1, 11.5),
			Block.box(4, 1, 4, 12, 3, 12)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public SCP019Block(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	protected BlockState setAdditionalDefaultStates() {
		return getStateDefinition().any().setValue(AXIS, Direction.Axis.X);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return isLowerHalf(state) ? SHAPE_BOTTOM : SHAPE_TOP[state.getValue(AXIS).equals(Direction.Axis.Z) ? 0 : 1];
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(200) != 0) return;
		//TODO Add cooler functionality
		spawn019Instance(level, getPosForHalf(level, pos, DoubleBlockHalf.UPPER));
	}

	@Override
	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return false;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null) return null;
		return state.setValue(AXIS, context.getHorizontalDirection().getAxis());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder.add(AXIS));
	}

	@Override
	public void miningTick(Level level, BlockPos pos, Player player) {
		if (level.random.nextInt(10) != 0) return;
		spawn019Instance(level, getPosForHalf(level, pos, DoubleBlockHalf.UPPER));
	}

	/**
	 * Throws out entities in a random arc/direction to make it seem like they were spat out
	 *
	 * @param level
	 * @param topPos
	 */
	public static void spawn019Instance(Level level, BlockPos topPos) {
		SCP019Entity monster = SCPEntities.SCP019.get().create(level);
		monster.setPos(topPos.getX() + 0.5, topPos.getY() + 0.3, topPos.getZ() + 0.5);

		RandomSource ran = level.random;
		double x = (0.1 + (ran.nextFloat() * 0.01)) * (ran.nextBoolean() ? 1 : -1);
		double z = (0.1 + (ran.nextFloat() * 0.01)) * (ran.nextBoolean() ? 1 : -1);
		double y = 0.4 + (ran.nextFloat() * 0.1);

		monster.setDeltaMovement(new Vec3(x, y, z));
		monster.setVariant(monster, SCP019EnumVariants.values()[ran.nextInt(SCP019EnumVariants.values().length)]);
		monster.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y, z).add(monster.position()));
		level.addFreshEntity(monster);
	}
}
