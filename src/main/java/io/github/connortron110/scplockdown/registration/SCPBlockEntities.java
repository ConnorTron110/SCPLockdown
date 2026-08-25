package io.github.connortron110.scplockdown.registration;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.blockentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SCPBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SCPLockdown.MOD_ID);

	public static final RegistryObject<BlockEntityType<CardReaderBlockEntity>> CARD_READER = BLOCK_ENTITIES.register(
			"card_reader", () -> BlockEntityType.Builder.of(CardReaderBlockEntity::new, SCPBlocks.CARD_READER.get()).build(null));

	public static final RegistryObject<BlockEntityType<SCP035CaseBlockEntity>> SCP035_CASE = BLOCK_ENTITIES.register(
			"scp035_case", () -> BlockEntityType.Builder.of(SCP035CaseBlockEntity::new, SCPBlocks.SCP035_GLASS_CASE.get()).build(null));

	public static final RegistryObject<BlockEntityType<SCP914BlockEntity>> SCP914 = BLOCK_ENTITIES.register(
			"scp914", () -> BlockEntityType.Builder.of(SCP914BlockEntity::new, SCPBlocks.SCP914.get()).build(null));

	public static final RegistryObject<BlockEntityType<CrateBlockEntity>> CRATE = BLOCK_ENTITIES.register(
			"crate", () -> BlockEntityType.Builder.of(CrateBlockEntity::new, SCPBlocks.WOOD_CRATE.get(), SCPBlocks.DARK_WOOD_CRATE.get()).build(null));

	public static final RegistryObject<BlockEntityType<ComputerBlockEntity>> COMPUTER = BLOCK_ENTITIES.register(
			"computer", () -> BlockEntityType.Builder.of(ComputerBlockEntity::new, SCPBlocks.COMPUTER.get(), SCPBlocks.PERSONAL_COMPUTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<LockerBlockEntity>> LOCKER = BLOCK_ENTITIES.register(
			"locker", () -> BlockEntityType.Builder.of(LockerBlockEntity::new, SCPBlocks.LOCKER.get()).build(null));

	public static final RegistryObject<BlockEntityType<SlidingDoorBlockEntity>> SLIDING_DOOR = BLOCK_ENTITIES.register(
			"sliding_door", () -> BlockEntityType.Builder.of(SlidingDoorBlockEntity::new, SCPBlocks.CONTAINMENT_DOOR.get(), SCPBlocks.SLIDING_DOOR.get(), SCPBlocks.MAGNETIZED_DOOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<BlastDoorBlockEntity>> BLAST_DOOR = BLOCK_ENTITIES.register(
			"blast_door", () -> BlockEntityType.Builder.of(BlastDoorBlockEntity::new, SCPBlocks.BLAST_DOOR.get()).build(null));
}
