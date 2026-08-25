package io.github.connortron110.scplockdown.level.effect;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * While {@link net.minecraft.world.effect.MobEffectInstance} now allows us to have full control over some parameters like infinite duration and visibility.
 * This class allows registration objects to restrict the effect to certain parameters. EG Vermin god effects are always Infinite.
 * It also further refines ticking of effects to allow more control.
 */
public abstract class SCPEffect extends MobEffect {

	//  Curative Item Lists
	protected static final Lazy<List<ItemStack>> EMPTY_LIST = Lazy.of(Lists::newArrayList);
	protected static final Lazy<List<ItemStack>> ONLY_005 = Lazy.of(() -> Lists.newArrayList(SCPItems.SCP500.getDefaultInstance()));

	//  Overriding parameters on MobEffectInstances
	private final boolean Visible;
	private final boolean InfiniteDuration;

	protected SCPEffect(MobEffectCategory pCategory, int pColor) {
		this(pCategory, pColor, true, false);
	}

	protected SCPEffect(MobEffectCategory pCategory, int pColor, boolean isVisible, boolean isInfinite) {
		super(pCategory, pColor);
		this.Visible = isVisible;
		this.InfiniteDuration = isInfinite;
	}

	/**
	 * Called by {@link io.github.connortron110.scplockdown.mixin.MixinMobEffectInstance} to override instance parameters<br>
	 * Determines if particles should be visible.
	 */
	public boolean isVisible() {
		return this.Visible;
	}

	/**
	 * Called by {@link io.github.connortron110.scplockdown.mixin.MixinMobEffectInstance} to override instance parameters<br>
	 * Determines if the effect instance is infinite.
	 */
	public boolean isDurationInfinite() {
		return this.InfiniteDuration;
	}

	/**
	 * Apply effect tick does exist, however it does not include the duration left on the instance.
	 */
	public abstract void tick(LivingEntity living, int duration, int amplifier);

	/**
	 * Not abstracted as not all effects will need a last tick event
	 */
	public void lastTick(LivingEntity living, int amplifier) {
		tick(living, 1, amplifier);
	}

    /*
    protected boolean isEntityInvulnerable(LivingEntity living) {
        return !EntityPredicates.ATTACK_ALLOWED.test(living) || !EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(living);
    }

 */

	/**
	 * Only gets called if {@link MobEffect#isInstantenous()} returns true<br>
	 * Overwritten to avoid running redundant code.
	 */
	@Override
	public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, @Nonnull LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
	}

	/**
	 * List of items that can cure this effect,
	 * As of currently All effects should not be curable by milk, but only by SCP-500
	 */
	@Override
	public List<ItemStack> getCurativeItems() {
		return EMPTY_LIST.get();
	}
}
