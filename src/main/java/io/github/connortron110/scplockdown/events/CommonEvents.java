package io.github.connortron110.scplockdown.events;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.events.hooks.IActionOnMining;
import io.github.connortron110.scplockdown.level.blocks.SCP330Block;
import io.github.connortron110.scplockdown.level.entity.IRequirePersistence;
import io.github.connortron110.scplockdown.network.SCPNetwork;
import io.github.connortron110.scplockdown.network.client.CBSCP330Sync;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID)
public class CommonEvents {
	//  TODO: Create the Q&A System

	@SubscribeEvent
	public static void entityConstructingPersistence(EntityEvent.EntityConstructing event) {
		if (event.getEntity() instanceof Mob mob && mob instanceof IRequirePersistence) {
			mob.setPersistenceRequired();
		}
	}

	//  FIXME isnt ticking only fired once
	@SubscribeEvent
	public static void playerInteractionEvent(PlayerInteractEvent.LeftClickBlock event) {
		if (event.getSide().isServer()) {
			Level level = event.getLevel();
			BlockPos blockPos = event.getPos();
			if (level.getBlockState(blockPos).getBlock() instanceof IActionOnMining iActionOnMining) {
				iActionOnMining.miningTick(level, blockPos, event.getEntity());
			}
		}
	}

	/**
	 * Server side only, currently used to sync specific persistent data to clients
	 */
	@SubscribeEvent
	public static void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
		CompoundTag playerTag = event.getEntity().getPersistentData();
		if (playerTag.contains(SCP330Block.CANDIES_KEY)) {
			SCPNetwork.NETWORK.send(PacketDistributor.ALL.noArg(), new CBSCP330Sync(event.getEntity().getUUID(), playerTag.getInt(SCP330Block.CANDIES_KEY), playerTag.getLong(SCP330Block.CANDIES_TIME_KEY)));
		}
	}
}
