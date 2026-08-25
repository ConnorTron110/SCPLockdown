package io.github.connortron110.scplockdown.client.renderer.entity;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

import javax.annotation.Nonnull;

public class RendererGenericEntity<E extends Mob, M extends EntityModel<E>> extends MobRenderer<E, M> {

	private final ResourceLocation texture;

	public RendererGenericEntity(EntityRendererProvider.Context pContext, M model, float shadowRadius, String textureLocation) {
		super(pContext, model, shadowRadius);
		this.texture = ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, textureLocation);
	}

	@Nonnull
	@Override
	public ResourceLocation getTextureLocation(@Nonnull E pEntity) {
		return texture;
	}
}