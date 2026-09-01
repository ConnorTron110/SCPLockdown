package io.github.connortron110.scplockdown.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.SCP035MaskModel;
import io.github.connortron110.scplockdown.client.renderer.blockentity.SCP035CaseRenderer;
import io.github.connortron110.scplockdown.level.blockentity.BlastDoorBlockEntity;
import io.github.connortron110.scplockdown.level.blockentity.LockerBlockEntity;
import io.github.connortron110.scplockdown.level.blockentity.SlidingDoorBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.BlastDoorBlock;
import io.github.connortron110.scplockdown.level.blocks.LockerBlock;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.level.items.SCP035MaskItem;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * Renders custom models into the world (inventory, in hand etc).
 * To avoid clashing with other mods, we use {@link io.github.connortron110.scplockdown.mixin.client.ClientMixinBlockEntityWithoutLevelRenderer} to "hook" into the main rendering class to then redirect calls to here once all vanilla / other mods are done with their stuff.
 * All functions are derived from {@link net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer}.
 */
public class ItemModelRenderer {

	private static final LockerBlockEntity LOCKER = new LockerBlockEntity(BlockPos.ZERO, SCPBlocks.LOCKER.get().defaultBlockState());
	private static final SlidingDoorBlockEntity SLIDING_DOOR = new SlidingDoorBlockEntity(BlockPos.ZERO, SCPBlocks.SLIDING_DOOR.get().defaultBlockState());
	private static final SlidingDoorBlockEntity CONTAINMENT_DOOR = new SlidingDoorBlockEntity(BlockPos.ZERO, SCPBlocks.CONTAINMENT_DOOR.get().defaultBlockState());
	private static final SlidingDoorBlockEntity MAGNETIZED_DOOR = new SlidingDoorBlockEntity(BlockPos.ZERO, SCPBlocks.MAGNETIZED_DOOR.get().defaultBlockState());
	private static final BlastDoorBlockEntity BLAST_DOOR = new BlastDoorBlockEntity(BlockPos.ZERO, SCPBlocks.BLAST_DOOR.get().defaultBlockState());

	private SCP035MaskModel scp035MaskModel;

	private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
	private final EntityModelSet entityModelSet;

	public ItemModelRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
		this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
		this.entityModelSet = pEntityModelSet;
	}

	public void onResourceManagerReload(ResourceManager pResourceManager) {
		this.scp035MaskModel = new SCP035MaskModel(this.entityModelSet.bakeLayer(SCPLayerDefinitions.SCP035_MASK));
	}

	public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		Item item = pStack.getItem();
		if (item instanceof SCP035MaskItem) {
			pPoseStack.pushPose();
			ClientUtils.blockEntityRotation(pPoseStack, 1.0D);
			VertexConsumer vertexconsumer = SCP035CaseRenderer.SCP035_MATERIAL.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.scp035MaskModel.renderType(SCP035CaseRenderer.SCP035_MATERIAL.atlasLocation()), true, pStack.hasFoil()));
			if (SCP035MaskItem.isComedy(pStack))
				this.scp035MaskModel.comedy().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
			else
				this.scp035MaskModel.tragedy().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
			pPoseStack.popPose();
		}

		if (item instanceof BlockItem blockItem) {
			if (blockItem.getBlock() instanceof LockerBlock) {
				pPoseStack.pushPose();
				ClientUtils.blockEntityRotation(pPoseStack, 1.0D);
				this.blockEntityRenderDispatcher.renderItem(LOCKER, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
				pPoseStack.popPose();
			} else if (blockItem.getBlock() instanceof SlidingDoorBlock slidingDoorBlock) {
				pPoseStack.pushPose();
				ClientUtils.blockEntityRotation(pPoseStack, 1.0D);
				if (slidingDoorBlock.equals(SCPBlocks.SLIDING_DOOR.get())) {
					this.blockEntityRenderDispatcher.renderItem(SLIDING_DOOR, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
				} else if (slidingDoorBlock.equals(SCPBlocks.CONTAINMENT_DOOR.get())) {
					this.blockEntityRenderDispatcher.renderItem(CONTAINMENT_DOOR, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
				} else if (slidingDoorBlock.equals(SCPBlocks.MAGNETIZED_DOOR.get())) {
					this.blockEntityRenderDispatcher.renderItem(MAGNETIZED_DOOR, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
				}
				pPoseStack.popPose();
			} else if (blockItem.getBlock() instanceof BlastDoorBlock) {
				pPoseStack.pushPose();
				ClientUtils.blockEntityRotation(pPoseStack, 1.0D);
				this.blockEntityRenderDispatcher.renderItem(BLAST_DOOR, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
				pPoseStack.popPose();
			}
		}
	}
}
