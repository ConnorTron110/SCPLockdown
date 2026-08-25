/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.connortron110.scplockdown.client.models.entity.SCP008IllagerModel;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008IllagerEntity;
import io.github.connortron110.scplockdown.level.entity.variants.EntityEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCP008IllagerEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SCP008IllagerEntityRenderer<E extends SCP008IllagerEntity, M extends SCP008IllagerModel<E>> extends MobRenderer<E, M> {

    public SCP008IllagerEntityRenderer(EntityRendererManager entityRenderDispatcher) {
        super(entityRenderDispatcher, (M) new SCP008IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return ((EntityEnumVariants) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).getTextureLocation();
    }

    @Override
    public void render(E pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight) {
        this.model.getHat().visible = pEntity.getVariantEnum(pEntity).equals(SCP008IllagerEnumVariants.ILLUSIONER);
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}


 */