package io.github.connortron110.scplockdown.client.gui.screen;

import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.server.screens.SBComputerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ComputerScreen extends Screen {

	protected EditBox url;
	protected EditBox display;
	protected Button doneButton;
	protected Button cancelButton;

	private final BlockPos pos;

	private boolean isURLSuggestionShowing = false;
	private boolean isDisplaySuggestionShowing = false;

	public ComputerScreen(BlockPos pos) {
		super(Component.empty());
		this.pos = pos;
	}

	@Override
	protected void init() {
		this.url = new EditBox(this.font,
				width / 2 - (250 / 2),
				height / 2 - 45,
				250,
				20,
				Component.empty());

		this.url.setMaxLength(256);
		this.url.setFocused(true);
		addWidget(this.url);

		this.display = new EditBox(this.font,
				width / 2 - 100,
				height / 2 - 5,
				200,
				20,
				Component.empty());

		this.display.setMaxLength(256);
		addWidget(this.display);

		this.doneButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> this.onDone())
				.bounds(width / 2 - 100, height / 2 + 19, 50, 20).build());

		this.doneButton.active = false;

		this.cancelButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, (button) -> this.minecraft.setScreen(null))
				.bounds(width / 2 + 50, height / 2 + 19, 50, 20).build());
	}

	@Override
	public void tick() {
		url.tick();
		display.tick();

		doneButton.active = !url.getValue().isEmpty();

		if (url.getValue().isEmpty() && !isURLSuggestionShowing) {
			url.setSuggestion("https://scp-wiki.wikidot.com");
			isURLSuggestionShowing = true;
		} else if (!url.getValue().isEmpty() && isURLSuggestionShowing) {
			url.setSuggestion("");
			isURLSuggestionShowing = false;
		}

		if (display.getValue().isEmpty() && !isDisplaySuggestionShowing) {
			display.setSuggestion("SCP Wiki");
			isDisplaySuggestionShowing = true;
		} else if (!display.getValue().isEmpty() && isDisplaySuggestionShowing) {
			display.setSuggestion("");
			isDisplaySuggestionShowing = false;
		}
	}

	protected void onDone() {
		SCPNetwork.NETWORK.sendToServer(new SBComputerScreen(pos, url.getValue(), display.getValue()));
		this.minecraft.setScreen(null);
	}

	@Override
	public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
		String surl = url.getValue();
		String sdisp = display.getValue();
		super.resize(pMinecraft, pWidth, pHeight);
		url.setValue(surl);
		display.setValue(sdisp);
		isDisplaySuggestionShowing = false;
		isURLSuggestionShowing = false;
	}

	private static final Component URL_COMPONENT = Component.translatable("screen.computer.url");
	private static final Component URL_DISPLAY = Component.translatable("screen.computer.display");

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pGuiGraphics);
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		pGuiGraphics.drawString(this.font, URL_COMPONENT,
				width / 2 - (250 / 2),
				height / 2 - 57, 16777215);
		url.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

		pGuiGraphics.drawString(this.font, URL_DISPLAY,
				width / 2 - 100,
				height / 2 - 17, 16777215);
		display.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
	}
}
