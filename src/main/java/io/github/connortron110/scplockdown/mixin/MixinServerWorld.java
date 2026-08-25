package io.github.connortron110.scplockdown.mixin;

import io.github.connortron110.scplockdown.events.lure.LureTracker;
import io.github.connortron110.scplockdown.events.lure.interfaces.IBlockLurable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This mixin is currently used specifically to detect block changes at the lowest level possible to help
 * {@link io.github.connortron110.scplockdown.events.lure.LureTracker} with accurate tracking of block lures.
 * <p>
 * Is on the common side of mixins as Client needs to technically have this for internal servers.
 */
@Mixin(ServerLevel.class)
public abstract class MixinServerWorld {
	/**
	 * Whenever a block gets updated in any way by: player, command, block-update. This method will be informed.
	 * This method must remain as lightweight as possible to not cause every block placed/removed to cause extra processing
	 */
	@Inject(method = "onBlockStateChange", at = @At("TAIL"))
	private void onBlockStateChange(BlockPos pPos, BlockState pBlockState, BlockState pNewState, CallbackInfo ci) {
		//  Seems to get called after the ending tick, and before the start of a new tick (which is good)

		//  First check if the block itself changed
		if (!pBlockState.getBlock().equals(pNewState.getBlock())) {
			//  Block has changed. Possibly can make this its own event and add an event call here?
			//  Check if either old or new block is a BlockLurable
			if ((pBlockState.getBlock() instanceof IBlockLurable) || (pNewState.getBlock() instanceof IBlockLurable)) {
				//  Since there was a change in the block type, that either means the lure got destroyed or placed
				boolean lureRemoved = !(pNewState.getBlock() instanceof IBlockLurable);
				LureTracker.notifyBlockLureChange(((ServerLevel) (Object) this), pPos, lureRemoved);
			}
		}
	}
}
