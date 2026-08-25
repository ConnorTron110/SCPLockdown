package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TothBrushItem extends SCPItem {
	public TothBrushItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BLOCK; //TODO Possible "Brush" Animation (and sound)
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getPlayer() != null) {
			context.getPlayer().startUsingItem(context.getHand());
		}
		return InteractionResult.PASS;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		if (!level.isClientSide) {
			HitResult result = livingEntity.pick(5D, 0F, false);
			if (result.getType() == HitResult.Type.BLOCK) {
				BlockPos blockpos = ((BlockHitResult) result).getBlockPos();
				if (level.getBlockState(blockpos).getDestroySpeed(level, blockpos) >= 0) {
					level.destroyBlock(blockpos, false);
				}
			}
		}
		return stack;
	}

	@SubscribeEvent
	public static void useTothBrushStartEvent(LivingEntityUseItemEvent.Start event) {
		if (event.getItem().getItem() instanceof TothBrushItem) {
			LivingEntity livingEntity = event.getEntity();
			HitResult result = livingEntity.pick(5D, 0F, false);
			if (result.getType() == HitResult.Type.BLOCK) {
				BlockPos blockpos = ((BlockHitResult) result).getBlockPos();
				if (livingEntity.level().getBlockState(blockpos).getDestroySpeed(livingEntity.level(), blockpos) >= 0) {
					event.setDuration(Mth.floor(livingEntity.level().getBlockState(blockpos).getDestroySpeed(livingEntity.level(), blockpos) * 20 + 1));
				}
			}
		}
	}
}
