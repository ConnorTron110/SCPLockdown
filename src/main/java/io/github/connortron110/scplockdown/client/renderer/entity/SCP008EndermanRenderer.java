/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.renderer.layers.SCP008EndermanGlowLayer;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCP008EndermanRenderer extends MobRenderer<SCP008Entity, EndermanModel<SCP008Entity>> {
    private static final ResourceLocation ENDERMAN_LOCATION = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID,"textures/entity/008/enderman.png");

    public SCP008EndermanRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EndermanModel<>(pContext.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);   //  FIXME: Layer might have to be changed??
        this.addLayer(new SCP008EndermanGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SCP008Entity pEntity) {
        return ENDERMAN_LOCATION;
    }

}

 */