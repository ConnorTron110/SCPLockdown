package io.github.connortron110.scplockdown.level.entity.variants;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.resources.ResourceLocation;

public enum SCP019EnumVariants implements EntityEnumVariants {
	A("a"),
	B("b"), //FIXME Fix texture
	C("c"), //FIXME Fix texture
	D("d"),
	E("e");

	private final ResourceLocation textureLocation;

	SCP019EnumVariants(String id) {
		this.textureLocation = makeLocation(id);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return textureLocation;
	}

	private static ResourceLocation makeLocation(String index) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "textures/entity/019/" + index + ".png");
	}
}
