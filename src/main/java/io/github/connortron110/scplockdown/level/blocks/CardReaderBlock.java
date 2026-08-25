package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.LockdownConfig;
import io.github.connortron110.scplockdown.level.blockentity.CardReaderBlockEntity;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.level.items.ScrewDriverItem;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.utils.Utils;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class CardReaderBlock extends LockdownHorizontalBlock implements IScrewdriverInteraction, EntityBlock {

	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final BooleanProperty PROG = BooleanProperty.create("prog");  //  Programming mode
	private static final VoxelShape[] SHAPES = VoxelShapeHelper.createHorizontalFacingVoxels(box(4, 0, 13, 12, 12, 16));

	public CardReaderBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false).setValue(PROG, true));
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		ItemStack stack = pPlayer.getItemInHand(pHand);
		boolean isCreative = pPlayer.isCreative() && !(stack.getItem() instanceof KeycardItem) && LockdownConfig.COMMON.canCreativeHandUnlockCardReader();

		//  Edge case of Screwdriver
		if (ScrewDriverItem.isScrewdriver(stack)) return InteractionResult.PASS;

		if (KeycardItem.isKeycard(stack) || isCreative) {
			if (pLevel.isClientSide) return InteractionResult.sidedSuccess(true);
			CardReaderBlockEntity readerBE = (CardReaderBlockEntity) pLevel.getBlockEntity(pPos);

			//  If the reader is in program mode, link the card
			if (pState.getValue(PROG) && KeycardItem.isKeycard(stack)) {
				readerBE.linkCard(stack, pPlayer);
				return InteractionResult.SUCCESS;
			}

			if (readerBE.tryActivate(stack, isCreative)) {
				//  Success
				if (!pState.getValue(POWERED)) {
					pLevel.updateNeighborsAt(pPos, this);
					pLevel.updateNeighborsAt(pPos.relative(pState.getValue(FACING).getOpposite()), this);
				}
			} else {
				pPlayer.displayClientMessage(CardReaderBlockEntity.ACCESS_DENIED, true);
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
		pLevel.updateNeighborsAt(pPos, this);
		pLevel.updateNeighborsAt(pPos.relative(pState.getValue(FACING).getOpposite()), this);
	}

	@Override
	public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
		return pBlockState.getValue(POWERED) ? 15 : 0;
	}

	@Override
	public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
		return pBlockState.getValue(POWERED) && pSide == pBlockState.getValue(FACING) ? 15 : 0; //FIXME
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPES[pState.getValue(FACING).get2DDataValue()];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(POWERED).add(PROG);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return SCPBlockEntities.CARD_READER.get().create(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide ? null : (pLevel1, pPos, pState1, pBlockEntity) -> ((CardReaderBlockEntity) pBlockEntity).tick();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
		Utils.createToolTip(tooltip, "card-reader", 1);
	}

	public static final Component PROGRAMMING_TIP = Component.translatable("scplockdown.block.cardreader.programmingtip");

	@Override
	public void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver) {
		if (level.getBlockEntity(pos) instanceof CardReaderBlockEntity cardReaderBE) {
			//  Open GUI if already in programming mode
			if (state.getValue(PROG)) {
				if (player instanceof ServerPlayer serverPlayer) {
					cardReaderBE.openProgrammingScreen(serverPlayer);
				}
				return;
			}

			//  Put into programming if reader has recently accepted a card
			if (state.getValue(POWERED)) {
				level.setBlockAndUpdate(pos, state.setValue(PROG, true));

				if (player instanceof ServerPlayer serverPlayer) {
					cardReaderBE.openProgrammingScreen(serverPlayer);
				}
			} else {
				player.displayClientMessage(PROGRAMMING_TIP, true);
			}
		}
	}
}
