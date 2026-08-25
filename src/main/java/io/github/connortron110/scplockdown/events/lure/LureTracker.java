package io.github.connortron110.scplockdown.events.lure;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;

/**
 * {@link LureTracker} only handles the server side tracking of entities, no storage. So events that involve players coming
 * within range of a Lureable instance, logging in/out near one, teleporting away from one etc.
 * <p>
 * It will then modify {@link LureStorage} based on what it tracks, it also uses {@link LureStorage} to be able to determine if current trapped
 * entities should be released.
 */
//@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LureTracker {

	//ONLY HAS SHORT TERM STORAGE, NOT LONG TERM
	//SHORT TERM
	//  Any players that are holding lure items (attract other players) (Login checks and adds them on temp list if found with lure)
	//  What entities are currently trapped
	//LONG TERM (LureStorage)
	//  Where block lures are placed
	//  Any entities that are non player based that have a lure (Item entities??) (VERIFY SOLID METHOD TO STORE CORRECT RELIABLE DATA)

	/**
	 * Notified of any BlockLure Changes by {@link io.github.connortron110.scplockdown.mixin.MixinServerWorld#onBlockStateChange(BlockPos, BlockState, BlockState, CallbackInfo)}
	 * that occur in the world. This method then handles the addition or removal of that block and other processes that come along with it.
	 *
	 * @param level   The Level the change is taking place in.
	 * @param pos     The position of the block the change took place at.
	 * @param removed Determines if the Lure block at the given position was placed, or removed.
	 */
	public static void notifyBlockLureChange(ServerLevel level, BlockPos pos, boolean removed) {
		if (removed) {
			LureStorage.removeBlockLure(level, pos);
			//  A Block lure has been removed, now to remove all entities within blockLuredEntities that contains that block pos
			blockLuredEntities.entrySet().removeIf(entry -> entry.getValue().equals(pos));
		} else {
			LureStorage.addBlockLure(level, pos);
		}

		SCPLockdown.LOGGER.debug(removed ? "Lure Removed: {}" : "Lure Placed: {}", pos);
	}

	//  Instead of checking each entity if it has a lure instance by it. We check the lure instances instead, as there should (server context) be fewer lures then there are players.

	//  Runtime variables containing all entities effected by both variations of lure
	public static final HashMap<Entity, BlockPos> blockLuredEntities = new HashMap<>();
	public static final HashMap<Entity, Entity> entityLuredEntities = new HashMap<>();


	//This should not handle the lure, but instead keep track of who is nearby lures to then send to the appropriate lure to do whatever with the entity

	/**
	 * Handles putting entities into the {@link LureTracker#blockLuredEntities}
	 */
	@SubscribeEvent
	public static void worldTick(TickEvent.LevelTickEvent event) {
		//if (event.world.isClientSide) return;

		//  We only want to run this once per tick, so run at the end of a tick ready for the next one.
		if (event.phase != TickEvent.Phase.START) return;
		ServerLevel level = (ServerLevel) event.level;

		//  Remove entities that are now too far away from the block it was getting lured by
		blockLuredEntities.entrySet().removeIf(entry -> {
			Entity luredEntity = entry.getKey();
			BlockPos lureBlock = entry.getValue();

			if (luredEntity.position().distanceToSqr(Vec3.atCenterOf(lureBlock)) > 420) {
				debug("Removed {} from block lure @ {}", luredEntity.getName().getString(), lureBlock);
				//  TODO: Release the entity from the lure effects (give back control)
				return true;
			} else return false;
		});

		//  Find entities that are not being lured, and track them
		for (BlockPos blockLurePos : LureStorage.getBlockPosLures(level)) {
			AABB aabb = new AABB(blockLurePos).inflate(20);
			List<Entity> entitiesInRange = level.getEntities(null, aabb);

			//  Filter for living entities
			entitiesInRange.removeIf(entity -> !(entity instanceof LivingEntity));

			//Filter out entities in a non-circular radius
			entitiesInRange.removeIf(entity -> entity.position().distanceToSqr(Vec3.atCenterOf(blockLurePos)) > 400);

			//entitiesInRange now contains all entities within range of this block lure
			//Check if entity is already in range of a lure
			entitiesInRange.removeIf(entity -> blockLuredEntities.containsKey(entity) || entityLuredEntities.containsKey(entity));

			//  For entities that are NOT already being effected by another lure instance
			for (Entity entity : entitiesInRange) {
				blockLuredEntities.put(entity, blockLurePos);
				debug("Added {} now lured by Block @ {}", entity.getName().getString(), blockLurePos);
			}
		}
	}

	/**
	 * Called when a player leaves the world. Its intent is to remove this player from all tracking instances and to ensure that the player has control over their character after leaving
	 *//*
    @SubscribeEvent
    public static void entityLeaveEvent(EntityLeaveWorldEvent event) {
        //TODO Check if entity that left was a lurere, then remove all things that are lured by it and restore control
        Entity entity = event.getEntity();

        //  Packet to unlock player input, it's unlikely it will be received, so ClientEvents#clientLogOut() should handle this
        lockEntityInput(entity, false);

        BlockPos luringPos = blockLuredEntities.remove(entity);
        Entity luringEntity = entityLuredEntities.remove(entity);
        if (luringPos != null || luringEntity != null) {
            debug("Removed {} from all lure lists", entity.getName().getString());
            debug("Current list is:");
            blockLuredEntities.forEach((entity1, blockPos) -> debug(entity1.getName().getString()));
        }
    }
    */
	private static void lockEntityInput(Entity entity, boolean shouldLockInput) {
		if (entity instanceof RemotePlayer remotePlayer) {
			//SCPNetwork.NETWORK.sendTo(new CBRestrictPlayerInput(shouldLockInput), remotePlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}


	//Debug Loggers\\
	private static final Marker MARKER = MarkerFactory.getMarker("LureTracker");

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
