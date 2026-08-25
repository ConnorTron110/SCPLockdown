/*package io.github.connortron110.scplockdown.registration.holders;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.SCPCreativeTabs;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.connortron110.scplockdown.registration.SCPFluids.FLUIDS;

/**
 * Holds all data regarding the registry of a fluid
 *//*
public class FluidRegistryHolder {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    private RegistryObject<FlowingFluid> SOURCE;
    private RegistryObject<FlowingFluid> FLOWING;
    private RegistryObject<FlowingFluidBlock> BLOCK;
    private RegistryObject<BucketItem> BUCKET;
    private final ForgeFlowingFluid.Properties PROPERTIES;

    //TODO: Add more customizability in terms of Block attributes
    private FluidRegistryHolder(String name, PropertiesBuilder properties) {
        SOURCE = RegistryObject.of(rl(name + "_still"), ForgeRegistries.FLUIDS);
        FLOWING = RegistryObject.of(rl(name + "_flowing"), ForgeRegistries.FLUIDS);
        BLOCK = RegistryObject.of(rl(name), ForgeRegistries.BLOCKS);
        BUCKET = RegistryObject.of(rl(name + "_bucket"), ForgeRegistries.ITEMS);

        ForgeFlowingFluid.Properties finalProperties = new ForgeFlowingFluid.Properties(getSource(), getFlowing(), properties.attributes).block(getBlock()).bucket(getBucket());
        copyProperties(properties, finalProperties);
        PROPERTIES = finalProperties;

        SOURCE = FLUIDS.register(path(SOURCE), () -> new ForgeFlowingFluid.Source(PROPERTIES));
        FLOWING = FLUIDS.register(path(FLOWING), () -> new ForgeFlowingFluid.Flowing(PROPERTIES));

        BLOCK = SCPBlocks.registerNoItem(path(BLOCK), () -> new FlowingFluidBlock(() -> SOURCE.get(), AbstractBlock.Properties.of(Material.WATER)
                .noCollission().strength(100).noDrops()));

        BUCKET = SCPItems.ITEMS.getRegister().register(path(BUCKET), () -> new BucketItem(() -> SOURCE.get(), new Item.Properties().tab(SCPCreativeTabs.TAB_SCP_ITEMS)));
    }

    private static ResourceLocation rl(String s) {
        return new ResourceLocation(SCPLockdown.MOD_ID, s);
    }

    private static String path(RegistryObject<?> object) {
        return object.getId().getPath();
    }

    public RegistryObject<FlowingFluid> getSource() {
        return SOURCE;
    }

    public RegistryObject<FlowingFluid> getFlowing() {
        return FLOWING;
    }

    public RegistryObject<FlowingFluidBlock> getBlock() {
        return BLOCK;
    }

    public RegistryObject<BucketItem> getBucket() {
        return BUCKET;
    }

    private static void copyProperties(PropertiesBuilder from, ForgeFlowingFluid.Properties to) {
        if (from.canMultiply) to.canMultiply();
        to.slopeFindDistance(from.slopeFindDistance);
        to.levelDecreasePerBlock(from.levelDecreasePerBlock);
        to.explosionResistance(from.explosionResistance);
        to.tickRate(from.tickRate);
    }

    public static FluidRegistryHolder registerFluid(String name, PropertiesBuilder properties) {
        return new FluidRegistryHolder(name, properties);
    }

    //Property builders

    public static PropertiesBuilder properties(FluidAttributes.Builder attributes) {
        return new PropertiesBuilder(attributes);
    }

    public static FluidAttributes.Builder attributes(ResourceLocation stillRL, ResourceLocation flowingRL) {
        return FluidAttributes.builder(stillRL, flowingRL);
    }

    //Reference ForgeFlowingFluid.Properties
    public static class PropertiesBuilder {
        public FluidAttributes.Builder attributes;
        public boolean canMultiply;
        public int slopeFindDistance = 4;
        public int levelDecreasePerBlock = 1;
        public float explosionResistance = 1;
        public int tickRate = 5;

        public PropertiesBuilder(FluidAttributes.Builder builder) {
            attributes = builder;
        }

        public PropertiesBuilder canMultiply() {
            canMultiply = true;
            return this;
        }

        public PropertiesBuilder slopeFindDistance(int slopeFindDistance) {
            this.slopeFindDistance = slopeFindDistance;
            return this;
        }

        public PropertiesBuilder levelDecreasePerBlock(int levelDecreasePerBlock) {
            this.levelDecreasePerBlock = levelDecreasePerBlock;
            return this;
        }

        public PropertiesBuilder explosionResistance(float explosionResistance) {
            this.explosionResistance = explosionResistance;
            return this;
        }

        public PropertiesBuilder tickRate(int tickRate) {
            this.tickRate = tickRate;
            return this;
        }
    }

    /*
    if (fluid instanceof WaterFluid)
            return net.minecraftforge.fluids.FluidAttributes.Water.builder(
                    new ResourceLocation("block/water_still"),
                    new ResourceLocation("block/water_flow"))
                    .overlay(new ResourceLocation("block/water_overlay"))
                    .translationKey("block.minecraft.water")
                    .color(0xFF3F76E4)
                    .sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY)
                    .build(fluid);
     *//*
}
*/
