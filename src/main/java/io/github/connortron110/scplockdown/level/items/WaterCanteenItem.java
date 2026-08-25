package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.utils.nbt.NBTWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WaterCanteenItem extends SCPItem {

	private static final String KEY_USED = "Used Amount";

	public WaterCanteenItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		ItemStack stack = context.getItemInHand();
		NBTWrapper nbt = NBTWrapper.getNBT(stack);
		byte usage = nbt.getOrCreateKey(KEY_USED, (byte) 10);

		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof BonemealableBlock growable) {
			if (growable.isBonemealSuccess(level, level.random, pos, state)) {
				if (usage == 0) {
					if (!level.isClientSide) {
						growable.performBonemeal((ServerLevel) level, level.random, pos, state);
					}
					usage = 11; //11 since when we go below to do the effects, we remove one, making it 10
				}

				//FIXME No particles across the surface, Possible util to have a particle manager of storts
				//level.addParticle(ParticleTypes.FALLING_WATER, pos.getX(), pos.getY() + 0.5D, pos.getZ(), 0 ,0 ,0);
				if (!level.isClientSide) {
					ServerLevel serverWorld = (ServerLevel) level;
					serverWorld.sendParticles(ParticleTypes.FALLING_WATER, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 5, 0, 0, 0, 0);
				}
				level.playSound(null, pos, SoundEvents.BOAT_PADDLE_WATER, SoundSource.MASTER, 1, 1);
				usage--;
			}
		}

		nbt.setByte(KEY_USED, usage);
		nbt.save();
		return InteractionResult.SUCCESS;
	}
}
