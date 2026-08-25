package io.github.connortron110.scplockdown.level.entity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.PartEntity;

import java.util.Objects;

//  TODO: Migrate the Multipart entity into something that's easier to implement
public class SCP939Entity extends Monster implements IRequirePersistence {

	private final HitboxEntity<?> HEAD_HITBOX;
	private final HitboxEntity<?>[] SUB_ENTITIES;

	public SCP939Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		setCanPickUpLoot(false);

		this.SUB_ENTITIES = new HitboxEntity[]{
				HEAD_HITBOX = new HitboxEntity<>(this, 1, 0.75F)
		};

		//  For each entity part, get and add all IDS that this entity and its part will use (The entity takes the first available ID and the sub entities take increments)
		this.setId(ENTITY_COUNTER.getAndAdd(this.SUB_ENTITIES.length + 1) + 1);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 0.5F));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
		return 1.3F;
	}

	//  Below this is all to do with the multipart entity

	@Override
	public void setId(int id) {
		super.setId(id);
		for (int i = 0; i < Objects.requireNonNull(this.getParts()).length; i++) {
			getParts()[i].setId(this.getId() + i + 1);
		}
	}

	@Override
	public void baseTick() {
		super.baseTick();
		updateHitboxes();
	}

	private void updateHitboxes() {
		float centerOfEyes = getEyeHeight() - (HEAD_HITBOX.getBbHeight() / 2);
		float rotationToNormalised = (float) ((getYRot() * 2 * Math.PI) / 360);
		float radius = 1F;
		HEAD_HITBOX.moveTo(getX() + (radius * -Mth.sin(rotationToNormalised)), getY() + centerOfEyes, getZ() + (radius * Mth.cos(rotationToNormalised)));
	}

	@Override
	public boolean isMultipartEntity() {
		return true;
	}

	@Override
	public PartEntity<?>[] getParts() {
		return this.SUB_ENTITIES;
	}
}
