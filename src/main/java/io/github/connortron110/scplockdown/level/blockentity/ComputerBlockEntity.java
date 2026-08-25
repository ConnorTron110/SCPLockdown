package io.github.connortron110.scplockdown.level.blockentity;

import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ComputerBlockEntity extends BlockEntity {

	private static final String URL_KEY = "DataUrl";
	private static final String DISP_KEY = "DataDisplay";

	private String url = "";
	private String display = "";
	private boolean isLocked = false;

	public ComputerBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.COMPUTER.get(), pPos, pState);
	}

	public void setData(String url, String display) {
		this.url = url;
		this.display = display;
		isLocked = true;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public MutableComponent getText() {
		MutableComponent ret = Component.literal(display.isEmpty() ? url : display);
		ret.withStyle((style) -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)).withColor(ChatFormatting.BLUE).withUnderlined(true));
		return ret;
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		url = pTag.getString(URL_KEY);
		display = pTag.getString(DISP_KEY);

		if (!url.isEmpty() || !display.isEmpty()) {
			isLocked = true;
		}
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putString(URL_KEY, url);
		pTag.putString(DISP_KEY, display);
	}
}
