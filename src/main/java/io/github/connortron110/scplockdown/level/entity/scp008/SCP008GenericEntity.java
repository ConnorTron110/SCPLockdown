package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.entity.variants.SCP008GenericEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SCP008GenericEntity extends SCP008Entity implements SCPEntityVariant<SCP008GenericEnumVariants> {
	public SCP008GenericEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	public SCP008GenericEnumVariants[] getEnumVariantValues() {
		return SCP008GenericEnumVariants.values();
	}
}
