package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.level.blocks.IScrewdriverInteraction;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ScrewDriverItem extends Item {

	public ScrewDriverItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if (pPlayer.isCrouching()) {
			pPlayer.getItemInHand(pUsedHand).setTag(this.getDefaultInstance().getOrCreateTag());
			pPlayer.displayClientMessage(LockdownTextComponents.SCREWDRIVER_TAGS_CLEAR, true);
		}
		return super.use(pLevel, pPlayer, pUsedHand);
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		BlockState state = pContext.getLevel().getBlockState(pContext.getClickedPos());
		if (state.getBlock() instanceof IScrewdriverInteraction driverInteractBlock) {
			driverInteractBlock.onScrewDriver(state, pContext.getLevel(), pContext.getClickedPos(), pContext.getPlayer(), pContext.getItemInHand());
			return InteractionResult.sidedSuccess(pContext.getLevel().isClientSide);
		}
		return InteractionResult.PASS;
	}

	public static boolean isScrewdriver(ItemStack stack) {
		return stack.getItem() == SCPItems.SCREWDRIVER.asItem();
	}
}
