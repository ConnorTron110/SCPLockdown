package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;

public enum ScientistEnumVariants implements EntityEnumVariants {
	TYPE_0("0"),
	TYPE_1("1"),
	TYPE_2("2"),
	TYPE_3("3");

	private final ResourceLocation textureLocation;

	ScientistEnumVariants(String id) {
		this.textureLocation = makeLocation(id);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String index) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/staff/scientist_" + index + ".png");
	}
}
