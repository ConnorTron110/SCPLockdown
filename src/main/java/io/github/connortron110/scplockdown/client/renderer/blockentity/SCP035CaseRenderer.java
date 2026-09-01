package io.github.connortron110.scplockdown.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.SCP035MaskModel;
import io.github.connortron110.scplockdown.level.blockentity.SCP035CaseBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.SCP035GlassCaseBlock;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SCP035CaseRenderer implements BlockEntityRenderer<SCP035CaseBlockEntity> {

	public static final Material SCP035_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/special/scp035"));

	private final SCP035MaskModel model;

	public SCP035CaseRenderer(BlockEntityRendererProvider.Context pContext) {
		this.model = new SCP035MaskModel(pContext.bakeLayer(SCPLayerDefinitions.SCP035));
	}

	private static final Quaternionf MASK_LEAN = new Quaternionf(new AxisAngle4d(Math.toRadians(-20), new Vector3f(1, 0, 0)));

	@Override
	public void render(SCP035CaseBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		if (pBlockEntity.isComedy()) {
			model.comedy();
		} else {
			model.tragedy();
		}

		BlockState state = pBlockEntity.getBlockState();
		if (!state.getValue(SCP035GlassCaseBlock.EMPTY)) {
			//	Centre the mask and rotate depending on facing value (North | South is swapped)
			pPoseStack.pushPose();
			ClientUtils.blockEntityRotation(pPoseStack, 1D);
			pPoseStack.mulPose(new Quaternionf(new AxisAngle4d(Math.toRadians(state.getValue(SCP035GlassCaseBlock.FACING).toYRot()), new Vector3f(0, 1, 0))));

			//	Offset and lean on post
			pPoseStack.translate(0F, 0F, 0.35F);
			pPoseStack.mulPose(MASK_LEAN);
			this.model.renderToBuffer(pPoseStack, SCP035_MATERIAL.buffer(pBuffer, RenderType::entitySolid), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
			pPoseStack.popPose();
		}
	}
}
