package io.github.connortron110.scplockdown.level.blocks;

import io.github.connortron110.scplockdown.level.items.CandyItem;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.CBSCP330Sync;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class SCP330Block extends Block {

	private static final VoxelShape SHAPE = Stream.of(
			Block.box(6, 2.5, 5, 7, 3.5, 6),
			Block.box(3, 2, 3, 13, 3, 13),
			Block.box(3, 0, 3, 13, 1, 13),
			Block.box(3, 0.5, 12.5, 13, 3.5, 13.5),
			Block.box(3, 0.5, 2.5, 13, 3.5, 3.5),
			Block.box(2.5, 0.5, 3, 3.5, 3.5, 13),
			Block.box(12.5, 0.5, 3, 13.5, 3.5, 13),
			Block.box(9, 2.5, 10, 10, 3.5, 11),
			Block.box(5, 2.5, 9, 6, 3.5, 10),
			Block.box(10, 2.5, 6, 11, 3.5, 7)
	).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public SCP330Block(Properties properties) {
		super(properties);
	}

	public static final String CANDIES_KEY = "SCP330CandiesTaken";
	public static final String CANDIES_TIME_KEY = "SCP330TimeTook";

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (hand == InteractionHand.MAIN_HAND) {
			if (!level.isClientSide) {
				CompoundTag playerTag = player.getPersistentData();

				//	Populate tags if they don't exist yet
				if (!playerTag.contains(CANDIES_KEY) && !player.isCreative()) {
					playerTag.putInt(CANDIES_KEY, 0);
					playerTag.putLong(CANDIES_TIME_KEY, level.getGameTime());
				}

				//	Check time last taken >= day reset count
				if (playerTag.contains(CANDIES_TIME_KEY) && (level.getGameTime() - playerTag.getLong(CANDIES_TIME_KEY)) >= Level.TICKS_PER_DAY) {
					playerTag.putInt(CANDIES_KEY, 0);
					playerTag.putLong(CANDIES_TIME_KEY, level.getGameTime());
				}

				if (!player.isCreative()) {
					playerTag.putInt(CANDIES_KEY, playerTag.getInt(CANDIES_KEY) + 1);
				}

				//	Sync to all clients
				SCPNetwork.NETWORK.send(PacketDistributor.ALL.noArg(), new CBSCP330Sync(player.getUUID(), playerTag.getInt(CANDIES_KEY), playerTag.getLong(CANDIES_TIME_KEY)));

				if (hasPlayerTakenTooManyCandies(player)) {
					player.addEffect(new MobEffectInstance(SCPEffects.SCP330_NOARMS.get(), -1, 0, false, false, true));
					return InteractionResult.SUCCESS;
				}

				ItemStack candyStack = CandyItem.CANDIES.get().get(level.random.nextInt(CandyItem.CANDIES.get().size())).copy();
				if (!player.addItem(candyStack)) {
					player.drop(candyStack, false, false);
				}

			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	public static boolean hasPlayerTakenTooManyCandies(Player player) {
		return player.getPersistentData().getInt(CANDIES_KEY) > 2;
	}

	@Nonnull
	@Override
	public VoxelShape getShape(@Nonnull BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
		return SHAPE;
	}
}
