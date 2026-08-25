package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.client.PlayerSkinHandler;
import io.github.connortron110.scplockdown.level.entity.SCP049PlayerEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SCP049PlayerEntityRenderer extends MobRenderer<SCP049PlayerEntity, PlayerModel<SCP049PlayerEntity>> {

	private final PlayerModel<SCP049PlayerEntity> HumanModel;
	private final PlayerModel<SCP049PlayerEntity> HumanSlimModel;

	public SCP049PlayerEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);
		this.HumanModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false);
		this.HumanSlimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), false);

	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(SCP049PlayerEntity pEntity) {
		return PlayerSkinHandler.getPlayerSkinInfoFromUUID(pEntity.getPlayerUUID()).getTexture();
	}

	@Override
	public void render(SCP049PlayerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
		this.model = PlayerSkinHandler.getPlayerSkinInfoFromUUID(pEntity.getPlayerUUID()).isSlim() ? HumanSlimModel : HumanModel;
		super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
	}

}
