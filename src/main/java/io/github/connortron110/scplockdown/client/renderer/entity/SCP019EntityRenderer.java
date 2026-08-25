package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.entity.SCP019Model;
import io.github.connortron110.scplockdown.level.entity.SCP019Entity;
import io.github.connortron110.scplockdown.level.entity.variants.EntityEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class SCP019EntityRenderer extends MobRenderer<SCP019Entity, SCP019Model<SCP019Entity>> {
	public SCP019EntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SCP019Model<>(context.bakeLayer(SCPLayerDefinitions.SCP019)), 0.25F);
	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(@Nonnull SCP019Entity pEntity) {
		return ((EntityEnumVariants) ((SCPEntityVariant<?>) pEntity).getVariantEnum(pEntity)).getTextureLocation();
	}
}
