/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.connortron110.scplockdown.client.models.entity.HumanModel;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008GenericEntity;
import io.github.connortron110.scplockdown.level.entity.variants.EntityEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.VariantUsesHumanSlim;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SCP008GenericEntityRenderer<E extends SCP008GenericEntity, M extends HumanModel<E>> extends MobRenderer<E, M> {

    private final M human = (M) new HumanModel<E>(false);
    private final M humanSlim = (M) new HumanModel<E>(true);

    public SCP008GenericEntityRenderer(EntityRendererManager entityRenderDispatcher) {
        super(entityRenderDispatcher, (M) new HumanModel<E>(false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return ((EntityEnumVariants) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).getTextureLocation();
    }

    @Override
    public void render(E pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight) {
        this.model = ((VariantUsesHumanSlim) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).useSlimModel() ? humanSlim : human;
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}


 */