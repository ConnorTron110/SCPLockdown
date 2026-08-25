package io.github.connortron110.scplockdown.client.renderer.layers;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.entity.SCP023Model;
import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SCP023GlowLayer<T extends SCP023Entity> extends EyesLayer<T, SCP023Model<T>> {
	private static final RenderType GLOW = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/scp023_glow.png"));

	public SCP023GlowLayer(RenderLayerParent<T, SCP023Model<T>> pRenderer) {
		super(pRenderer);
	}

	@Nonnull
	public RenderType renderType() {
		return GLOW;
	}
}
