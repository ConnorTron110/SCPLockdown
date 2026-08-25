package io.github.connortron110.scplockdown.level.effect;

import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import io.github.connortron110.scplockdown.level.entity.variants.VanillaToVariant;
import io.github.connortron110.scplockdown.registration.SCPEffects;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import io.github.connortron110.scplockdown.registration.SCPTags;
import io.github.connortron110.scplockdown.utils.Utils;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZombismEffect extends SCPEffect {
	public ZombismEffect(int pColor) {
		super(MobEffectCategory.HARMFUL, pColor, false, false);
	}

	@Override
	public void tick(LivingEntity living, int duration, int amplifier) {
		//If Entity cannot be infected with SCP008, Remove Effect (excluding players)
		if (!canBeInfected(living)) {
			living.removeEffect(this);
			return;
		}

		if (duration % 40 == 0) {
			//TODO Probably find a more efficient way for this to not cause any actual damage but still cause the sound and effect to play
			living.hurt(SCPDamageTypes.source(living.level(), SCPDamageTypes.SCP008ZOMBISM), 0.01F);
		}
	}

	@Override
	public void lastTick(LivingEntity living, int amplifier) {
		super.lastTick(living, amplifier);
		if (living.isInvulnerable())
			return; //Check for Entity invulnerability (Does not check for player invulnerability)
		if (living instanceof Mob mob) {
			if (!attemptKill(living))
				return; //Doesn't actually need to happen, but is good to check if entity actually dies
			//TODO Play some conversion sound
			SCP008Entity convertedEntity = mob.convertTo(getConversionTypeFromEntity(living), true);
			Utils.copyEntityRotationsToEntity(living, convertedEntity);
			if (convertedEntity instanceof SCPEntityVariant<?>) {
				//Check if the current entity is a variant as well (edge case for illagers)
				if (living instanceof SCPEntityVariant<?>) { //Used for scientists/Dclass Etc
					((SCPEntityVariant<?>) convertedEntity).setVariant(convertedEntity, ((SCPEntityVariant<?>) living).getVariantID(living));
				} else if (((SCPEntityVariant<?>) convertedEntity).getEnumVariantValues()[0] instanceof VanillaToVariant<?>) { //Used for vanilla entities
					((SCPEntityVariant<?>) convertedEntity).setVariant(convertedEntity, ((VanillaToVariant<Enum<?>>) ((SCPEntityVariant<?>) convertedEntity).getEnumVariantValues()[0]).getVariantFromEntity(living).ordinal());
				}
			}
		} else if (living instanceof Player player) {
			if (player.isInvulnerable() || !attemptKill(player))
				return; //Check if player is invulnerable and attempt to kill
			//SCP008PlayerEntity player008 = SCPEntities.SCP008_PLAYER.get().create(player.level());
			//player008.setPlayerUUID(player.getUUID());
			//player008.setPos(player.getX(), player.getY(), player.getZ());
			//Utils.copyEntityRotationsToEntity(player, player008);
			//player.level().addFreshEntity(player008);
		}
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return ONLY_005.get();
	}

	private boolean attemptKill(LivingEntity living) {
		//TODO (Hurt, Unreproducible????) Infinite death when absorption somehow becomes NaN
		//  Probably under really specific conditions, however this is how the kill command works so don't change
		//  Happened when getting killed with totem of undying
		return (living.hurt(SCPDamageTypes.source(living.level(), SCPDamageTypes.SCP008ZOMBISM), Float.MAX_VALUE) && living.isDeadOrDying()) || living.isDeadOrDying();
	}

	public static boolean canBeInfected(LivingEntity living) {
		return living.getType().is(SCPTags.Entity.SCP008_INFECTABLE) || living instanceof Player;
	}

	public static MobEffectInstance getDefaultInstance() {
		return new MobEffectInstance(SCPEffects.SCP008_ZOMBISM.get(), 60 * 20);
	}

	private static final Map<Lazy<EntityType<? extends Entity>>, Lazy<EntityType<? extends SCP008Entity>>> CONVERSION_MAP = new HashMap<>();

	static {
		//CONVERSION_MAP.put(Lazy.of(SCPEntities.DCLASS::get), Lazy.of(SCPEntities.SCP008_DCLASS::get));
		//CONVERSION_MAP.put(Lazy.of(SCPEntities.SCIENTIST::get), Lazy.of(SCPEntities.SCP008_SCIENTIST::get));
		CONVERSION_MAP.put(Lazy.of(SCPEntities.GUARD::get), Lazy.of(SCPEntities.SCP008_GUARD::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.VILLAGER), Lazy.of(SCPEntities.SCP008_VILLAGER::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.PILLAGER), Lazy.of(SCPEntities.SCP008_ILLAGER::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.VINDICATOR), Lazy.of(SCPEntities.SCP008_ILLAGER::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.EVOKER), Lazy.of(SCPEntities.SCP008_ILLAGER::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.ILLUSIONER), Lazy.of(SCPEntities.SCP008_ILLAGER::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.ENDERMAN), Lazy.of(SCPEntities.SCP008_ENDERMAN::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.PIGLIN), Lazy.of(SCPEntities.SCP008_PIGLIN::get));
		//CONVERSION_MAP.put(Lazy.of(() -> EntityType.PIGLIN_BRUTE), Lazy.of(SCPEntities.SCP008_PIGLIN_BRUTE::get));
	}

	private EntityType<? extends SCP008Entity> getConversionTypeFromEntity(LivingEntity living) {
		//TODO Allow for API or some other method for adding custom entities to get converted to their 008 counterparts
		//  FIXME Replace back with the Generic one
		return CONVERSION_MAP.getOrDefault(living.getType(), Lazy.of(SCPEntities.SCP008_GUARD::get)).get();
	}
}
