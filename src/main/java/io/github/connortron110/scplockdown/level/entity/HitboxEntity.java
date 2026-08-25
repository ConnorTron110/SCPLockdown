package io.github.connortron110.scplockdown.level.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.entity.PartEntity;

public class HitboxEntity<E extends Entity> extends PartEntity<E> {

	private final EntityDimensions SIZE;

	public HitboxEntity(E parent, float width, float height) {
		super(parent);
		this.SIZE = EntityDimensions.scalable(width, height);
		refreshDimensions();
	}

	@Override
	protected void defineSynchedData() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag pCompound) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag pCompound) {

	}

	/**
	 * Documentation on this function is wrong. This determines if this entity can be ray-casted to. IE can the player interact with it by left/right click. NOT if entities can move through it...
	 *
	 * @return TRUE because we want the player to be able to hit this.
	 */
	public boolean isPickable() {
		return true;
	}

	/**
	 * Easiest most hacky way to redirect the attack.
	 * A better implementation would require mixining the players ray cast found in {@link net.minecraft.client.renderer.GameRenderer#pick(float)} server side has a different operation
	 * Basically if anyone complains about things not working on multi hitbox things, fix this first
	 */
	@Override
	public boolean hurt(DamageSource source, float amount) {
		return this.getParent().hurt(source, amount);
	}

	@Override
	public boolean is(Entity entity) {
		return this == entity || this.getParent() == entity;
	}

	@Override
	public EntityDimensions getDimensions(Pose pPose) {
		return SIZE;
	}
}
