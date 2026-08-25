package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;

public enum DClassEnumVariants implements EntityEnumVariants, VariantUsesHumanSlim {
	TYPE_0("0", false),
	TYPE_1("1", false),
	TYPE_2("2", true),
	TYPE_3("3", true),
	TYPE_4("4", true),
	TYPE_5("5", false),
	TYPE_6("6", false),
	TYPE_7("7", false);

	private final ResourceLocation textureLocation;
	private final boolean slim;

	DClassEnumVariants(String id, boolean slim) {
		this.textureLocation = makeLocation(id);
		this.slim = slim;
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String index) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/staff/class_d_" + index + ".png");
	}

	@Override
	public boolean useSlimModel() {
		return slim;
	}
}
