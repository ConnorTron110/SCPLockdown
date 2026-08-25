package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.level.blockentity.CardReaderBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.BlastDoorBlock;
import io.github.connortron110.scplockdown.level.blocks.CardReaderBlock;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.registration.SCPSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SCP005Item extends Item {
	public SCP005Item(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (level.isClientSide) return InteractionResult.sidedSuccess(true);
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);

		//Iron Doors
		if (state.getBlock() instanceof DoorBlock door && !DoorBlock.isWoodenDoor(state)) {
			door.setOpen(context.getPlayer(), level, state, pos, !state.getValue(DoorBlock.OPEN));
			return InteractionResult.SUCCESS;
		}

		//Sliding Doors
		if (state.getBlock() instanceof SlidingDoorBlock) {
			SlidingDoorBlock slidingDoor = (SlidingDoorBlock) state.getBlock();
			level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.OPEN));
			playSound(level, pos, slidingDoor.getSound(state.cycle(BlockStateProperties.OPEN)));
			return InteractionResult.SUCCESS;
		}

		//Blast Doors
		if (state.getBlock() instanceof BlastDoorBlock) {
			BlastDoorBlock blastDoor = (BlastDoorBlock) state.getBlock();
			level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.OPEN));
			playSound(level, pos, blastDoor.getSound(state.cycle(BlockStateProperties.OPEN)));
			return InteractionResult.SUCCESS;
		}

		//Keycard Readers
		if (state.getBlock() instanceof CardReaderBlock) {
			CardReaderBlockEntity readerBE = (CardReaderBlockEntity) level.getBlockEntity(pos);
			readerBE.tryActivate(null, true);
			if (!state.getValue(BlockStateProperties.POWERED)) {
				playSound(level, pos, SCPSounds.KEYCARD_SUCCESS.get());
			}
			return InteractionResult.SUCCESS;
		}

		if (state.hasProperty(BlockStateProperties.OPEN)) {
			level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.OPEN));
			playOpenCloseSound(level, pos, state, !state.getValue(BlockStateProperties.OPEN));
			return InteractionResult.SUCCESS;
		}

        /*

        //Old Code
        BlockState blockState = level.getBlockState(pos);
        Block block = blockState.getBlock();
        if( block instanceof DoorBlock) {
            DoorBlock blockDoor= (DoorBlock) block;
            if(blockState.getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
                pos = pos.below();
                blockState = level.getBlockState(pos);
            }
            blockDoor.toggleDoor(level, pos, !blockState.getValue(DoorBlock.OPEN));
            return ActionResultType.SUCCESS;
        }

        for(Property<?> property : blockState.getProperties()) {
            if(property.getName().equals("open") && property instanceof BooleanProperty) {
                BooleanProperty bool = (BooleanProperty) property;
                boolean open = blockState.getValue(bool);
                level.setBlockAndUpdate(pos, blockState.setValue(bool, !open));
                level.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1F, 1F);
                return ActionResultType.SUCCESS;
            }
        }

        if(blockState.getBlock() == Blocks.OBSIDIAN && level.provider.getDimensionType().getId() == 0 && Blocks.PORTAL.trySpawnPortal(level, pos.offset(side)))
            return ActionResultType.SUCCESS;

        if(block instanceof SlidingDoorBlock) {
            SlidingDoorBlock slDoor= (SlidingDoorBlock) block;
            slDoor.neighborChanged(blockState, level, pos, Blocks.BARRIER, pos);
            return ActionResultType.SUCCESS;
        }

         */

		return InteractionResult.sidedSuccess(false);
	}

	private void playOpenCloseSound(Level level, BlockPos pos, BlockState state, boolean isOpen) {
		//Special case for trapdoors
		if (state.getBlock() instanceof TrapDoorBlock) {
			if (isOpen) {
				playSound(level, pos, state.getSoundType() == SoundType.METAL ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_OPEN);
			} else {
				playSound(level, pos, state.getSoundType() == SoundType.METAL ? SoundEvents.IRON_TRAPDOOR_CLOSE : SoundEvents.WOODEN_TRAPDOOR_CLOSE);
			}
			return;
		}

		//Everything else
		if (isOpen) {
			playSound(level, pos, state.getSoundType() == SoundType.METAL ? SoundEvents.IRON_DOOR_OPEN : SoundEvents.WOODEN_DOOR_OPEN);
		} else {
			playSound(level, pos, state.getSoundType() == SoundType.METAL ? SoundEvents.IRON_DOOR_CLOSE : SoundEvents.WOODEN_DOOR_CLOSE);
		}
	}

	private void playSound(Level level, BlockPos pos, SoundEvent soundEvent) {
		level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1F, 1F);
	}
}
