package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.blockentity.LockerBlockEntity;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

//TODO Could Abstract Multi blocks
public class LockerBlock extends LockdownHorizontalBlock implements EntityBlock {

	public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, Type.values().length - 1);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	private static final String ITEM_TYPE_KEY = "StackType";

	private static final VoxelShape[] TOP_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			box(9, 0, 0, 10, 1.5, 1),
			box(6, 0, 0, 7, 1.5, 1),
			box(0, 0, 1, 16, 16, 15)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	private static final VoxelShape[] BOTTOM_SHAPES = Utils.makeHorizontalShapes(Stream.of(
			box(9, 13.5, 0, 10, 16, 1),
			box(13, 0, 12, 15, 1, 14),
			box(1, 0, 12, 3, 1, 14),
			box(13, 0, 3, 15, 1, 5),
			box(1, 0, 3, 3, 1, 5),
			box(0, 1, 1, 16, 16, 15),
			box(6, 13.5, 0, 7, 16, 1)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());

	public LockerBlock(Properties properties) {
		super(properties);
		registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(TYPE, Type.GREEN.ordinal()).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	public static Direction getConnectedDirection(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide) {
			BlockEntity blockEntity = level.getBlockEntity(state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.relative(Direction.DOWN));
			if (blockEntity instanceof LockerBlockEntity) {
				player.openMenu((LockerBlockEntity) blockEntity);
				//PiglinTasks.angerNearbyPiglins(player, true);
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}

    /*
    @Override //Temporary for the sanity of the builders, or we could keep this
    public void fillItemCategory(ItemGroup pTab, NonNullList<ItemStack> pItems) {
        ItemStack stack = new ItemStack(this);
        for (Type type : Type.values()) {
            stack.setHoverName(new TranslationTextComponent(stack.getDescriptionId() + "." + type.name));
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt(ITEM_TYPE_KEY, type.ordinal());
            stack.setTag(nbt);
            pItems.add(stack.copy());
        }
    }

     */


/*
    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        Type type = getTypeFromState(state);
        ItemStack stack = new ItemStack(this);
        stack.setHoverName(Component.translatable(stack.getDescriptionId() + "." + type.name));
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt(ITEM_TYPE_KEY, type.ordinal());
        stack.setTag(nbt);
        return stack;
    }

 */

	@Override
	public boolean triggerEvent(BlockState pState, Level pLevel, BlockPos pPos, int pId, int pParam) {
		BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
		return blockEntity != null && blockEntity.triggerEvent(pId, pParam);
	}

	//Valid Placement Checks\\

	@Nullable
	@Override //Checks if the block can be placed
	public BlockState getStateForPlacement(BlockPlaceContext pContext) {
		return pContext.getLevel().getBlockState(pContext.getClickedPos().relative(Direction.UP)).canBeReplaced(pContext) ? defaultBlockState().setValue(FACING, pContext.getHorizontalDirection()) : null;
	}

	@Override //When block is updated by neighbors, check if still connected
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (getConnectedDirection(pState) == pFacing) {
			return ((pFacingState.is(this)) && (pState.getValue(HALF) != pFacingState.getValue(HALF)) && (pState.getValue(FACING) == pFacingState.getValue(FACING))) ? pState : Blocks.AIR.defaultBlockState();
		} else return pState;
	}

	@Override //To place the upper block
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
		if (!pLevel.isClientSide) {
			//Temp to set state based on stack id
			CompoundTag nbt = pStack.getOrCreateTag();
			if (nbt.contains(ITEM_TYPE_KEY)) {
				pState = pState.setValue(TYPE, nbt.getInt(ITEM_TYPE_KEY));
				pLevel.setBlockAndUpdate(pPos, pState);
			}


			pLevel.setBlockAndUpdate(pPos.relative(Direction.UP), pState.setValue(HALF, DoubleBlockHalf.UPPER));
			pLevel.updateNeighborsAt(pPos, this);
			pLevel.updateNeighborsAt(pPos.relative(Direction.UP), this);
		}
	}

	@Override //Removes contents of inventory
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if (!pState.is(pNewState.getBlock())) {
			BlockEntity be = pLevel.getBlockEntity(pPos);
            /*
            if (be instanceof IInventory) {
                InventoryHelper.dropContents(pLevel, pPos, (IInventory)be);
                pLevel.updateNeighbourForOutputSignal(pPos, this);
            }

             */

			super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
		}
	}

	@Override //Removes the other half when player is destroying this block
	public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
		//As a cool workaround, the lower half of the block does not drop anything, but the top half will
		if (!pLevel.isClientSide && pPlayer.isCreative() && pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
			pLevel.setBlockAndUpdate(pPos.relative(Direction.UP), Blocks.AIR.defaultBlockState());
			pLevel.updateNeighborsAt(pPos.relative(Direction.UP), this);
		}
		super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		int facing = pState.getValue(FACING).getOpposite().get2DDataValue();
		if (pState.getValue(HALF) == DoubleBlockHalf.UPPER) return TOP_SHAPES[facing];
		else return BOTTOM_SHAPES[facing];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, TYPE, HALF);
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState pState) {
		return PushReaction.DESTROY;
	}

	public static Type getTypeFromState(BlockState state) {
		return Type.values()[state.getValue(TYPE)];
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		if (pState.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return SCPBlockEntities.LOCKER.get().create(pPos, pState);
		} else return null;
	}

	public enum Type {
		GREEN("green"),
		LIME("lime"),
		RUST("rust"),
		WHITE("white");

		public final String name;

		Type(String name) {
			this.name = name;
		}
	}
}
