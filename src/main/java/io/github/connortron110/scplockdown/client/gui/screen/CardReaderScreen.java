package io.github.connortron110.scplockdown.client.gui.screen;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.server.screens.SBCardReaderScreen;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;

import java.util.List;

public class CardReaderScreen extends Screen {

	private static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/gui/card_reader.png");

	private static final int GUI_WIDTH = 176;
	private static final int GUI_HEIGHT = 171;

	private static final int ENTRY_WIDTH = 142;
	private static final int ENTRY_HEIGHT = 25;

	private static final int SCROLL_WIDTH = 12;
	private static final int SCROLL_HEIGHT = 15;

	//  Offset from the left and top pos
	private static final int ENTRY_X = 8;
	private static final int ENTRY_Y = 13;

	private static final int MAX_DISPLAY_ENTRIES = 5;

	//  Top left of initial positions
	private static final int SCROLL_X = 156;
	private static final int SCROLL_Y = 13;

	//  Max and min from center (Absolute)
	private static final int SCROLL_MIN = 0;
	private static final int SCROLL_MAX = 110;

	private static final int BUTTON_SIZE = 19;

	private int leftPos = 0;
	private int topPos = 0;

	private final BlockPos ReaderPos;
	private final List<KeycardItem.Data> LinkedCards;

	private Button StopProgramming;
	private Button DeleteEntry;

	/**
	 * The offset of which the render index starts at
	 */
	private int ScrollOffset = 0;
	private double ScrollerYPos = 0F;
	private boolean IsScrolling = false;
	private int CurrentSelection = -1;

	public CardReaderScreen(BlockPos readerPosition, List<KeycardItem.Data> linkedCards) {
		super(Component.empty());
		this.ReaderPos = readerPosition;
		this.LinkedCards = linkedCards;
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - GUI_WIDTH) / 2;
		this.topPos = (this.height - GUI_HEIGHT) / 2;

