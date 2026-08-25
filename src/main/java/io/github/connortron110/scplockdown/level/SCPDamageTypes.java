package io.github.connortron110.scplockdown.level;

import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

/**
 * Class Containing all damage types and source providers the mod will use
 */
public class SCPDamageTypes {
	public static final ResourceKey<DamageType> SCP002CONSUME = register("scp002_consume");
	public static final ResourceKey<DamageType> SCP008ZOMBISM = register("scp008_zombism");
	public static final ResourceKey<DamageType> SCP009FREEZE = register("scp009_freeze");
	public static final ResourceKey<DamageType> SCP012CURSE = register("scp012_curse");
	public static final ResourceKey<DamageType> SCP015DEFENCE = register("scp015_defence");
	public static final ResourceKey<DamageType> SCP023EXPIRE = register("scp023_expire");
	public static final ResourceKey<DamageType> SCP035MASK = register("scp035_mask");
	public static final ResourceKey<DamageType> SCP053HEARTATTACK = register("scp053_heartattack");
	public static final ResourceKey<DamageType> SCP143PETAL = register("scp143_petal");
	public static final ResourceKey<DamageType> SCP330NOARMS = register("scp330_noarms");
	public static final ResourceKey<DamageType> SCP822EXPLODE = register("scp822_explosion");
	public static final ResourceKey<DamageType> SCP822TOXIN = register("scp822_toxin");

	private static ResourceKey<DamageType> register(String id) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, id));
	}

	/**
	 * Called from {@link io.github.connortron110.scplockdown.data.DataGenerators} to generate data driven values
	 */
	public static void damageTypesData(BootstapContext<DamageType> context) {
		damageTypeData(context, SCP002CONSUME);
		damageTypeData(context, SCP008ZOMBISM);
		damageTypeData(context, SCP009FREEZE);
		damageTypeData(context, SCP012CURSE, 0.1F);
		damageTypeData(context, SCP015DEFENCE);
		damageTypeData(context, SCP023EXPIRE);
		damageTypeData(context, SCP035MASK);
		damageTypeData(context, SCP053HEARTATTACK, 1.0F);
		damageTypeData(context, SCP143PETAL);
		damageTypeData(context, SCP330NOARMS, 1F);
		damageTypeData(context, SCP822EXPLODE, 0.2F);
		damageTypeData(context, SCP822TOXIN, 0.1F);
	}

	private static void damageTypeData(BootstapContext<DamageType> context, ResourceKey<DamageType> damageType, float exhaustion, DamageScaling scaling, DamageEffects effects) {
		context.register(damageType, new DamageType(damageType.location().getPath(), scaling, exhaustion, effects));
	}

	private static void damageTypeData(BootstapContext<DamageType> context, ResourceKey<DamageType> damageType, float exhaustion, DamageScaling scaling) {
		context.register(damageType, new DamageType(damageType.location().getPath(), scaling, exhaustion, DamageEffects.HURT));
	}

	private static void damageTypeData(BootstapContext<DamageType> context, ResourceKey<DamageType> damageType, float exhaustion) {
		context.register(damageType, new DamageType(damageType.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, exhaustion, DamageEffects.HURT));
	}

	private static void damageTypeData(BootstapContext<DamageType> context, ResourceKey<DamageType> damageType) {
		context.register(damageType, new DamageType(damageType.location().getPath(), DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.0F, DamageEffects.HURT));
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> damageType, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
		return new DamageSource(level.registryAccess().registry(Registries.DAMAGE_TYPE).orElseThrow().getHolderOrThrow(damageType), directEntity, causingEntity, damageSourcePosition);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> damageType, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
		return source(level, damageType, directEntity, causingEntity, null);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> damageType, @Nullable Entity directEntity) {
		return source(level, damageType, directEntity, null, null);
	}

	public static DamageSource source(Level level, ResourceKey<DamageType> damageType) {
		return source(level, damageType, null, null, null);
	}
}
