package io.github.connortron110.scplockdown.level.entity.scp008;

import io.github.connortron110.scplockdown.level.effect.ZombismEffect;
import io.github.connortron110.scplockdown.level.entity.DClassEntity;
import io.github.connortron110.scplockdown.level.entity.GuardEntity;
import io.github.connortron110.scplockdown.level.entity.IRequirePersistence;
import io.github.connortron110.scplockdown.level.entity.ScientistEntity;
import io.github.connortron110.scplockdown.level.entity.ai.goal.HerdGoal;
import io.github.connortron110.scplockdown.level.entity.ai.goal.HurtByTargetAlertTypeGoal;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

/**
 * Holds the Base code that all SCP008 Instances use
 */
public class SCP008Entity extends Monster implements IRequirePersistence {

	public SCP008Entity(EntityType<? extends Monster> pType, Level pLevel) {
		super(pType, pLevel);

		setCanPickUpLoot(false); //Disables 008 instances from picking up anything

		((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);

		//Makes mob ignorant to sources of damage however does avoid them if safer path exists
		this.setPathfindingMalus(BlockPathTypes.LAVA, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, 1.0F);
		//this.setPathfindingMalus(BlockPathTypes.DANGER_CACTUS, 1.0F);
		//this.setPathfindingMalus(BlockPathTypes.DAMAGE_CACTUS, 1.0F);
	}

	/**
	 * This is overridden in the more special case classes, such as the brute and 682 for more unique attacks
	 */
	@Override
	protected void registerGoals() {
		registerBaseGoals();
	}

	private void registerBaseGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(1, new BreakDoorGoal(this, difficulty -> true));
		this.goalSelector.addGoal(2, new HerdGoal<>(this, SCP008Entity.class, 0.8D));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D, 0.5F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

		//Hurt > Player > Humanoid > Golems > Animal
		this.targetSelector.addGoal(1, (new HurtByTargetAlertTypeGoal<>(this, SCP008Entity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DClassEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ScientistEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GuardEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Raider.class, true)); //Covers all Raiders
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Animal.class, true)); //Should Cover All Animals
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean ret = super.doHurtTarget(entity);
		if (ret && entity instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entity;
			//If entity is dying, has the effect and is convertible, convert them
			if (living.isDeadOrDying() && living.hasEffect(SCPEffects.SCP008_ZOMBISM.get())) {
				SCPEffects.SCP008_ZOMBISM.get().lastTick(living, living.getEffect(SCPEffects.SCP008_ZOMBISM.get()).getAmplifier());
			}

			//TODO Add a "Bite" mark to the entity
			if (!living.hasEffect(SCPEffects.SCP008_ZOMBISM.get())) {
				living.addEffect(ZombismEffect.getDefaultInstance());
			}
		}
		return ret;
	}

	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
		//if (this.getType() == SCPEntities.SCP008_ENDERMAN.get()) {
		//    return 2.55F;
		//}
		return super.getStandingEyeHeight(pPose, pDimensions);
	}
}
