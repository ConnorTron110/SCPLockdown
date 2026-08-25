package io.github.connortron110.scplockdown.events.setup;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraftforge.fml.common.Mod;

/**
 * Currently used to modify things which was not modifiable via mixins
 */
@Mod.EventBusSubscriber(modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvents {
    /*
    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        //Adds all hostiles Villagers should avoid
        ImmutableMap<EntityType<?>, Float> villagerHostilesImmutableMap = VillagerHostilesSensor.ACCEPTABLE_DISTANCE_FROM_HOSTILES;
        ImmutableMap.Builder<EntityType<?>, Float> villagerHostilesBuilder = new ImmutableMap.Builder<EntityType<?>, Float>().putAll(villagerHostilesImmutableMap.entrySet());
        //Float indicates distance when villager starts actively avoiding
        villagerHostilesBuilder.put(SCPEntities.SCP008_PLAYER.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_GENERIC.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_DCLASS.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_SCIENTIST.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_GUARD.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_VILLAGER.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_ILLAGER.get(), 8F);
        //Add Witch
        villagerHostilesBuilder.put(SCPEntities.SCP008_ENDERMAN.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_PIGLIN.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP008_PIGLIN_BRUTE.get(), 8F);
        //Add SCP 682 008 Boss thing

        villagerHostilesBuilder.put(SCPEntities.SCP049.get(), 8F);
        villagerHostilesBuilder.put(SCPEntities.SCP049_PLAYER.get(), 8F);

        VillagerHostilesSensor.ACCEPTABLE_DISTANCE_FROM_HOSTILES = villagerHostilesBuilder.build();
    }

     */
}
