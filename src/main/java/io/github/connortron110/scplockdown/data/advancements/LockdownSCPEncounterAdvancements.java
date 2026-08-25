package io.github.connortron110.scplockdown.data.advancements;

import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class LockdownSCPEncounterAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {

	public static final Component SCP_ENCOUNTER_ADVANCEMENT_TITLE = Component.translatable("advancements.scpencounter.root.title");
	public static final Component SCP_ENCOUNTER_ADVANCEMENT_DESC = Component.translatable("advancements.scpencounter.root.description");

	public static final Component SCP148_ADVANCEMENT_TITLE = Component.translatable("advancements.scpencounter.scp148.title");
	public static final Component SCP148_ADVANCEMENT_DESC = Component.translatable("advancements.scpencounter.scp148.description");

	/**
	 * See {@link net.minecraft.data.advancements.packs.VanillaAdvancementProvider} for hints on what to do
	 */
	@Override
	public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
		Advancement encounterRoot = Advancement.Builder.advancement()
				.display(createDisplayInfo(SCPItems.SCP500, SCP_ENCOUNTER_ADVANCEMENT_TITLE, SCP_ENCOUNTER_ADVANCEMENT_DESC, ResourceLocation.parse("textures/gui/advancements/backgrounds/stone.png"), FrameType.TASK, true, false, false))
				.addCriterion("check_advancements", PlayerTrigger.TriggerInstance.located(EntityPredicate.Builder.entity().subPredicate(PlayerPredicate.Builder.player().checkAdvancementDone(ResourceLocation.parse("scp148"), true).build()).build()))
				.save(saver, "scpencounter/root");  //  TODO This check may be faulty

		Advancement.Builder.advancement()
				.display(createDisplayInfo(SCPItems.SCP148_INGOT, SCP148_ADVANCEMENT_TITLE, SCP148_ADVANCEMENT_DESC, null, FrameType.TASK, false, true, false))
				.addCriterion("obtain_telekill", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(SCPBlocks.SCP148_ORE.get(), SCPBlocks.SCP148_BLOCK.get(), SCPItems.SCP148_INGOT, SCPItems.SCP148_SWORD, SCPItems.SCP148_AXE, SCPItems.SCP148_PICKAXE, SCPItems.SCP148_SHOVEL, SCPItems.SCP148_HOE, SCPItems.SCP148_HELMET).build()))
				.parent(encounterRoot)
				.save(saver, "scpencounter/scp148");
	}

	private DisplayInfo createDisplayInfo(ItemLike icon, Component title, Component description, @Nullable ResourceLocation background, FrameType frame, boolean showToast, boolean announceChat, boolean isHidden) {
		return new DisplayInfo(icon.asItem().getDefaultInstance(), title, description, background, frame, showToast, announceChat, isHidden);
	}
}
