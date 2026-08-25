package io.github.connortron110.scplockdown.level.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IScrewdriverInteraction {
	void onScrewDriver(BlockState state, Level level, BlockPos pos, Player player, ItemStack screwdriver);
}
