package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.level.items.*;
import io.github.connortron110.scplockdown.level.items.biocontainer.SyringeItem;
import io.github.connortron110.scplockdown.level.items.biocontainer.VialItem;
import io.github.connortron110.scplockdown.level.items.tools.SCPArmorMaterials;
import io.github.connortron110.scplockdown.level.items.tools.SCPItemTiers;
import io.github.connortron110.scplockdown.registration.builder.item.ItemBuilder;
import io.github.connortron110.scplockdown.registration.builder.item.ItemDeferredRegister;
import io.github.connortron110.scplockdown.registration.builder.item.ItemRegistryObject;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.function.Function;

import static io.github.connortron110.scplockdown.SCPLockdown.MOD_ID;

public class SCPItems {
	public static final ItemDeferredRegister ITEMS = ItemDeferredRegister.create(MOD_ID);

	public static final ItemRegistryObject<ScrewDriverItem> SCREWDRIVER = register("screwdriver", ScrewDriverItem::new);
	//TODO Finalize Porting
	public static final ItemRegistryObject<TothBrushItem> SCP063 = registerStacksTo("scp063", TothBrushItem::new, 1);
	public static final ItemRegistryObject<WaterCanteenItem> SCP109 = registerStacksTo("scp109", WaterCanteenItem::new, 1);
	public static final ItemRegistryObject<PanaceaPillItem> SCP500 = registerStacksTo("scp500", PanaceaPillItem::new, 47); //TODO Cure Player (Currently Cures Effects)

	public static final ItemRegistryObject<SCPItem> ASH = register("ash", SCPItem::new);
	public static final ItemRegistryObject<SCPItem> BURNT_BONE = register("burnt_bone", SCPItem::new);

	public static final ItemRegistryObject<CardWriterItem> CARD_WRITER = register("card_writer", CardWriterItem::new);
	public static final ItemRegistryObject<KeycardItem> KEYCARD = register("keycard", KeycardItem::new);

