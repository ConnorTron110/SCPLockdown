/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.client.models.entity.HumanModel;
import io.github.connortron110.scplockdown.level.entity.ScientistEntity;
import io.github.connortron110.scplockdown.level.entity.variants.EntityEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScientistEntityRenderer<E extends ScientistEntity, M extends HumanModel<E>> extends MobRenderer<E, M> {
    public ScientistEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, (M) new HumanModel<E>(false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return ((EntityEnumVariants) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).getTextureLocation();
    }
}


 */