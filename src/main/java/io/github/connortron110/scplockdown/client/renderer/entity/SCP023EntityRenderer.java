package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.entity.SCP023Model;
import io.github.connortron110.scplockdown.client.renderer.layers.SCP023GlowLayer;
import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SCP023EntityRenderer extends MobRenderer<SCP023Entity, SCP023Model<SCP023Entity>> {

	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/scp023.png");

	public SCP023EntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SCP023Model<>(context.bakeLayer(SCPLayerDefinitions.SCP023)), 1F);
		this.addLayer(new SCP023GlowLayer<>(this));
	}

	@Override
	public void render(SCP023Entity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		pPoseStack.pushPose();
		pPoseStack.scale(1.5F, 1.5F, 1.5F);
		super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
		pPoseStack.popPose();
	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SCP023Entity pEntity) {
		return TEXTURE;
	}
}
