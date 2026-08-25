package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;

public enum SCP008GenericEnumVariants implements EntityEnumVariants, VariantUsesHumanSlim {
	STEVE("steve", false),
	ALEX("alex", true);

	private final ResourceLocation textureLocation;
	private final boolean slim;

	SCP008GenericEnumVariants(String id, boolean slim) {
		this.textureLocation = makeLocation(id);
		this.slim = slim;
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String index) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/008/generic_" + index + ".png");
	}

	@Override
	public boolean useSlimModel() {
		return slim;
	}
}
