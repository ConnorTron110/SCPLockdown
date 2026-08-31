package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.api.SCPEntry;
import io.github.connortron110.scplockdown.api.SCPObjectClass;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static io.github.connortron110.scplockdown.api.SCPEntry.Builder.builder;

//Used to give all SCP related objects its information
public class SCPs {
	//Not going to use a forge registry unless I decide later as it doesn't affect the core part of the game as we are not really registering any physical things that need to be synced client-server

	//  TODO Probably a better and more memory efficient way to do this
	public static final ArrayList<SCPEntry> entries = new ArrayList<>();
	static final HashMap<Object, Integer> fastLookup = new HashMap<>(); //Used to store the index of the Entry its already gotten instead of having to loop through it all again
	static final HashSet<Object> noEntryObjects = new HashSet<>(); //Currently only contains things that are from this mod that is not a part of an SCP entry

	//TODO Include NBT Specific Item Entries (For SCP-008 Vial)
	static {
		register(builder("002", "The \"Living\" Room", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP002_METAL,
						SCPBlocks.SCP002_FLESH_A,
						SCPBlocks.SCP002_FLESH_B,
						SCPBlocks.SCP002_FLESH_C,
						SCPBlocks.SCP002_FLESH_D,
						SCPBlocks.SCP002_COFFEE_TABLE,
						SCPBlocks.SCP002_PLANT_POT,
						SCPBlocks.SCP002_TV,
						SCPBlocks.SCP002_DOOR,
						SCPBlocks.SCP002_LAMP,
						SCPBlocks.SCP002_ARM_CHAIR,
						SCPBlocks.SCP002_TABLE));
		register(builder("005", "Skeleton Key", SCPObjectClass.SAFE)
				.addItems(SCPItems.SCP005));
		//register(builder("006", "Fountain of Youth", SCPObjectClass.SAFE)
		//        .addItems(SCPFluids.SCP006_FOUNTAIN.getBucket()));
		register(builder("008", "Zombie Plague", SCPObjectClass.EUCLID)
				.addItems(SCPItems.PUTRID_FLESH)
				//.addEntities(SCPEntities.SCP008_PLAYER)
				//.addEntities(SCPEntities.SCP008_GENERIC)
				//.addEntities(SCPEntities.SCP008_DCLASS)
				//.addEntities(SCPEntities.SCP008_SCIENTIST)
				.addEntities(SCPEntities.SCP008_GUARD));
		//.addEntities(SCPEntities.SCP008_VILLAGER)
		//.addEntities(SCPEntities.SCP008_ILLAGER)
		//.addEntities(SCPEntities.SCP008_ENDERMAN)
		//.addEntities(SCPEntities.SCP008_PIGLIN)
		//.addEntities(SCPEntities.SCP008_PIGLIN_BRUTE));
		register(builder("009", "Red Ice", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP009));
		register(builder("012", "A Bad Composition", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP012));
		register(builder("015", "Pipe Nightmare", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP015_PIPE, SCPBlocks.SCP015_BLOCK));
		register(builder("019", "The Monster Pot", SCPObjectClass.KETER)
				.addBlocks(SCPBlocks.SCP019)
				.addEntities(SCPEntities.SCP019));
		register(builder("023", "Black Shuck", SCPObjectClass.EUCLID)
				.addEntities(SCPEntities.SCP023));
		register(builder("035", "Possessive Mask", SCPObjectClass.KETER)
				.addItems(SCPItems.SCP035_MASK)
				.addBlocks(SCPBlocks.SCP035_GLASS_CASE));
		//.addEntities(SCPEntities.SCP035_VICTIM));
		register(builder("049", "Plague Doctor", SCPObjectClass.EUCLID)
				.addEntities(SCPEntities.SCP049)
				.addEntities(SCPEntities.SCP049_PLAYER));
		//register(builder("053", "Young Girl", SCPObjectClass.EUCLID)
		//        .addEntities(SCPEntities.SCP053));
		register(builder("063", "\"The World's Best TothBrush\"", SCPObjectClass.SAFE)
				.addItems(SCPItems.SCP063));
		register(builder("109", "Infinite Canteen", SCPObjectClass.EUCLID)
				.addItems(SCPItems.SCP109));
		register(builder("124", "Fertile Soil", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP124));
		register(builder("143", "The Bladewood Grove", SCPObjectClass.EUCLID)
				.addItems(SCPItems.SCP143_PETALS,
						SCPItems.SCP143_PROCESSED_LOG,
						SCPItems.SCP143_INGOT,
						SCPItems.SCP143_SWORD,
						SCPItems.SCP143_AXE,
						SCPItems.SCP143_PICKAXE,
						SCPItems.SCP143_SHOVEL,
						SCPItems.SCP143_HOE,
						SCPItems.SCP143_HELMET,
						SCPItems.SCP143_CHESTPLATE,
						SCPItems.SCP143_LEGGINGS,
						SCPItems.SCP143_BOOTS)
				.addBlocks(SCPBlocks.SCP143_LOG,
						SCPBlocks.SCP143_LEAVES,
						SCPBlocks.SCP143_PLANKS,
						SCPBlocks.SCP143_SAPLING));
		register(builder("148", "The \"Telekill\" Alloy", SCPObjectClass.EUCLID)
				.addItems(
						SCPItems.SCP148_INGOT,
						SCPItems.SCP148_SWORD,
						SCPItems.SCP148_AXE,
						SCPItems.SCP148_PICKAXE,
						SCPItems.SCP148_SHOVEL,
						SCPItems.SCP148_HOE,
						SCPItems.SCP148_HELMET)
				.addBlocks(SCPBlocks.SCP148_ORE, SCPBlocks.SCP148_BLOCK));
		register(builder("330", "Take Only Two", SCPObjectClass.SAFE)
				.addBlocks(SCPBlocks.SCP330));
		register(builder("458", "The Never-Ending Pizza Box", SCPObjectClass.SAFE)
				.addBlocks(SCPBlocks.SCP458));
		register(builder("500", "Panacea", SCPObjectClass.SAFE)
				.addItems(SCPItems.SCP500));
		register(builder("822", "Landmine Cacti", SCPObjectClass.EUCLID)
				.addBlocks(SCPBlocks.SCP822));
		register(builder("902", "The Final Countdown", SCPObjectClass.KETER)
				.addBlocks(SCPBlocks.SCP902));
		register(builder("914", "The Clockworks", SCPObjectClass.SAFE)
				.addBlocks(SCPBlocks.SCP914, SCPBlocks.SCP914_METAL, SCPBlocks.SCP914_GEARS));
		register(builder("939", "With Many Voices", SCPObjectClass.KETER)
				.addEntities(SCPEntities.SCP939));
	}


	@Nullable
	public static SCPEntry getEntryFromObject(Object object) {
		if (noEntryObjects.contains(object)) return null;
		else if (fastLookup.containsKey(object)) return entries.get(fastLookup.get(object));
		else {
			for (SCPEntry entry : entries) {
				if (entry.associatedItems.contains(object)) {
					fastLookup.put(object, entries.indexOf(entry));
					return entry;
				}
			}
			noEntryObjects.add(object);
			return null; //Object does not have an Entry
		}
	}

	private static void register(SCPEntry.Builder b) {
		entries.add(b.build());
	}
}
