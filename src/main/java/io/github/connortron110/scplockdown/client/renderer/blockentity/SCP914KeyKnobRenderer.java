package io.github.connortron110.scplockdown.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.SCP914KeyKnobModel;
import io.github.connortron110.scplockdown.level.blockentity.SCP914BlockEntity;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.joml.Math;

public class SCP914KeyKnobRenderer implements BlockEntityRenderer<SCP914BlockEntity> {

	public static final Material MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/scp914keyknob"));
	private final SCP914KeyKnobModel Model;

	public SCP914KeyKnobRenderer(BlockEntityRendererProvider.Context pContext) {
		this.Model = new SCP914KeyKnobModel(pContext.bakeLayer(SCPLayerDefinitions.SCP914_KEY_KNOB));
	}

	@Override
	public void render(SCP914BlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) return;

		pPoseStack.pushPose();
		ClientUtils.blockEntityRotation(pPoseStack, 1D);
		this.Model.Center.yRot = Math.toRadians(pBlockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());

		this.Model.Knob.zRot = Math.toRadians(pBlockEntity.KnobRotationDegrees - 90);
		this.Model.Key.zRot = Math.toRadians(pBlockEntity.KeyRotationDegrees);

		this.Model.renderToBuffer(pPoseStack, MATERIAL.buffer(pBuffer, RenderType::entitySolid), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
		pPoseStack.popPose();
	}
}
