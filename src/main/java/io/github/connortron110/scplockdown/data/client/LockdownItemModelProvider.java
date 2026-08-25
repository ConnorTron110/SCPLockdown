package io.github.connortron110.scplockdown.data.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.items.biocontainer.BioContents;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.registration.SCPItems;
import io.github.connortron110.scplockdown.registration.holders.StairSlabWallTriple;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LockdownItemModelProvider extends ItemModelProvider {

	public LockdownItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//Items
		handheldItem(SCPItems.SCREWDRIVER);
		simpleItem(SCPItems.SCP500);
		simpleItem(SCPItems.SCP063);
		simpleItem(SCPItems.SCP109);
		simpleItem(SCPItems.ASH);
		simpleItem(SCPItems.BURNT_BONE);

		getBuilder(getPath(SCPItems.CARD_WRITER))
				.parent(getExistingFile(rl("item/cardwriter_nocard")))
				.override().predicate(rl("card"), 1)
				.model(getExistingFile(rl("item/cardwriter_card")))
				.end();


		getBuilder(getPath(SCPItems.KEYCARD)).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", "item/keycard_base")
				.texture("layer1", "item/keycard_tint")
				.texture("layer2", "item/keycard_top");

		simpleItem(SCPItems.PIZZA);
		simpleItem(SCPItems.RED_CANDY);
		simpleItem(SCPItems.ORANGE_CANDY);
		simpleItem(SCPItems.YELLOW_CANDY);
		simpleItem(SCPItems.GREEN_CANDY);
		simpleItem(SCPItems.BLUE_CANDY);
		simpleItem(SCPItems.PURPLE_CANDY);

		simpleItem(SCPItems.RODENT_TAIL);

		ItemModelBuilder vial = getBuilder(getPath(SCPItems.VIAL)).parent(getExistingFile(rl("item/vial/vial_0_cap")));
		ItemModelBuilder syringe = getBuilder(getPath(SCPItems.SYRINGE)).parent(getExistingFile(rl("item/syringe_empty")));
		//For Each Vial Type
		for (int i = 0; i < BioContents.values().length; i++) {
			BioContents content = BioContents.values()[i];
			//Vials
			for (int hasCap = 0; hasCap <= 1; hasCap++) { //Cap
				if (content == BioContents.EMPTY) {
					vial.override()
							.predicate(rl("contents"), i)
							.predicate(rl("cap"), hasCap)
							.predicate(rl("amount"), 0)
							.model(getBuilder(getPath(SCPItems.VIAL) + "_empty" + (hasCap == 0 ? "" : "_cap")).parent(getExistingFile(rl("item/vial/vial_0" + (hasCap == 0 ? "" : "_cap")))))
							.end();
					continue;
				}

				for (int amount = 1; amount <= 3; amount++) {
					vial.override()
							.predicate(rl("contents"), i)
							.predicate(rl("cap"), hasCap)
							.predicate(rl("amount"), amount)
							.model(getBuilder(getPath(SCPItems.VIAL) + "_" + i + "_" + amount + (hasCap == 0 ? "" : "_cap"))
									.parent(getExistingFile(rl("item/vial/vial_" + amount + (hasCap == 0 ? "" : "_cap"))))
									.texture("contents", rl("item/vial/" + content.getName())))
							.end();
				}
			}

			//Syringes
			if (content != BioContents.EMPTY) {
				syringe.override().predicate(rl("contents"), i)
						.model(getBuilder(getPath(SCPItems.SYRINGE) + "_" + i)
								.parent(getExistingFile(rl("item/syringe_full")))
								.texture("contents", rl("item/vial/" + content.getName())))
						.end();
			}
		}

		simpleItem(SCPItems.PUTRID_FLESH);

		//SCP Items

		simpleItem(SCPItems.SCP005);

		//bucketItem(SCPFluids.SCP006_FOUNTAIN.getBucket().get(), "scp006_fountain_bucket");

		//getBuilder(getPath(SCPItems.SCP035_MASK)).parent(getExistingFile(mcLoc("builtin/entity"))).texture("particle", modLoc("entity/scp035"));

		simpleItem(SCPItems.SCP143_PETALS);
		simpleItem(SCPItems.SCP143_PROCESSED_LOG);
		simpleItem(SCPItems.SCP143_INGOT);
		handheldItem(SCPItems.SCP143_SWORD);
		handheldItem(SCPItems.SCP143_AXE);
		handheldItem(SCPItems.SCP143_PICKAXE);
		handheldItem(SCPItems.SCP143_SHOVEL);
		handheldItem(SCPItems.SCP143_HOE);
		simpleItem(SCPItems.SCP143_HELMET);
		simpleItem(SCPItems.SCP143_CHESTPLATE);
		simpleItem(SCPItems.SCP143_LEGGINGS);
		simpleItem(SCPItems.SCP143_BOOTS);

		simpleItem(SCPItems.SCP148_INGOT);
		handheldItem(SCPItems.SCP148_SWORD);
		handheldItem(SCPItems.SCP148_AXE);
		handheldItem(SCPItems.SCP148_PICKAXE);
		handheldItem(SCPItems.SCP148_SHOVEL);
		handheldItem(SCPItems.SCP148_HOE);
		simpleItem(SCPItems.SCP148_HELMET);

		//Blocks

		//SCP Blocks
		existingParent(SCPBlocks.SCP002_METAL);
		existingParent(SCPBlocks.SCP002_FLESH_A);
		existingParent(SCPBlocks.SCP002_FLESH_B);
		existingParent(SCPBlocks.SCP002_FLESH_C);
		existingParent(SCPBlocks.SCP002_FLESH_D);
		withExistingParent(getPath(SCPBlocks.SCP002_COFFEE_TABLE), modLoc("block/scp002_coffee_table"));
		existingParent(SCPBlocks.SCP002_PLANT_POT);
		withExistingParent(getPath(SCPBlocks.SCP002_TV), modLoc("block/scp002_tv"));
		simpleItem(SCPBlocks.SCP002_DOOR.get());
		simpleItem(SCPBlocks.SCP002_LAMP.get());
		withExistingParent(getPath(SCPBlocks.SCP002_ARM_CHAIR), modLoc("block/scp002_arm_chair"));
		simpleItem(SCPBlocks.SCP002_TABLE.get());

		existingParent(SCPBlocks.SCP009);

		withExistingParent(getPath(SCPBlocks.SCP012), modLoc("block/scp012"));

		withExistingParent(getPath(SCPBlocks.SCP015_PIPE), modLoc("block/scp015_pipe_center"));
		existingParent(SCPBlocks.SCP015_BLOCK);

		simpleItem(SCPBlocks.SCP019.get());

		existingParent(SCPBlocks.SCP124);

		withExistingParent(getPath(SCPBlocks.SCP143_LOG), modLoc("block/" + getPath(SCPBlocks.SCP143_LOG)));
		withExistingParent(getPath(SCPBlocks.SCP143_LEAVES), modLoc("block/" + getPath(SCPBlocks.SCP143_LEAVES)));
		withExistingParent(getPath(SCPBlocks.SCP143_PLANKS), modLoc("block/" + getPath(SCPBlocks.SCP143_PLANKS)));
		literalSimpleItem(SCPBlocks.SCP143_SAPLING.get(), "block/scp143_sapling");

		withExistingParent(getPath(SCPBlocks.SCP148_ORE), modLoc("block/" + getPath(SCPBlocks.SCP148_ORE)));
		withExistingParent(getPath(SCPBlocks.SCP148_BLOCK), modLoc("block/" + getPath(SCPBlocks.SCP148_BLOCK)));

		withExistingParent(getPath(SCPBlocks.SCP330), modLoc("block/" + getPath(SCPBlocks.SCP330)));

		withExistingParent(getPath(SCPBlocks.SCP458), modLoc("block/" + getPath(SCPBlocks.SCP458)));

		withExistingParent(getPath(SCPBlocks.SCP822), modLoc("block/" + getPath(SCPBlocks.SCP822)));

		withExistingParent(getPath(SCPBlocks.SCP902), modLoc("block/scp902_closed"));

		existingParent(SCPBlocks.SCP914);
		existingParent(SCPBlocks.SCP914_METAL);
		existingParent(SCPBlocks.SCP914_GEARS);

		//Functional Blocks\\

		withExistingParent(getPath(SCPBlocks.CARD_READER), modLoc("block/card_reader_off"));
		withExistingParent(getPath(SCPBlocks.HEAVY_BUTTON), modLoc("block/heavy_button_off"));
		existingParent(SCPBlocks.TOILET);
		existingParent(SCPBlocks.CHAIR);
		existingParent(SCPBlocks.OFFICE_CHAIR);
		existingParent(SCPBlocks.TABLE);
		withExistingParent(getPath(SCPBlocks.OPAQUE_TRAPDOOR), modLoc("block/" + getPath(SCPBlocks.OPAQUE_TRAPDOOR) + "_bottom"));
		existingParent(SCPBlocks.CEILING_LAMP);
		existingParent(SCPBlocks.SMALL_LAMP);
		existingParent(SCPBlocks.CEILING_LIGHT);

		existingParent(SCPBlocks.WOOD_CRATE);
		existingParent(SCPBlocks.DARK_WOOD_CRATE);

		withExistingParent(getPath(SCPBlocks.PERSONAL_COMPUTER), modLoc("block/personal_computer"));
		withExistingParent(getPath(SCPBlocks.COMPUTER), modLoc("block/computer"));

		//Normal Blocks\\

		existingParent(SCPBlocks.CEILING);
		existingParent(SCPBlocks.CEILING_TILE);
		existingParent(SCPBlocks.CEILING_GRATE);

		existingParent(SCPBlocks.CONTAINMENT_FLOOR);
		existingParent(SCPBlocks.FLOOR_A);
		existingParent(SCPBlocks.FLOOR_B);
		existingParent(SCPBlocks.MESH_FLOOR);

		existingParent(SCPBlocks.PLASTER_WALL);
		existingParent(SCPBlocks.VENT);
		existingParent(SCPBlocks.VENTILATION);

		existingParent(SCPBlocks.WALL_A);
		existingParent(SCPBlocks.WALL_B);
		existingParent(SCPBlocks.WALL_C);
		existingParent(SCPBlocks.WALL_D);
		existingParent(SCPBlocks.WALL_E);
		existingParent(SCPBlocks.WALL_F);
		existingParent(SCPBlocks.WALL_G);

		existingParent(SCPBlocks.WHITE_WALL);
		existingParent(SCPBlocks.OLD_WHITE_WALL);

		existingParent(SCPBlocks.REINFORCED_IRON);
		existingParent(SCPBlocks.STEEL);
		existingParent(SCPBlocks.REINFORCED_WALL_A);
		existingParent(SCPBlocks.REINFORCED_WALL_B);
		existingParent(SCPBlocks.REINFORCED_WALL_C);


		existingParent(SCPBlocks.EXTERIOR_BOTTOM);
		existingParent(SCPBlocks.EXTERIOR_MIDDLE);
		existingParent(SCPBlocks.EXTERIOR_TOP);

		existingParent(SCPBlocks.MEDICAL_BOTTOM);
		existingParent(SCPBlocks.MEDICAL_TOP);

		existingParent(SCPBlocks.OFFICE_BOTTOM);
		existingParent(SCPBlocks.OFFICE_TOP);

		existingParent(SCPBlocks.PIPE_WALL);

		existingParent(SCPBlocks.SUBLEVEL_WALL_A);
		existingParent(SCPBlocks.SUBLEVEL_WALL_B);
		existingParent(SCPBlocks.SUBLEVEL_WALL_C);
		existingParent(SCPBlocks.REINFORCED_SUBLEVEL_WALL_A);
		existingParent(SCPBlocks.REINFORCED_SUBLEVEL_WALL_B);
		existingParent(SCPBlocks.REINFORCED_SUBLEVEL_WALL_C);
		existingParent(SCPBlocks.METAL_SUBLEVEL);

		existingParent(SCPBlocks.GRATE);
		existingParent(SCPBlocks.BLAST_RESISTANT_GLASS);

		existingParent(SCPBlocks.ARMORY_BOTTOM_A);
		existingParent(SCPBlocks.ARMORY_TOP_A);
		existingParent(SCPBlocks.ARMORY_BOTTOM_B);
		existingParent(SCPBlocks.ARMORY_TOP_B);

		existingParent(SCPBlocks.LABORATORY_BOTTOM_A);
		existingParent(SCPBlocks.LABORATORY_TOP_A);
		existingParent(SCPBlocks.LABORATORY_BOTTOM_B);
		existingParent(SCPBlocks.LABORATORY_TOP_B);

		existingParent(SCPBlocks.METAL_PANEL_A);
		existingParent(SCPBlocks.METAL_PANEL_B);
		existingParent(SCPBlocks.METAL_PANEL_C);

		existingParent(SCPBlocks.REINFORCED_GRANITE);
		existingParent(SCPBlocks.REINFORCED_QUARTZ);

		existingParent(SCPBlocks.GREEN_PANEL_A);
		existingParent(SCPBlocks.GREEN_PANEL_B);
		existingParent(SCPBlocks.GREEN_PANEL_C);
		existingParent(SCPBlocks.GREEN_PANEL_D);

		existingParent(SCPBlocks.METAL_FLOOR_A);
		existingParent(SCPBlocks.METAL_FLOOR_B);

		existingParent(SCPBlocks.METAL_WALL_A);
		existingParent(SCPBlocks.METAL_WALL_B);

		existingParent(SCPBlocks.STEEL_FLOOR);

		existingParent(SCPBlocks.HAZARD);

		addStairSlabWallTriple(SCPBlocks.REINFORCED_IRON_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.REINFORCED_IRON)));
		addStairSlabWallTriple(SCPBlocks.STEEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.STEEL)));
		addStairSlabWallTriple(SCPBlocks.SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.SUBLEVEL_WALL_A)));
		addStairSlabWallTriple(SCPBlocks.REINFORCED_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.REINFORCED_SUBLEVEL_WALL_A)));
		addStairSlabWallTriple(SCPBlocks.METAL_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.METAL_SUBLEVEL)));
		addStairSlabWallTriple(SCPBlocks.METAL_WALL_A_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.METAL_WALL_A)));

		existingParent(SCPBlocks.WALL_STAIRS);
		existingParent(SCPBlocks.FLOOR_A_STAIRS);
		existingParent(SCPBlocks.FLOOR_B_STAIRS);
		existingParent(SCPBlocks.WHITE_STAIRS);
		existingParent(SCPBlocks.REINFORCED_WALL_A_STAIRS);
		existingParent(SCPBlocks.REINFORCED_WALL_B_STAIRS);
		existingParent(SCPBlocks.REINFORCED_WALL_C_STAIRS);

		slab(SCPBlocks.WALL_SLAB, blockRl(getPath(SCPBlocks.WALL_A)));
		slab(SCPBlocks.FLOOR_A_SLAB, blockRl(getPath(SCPBlocks.FLOOR_A)));
		slab(SCPBlocks.FLOOR_B_SLAB, blockRl(getPath(SCPBlocks.FLOOR_B)));
		slab(SCPBlocks.WHITE_SLAB, blockRl(getPath(SCPBlocks.WHITE_WALL)));
		slab(SCPBlocks.REINFORCED_WALL_A_SLAB, blockRl(getPath(SCPBlocks.REINFORCED_WALL_A)));
		slab(SCPBlocks.REINFORCED_WALL_B_SLAB, blockRl(getPath(SCPBlocks.REINFORCED_WALL_B)));
		slab(SCPBlocks.REINFORCED_WALL_C_SLAB, blockRl(getPath(SCPBlocks.REINFORCED_WALL_C)));

		wallInventory(getPath(SCPBlocks.REINFORCED_WALL_C_WALL), blockRl(getPath(SCPBlocks.REINFORCED_WALL_A)));
		wallInventory(getPath(SCPBlocks.EXTERIOR_BOTTOM_WALL), blockRl(getPath(SCPBlocks.EXTERIOR_BOTTOM)));
		wallInventory(getPath(SCPBlocks.EXTERIOR_MIDDLE_WALL), blockRl(getPath(SCPBlocks.EXTERIOR_MIDDLE)));
		wallInventory(getPath(SCPBlocks.EXTERIOR_TOP_WALL), blockRl(getPath(SCPBlocks.EXTERIOR_TOP)));
		wallInventory(getPath(SCPBlocks.QUARTZ_WALL), ResourceLocation.parse("block/quartz_block_side"));
		wallInventory(getPath(SCPBlocks.PIPE_WALL_WALL), blockRl(getPath(SCPBlocks.PIPE_WALL)));
		wallInventory(getPath(SCPBlocks.METAL_WALL_B_WALL), blockRl(getPath(SCPBlocks.METAL_WALL_B)));
		wallInventory(getPath(SCPBlocks.SMOOTH_QUARTZ_WALL), ResourceLocation.parse("block/quartz_block_bottom"));

		SCPBlocks.STRAIGHT_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_straight")));
		SCPBlocks.CORNER_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_corner")));
		SCPBlocks.JUNC3_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_3junc")));
		SCPBlocks.TJUNC_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_tjunc")));
		SCPBlocks.JUNC4X_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_4xjunc")));
		SCPBlocks.JUNC4_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_4junc")));
		SCPBlocks.JUNC5_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_5junc")));
		SCPBlocks.JUNC6_PIPES.getPairs().forEach(pair -> withExistingParent(getPath(pair.getObject()), modLoc("block/pipe_6junc")));

		//Vanilla Extended Building Blocks
		existingParent(SCPBlocks.SMOOTH_STONE_STAIRS);

		addStairSlabWallTriple(SCPBlocks.WHITE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/white_terracotta"));
		addStairSlabWallTriple(SCPBlocks.ORANGE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/orange_terracotta"));
		addStairSlabWallTriple(SCPBlocks.MAGENTA_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/magenta_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIGHT_BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/light_blue_terracotta"));
		addStairSlabWallTriple(SCPBlocks.YELLOW_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/yellow_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIME_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/lime_terracotta"));
		addStairSlabWallTriple(SCPBlocks.PINK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/pink_terracotta"));
		addStairSlabWallTriple(SCPBlocks.GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/gray_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIGHT_GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/light_gray_terracotta"));
		addStairSlabWallTriple(SCPBlocks.CYAN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/cyan_terracotta"));
		addStairSlabWallTriple(SCPBlocks.PURPLE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/purple_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/blue_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BROWN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/brown_terracotta"));
		addStairSlabWallTriple(SCPBlocks.GREEN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/green_terracotta"));
		addStairSlabWallTriple(SCPBlocks.RED_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/red_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BLACK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/black_terracotta"));
		addStairSlabWallTriple(SCPBlocks.TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/terracotta"));

		wallInventory(getPath(SCPBlocks.STONE_WALL), ResourceLocation.parse("block/stone"));
		wallInventory(getPath(SCPBlocks.POLISHED_GRANITE_WALL), ResourceLocation.parse("block/polished_granite"));
		wallInventory(getPath(SCPBlocks.POLISHED_DIORITE_WALL), ResourceLocation.parse("block/polished_diorite"));
		wallInventory(getPath(SCPBlocks.POLISHED_ANDESITE_WALL), ResourceLocation.parse("block/polished_andesite"));


		//Entity Eggs
		SCPEntities.ENTITY_EGGS.forEach((ignored, eggItem) -> getBuilder(getPath(eggItem)).parent(getExistingFile(mcLoc("item/template_spawn_egg"))));

/*
        ItemModelBuilder tokenOverride = getBuilder(getPath(SCPItems.TOKEN)).parent(getExistingFile(mcLoc("item/generated")));
        SCPs.getSCPs().forEach(entry -> {
            tokenItem("token_" + entry.getRegistryName(), entry.getNumber());
            tokenOverride.override().predicate(new ResourceLocation(SCPLockdown.MOD_ID, "number"), Float.parseFloat(entry.getNumber())).model(getExistingFile(modLoc("item/token_" + entry.getRegistryName()))).end();
        });
        SCPBlocks.BLOCKS.getEntries().forEach(blockRegistryObject -> {
            if (!(blockRegistryObject.get() instanceof LabelBlock)) { //TODO Remove once Labels have been added
                if (!generateExclusion.contains(blockRegistryObject) && !generatedModels.containsKey(blockRegistryObject.get()) && !existingFileHelper.exists(blockRegistryObject.get().getRegistryName(), ResourcePackType.CLIENT_RESOURCES, ".json", "models/item")) {
                    withExistingParent(getPath(blockRegistryObject), modLoc("block/" + getPath(blockRegistryObject)));
                }
            }
        });

 */
	}

	protected void addStairSlabWallTriple(StairSlabWallTriple stairSlabWall, ResourceLocation baseTexture) {
		existingParent(stairSlabWall.getStairs());
		slab(stairSlabWall.getSlab(), baseTexture);
		wallInventory(getPath(stairSlabWall.getWall()), baseTexture);
	}

	private void slab(RegistryObject<?> reg, ResourceLocation textureAll) {
		slab(getPath(reg), textureAll, textureAll, textureAll);
	}

	private ResourceLocation rl(String loc) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, loc);
	}

	private ResourceLocation blockRl(String loc) {
		return rl("block/" + loc);
	}

	//TODO Rework some of the methods as blocks need to be in here too
	private String getPath(ItemLike itemProvider) {
		return ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).getPath();
	}

	private String getPath(RegistryObject<?> reg) {
		return reg.getId().getPath();
	}

	/**
	 * Use for item models that use a parent with the same location as the name of the block
	 */
	private void existingParent(RegistryObject<?> reg) {
		withExistingParent(getPath(reg), modLoc("block/" + getPath(reg)));
	}


	/**
	 * Models that have the same texture name as their ID
	 */
	private ItemModelBuilder simpleItem(ItemLike itemProvider) {
		return simpleItem(itemProvider, ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).getPath());
	}

	private ItemModelBuilder handheldItem(ItemLike itemProvider) {
		return handheldItem(itemProvider, ForgeRegistries.ITEMS.getKey(itemProvider.asItem()).getPath());
	}

	/**
	 * Methods more specific on the texture to use
	 */
	private ItemModelBuilder literalSimpleItem(ItemLike itemProvider, String textureLoc) {
		return getBuilder(getPath(itemProvider)).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", textureLoc);
	}

	private ItemModelBuilder simpleItem(ItemLike itemProvider, String textureLoc) {
		return getBuilder(getPath(itemProvider)).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/" + textureLoc);
	}

	private ItemModelBuilder bucketItem(ItemLike itemProvider, String textureLoc) {
		return getBuilder(getPath(itemProvider)).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", "minecraft:item/bucket")
				.texture("layer1", "item/bucket/" + textureLoc)
				.texture("layer2", "item/bucket/bucket_texture_overlay");
	}

	private ItemModelBuilder handheldItem(ItemLike itemProvider, String textureLoc) {
		return getBuilder(getPath(itemProvider)).parent(getExistingFile(mcLoc("item/handheld"))).texture("layer0", "item/" + textureLoc);
	}

	private ItemModelBuilder tokenItem(String path, String textureLoc) {
		return getBuilder(path).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "token/" + textureLoc);
	}
}
