package io.github.connortron110.scplockdown.client.gui.screen.inventory;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.inventory.CardWriterMenu;
import io.github.connortron110.scplockdown.level.items.KeycardItem;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.server.screens.SBCardWriterScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class CardWriterScreen extends AbstractContainerScreen<CardWriterMenu> {

	private static final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/gui/container/card_writer.png");

	protected EditBox Name;
	protected EditBox Colour;
	protected EditBox SecretKey;
	protected Button ChangeCardButton;

	private boolean HideSecretKey = false;

	public CardWriterScreen(CardWriterMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		pMenu.registerUpdateListener(this::containerChanged);
	}

	private void containerChanged() {
		ItemStack stack = menu.getItemInCardSlot();
		boolean hasCard = !stack.isEmpty();

		Name.active = hasCard;
		Colour.active = hasCard;
		SecretKey.active = hasCard;

		HideSecretKey = false;

		if (!hasCard) {
			Name.setValue("");
			Colour.setValue("");
			SecretKey.setValue("");

			Name.setFocused(false);
			Colour.setFocused(false);
			SecretKey.setFocused(false);
			ChangeCardButton.setFocused(false);
		} else {
			KeycardItem.Data cardData = new KeycardItem.Data(stack);

			Name.setValue(cardData.Name.getString());
			Colour.setValue(KeycardItem.Data.hasData(stack) ? cardData.getHexString() : "");

			if (!Objects.equals(cardData.Key, "")) {
				SecretKey.setValue(cardData.Key);
				HideSecretKey = true;
			}
		}
	}

	@Override
	protected void init() {
		super.init();

		this.Name = new EditBox(this.font,
				leftPos + 70,
				topPos + 20,
				95,
				10,
				Component.empty());

		this.Name.setMaxLength(50);
		addRenderableWidget(this.Name);

		this.Colour = new EditBox(this.font,
				leftPos + 92,
				topPos + 35,
				73,
				10,
				Component.empty());

		this.Colour.setMaxLength(6);
		addRenderableWidget(this.Colour);

		this.SecretKey = new EditBox(this.font,
				leftPos + 65,
				topPos + 50,
				100,
				10,
				Component.empty());

		this.SecretKey.setMaxLength(32);
		addRenderableWidget(this.SecretKey);

		this.ChangeCardButton = this.addRenderableWidget(Button.builder(BUTTON_INSERT_CARD, (button) -> this.onUpdateCard())
				.bounds(leftPos + 64, topPos + 65, 102, 15).build());
	}

	@Override
	protected void containerTick() {
		Name.tick();
		Colour.tick();
		SecretKey.tick();

		secretKeyTick();
		buttonLogic();
		doSuggestions();
	}

	private void secretKeyTick() {
		if (HideSecretKey) {
			SecretKey.setFormatter((s, integer) -> FormattedCharSequence.forward(s, Style.EMPTY.withObfuscated(true)));

			//  If The Secret key has been completely removed, we un-obfuscate the text
			if (SecretKey.getValue().isEmpty()) {
				HideSecretKey = false;
			}
		} else {
			SecretKey.setFormatter((s, integer) -> FormattedCharSequence.forward(s, Style.EMPTY));
		}
	}

	public static final Component BUTTON_INSERT_CARD = Component.translatable("scplockdown.gui.menu.cardwriter.insertcard");
	public static final Component BUTTON_NAME_REQUIRED = Component.translatable("scplockdown.gui.menu.cardwriter.namerequired");
	public static final Component BUTTON_COLOUR_REQUIRED = Component.translatable("scplockdown.gui.menu.cardwriter.colourrequired");
	public static final Component BUTTON_COLOUR_INVALID = Component.translatable("scplockdown.gui.menu.cardwriter.colourinvalid");
	public static final Component BUTTON_KEY_REQUIRED = Component.translatable("scplockdown.gui.menu.cardwriter.keyrequired");
	public static final Component BUTTON_CONFIGURE = Component.translatable("scplockdown.gui.menu.cardwriter.configurecard");

	private void buttonLogic() {
		if (menu.getItemInCardSlot().isEmpty()) {
			ChangeCardButton.active = false;
			ChangeCardButton.setMessage(BUTTON_INSERT_CARD);
			return;
		}

		//  Check Name
		if (Name.getValue().isEmpty()) {
			ChangeCardButton.active = false;
			ChangeCardButton.setMessage(BUTTON_NAME_REQUIRED);
			return;
		}

		//  Check Colour
		if (Colour.getValue().isEmpty()) {
			ChangeCardButton.active = false;
			ChangeCardButton.setMessage(BUTTON_COLOUR_REQUIRED);
			return;
		}

		//  Check for Valid Colour
		int colour = EditBox.DEFAULT_TEXT_COLOR;
		try {
			colour = Integer.parseInt(Colour.getValue(), 16);
		} catch (NumberFormatException ignored) {
			ChangeCardButton.active = false;
			ChangeCardButton.setMessage(BUTTON_COLOUR_INVALID);
			Colour.setTextColor(colour);
			return;
		}

		Colour.setTextColor(colour);

		//  Check Key
		if (SecretKey.getValue().isEmpty()) {
			ChangeCardButton.active = false;
			ChangeCardButton.setMessage(BUTTON_KEY_REQUIRED);
			return;
		}

		ChangeCardButton.active = true;
		ChangeCardButton.setMessage(BUTTON_CONFIGURE);
	}

	public static final Component NAME_SUGGESTION = Component.translatable("scplockdown.gui.menu.cardwriter.namesuggestion");
	public static final Component KEY_SUGGESTION = Component.translatable("scplockdown.gui.menu.cardwriter.keysuggestion");

	private void doSuggestions() {
		Name.setSuggestion(Name.getValue().isEmpty() ? NAME_SUGGESTION.getString() : "");
		Colour.setSuggestion(Colour.getValue().isEmpty() ? "FFFFFF" : "");
		SecretKey.setSuggestion(SecretKey.getValue().isEmpty() ? KEY_SUGGESTION.getString() : "");
	}

	protected void onUpdateCard() {
		int colour = 0;
		try {
			colour = Integer.parseInt(Colour.getValue(), 16);
		} catch (NumberFormatException ignored) {
		}

		SCPNetwork.NETWORK.sendToServer(new SBCardWriterScreen(menu.containerId, KeycardItem.setKeycardValues(menu.getItemInCardSlot(), Name.getValue(), colour, SecretKey.getValue())));
	}

	@Override
	public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
		String strName = Name.getValue();
		String strColour = Colour.getValue();
		String strKey = SecretKey.getValue();
		super.resize(pMinecraft, pWidth, pHeight);
		Name.setValue(strName);
		Colour.setValue(strColour);
		SecretKey.setValue(strKey);
	}

	@Override
	public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
		if (pKeyCode == InputConstants.KEY_ESCAPE) {
			this.minecraft.player.closeContainer();
		}

		// Handle E
		if (this.minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(pKeyCode, pScanCode))) {
			return true;
		}

		//  Consume the input if the player is trying to copy the secret key
		if (HideSecretKey && (Screen.isCopy(pKeyCode) || Screen.isCut(pKeyCode))) {
			return true;
		}

		return super.keyPressed(pKeyCode, pScanCode, pModifiers);
	}

	public static final Component CARD_NAME = Component.translatable("scplockdown.gui.menu.cardwriter.cardname");
	public static final Component CARD_COLOUR = Component.translatable("scplockdown.gui.menu.cardwriter.cardcolour");
	public static final Component CARD_KEY = Component.translatable("scplockdown.gui.menu.cardwriter.cardkey");

	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
		renderTooltip(pGuiGraphics, pMouseX, pMouseY);

		pGuiGraphics.drawString(this.font, CARD_NAME,
				leftPos + 40,
				topPos + 21, 0x3F3F3F, false);

		pGuiGraphics.drawString(this.font, CARD_COLOUR,
				leftPos + 40,
				topPos + 36, 0x3F3F3F, false);

		pGuiGraphics.drawString(this.font, Component.literal("0x"),
				leftPos + 79,
				topPos + 36, 0x3F3F3F, false);

		pGuiGraphics.drawString(this.font, CARD_KEY,
				leftPos + 40,
				topPos + 51, 0x3F3F3F, false);
	}

	@Override
	protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
		this.renderBackground(pGuiGraphics);
		pGuiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}
}