		this.StopProgramming = this.addRenderableWidget(new ImageButton(leftPos + 150, topPos + 144, BUTTON_SIZE, BUTTON_SIZE, GUI_WIDTH, 0, GUI, (button) -> onStopProgramming()));
		this.DeleteEntry = this.addRenderableWidget(new ImageButton(leftPos + 7, topPos + 144, BUTTON_SIZE, BUTTON_SIZE, GUI_WIDTH, 0, GUI, (button) -> onDeleteEntry()) {
			@Override
			public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
				super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
				pGuiGraphics.blit(GUI, CardReaderScreen.this.leftPos + 7, CardReaderScreen.this.topPos + 144, GUI_WIDTH + BUTTON_SIZE, BUTTON_SIZE + ((isHoveredOrFocused() && isActive()) ? BUTTON_SIZE : 0), BUTTON_SIZE, BUTTON_SIZE);
			}
		});
		this.DeleteEntry.active = false;

		StopProgramming.setTooltip(Tooltip.create(TOOLTIP_EXIT_PROG));
		DeleteEntry.setTooltip(Tooltip.create(TOOLTIP_DELETE_ENTRY));
	}

	public void onStopProgramming() {
		SCPNetwork.NETWORK.sendToServer(new SBCardReaderScreen(ReaderPos, true, -1));
		onClose();
	}

	public void onDeleteEntry() {
		SCPNetwork.NETWORK.sendToServer(new SBCardReaderScreen(ReaderPos, false, CurrentSelection));
		LinkedCards.remove(CurrentSelection);
		DeleteEntry.setFocused(false);

		//  Scroll all the way to the top
		mouseScrolled(0, 0, LinkedCards.size());

		//  Reset Selection
		CurrentSelection = -1;
	}

	@Override
	public void tick() {
		this.DeleteEntry.active = !LinkedCards.isEmpty() && CurrentSelection >= 0;
		if (LinkedCards.size() <= MAX_DISPLAY_ENTRIES) {
			ScrollerYPos = 0;
		}
	}

	private void renderEntry(GuiGraphics pGuiGraphics, int index, KeycardItem.Data data, boolean isHovering) {
		final int XPos = leftPos + ENTRY_X;
		final int YPos = topPos + ENTRY_Y + (index * ENTRY_HEIGHT);

		pGuiGraphics.blit(GUI, XPos, YPos, 0, GUI_HEIGHT + ((CurrentSelection == (index + ScrollOffset) || isHovering) ? ENTRY_HEIGHT : 0), ENTRY_WIDTH, ENTRY_HEIGHT);

		pGuiGraphics.renderItem(data.setItemTag(SCPItems.KEYCARD.getDefaultInstance()), XPos + 2, YPos);
		pGuiGraphics.drawString(this.font, this.font.substrByWidth(data.Name, 120).getString(), XPos + 19, YPos + 4, 4210752, false);
		pGuiGraphics.drawString(this.font, "0x" + data.getHexString(), XPos + 2, YPos + 16, data.Colour, false);
	}

	public static final Component SCREEN_TITLE = Component.translatable("scplockdown.gui.screen.cardreader.title");
	public static final Component ADD_MORE_ENTRIES = Component.translatable("scplockdown.gui.screen.cardreader.moreentries");
	public static final Component TOOLTIP_EXIT_PROG = Component.translatable("scplockdown.gui.screen.cardreader.exitprog");
	public static final Component TOOLTIP_DELETE_ENTRY = Component.translatable("scplockdown.gui.screen.cardreader.deleteentry");

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		renderBackground(pGuiGraphics);
		pGuiGraphics.blit(GUI, this.leftPos, this.topPos, 0, 0, GUI_WIDTH, GUI_HEIGHT);
		pGuiGraphics.drawString(this.font, SCREEN_TITLE, leftPos + 7, topPos + 4, 4210752, false);

		for (int i = 0; i < MAX_DISPLAY_ENTRIES; i++) {
			if (LinkedCards.size() <= i + ScrollOffset) break;

			boolean isHovering = pMouseX >= (leftPos + ENTRY_X) && pMouseX < (leftPos + ENTRY_X + ENTRY_WIDTH) && pMouseY >= (topPos + ENTRY_Y + (i * ENTRY_HEIGHT)) && pMouseY < (topPos + ENTRY_Y + ENTRY_HEIGHT + (i * ENTRY_HEIGHT));

			renderEntry(pGuiGraphics, i, LinkedCards.get(i + ScrollOffset), isHovering);
		}

		//  Scroll Wheel
		pGuiGraphics.blit(GUI, this.leftPos + SCROLL_X, (int) (this.topPos + SCROLL_Y + ScrollerYPos), ENTRY_WIDTH + (LinkedCards.size() <= MAX_DISPLAY_ENTRIES ? SCROLL_WIDTH : 0), GUI_HEIGHT, SCROLL_WIDTH, SCROLL_HEIGHT);

		pGuiGraphics.drawString(this.font, ADD_MORE_ENTRIES, leftPos + (GUI_WIDTH / 2) - (this.font.width(ADD_MORE_ENTRIES) / 2), topPos + 144 + 5, 4210752, false);

		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
		pGuiGraphics.blit(GUI, this.leftPos + 150, this.topPos + 144, GUI_WIDTH + BUTTON_SIZE, 0, BUTTON_SIZE, BUTTON_SIZE);
	}

	@Override
	public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
		//  Similar to the drg function, but now we do the inverse to find the position given the ScrollOffset
		int scrollOffsetMax = Mth.clamp(LinkedCards.size() - MAX_DISPLAY_ENTRIES, 0, LinkedCards.size());
		if (scrollOffsetMax == 0) return false;

		ScrollOffset = (int) Mth.clamp(ScrollOffset - pDelta, 0, scrollOffsetMax);
		ScrollerYPos = SCROLL_MAX * ((double) ScrollOffset / scrollOffsetMax);
		return true;
	}

	@Override
	public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {

		//  Selection checks on entries, for each Y pos on each entry
		for (int y = 0; y < MAX_DISPLAY_ENTRIES; y++) {
			//  Check if Y falls outside of selections (only happens if the list is not full)
			if (y >= LinkedCards.size()) break;

			//  Check if in bounds of Entry
			if (pMouseX >= (leftPos + ENTRY_X) && pMouseX < (leftPos + ENTRY_X + ENTRY_WIDTH) && pMouseY >= (topPos + ENTRY_Y + (y * ENTRY_HEIGHT)) && pMouseY < (topPos + ENTRY_Y + ENTRY_HEIGHT + (y * ENTRY_HEIGHT))) {
				this.CurrentSelection = ScrollOffset + y;
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			}
		}

		IsScrolling = false;
		//  Check if click was within the bounds of the scroll wheel and the scroll wheel is active
		if (LinkedCards.size() > MAX_DISPLAY_ENTRIES) {
			if (pMouseX >= (leftPos + SCROLL_X) && pMouseX < (leftPos + SCROLL_X + SCROLL_WIDTH) && pMouseY >= (topPos + SCROLL_Y + SCROLL_MIN) && pMouseY < (topPos + SCROLL_Y + SCROLL_MAX + SCROLL_HEIGHT)) {
				this.IsScrolling = true;
			}
		}

		return super.mouseClicked(pMouseX, pMouseY, pButton);
	}

	@Override
	public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
		if (IsScrolling) {
			//  Normalize scrolling position
			float min = SCROLL_MIN + topPos + SCROLL_Y + (SCROLL_HEIGHT / 2F);
			float max = SCROLL_MAX + topPos + SCROLL_Y + (SCROLL_HEIGHT / 2F);

			float delta = (float) ((pMouseY - min) / (max - min));
			delta = Mth.clamp(delta, 0, 1);
			float normalizedPosition = Mth.lerp(delta, min, max);

			ScrollerYPos = normalizedPosition - SCROLL_Y - topPos - (SCROLL_HEIGHT / 2F);

			int scrollOffsetMax = Mth.clamp(LinkedCards.size() - MAX_DISPLAY_ENTRIES, 0, LinkedCards.size());

			ScrollOffset = Math.round(delta * scrollOffsetMax);
		}

		return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
