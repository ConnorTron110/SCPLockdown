/*package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.PiglinModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCP008PiglinBruteRenderer extends MobRenderer<SCP008Entity, PiglinModel<SCP008Entity>> {
    private static final ResourceLocation PIGLIN_BRUTE_LOCATION = new ResourceLocation(SCPLockdown.MOD_ID,"textures/entity/008/piglin_brute.png");

    public SCP008PiglinBruteRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager, new PiglinModel<>(0.0F, 64, 64), 0.5F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SCP008Entity pEntity) {
        return PIGLIN_BRUTE_LOCATION;
    }

}

 */