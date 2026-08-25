package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.events.lure.interfaces.IBlockLurable;
import io.github.connortron110.scplockdown.level.blockentity.SCP035CaseBlockEntity;
import io.github.connortron110.scplockdown.level.items.SCP035MaskItem;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPItems;
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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Used for both the telekill block and the normal block
 */
public class SCP035GlassCaseBlock extends LockdownHorizontalBlock implements IBlockLurable, EntityBlock {

	//TODO Corrosive "Carpet" that gets secreted by this block (and entity)

	//TODO Change the Lure event to make it so some SCPs require the player to look at it first
	//TODO Crazy - "Bloodstones, 997 and 035 are area I believe the rest are eye contact"

	private static final VoxelShape NO_GLASS_SHAPE = Shapes.join(box(0, 0, 0, 16, 1, 16), box(4, 1, 4, 12, 6, 12), BooleanOp.OR);

	public static final BooleanProperty EMPTY = BooleanProperty.create("empty"); //FIXME Lures when empty
	public static final IntegerProperty DAMAGE = IntegerProperty.create("damage", 0, 3);

	public SCP035GlassCaseBlock(Properties properties) {
		super(properties.noOcclusion());
		registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(EMPTY, Boolean.FALSE).setValue(DAMAGE, 0));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (state.getValue(DAMAGE).equals(3)) {
			if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof SCP035CaseBlockEntity be) {
				ItemStack stackInHand = player.getItemInHand(hand);

				//FIXME Possible Client/Server NBT missmatch
				if (state.getValue(EMPTY)) {
					//Is empty and check if player has 035 in hand
					if (stackInHand.getItem() instanceof SCP035MaskItem) {
						level.setBlockAndUpdate(pos, state.setValue(EMPTY, false));
						be.setComedy(SCP035MaskItem.isComedy(stackInHand));
						stackInHand.shrink(1);
						return InteractionResult.sidedSuccess(level.isClientSide);
					}
				} else {
					//Is not empty, should give player the mask
					if (stackInHand.isEmpty() && hand == hand.MAIN_HAND) {
						level.setBlockAndUpdate(pos, state.setValue(EMPTY, true));
						ItemStack mask = SCPItems.SCP035_MASK.getDefaultInstance();
						SCP035MaskItem.setComedy(mask, be.isComedy());
						if (!player.addItem(mask)) {
							player.drop(mask, false);
						}
						return InteractionResult.sidedSuccess(level.isClientSide);
					}
				}
			}
		}
		return super.use(state, level, pos, player, hand, hit);
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
		if (state.getValue(DAMAGE) == 3 || (player.isCreative() && !player.isCrouching()))
			return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);

		level.setBlockAndUpdate(pos, state.setValue(DAMAGE, 3));
		level.levelEvent(player, 2001, pos, getId(state)); //Plays sound and particles to clients

		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        /* //FIXME Drop Case with values if it contains 035 or not
        TileEntity tileentity = pBuilder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof ShulkerBoxTileEntity) {
            ShulkerBoxTileEntity shulkerboxtileentity = (ShulkerBoxTileEntity)tileentity;
            pBuilder = pBuilder.withDynamicDrop(new ResourceLocation("contents"), (p_220168_1_, p_220168_2_) -> {
                for(int i = 0; i < shulkerboxtileentity.getContainerSize(); ++i) {
                    p_220168_2_.accept(shulkerboxtileentity.getItem(i));
                }

            });
        }

         */

		return super.getDrops(pState, pParams);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return SCPBlockEntities.SCP035_CASE.get().create(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide ? null : (pLevel1, pPos, pState1, pBlockEntity) -> ((SCP035CaseBlockEntity) pBlockEntity).tick();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(DAMAGE) == 3 ? NO_GLASS_SHAPE : Shapes.block();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(EMPTY, DAMAGE, FACING);
	}

	@Override
	public void handleLure() {

	}

	@Override
	public void untrapEntity() {

	}

    /*
    @Override
    public void handleTrappedBlockEvent(World world, Entity trappedEntity, BlockPos pos, int ticks) {
        //TODO
    }

    @Override
    public boolean doesDirectionMatterToLure() {
        return false;
    }

    @Override
    public boolean mustLookAtFirstToLure() {
        return false;
    }

     */
}
