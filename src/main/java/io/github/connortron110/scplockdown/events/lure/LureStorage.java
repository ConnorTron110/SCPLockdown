package io.github.connortron110.scplockdown.events.lure;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.events.lure.interfaces.IBlockLurable;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.debug.CBLureDebugSync;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkDirection;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link LureStorage} is used as the runtime interface for loading and handling of {@link LureSavedData}.
 * Used mostly for long term storage.
 */
//@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LureStorage {

	/**
	 * Runtime variable that stores all long term data regarding Block Lure instances in all levels
	 */
	private static final Map<ServerLevel, LureSavedData> WORLD_DATA = new HashMap<>();

	/**
	 * Returns a List of all positions that a Block Lure instance exists in the given level
	 */
	protected static List<BlockPos> getBlockPosLures(ServerLevel level) {
		return WORLD_DATA.get(level).blockLureLocations;
	}

	/**
	 * Adds a Tracked Lure instance to a given level
	 *
	 * @param level The level on where this Block Lure instance exists
	 * @param pos   The position of this Block lure instance
	 */
	public static void addBlockLure(ServerLevel level, BlockPos pos) {
		LureSavedData data = WORLD_DATA.get(level);
		data.blockLureLocations.add(pos);
		data.setDirty();

		level.players().forEach(player -> SCPNetwork.NETWORK.sendTo(new CBLureDebugSync(data.blockLureLocations), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT));
	}

	/**
	 * Removes a Tracked Lure instance on a given level
	 *
	 * @param level The level on where this Block Lure instance existed
	 * @param pos   The position of this Block lure instance
	 */
	public static void removeBlockLure(ServerLevel level, BlockPos pos) {
		LureSavedData data = WORLD_DATA.get(level);
		data.blockLureLocations.remove(pos);
		data.setDirty();

		level.players().forEach(player -> SCPNetwork.NETWORK.sendTo(new CBLureDebugSync(data.blockLureLocations), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT));
	}

	//Blocks and their location
	//Entities that are holding a Lurable instance
	//Entities that are lures themselves (IE Item Drops)
	//Trapped Entities and what they are trapped by

	/**
	 * Loads the World Saved data into runtime data
	 */
	@SubscribeEvent
	public static void worldLoad(LevelEvent.Load event) {
		if (!(event.getLevel() instanceof ServerLevel level)) return;

        /*
        LureSavedData data = level.getDataStorage().computeIfAbsent(() -> {
            debug("Creating LureSavedData instance for {}", getWorldName(level));
            return new LureSavedData();
        }, LureSavedData.ID);


        validateData(level, data);

        WORLD_DATA.put(level, data);*/
		debug("Loaded {}", getWorldName(level));
	}

	/**
	 * Used to clear up the data map. Mostly for client saves when they change world.
	 */
	@SubscribeEvent
	public static void worldUnload(LevelEvent.Unload event) {
		if (!(event.getLevel() instanceof ServerLevel level)) return;
		WORLD_DATA.remove(level);
		debug("Unloaded {}", getWorldName(level));
	}

	/**
	 * Once the world has opened and data has been loaded from memory, this method checks to see if the saved lure
	 * positions are still valid, notifying of any discrepancies.
	 */
	private static void validateData(ServerLevel level, LureSavedData data) {
		//Loop through all loaded positions
		List<BlockPos> invalidPositions = new ArrayList<>();
		for (BlockPos pos : data.blockLureLocations) {
			if (!(level.getBlockState(pos).getBlock() instanceof IBlockLurable)) {
				invalidPositions.add(pos);
			}
		}

		if (!invalidPositions.isEmpty()) {
			warn("Lure Data Validation Warning for {}!", getWorldName(level));
			warn("Found {} invalid Block Lure positions from a total of {}!", invalidPositions.size(), data.blockLureLocations.size());
			warn("Invalid positions are listed below and will be removed.");
			invalidPositions.forEach(pos -> warn("\t{}", pos));
			data.blockLureLocations.removeAll(invalidPositions);
		} else {
			debug("Data Validation Successful!");
		}
	}

	/**
	 * Gets the dimension name and formats it
	 */
	private static String getWorldName(ServerLevel level) {
		return WordUtils.capitalize(level.dimension().location().getPath().replace("_", " "));
	}

	//Debug Loggers\\
	private static final Marker MARKER = MarkerFactory.getMarker("LureStorage");

	private static void debug(String message, Object... objects) {
		SCPLockdown.LOGGER.debug(MARKER, message, objects);
	}

	private static void info(String message, Object... objects) {
		SCPLockdown.LOGGER.info(MARKER, message, objects);
	}

	private static void warn(String message, Object... objects) {
		SCPLockdown.LOGGER.warn(MARKER, message, objects);
	}

	private static void err(String message, Object... objects) {
		SCPLockdown.LOGGER.error(MARKER, message, objects);
	}
}
