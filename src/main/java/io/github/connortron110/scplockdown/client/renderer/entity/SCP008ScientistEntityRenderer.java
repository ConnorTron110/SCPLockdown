/*package io.github.connortron110.scplockdown.client.renderer.entity;

import com.google.common.collect.ImmutableList;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.entity.HumanModel;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008ScientistEntity;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.ScientistEnumVariants;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public class SCP008ScientistEntityRenderer<E extends SCP008ScientistEntity, M extends HumanModel<E>> extends MobRenderer<E, M> {

    private final static ImmutableList<ResourceLocation> TEXTURES;
    static {
        ImmutableList.Builder<ResourceLocation> builder =  ImmutableList.builder();
        Arrays.stream(ScientistEnumVariants.values()).forEachOrdered(dClassEnumVariants -> {
            builder.add(new ResourceLocation(SCPLockdown.MOD_ID, "textures/entity/008/scientist_" + dClassEnumVariants.ordinal() + ".png"));
        });
        TEXTURES = builder.build();
    }

    public SCP008ScientistEntityRenderer(EntityRendererManager entityRenderDispatcher) {
        super(entityRenderDispatcher, (M) new HumanModel<E>(false), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURES.get(((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity).ordinal());
    }
}


 */