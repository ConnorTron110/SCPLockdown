/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.entity.SCP008VillagerModel;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008VillagerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SCP008VillagerEntityRenderer extends MobRenderer<SCP008VillagerEntity, SCP008VillagerModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SCPLockdown.MOD_ID, "textures/entity/008/villager/villager.png");

    public SCP008VillagerEntityRenderer(EntityRendererManager entityRenderDispatcher) {
        super(entityRenderDispatcher, new SCP008VillagerModel(), 0.5F);
        //TODO Add villager clothes
        //this.addLayer(new VillagerLevelPendantLayer<>(this, (IReloadableResourceManager) Minecraft.getInstance().getResourceManager(), "villager"));
    }

    @Override
    public ResourceLocation getTextureLocation(SCP008VillagerEntity pEntity) {
        return TEXTURE;
    }
}


 */