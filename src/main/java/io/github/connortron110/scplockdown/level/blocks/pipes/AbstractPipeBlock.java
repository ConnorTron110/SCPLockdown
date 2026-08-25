package io.github.connortron110.scplockdown.level.blocks.pipes;

import io.github.connortron110.scplockdown.level.blocks.IScrewdriverInteraction;
import io.github.connortron110.scplockdown.level.items.ScrewDriverItem;
import io.github.connortron110.scplockdown.registration.holders.ColourObjectsRegistry;
import io.github.connortron110.scplockdown.utils.SCPDefaultColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

public abstract class AbstractPipeBlock extends Block implements IScrewdriverInteraction {

	public AbstractPipeBlock(Properties properties) {
		super(properties.noOcclusion());
	}

	@Override
	public final void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver) {
		if (!level.isClientSide) {
			//Checks for creative and screwdriver in off-hand (and not one in main hand)
			if (player.isCreative() && ScrewDriverItem.isScrewdriver(player.getOffhandItem()) && !ScrewDriverItem.isScrewdriver(player.getMainHandItem())) {
				Function<BlockState, BlockState> func = player.isShiftKeyDown() ? this::cycleType : this::rotate;
				level.setBlockAndUpdate(pos, func.apply(state));
			}
		}
	}

	//TODO Request by Lucent to make them rotatable without a screwdriver
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide) {
			//Checks for creative and screwdriver in off-hand (and not one in main hand)
			if (player.isCreative() && !isHoldingPipe(player)) {
				Function<BlockState, BlockState> func = player.isShiftKeyDown() ? this::cycleType : this::rotate;
				level.setBlockAndUpdate(pos, func.apply(state));
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.FAIL;
	}

	/**
	 * Used to rotate the pipe by 1 <br>
	 * Each pipe has its own pattern on how it rotates, and this method cycles through them
	 *
	 * @param state the state to rotate
	 * @return the rotated state
	 */
	abstract BlockState rotate(BlockState state);

	abstract Pair<ColourObjectsRegistry<? extends AbstractPipeBlock>, ColourObjectsRegistry<? extends AbstractPipeBlock>> getRegistrySwapper();

	abstract VoxelShape shape(BlockState pState);

	/**
	 * Used to cycle through the different Pipe Types.
	 *
	 * @return the next pipe type
	 */
	public final BlockState cycleType(BlockState state) {
		SCPDefaultColors ourColour = getRegistrySwapper().getKey().getColourFromObject(state.getBlock());
		if (ourColour != null) {
			return getRegistrySwapper().getValue().getObjectFromColour(ourColour).defaultBlockState();
		}
		return state;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (context instanceof EntityCollisionContext entityCollisionContext && entityCollisionContext.getEntity() instanceof LivingEntity living) {
			if (isHoldingPipe(living)) {
				return Shapes.block();
			}
		}
		return shape(state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shape(state);
	}

	private boolean isHoldingPipe(LivingEntity entity) {
		if (entity.getMainHandItem().getItem() instanceof BlockItem) {
			if (((BlockItem) entity.getMainHandItem().getItem()).getBlock() instanceof AbstractPipeBlock) {
				return true;
			}
		}

		if (entity.getOffhandItem().getItem() instanceof BlockItem) {
			return ((BlockItem) entity.getOffhandItem().getItem()).getBlock() instanceof AbstractPipeBlock;
		}

		return false;
	}
}
