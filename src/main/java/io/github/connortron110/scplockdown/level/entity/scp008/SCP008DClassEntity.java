package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.entity.variants.DClassEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SCP008DClassEntity extends SCP008Entity implements SCPEntityVariant<DClassEnumVariants> {
	public SCP008DClassEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	public DClassEnumVariants[] getEnumVariantValues() {
		return DClassEnumVariants.values();
	}
}
