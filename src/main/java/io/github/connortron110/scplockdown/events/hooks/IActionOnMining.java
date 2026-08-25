package io.github.connortron110.scplockdown.events.hooks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IActionOnMining {
	void miningTick(Level level, BlockPos pos, Player player);
}
