/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.client.models.entity.HumanModel;
import io.github.connortron110.scplockdown.level.entity.DClassEntity;
import io.github.connortron110.scplockdown.level.entity.variants.EntityEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.VariantUsesHumanSlim;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DClassEntityRenderer<E extends DClassEntity, M extends HumanModel<E>> extends MobRenderer<E, M> {

    private final M human = (M) new HumanModel<E>(false);
    private final M humanSlim = (M) new HumanModel<E>(true);

    public DClassEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, (M) new HumanModel<E>(false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return ((EntityEnumVariants) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).getTextureLocation();
    }

    @Override
    public void render(E entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        this.model = ((VariantUsesHumanSlim) ((SCPEntityVariant<?>) entity).getVariantEnum(entity)).useSlimModel() ? humanSlim : human;
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}

 */
