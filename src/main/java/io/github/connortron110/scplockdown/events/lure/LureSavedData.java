package io.github.connortron110.scplockdown.events.lure;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds Data per world that will be saved upon the closing of the world
 */
public class LureSavedData extends SavedData {

	//  FIXME: This files save method needs redoing as ID gets attatched during save (seems like all saved data goes to one area now)

	public static final String ID = "scpluredata";

	protected final List<BlockPos> blockLureLocations;
	//  TODO: Decide if we need to save entity lures

	public LureSavedData() {
		//super(ID);
		this.blockLureLocations = new ArrayList<>();
	}

//    @Override
//    public void load(CompoundTag nbt) {
//        this.blockLureLocations.addAll(Arrays.stream(nbt.getLongArray("BlockLures")).mapToObj(BlockPos::of).collect(Collectors.toList()));
//        debug("Unpacked {} Lure Positions from {}", this.blockLureLocations.size(), ID);
//    }

	@Override
	public CompoundTag save(CompoundTag nbt) {
		debug("Saving Lure Data");
		nbt.putLongArray("BlockLures", this.blockLureLocations.stream().mapToLong(BlockPos::asLong).toArray());

		return nbt;
	}

	private static final Marker MARKER = MarkerFactory.getMarker("LureSavedData");

	private static void debug(String message, Object... objects) {
		SCPLockdown.LOGGER.debug(MARKER, message, objects);
	}

	private static void warn(String message, Object... objects) {
		SCPLockdown.LOGGER.warn(MARKER, message, objects);
	}

	private static void err(String message, Object... objects) {
		SCPLockdown.LOGGER.error(MARKER, message, objects);
	}
}
