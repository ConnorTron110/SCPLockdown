/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.entity.HumanModel;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008DClassEntity;
import io.github.connortron110.scplockdown.level.entity.variants.DClassEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.VariantUsesHumanSlim;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;

public class SCP008DClassEntityRenderer<E extends SCP008DClassEntity, M extends HumanModel<E>> extends MobRenderer<E, M> {

    private final static ImmutableList<ResourceLocation> TEXTURES;
    static {
        ImmutableList.Builder<ResourceLocation> builder =  ImmutableList.builder();
        Arrays.stream(DClassEnumVariants.values()).forEachOrdered(dClassEnumVariants -> {
            builder.add(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/008/class_d_" + dClassEnumVariants.ordinal() + ".png"));
        });
        TEXTURES = builder.build();
    }

    private final M human = (M) new HumanModel<E>(false);
    private final M humanSlim = (M) new HumanModel<E>(true);

    public SCP008DClassEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, (M) new HumanModel<E>(false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURES.get(((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity).ordinal());
    }


    @Override
    public void render(E entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        this.model = ((VariantUsesHumanSlim) ((SCPEntityVariant<?>) entity).getVariantEnum(entity)).useSlimModel() ? humanSlim : human;
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}


 */