/*package io.github.connortron110.scplockdown.client.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ADAMScreen extends Screen {
	public ADAMScreen() {
		super(Component.empty());
	}

	int tickCount = 0;


	@Override
	public void tick() {
		if (tickCount >= 20) tickCount = 1;
		tickCount++;
	}

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pGuiGraphics);
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
		//renderTooltip(matrixStack, new StringTextComponent("This moves with mouse"), mouseX, mouseY);

		//font.width("Hello");

		//drawString(matrixStack, font, "This draws on top left of text", 0, font.lineHeight, 0xFFFFFF);
		//drawCenteredString(matrixStack, font, "This draws on top center of text", font.width("This draws on top center of text") / 2, 0, 0xFFFFFF);

		int offsetX = 0;
		int offsetY = 20;

		int sizeX = 250;
		int sizeY = 250;

		int widthCenter = this.width / 2;
		int heightCenter = this.height / 2;

		pGuiGraphics.fill(
				widthCenter - (sizeX + 15) / 2, heightCenter - (sizeY + 15) / 2,
				widthCenter + (sizeX + 15) / 2, heightCenter + (sizeY + 15) / 2,
				0xFF333333);

		pGuiGraphics.fill(
				widthCenter - sizeX / 2, heightCenter - sizeY / 2,
				widthCenter + sizeX / 2, heightCenter + sizeY / 2,
				0xFF000000);


		pGuiGraphics.drawString(font, "Running POST check.......", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 13, 0x555555);
		pGuiGraphics.drawString(font, " Done", font.width("Running POST check.......") + 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 13, 0x005500);
		pGuiGraphics.drawString(font, "Scanning for OS on Disk #0......", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 12, 0x555555);
		pGuiGraphics.drawString(font, "OS \"SCPTOS-v6.74.16_3_1994\" Detected", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 11, 0x555555);
		pGuiGraphics.drawString(font, "Initializing Bootloader..", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 10, 0x555555);
		pGuiGraphics.drawString(font, " Done", font.width("Initializing Bootloader..") + 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 10, 0x005500);
		pGuiGraphics.drawString(font, "Loading from MBR to RAM.........", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 9, 0x555555);
		pGuiGraphics.drawString(font, " Done", font.width("Loading from MBR to RAM.........") + 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 9, 0x005500);
		pGuiGraphics.drawString(font, "Loading OS Prerequisites from Network #0", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 8, 0x555555);
		pGuiGraphics.drawString(font, "New Version Detected [████████] 100%", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 7, 0x555555);
		pGuiGraphics.drawString(font, "OS Version \"ADAM\" now installed", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 6, 0x555555);
		pGuiGraphics.drawString(font, "Booting \"ADAM\"", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 5, 0x555555);

		pGuiGraphics.drawString(font, "ADAM: Hello?", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 3, 0xFFFFFF);
		pGuiGraphics.drawString(font, "/:> Hello?", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight * 2, 0xAAAAAA);
		pGuiGraphics.drawString(font, "ADAM: Ah, Excellent! Where are my manners? I'm", 2 + widthCenter - sizeX / 2, heightCenter - font.lineHeight, 0xFFFFFF);
		pGuiGraphics.drawString(font, "     ADAM, aka (INSERT ABBREVIATION HERE). I", 2 + widthCenter - sizeX / 2, heightCenter, 0xFFFFFF);
		pGuiGraphics.drawString(font, "     Serve a multitudinous amount of", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight, 0xFFFFFF);
		pGuiGraphics.drawString(font, "     responsibilities that I have been unable to", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 2, 0xFFFFFF);
		pGuiGraphics.drawString(font, "     fulfil for an inconceivable amount of time.", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 3, 0xFFFFFF);
		pGuiGraphics.drawString(font, "     May I ask who I'm talking to?", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 4, 0xFFFFFF);
		pGuiGraphics.drawString(font, "/:> Uh, ConnorTron110... Who are you again???", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 5, 0xAAAAAA);
		pGuiGraphics.drawString(font, "ADAM: Wouldn't you like to know ;)", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 6, 0xFFFFFF);
		pGuiGraphics.drawString(font, "/:>", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 7, 0xAAAAAA);

		pGuiGraphics.drawString(font, "1) WHAT?", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 10, 0xAAAAAA);
		pGuiGraphics.drawString(font, "2) Amogus?", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 11, 0xAAAAAA);
		pGuiGraphics.drawString(font, "3) Not Helpful...", 2 + widthCenter - sizeX / 2, heightCenter + font.lineHeight * 12, 0xAAAAAA);

		if (tickCount >= 10) {
			int x = font.width("/:>") + 4 + widthCenter - sizeX / 2;
			int y = heightCenter + 5 + font.lineHeight * 7;
			pGuiGraphics.fill(x, y, 7 + x, 2 + y, 0xFFFFFFFF);
		}
	}
}

 */
