package io.github.connortron110.scplockdown.mixin;

import io.github.connortron110.scplockdown.level.entity.variants.SCPEntityVariant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin currently used to allow for multivariant entities to save their data, mostly so code doesnt repeat itself as extending classes cannot overlap
 */
@Mixin(Entity.class)
public abstract class MixinEntity {

	@Shadow
	protected abstract void readAdditionalSaveData(CompoundTag nbt);

	@Shadow
	protected abstract void addAdditionalSaveData(CompoundTag nbt);

	@Shadow
	@Final
	protected SynchedEntityData entityData;

	//@Shadow public abstract boolean updateInWaterStateAndDoFluidPushing(ITag<Fluid> pFluidTag, double pMotionScale);

	@Inject(at = @At(value = "TAIL"), method = "<init>")
	protected void initEntity(EntityType<?> pType, Level pLevel, CallbackInfo ci) {
		if (this instanceof SCPEntityVariant<?>) {
			SCPEntityVariant.createVariantDataParam(((Entity) ((Object) this)).getClass(), ((SCPEntityVariant<?>) this).getEnumVariantValues());
			this.entityData.define(((SCPEntityVariant<?>) this).getDataParameter(((Entity) ((Object) this)).getClass()), 0);
		}
	}

    /*
    @Inject(at = @At(value = "RETURN"), method = "updateInWaterStateAndDoFluidPushing", cancellable = true)
    protected void updateInWaterStateAndDoFluidPushing(CallbackInfoReturnable<Boolean> cir) {
        boolean inFountainFluid = updateInWaterStateAndDoFluidPushing(SCPTags.Fluids.SCP006_FOUNTAIN_FLUID, 0.0025D);
        cir.setReturnValue(cir.getReturnValue() || inFountainFluid);
    }

     */

	/**
	 * Appends the entity variant save data if the current entity that is being saved implements the variant interface
	 */
	@Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
	private void readAdditionalSaveData(Entity entity, CompoundTag nbt) {
		readAdditionalSaveData(nbt);

		//If the entity implements the Variant Interface then get the Variant ID
		if (entity instanceof SCPEntityVariant<?>) {
			((SCPEntityVariant<?>) entity).setVariant(entity, nbt.getInt("EntityVariant"));
		}
	}

	@Redirect(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
	private void addAdditionalSaveData(Entity entity, CompoundTag nbt) {
		addAdditionalSaveData(nbt);

		//If the entity implements the Variant Interface then store the Variant ID
		if (entity instanceof SCPEntityVariant<?>) {
			nbt.putInt("EntityVariant", ((SCPEntityVariant<?>) entity).getVariantID(entity));
		}
	}
}
