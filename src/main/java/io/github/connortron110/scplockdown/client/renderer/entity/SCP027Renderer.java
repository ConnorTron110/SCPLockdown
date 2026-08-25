/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.renderer.layers.SCP027CapLayer;
import io.github.connortron110.scplockdown.level.entity.SCP027Entity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class SCP027Renderer<E extends SCP027Entity, M extends BipedModel<E>> extends BipedRenderer<E, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SCPLockdown.MOD_ID, "textures/entity/scp027.png");

    public SCP027Renderer(EntityRendererManager rendererManager) {
        super(rendererManager, (M) new BipedModel(location -> RenderType.entityCutoutNoCull((ResourceLocation) location),0F, 0F, 64, 64), 0.5F);
        this.addLayer(new SCP027CapLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(E pEntity) {
        return TEXTURE;
    }
}


 */