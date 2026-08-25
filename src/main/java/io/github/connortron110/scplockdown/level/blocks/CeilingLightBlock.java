package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class CeilingLightBlock extends LockdownHorizontalBlock implements IScrewdriverInteraction {

	protected static final VoxelShape SHAPE = Shapes.join(box(0, 15, 0, 16, 16, 16), box(1, 14, 1, 15, 15, 15), BooleanOp.OR);

	public static final EnumProperty<CeilingLightMode> LIGHT_MODE = EnumProperty.create("mode", CeilingLightMode.class);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public CeilingLightBlock(Properties properties) {
		super(properties);
	}

	@Override   //  Grabbed from CeilingDependantBlock Class
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.above()).isFaceSturdy(level, pos.above(), Direction.DOWN, SupportType.RIGID);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
		if (pState.getValue(LIGHT_MODE).equals(CeilingLightMode.DAMAGED)) {
			pLevel.setBlockAndUpdate(pPos, pState.cycle(LIT));
			pLevel.scheduleTick(pPos, this, Mth.nextInt(pRandom, 10, 120));
		}
	}

	@Override   //  Called on any form of placement or update
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		//  Since this can get called on an update, we want to compare if the modes have changed or this is a new instance, we want to block this from running if the mode is the same in the instance of this being an update
		if ((state.hasProperty(LIGHT_MODE) && oldState.hasProperty(LIGHT_MODE)) && state.getValue(LIGHT_MODE).equals(oldState.getValue(LIGHT_MODE)))
			return;

		//  ON doesn't need to do anything
		//  OFF doesn't need to do anything
		//  TOGGLE doesn't need to do anything

		if (state.getValue(LIGHT_MODE).equals(CeilingLightMode.DAMAGED)) {
			level.scheduleTick(pos, this, level.random.nextInt(50));
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		super.neighborChanged(state, level, pos, block, fromPos, isMoving);
		if (!level.isClientSide) {
			//  If the light is damaged, we don't want it to toggle
			if (state.getValue(LIGHT_MODE).equals(CeilingLightMode.DAMAGED)) return;

			//  Only change the lit state if what we are and what we should be is mismatched
			if (state.getValue(LIT) != level.hasNeighborSignal(pos)) {
				level.setBlock(pos, state.cycle(LIT), Block.UPDATE_ALL);
			}
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIGHT_MODE, CeilingLightMode.TOGGLE).setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LIGHT_MODE);
		builder.add(LIT);
	}

	@Override
	public void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver) {
		level.setBlockAndUpdate(pos, state.cycle(LIGHT_MODE));
	}

	public enum CeilingLightMode implements StringRepresentable {
		TOGGLE("toggle"),
		ON("on"),
		OFF("off"),
		DAMAGED("damaged");

		private final String id;

		CeilingLightMode(String id) {
			this.id = id;
		}

		public String getSerializedName() {
			return this.id;
		}
	}
}