	//  FOOD Items
	public static final ItemRegistryObject<SCPItem> PIZZA = registerStacksTo("pizza", properties -> new SCPItem(properties.food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).fast().build())), 12);
	public static final ItemRegistryObject<CandyItem> RED_CANDY = register("red_candy", CandyItem::new);
	public static final ItemRegistryObject<CandyItem> ORANGE_CANDY = register("orange_candy", CandyItem::new);
	public static final ItemRegistryObject<CandyItem> YELLOW_CANDY = register("yellow_candy", CandyItem::new);
	public static final ItemRegistryObject<CandyItem> GREEN_CANDY = register("green_candy", CandyItem::new);
	public static final ItemRegistryObject<CandyItem> BLUE_CANDY = register("blue_candy", CandyItem::new);
	public static final ItemRegistryObject<CandyItem> PURPLE_CANDY = register("purple_candy", CandyItem::new);

	public static final ItemRegistryObject<Item> RODENT_TAIL = register("rodent_tail", SCPItem::new);

	public static final ItemRegistryObject<VialItem> VIAL = registerStacksTo("vial", VialItem::new, 1);
	public static final ItemRegistryObject<SyringeItem> SYRINGE = registerStacksTo("syringe", SyringeItem::new, 1);
	public static final ItemRegistryObject<Item> PUTRID_FLESH = register("putrid_flesh", Item::new);

	//TODO 008 Multiple Entity Egg

	public static final ItemRegistryObject<SCP005Item> SCP005 = register("scp005", SCP005Item::new);

	public static final ItemRegistryObject<SCP035MaskItem> SCP035_MASK = registerStacksTo("scp035_mask", SCP035MaskItem::new, 1);

	public static final ItemRegistryObject<SCP143PetalItem> SCP143_PETALS = register("scp143_petals", SCP143PetalItem::new);
	public static final ItemRegistryObject<SCPItem> SCP143_PROCESSED_LOG = register("scp143_processed_log", SCPItem::new);
	public static final ItemRegistryObject<SCPItem> SCP143_INGOT = register("scp143_ingot", SCPItem::new);
	public static final ItemRegistryObject<SwordItem> SCP143_SWORD = register("scp143_sword", properties -> new SwordItem(SCPItemTiers.SCP143, 1, 1F, properties));
	public static final ItemRegistryObject<AxeItem> SCP143_AXE = register("scp143_axe", properties -> new AxeItem(SCPItemTiers.SCP143, 1F, 1F, properties));
	public static final ItemRegistryObject<PickaxeItem> SCP143_PICKAXE = register("scp143_pickaxe", properties -> new PickaxeItem(SCPItemTiers.SCP143, 1, 1F, properties));
	public static final ItemRegistryObject<ShovelItem> SCP143_SHOVEL = register("scp143_shovel", properties -> new ShovelItem(SCPItemTiers.SCP143, 1F, 1F, properties));
	public static final ItemRegistryObject<HoeItem> SCP143_HOE = register("scp143_hoe", properties -> new HoeItem(SCPItemTiers.SCP143, 1, 1F, properties));
	public static final ItemRegistryObject<ArmorItem> SCP143_HELMET = register("scp143_helmet", properties -> new ArmorItem(SCPArmorMaterials.SCP143, ArmorItem.Type.HELMET, properties));
	public static final ItemRegistryObject<ArmorItem> SCP143_CHESTPLATE = register("scp143_chestplate", properties -> new ArmorItem(SCPArmorMaterials.SCP143, ArmorItem.Type.CHESTPLATE, properties));
	public static final ItemRegistryObject<ArmorItem> SCP143_LEGGINGS = register("scp143_leggings", properties -> new ArmorItem(SCPArmorMaterials.SCP143, ArmorItem.Type.LEGGINGS, properties));
	public static final ItemRegistryObject<ArmorItem> SCP143_BOOTS = register("scp143_boots", properties -> new ArmorItem(SCPArmorMaterials.SCP143, ArmorItem.Type.BOOTS, properties));

	public static final ItemRegistryObject<SCPItem> SCP148_INGOT = register("scp148_ingot", SCPItem::new);
	public static final ItemRegistryObject<SwordItem> SCP148_SWORD = register("scp148_sword", properties -> new SwordItem(SCPItemTiers.SCP143, 6, 1.6F, properties));
	public static final ItemRegistryObject<AxeItem> SCP148_AXE = register("scp148_axe", properties -> new AxeItem(SCPItemTiers.SCP143, 3.5F, 1.4F, properties));
	public static final ItemRegistryObject<PickaxeItem> SCP148_PICKAXE = register("scp148_pickaxe", properties -> new PickaxeItem(SCPItemTiers.SCP143, 4, 1.2F, properties));
	public static final ItemRegistryObject<ShovelItem> SCP148_SHOVEL = register("scp148_shovel", properties -> new ShovelItem(SCPItemTiers.SCP143, 5, 1F, properties));
	public static final ItemRegistryObject<HoeItem> SCP148_HOE = register("scp148_hoe", properties -> new HoeItem(SCPItemTiers.SCP143, 1, 3.5F, properties));
	public static final ItemRegistryObject<SCP148ArmorItem> SCP148_HELMET = register("scp148_helmet", properties -> new SCP148ArmorItem(SCPArmorMaterials.SCP148, ArmorItem.Type.HELMET, properties));

	//public static final ItemRegistryObject<ItemDragonEgg> DRAGON_EGG = registerStacksTo("dragonegg", ItemDragonEgg::new, 16); //TODO Item Model Overrides

    /*
    for(DragonSnails dragonSnail : DragonSnails.values()){
        PROXY.registerModel(DRAGON_EGG,dragonSnail.ordinal(),dragonSnail.getName());
    }
     */

	//public static final ItemRegistryObject<ItemOrgan> ORGAN = register("organ", ItemOrgan::new); //TODO Item Model Overrides
    /*
    for(ItemOrgan.Organs organType : ItemOrgan.Organs.values()){
        PROXY.registerModel(ORGAN,organType.ordinal(),organType.getName());
    }
     */

	//public static ItemRegistryObject<ItemNightvisionGoggles> NVG_GOGGLES = register("nightvision_goggles", ItemNightvisionGoggles::new);
	//public static ItemRegistryObject<ItemBattery> BATTERY = register("battery", ItemBattery::new); //TODO .setRechargeAmount(4000);


	//public static ArrayList<Item> candies = new ArrayList<>(7); //TODO change this to a multi item to be more consistent with other items of a similar nature

	public static <I extends Item> ItemRegistryObject<I> register(String name, Function<Item.Properties, I> builder) {
		return builder(name, builder).build();
	}

	private static <I extends Item> ItemRegistryObject<I> registerStacksTo(String name, Function<Item.Properties, I> builder, int maxStackSize) {
		return builder(name, builder).stacksTo(maxStackSize).build();
	}

	private static <I extends Item> ItemBuilder<I> builder(String name, Function<Item.Properties, I> builder) {
		return ITEMS.register(name, builder);
	}
}
