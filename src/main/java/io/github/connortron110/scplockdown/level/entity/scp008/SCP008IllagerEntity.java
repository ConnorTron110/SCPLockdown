package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.entity.variants.SCP008IllagerEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SCP008IllagerEntity extends SCP008Entity implements SCPEntityVariant<SCP008IllagerEnumVariants> {
	public SCP008IllagerEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	public SCP008IllagerEnumVariants[] getEnumVariantValues() {
		return SCP008IllagerEnumVariants.values();
	}
}
