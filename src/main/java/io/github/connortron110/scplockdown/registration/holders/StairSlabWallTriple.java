package io.github.connortron110.scplockdown.registration.holders;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Triple;

public class StairSlabWallTriple extends Triple<RegistryObject<StairBlock>, RegistryObject<SlabBlock>, RegistryObject<WallBlock>> {

	private final RegistryObject<StairBlock> stairs;
	private final RegistryObject<SlabBlock> slab;
	private final RegistryObject<WallBlock> wall;

	public StairSlabWallTriple(RegistryObject<StairBlock> stairs, RegistryObject<SlabBlock> slab, RegistryObject<WallBlock> wall) {
		this.stairs = stairs;
		this.slab = slab;
		this.wall = wall;
	}


	public RegistryObject<StairBlock> getStairs() {
		return stairs;
	}

	public RegistryObject<SlabBlock> getSlab() {
		return slab;
	}

	public RegistryObject<WallBlock> getWall() {
		return wall;
	}

	@Override
	public RegistryObject<StairBlock> getLeft() {
		return getStairs();
	}

	@Override
	public RegistryObject<SlabBlock> getMiddle() {
		return getSlab();
	}

	@Override
	public RegistryObject<WallBlock> getRight() {
		return getWall();
	}
}
