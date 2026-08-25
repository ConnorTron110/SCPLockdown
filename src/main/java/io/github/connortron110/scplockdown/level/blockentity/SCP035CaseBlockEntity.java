package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.level.blocks.SCP035GlassCaseBlock;
import io.github.connortron110.scplockdown.level.items.SCP035MaskItem;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Just used for rendering the mask in the case, nothing else.
 * Could Generify it if more blocks end up like this
 */
public class SCP035CaseBlockEntity extends BlockEntity {

	private boolean isComedy = false;
	private int time = 0;

	public SCP035CaseBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.SCP035_CASE.get(), pPos, pState);
	}

	@Override
	public void setLevel(Level pLevel) {
		super.setLevel(pLevel);
		isComedy = pLevel.random.nextBoolean();
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		isComedy = pTag.getBoolean("isComedy");
		time = pTag.getInt("time");
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putBoolean("isComedy", isComedy);
		pTag.putInt("time", time);
	}

	public boolean isComedy() {
		return isComedy;
	}

	/**
	 * Used by the block to set the expression
	 */
	public void setComedy(boolean value) {
		isComedy = value;
	}


	public void tick() {
		if (!getBlockState().getValue(SCP035GlassCaseBlock.EMPTY)) {
			boolean result = SCP035MaskItem.maskChangeProbability(time++);
			if (result) {
				isComedy = !isComedy;
				time = 0;
			}
		} else {
			time = 0;
		}
	}
}
