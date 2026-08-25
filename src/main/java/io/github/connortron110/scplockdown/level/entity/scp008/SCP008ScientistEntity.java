package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.ScientistEnumVariants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SCP008ScientistEntity extends SCP008Entity implements SCPEntityVariant<ScientistEnumVariants> {
	public SCP008ScientistEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	public ScientistEnumVariants[] getEnumVariantValues() {
		return ScientistEnumVariants.values();
	}
}
