package io.github.connortron110.scplockdown.client;

import io.github.connortron110.scplockdown.client.gui.screen.CardReaderScreen;
import io.github.connortron110.scplockdown.client.gui.screen.ComputerScreen;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import java.util.List;

/**
 * Has to be separate class otherwise server tries to access the packets which contain client only methods
 */
public class ClientScreenOpener {
	public static void openComputerScreen(BlockPos pos) {
		Minecraft.getInstance().setScreen(new ComputerScreen(pos));
	}

	public static void openCardReaderScreen(BlockPos pos, List<KeycardItem.Data> linkedCards) {
		Minecraft.getInstance().setScreen(new CardReaderScreen(pos, linkedCards));
	}
}
