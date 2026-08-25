/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.entity.SCP035VictimEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class SCP035VictimRenderer<E extends SCP035VictimEntity, M extends BipedModel<E>> extends BipedRenderer<E, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SCPLockdown.MOD_ID, "textures/entity/scp035_victim.png");

    public SCP035VictimRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, (M) new BipedModel(location -> RenderType.entityCutoutNoCull((ResourceLocation) location),0F, 0F, 64, 64), 0.5F);
        this.addLayer(new BipedArmorLayer<>(this, (M) new BipedModel( 0.5F), (M) new BipedModel(1F)));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }
}


 */