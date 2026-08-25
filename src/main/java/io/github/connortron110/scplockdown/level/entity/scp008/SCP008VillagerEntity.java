package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.entity.variants.SCP008VillagerEnumVariants;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SCP008VillagerEntity extends SCP008Entity implements SCPEntityVariant<SCP008VillagerEnumVariants> {
	public SCP008VillagerEntity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);
	}

	@Override
	public SCP008VillagerEnumVariants[] getEnumVariantValues() {
		return SCP008VillagerEnumVariants.values();
	}
}
