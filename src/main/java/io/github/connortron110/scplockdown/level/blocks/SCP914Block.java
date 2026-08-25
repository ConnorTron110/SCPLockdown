package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class SCP914Block extends LockdownHorizontalBlock implements EntityBlock, IScrewdriverInteraction {

	public static final String DOOR_LINK_KEY = "SCP914Link";

	private static final VoxelShape[] VISSHAPE = VoxelShapeHelper.createHorizontalFacingVoxels(Stream.of(
			Block.box(0, 0, 0, 16, 16, 16),
			Block.box(7, 3, -1, 9, 5, 0),
			Block.box(6, 6, -1, 10, 10, 0)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public SCP914Block(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		//  First check if the user interacted with the front face where all the interactable actually live
		if (pHit.getDirection() != pState.getValue(FACING) || pHand == InteractionHand.OFF_HAND || !pPlayer.getItemInHand(pHand).isEmpty()) {
			return InteractionResult.PASS;
		}

		//  Block Entity check and retrieve
		SCP914BlockEntity blockEntity;
		if (!(pLevel.getBlockEntity(pPos) instanceof SCP914BlockEntity be)) {
			return InteractionResult.PASS;
		} else blockEntity = be;

		//  Normalise the input coords to the face so it's easier to work with
		Vec3 hitLoc = pHit.getLocation().subtract(pPos.getX(), pPos.getY(), pPos.getZ());
		double yPosition = hitLoc.y;
		double xPosition = hitLoc.get(pHit.getDirection().getClockWise().getAxis());
		if (pHit.getDirection() == Direction.NORTH || pHit.getDirection() == Direction.EAST) {   //  Flip "x" direction for north and east facing
			xPosition = 1 + (xPosition * -1);
		}

		final float KnobTopLeftXY = 6 / 16F;
		final float KnobBottomRightXY = 10 / 16F;

		final float KeyTopLeftX = 7 / 16F;
		final float KeyTopLeftY = 3 / 16F;

		final float KeyBottomRightX = 9 / 16F;
		final float KeyBottomRightY = 5 / 16F;

		boolean shouldConsumeInteraction = false;

		//  Knob Detection
		if (xPosition >= KnobTopLeftXY && xPosition <= KnobBottomRightXY && yPosition >= KnobTopLeftXY && yPosition <= KnobBottomRightXY) {
			shouldConsumeInteraction = blockEntity.tryKnob(pPlayer);
		}

		//  Key Detection
		if (xPosition >= KeyTopLeftX && xPosition <= KeyBottomRightX && yPosition >= KeyTopLeftY && yPosition <= KeyBottomRightY) {
			shouldConsumeInteraction = blockEntity.tryKey(pPlayer);
		}

		return shouldConsumeInteraction ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return Shapes.block();
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return VISSHAPE[pState.getValue(FACING).get2DDataValue()];
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return SCPBlockEntities.SCP914.get().create(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return ((pLevel1, pPos, pState1, pBlockEntity) -> ((SCP914BlockEntity) pBlockEntity).tick());
	}

	@Override
	public void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver) {
		if (level.getBlockEntity(pos) instanceof SCP914BlockEntity be) {
			//	Check if the input door has been assigned
			if (!be.isInputDoorValid()) {
				//	Check if Link tag already exists
				if (screwdriver.getOrCreateTag().contains(DOOR_LINK_KEY)) {
					player.displayClientMessage(LockdownTextComponents.SCREWDRIVER_HAS_TAG, true);
					return;
				}

				screwdriver.getOrCreateTag().putLong(DOOR_LINK_KEY, pos.asLong());
				player.displayClientMessage(LockdownTextComponents.SCP914_LINK_INPUT, true);
				return;
			}

			//	Check if the output door has been assigned
			if (!be.isOutputDoorValid()) {
				//	Check if Link tag already exists
				if (screwdriver.getOrCreateTag().contains(DOOR_LINK_KEY)) {
					player.displayClientMessage(LockdownTextComponents.SCREWDRIVER_HAS_TAG, true);
					return;
				}

				screwdriver.getOrCreateTag().putLong(DOOR_LINK_KEY, pos.asLong());
				player.displayClientMessage(LockdownTextComponents.SCP914_LINK_OUTPUT, true);
				return;
			}

			//	Both doors are already linked!
			player.displayClientMessage(LockdownTextComponents.SCP914_LINK_EXISTS, true);
		}
	}
}
