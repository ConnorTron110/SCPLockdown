package io.github.connortron110.scplockdown.level.entity;

import com.google.common.collect.ImmutableList;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.utils.LockdownTextComponents;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class SCP049Entity extends Monster implements IRequirePersistence {

	private static final ImmutableList<MutableComponent> PHRASES = ImmutableList.of(
			LockdownTextComponents.SCP049_SPEECH_1,
			LockdownTextComponents.SCP049_SPEECH_2,
			LockdownTextComponents.SCP049_SPEECH_3,
			LockdownTextComponents.SCP049_SPEECH_4,
			LockdownTextComponents.SCP049_SPEECH_5,
			LockdownTextComponents.SCP049_SPEECH_6
	);
	private static final ImmutableList<MutableComponent> ATTACKED_PHRASES = ImmutableList.of(
			LockdownTextComponents.SCP049_ATTACKED_1,
			LockdownTextComponents.SCP049_ATTACKED_2
	);

	public SCP049Entity(EntityType<? extends Monster> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, DClassEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ScientistEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GuardEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Raider.class, true)); //Covers all Raiders
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, true));
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		boolean ret = super.doHurtTarget(entity);
		if (ret && entity instanceof LivingEntity living) {

			//  Give or refresh poison on hit
			living.addEffect(new MobEffectInstance(MobEffects.POISON, 60 * 20, 5));

			//  If entity has been killed by me put a zombie instance in their place
			if (living.isDeadOrDying()) {
				if (living instanceof Player player) {
					SCP049PlayerEntity player049 = SCPEntities.SCP049_PLAYER.get().create(player.level());
					player049.setPlayerUUID(player.getUUID());
					player049.setPos(player.getX(), player.getY(), player.getZ());
					Utils.copyEntityRotationsToEntity(player, player049);
					player.level().addFreshEntity(player049);
				}
			}
		}
		return ret;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		boolean ret = super.hurt(source, amount);

		//  Send message to player if attack was successful, and they are not in creative mode to say something like "do not resist"
		if (ret && !level().isClientSide && source.getEntity() != null && source.getEntity() instanceof Player player && !player.isCreative()) {
			player.sendSystemMessage(ATTACKED_PHRASES.get(random.nextInt(ATTACKED_PHRASES.size())));
		}

		return ret;
	}

	@Nullable
	private LivingEntity lastTarget;

	@Override
	public void tick() {
		super.tick();

		//  Hacky way to get speech to work correctly as set target is called multiple times
		if (!level().isClientSide) {
			if (lastTarget != getTarget()) {
				lastTarget = getTarget();
				if (getTarget() instanceof Player player && !level().isClientSide) {
					player.sendSystemMessage(PHRASES.get(random.nextInt(PHRASES.size())));
				}
			}
		}
	}

	@Override
	protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
		return 1.74F;
	}
}
