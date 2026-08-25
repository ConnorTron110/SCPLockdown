package io.github.connortron110.scplockdown.data.loot;

import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class LockdownEntityLootTables extends EntityLootSubProvider {
	protected LockdownEntityLootTables() {
		super(FeatureFlags.REGISTRY.allFlags());
	}

	@Override
	public void generate() {
		//add(SCPEntities.DCLASS.get(), LootTable.lootTable());
		//add(SCPEntities.SCIENTIST.get(), LootTable.lootTable());
		add(SCPEntities.GUARD.get(), LootTable.lootTable());

		add(SCPEntities.MOUSE.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(SCPItems.RODENT_TAIL).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))));
		add(SCPEntities.RAT.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(SCPItems.RODENT_TAIL).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))));


		//add(SCPEntities.SCP008_PLAYER.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_GENERIC.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_DCLASS.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_SCIENTIST.get(), LootTable.lootTable());
		add(SCPEntities.SCP008_GUARD.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_VILLAGER.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_ILLAGER.get(), LootTable.lootTable());
		//add(SCPEntities.SCP008_ENDERMAN.get(), LootTable.lootTable()
		//        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
		//                .add(LootTableReference.lootTableReference(EntityType.ENDERMAN.getDefaultLootTable()))));
		//add(SCPEntities.SCP008_PIGLIN.get(), LootTable.lootTable()
		//        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
		//                .add(LootTableReference.lootTableReference(EntityType.PIGLIN.getDefaultLootTable()))));
		//add(SCPEntities.SCP008_PIGLIN_BRUTE.get(), LootTable.lootTable()
		//        .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
		//                .add(LootTableReference.lootTableReference(EntityType.PIGLIN_BRUTE.getDefaultLootTable()))
		//                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
		//                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
		//                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));

		add(SCPEntities.SCP019.get(), LootTable.lootTable());

		add(SCPEntities.SCP023.get(), LootTable.lootTable());

		//add(SCPEntities.SCP027.get(), LootTable.lootTable());

		//add(SCPEntities.SCP035_VICTIM.get(), LootTable.lootTable());

		add(SCPEntities.SCP049.get(), LootTable.lootTable());
		add(SCPEntities.SCP049_PLAYER.get(), LootTable.lootTable());

		//add(SCPEntities.SCP053.get(), LootTable.lootTable());

		add(SCPEntities.SCP939.get(), LootTable.lootTable());

		//CompoundTag var0 = new CompoundNBT(); var0.putInt("Variant", 0);
		//CompoundTag var1 = new CompoundNBT(); var1.putInt("Variant", 1);
		//add(ModEntityTypes.SCP_058.get(), new LootTable.Builder()
		//        .withPool(new LootPool.Builder().setRolls(ConstantValue.exactly(1))
		//                .add(LootItem.lootTableItem(Items.RED_WOOL))
		//                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().nbt(new NbtPredicate(var0)))))

		//        .withPool(new LootPool.Builder().setRolls(ConstantValue.exactly(1))
		//                .add(LootItem.lootTableItem(Items.GREEN_WOOL))
		//                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().nbt(new NbtPredicate(var1))))));
	}

	@Nonnull
	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return SCPEntities.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
	}
}
