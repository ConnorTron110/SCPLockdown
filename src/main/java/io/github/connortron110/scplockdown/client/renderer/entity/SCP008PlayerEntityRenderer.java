/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.client.PlayerSkinHandler;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008PlayerEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SCP008PlayerEntityRenderer<E extends SCP008PlayerEntity> extends MobRenderer<E, PlayerModel<E>> {

    private final PlayerModel<E> human = new PlayerModel<>(0.0F, false);
    private final PlayerModel<E> humanSlim = new PlayerModel<>(0.0F, true);

    public SCP008PlayerEntityRenderer(EntityRendererProvider.Context entityRenderDispatcher) {
        super(entityRenderDispatcher, new PlayerModel<>(0.0F, false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return PlayerSkinHandler.getPlayerSkinInfoFromUUID(pEntity.getPlayerUUID()).getTexture();
    }

    @Override
    public void render(E pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = PlayerSkinHandler.getPlayerSkinInfoFromUUID(pEntity.getPlayerUUID()).isSlim() ? humanSlim : human;
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}


 */