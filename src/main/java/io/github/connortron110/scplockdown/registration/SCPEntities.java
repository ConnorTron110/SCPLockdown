package io.github.connortron110.scplockdown.registration;

import com.google.common.collect.Maps;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.entity.*;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import io.github.connortron110.scplockdown.registration.builder.item.ItemRegistryObject;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Map;

@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCPEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SCPLockdown.MOD_ID);
	public static final Map<RegistryObject<? extends EntityType<?>>, ItemRegistryObject<ForgeSpawnEggItem>> ENTITY_EGGS = Maps.newHashMap();

	//Non Living\\
	public static final RegistryObject<EntityType<SCP143PetalItemEntity>> PETAL_ITEM = register("petal_item", EntityType.Builder.<SCP143PetalItemEntity>of(SCP143PetalItemEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(6).updateInterval(20));
	public static final RegistryObject<EntityType<ChairEntity>> CHAIR = register("chair", EntityType.Builder.<ChairEntity>of(ChairEntity::new, MobCategory.MISC).sized(1F, 1F));

	//Staff\\
	//public static final RegistryObject<EntityType<DClassEntity>> DCLASS = registerWithEgg("dclass", 0xd19848, 0x1c1a18, EntityType.Builder.of(DClassEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F));
	//public static final RegistryObject<EntityType<ScientistEntity>> SCIENTIST = registerWithEgg("scientist", 0xebebeb, 0x6f807e, EntityType.Builder.of(ScientistEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<GuardEntity>> GUARD = registerWithEgg("guard", 0x2d2d2d, 0xd8df00, EntityType.Builder.of(GuardEntity::new, MobCategory.CREATURE).sized(0.6F, 1.95F));

	//Creatures\\
	public static final RegistryObject<EntityType<RodentEntity>> MOUSE = registerWithEgg("mouse", 0x957a4f, 0xf3f3f3, EntityType.Builder.of(RodentEntity::new, MobCategory.CREATURE).sized(0.4F, 0.25F));
	public static final RegistryObject<EntityType<RodentEntity>> RAT = registerWithEgg("rat", 0x878380, 0xc8b2a5, EntityType.Builder.of(RodentEntity::new, MobCategory.CREATURE).sized(0.4F, 0.3F));

	//SCPs\\
	//public static final RegistryObject<EntityType<SCP008PlayerEntity>> SCP008_PLAYER = register("scp008_player", EntityType.Builder.of(SCP008PlayerEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008GenericEntity>> SCP008_GENERIC = registerWithEgg("scp008_generic", 0x7a7a7a, 0x5a3636, EntityType.Builder.of(SCP008GenericEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008DClassEntity>> SCP008_DCLASS = registerWithEgg("scp008_dclass", 0x7b3719, 0x604240, EntityType.Builder.of(SCP008DClassEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008ScientistEntity>> SCP008_SCIENTIST = registerWithEgg("scp008_scientist", 0xa28787, 0x563b3b, EntityType.Builder.of(SCP008ScientistEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	public static final RegistryObject<EntityType<SCP008Entity>> SCP008_GUARD = registerWithEgg("scp008_guard", 0x3e2929, 0x856e2a, EntityType.Builder.of(SCP008Entity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008VillagerEntity>> SCP008_VILLAGER = registerWithEgg("scp008_villager", 0x583127, 0xb79b8f, EntityType.Builder.of(SCP008VillagerEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008IllagerEntity>> SCP008_ILLAGER = registerWithEgg("scp008_illager", 0x3e1e0f, 0xa38787, EntityType.Builder.of(SCP008IllagerEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//Witch //TODO No texture yet
	//public static final RegistryObject<EntityType<SCP008Entity>> SCP008_ENDERMAN = registerWithEgg("scp008_enderman", 0x1e1111, 0x681d80, EntityType.Builder.of(SCP008Entity::new, MobCategory.MONSTER).sized(0.6F, 2.9F));
	//public static final RegistryObject<EntityType<SCP008Entity>> SCP008_PIGLIN = registerWithEgg("scp008_piglin", 0xba8f66, 0x33402e, EntityType.Builder.of(SCP008Entity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//public static final RegistryObject<EntityType<SCP008PiglinBruteEntity>> SCP008_PIGLIN_BRUTE = registerWithEgg("scp008_piglin_brute", 0x232227, 0x785916, EntityType.Builder.of(SCP008PiglinBruteEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));
	//008 Infected 682 //TODO

	public static final RegistryObject<EntityType<SCP019Entity>> SCP019 = registerWithEgg("scp019_monster", 0xfae0e0, 0x7e939a, EntityType.Builder.of(SCP019Entity::new, MobCategory.MONSTER).sized(0.4F, 0.3F));

	public static final RegistryObject<EntityType<SCP023Entity>> SCP023 = registerWithEgg("scp023", 0x141414, 0xde1515, EntityType.Builder.of(SCP023Entity::new, MobCategory.MONSTER).sized(1.25F, 1.25F));

	//public static final RegistryObject<EntityType<SCP027Entity>> SCP027 = registerWithEgg("scp027", 0xf3f3f3, 0x63625b, EntityType.Builder.of(SCP027Entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F));

	//public static final RegistryObject<EntityType<SCP035VictimEntity>> SCP035_VICTIM = registerWithEgg("scp035_victim", 0xf5fcff, 0x121517, EntityType.Builder.of(SCP035VictimEntity::new, MobCategory.MONSTER).sized(0.65F, 2F).clientTrackingRange(6).updateInterval(3));

	public static final RegistryObject<EntityType<SCP049Entity>> SCP049 = registerWithEgg("scp049", 0x101010, 0xa1a1a1, EntityType.Builder.of(SCP049Entity::new, MobCategory.MONSTER).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<SCP049PlayerEntity>> SCP049_PLAYER = register("scp049_player", EntityType.Builder.of(SCP049PlayerEntity::new, MobCategory.MONSTER).sized(0.6F, 2F));

	//public static final RegistryObject<EntityType<SCP053Entity>> SCP053 = registerWithEgg("scp053", 0xded660, 0xd9b69c, EntityType.Builder.of(SCP053Entity::new, MobCategory.CREATURE).sized(0.4F, 1F));

	public static final RegistryObject<EntityType<SCP939Entity>> SCP939 = registerWithEgg("scp939", 0x500602, 0x930c05, EntityType.Builder.of(SCP939Entity::new, MobCategory.MONSTER).sized(1.3F, 1.75F));

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent e) {
		//e.put(DCLASS.get(), createMonsterAttributes(25, 0.2, 3.5, 3).build()); //Not Set Correctly
		//e.put(SCIENTIST.get(), createMonsterAttributes(25, 0.2, 3.5, 3).build()); //Not Set Correctly
		e.put(GUARD.get(), createMonsterAttributes(25, 0.2, 3.5, 5).build()); //Not Set Correctly

		e.put(MOUSE.get(), createMonsterAttributes(3, 0.22, 0.5, 0).build());
		e.put(RAT.get(), createMonsterAttributes(4, 0.25, 0.6, 0).build());

		//e.put(SCP008_PLAYER.get(), createMonsterAttributes(25, 0.18, 3.5, 3).build());
		//e.put(SCP008_GENERIC.get(), createMonsterAttributes(25, 0.18, 3.5, 3).build());
		//e.put(SCP008_DCLASS.get(), createMonsterAttributes(25, 0.18, 3.5, 3).build());
		//e.put(SCP008_SCIENTIST.get(), createMonsterAttributes(25, 0.18, 3.5, 3).build());
		e.put(SCP008_GUARD.get(), createMonsterAttributes(25, 0.18, 3.5, 5).build());
		//e.put(SCP008_VILLAGER.get(), createMonsterAttributes(24, 0.23, 5, 4).build());
		//e.put(SCP008_ILLAGER.get(), createMonsterAttributes(24, 0.23, 5, 4).build());

		//e.put(SCP008_ENDERMAN.get(), createMonsterAttributes(50, 0.3, 7, 8).build());
		//e.put(SCP008_PIGLIN.get(), createMonsterAttributes(12, 0.13, 12, 5).build());
		//e.put(SCP008_PIGLIN_BRUTE.get(), createMonsterAttributes(160, 0.4, 19.5, 15).build());

		e.put(SCP019.get(), createMonsterAttributes(3, 0.3, 6, 0).build());

		e.put(SCP023.get(), createMonsterAttributes(40, 0.43, 8, 0).build());

		//e.put(SCP027.get(), createMonsterAttributes(25, 0.18, 3.5, 3).build());

		//e.put(SCP035_VICTIM.get(), createMonsterAttributes(20, 0.23, 2, 0).build()); //Not Set Correctly

		e.put(SCP049.get(), createMonsterAttributes(40, 0.4, 10, 0).build());
		e.put(SCP049_PLAYER.get(), createMonsterAttributes(25, 0.18, 3.5, 0).build());

		//e.put(SCP053.get(), createMonsterAttributes(10, 0.2, 0, 0).build());

		e.put(SCP939.get(), createMonsterAttributes(40, 0.3, 10, 1).build());
	}

	/**
	 * Gets the egg for the given entity registry object
	 *
	 * @param registryObject The entity registry object to get the egg for.
	 * @return Returns the Item Registry object, can return null if none found.
	 */
	@Nullable
	public static <E extends Entity> ItemRegistryObject<ForgeSpawnEggItem> getEggFromEntity(RegistryObject<EntityType<E>> registryObject) {
		return ENTITY_EGGS.getOrDefault(registryObject, null);
	}

	/**
	 * Base Attributes for all entities (mostly has attributes related to hostile entities)
	 *
	 * @param health       Max Health of the Entity
	 * @param moveSpeed    Speed of the Entity
	 * @param attackDamage Damage that the entity gives
	 * @return Builder to append more Attributes if needed
	 */
	private static AttributeSupplier.Builder createMonsterAttributes(double health, double moveSpeed, double attackDamage, double armor) {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, health)
				.add(Attributes.FOLLOW_RANGE, 32)
				.add(Attributes.MOVEMENT_SPEED, moveSpeed)
				.add(Attributes.ATTACK_DAMAGE, attackDamage)
				.add(Attributes.ARMOR, armor);
	}

	private static <M extends Mob> RegistryObject<EntityType<M>> registerWithEgg(String name, int bgColor, int highlightColor, EntityType.Builder<M> builder) {
		RegistryObject<EntityType<M>> registryObject = register(name, builder);
		//Might be wise to append _egg to the end of the name if any conflicts do occur
		ENTITY_EGGS.put(registryObject, SCPItems.register(name, properties -> new ForgeSpawnEggItem(registryObject, bgColor, highlightColor, properties)));
		return registryObject;
	}

	private static <E extends Entity> RegistryObject<EntityType<E>> register(String name, EntityType.Builder<E> builder) {
		return ENTITY_TYPES.register(name, () -> builder.build(SCPLockdown.MOD_ID + ":" + name));
	}
}
