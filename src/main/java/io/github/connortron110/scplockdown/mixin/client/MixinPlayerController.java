/*package io.github.connortron110.scplockdown.mixin.client;

import io.github.connortron110.scplockdown.level.entity.HitboxEntity;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerController.class)
public abstract class MixinPlayerController {

	@Inject(at = @At("HEAD"), method = "attack")
	protected void attack(Player pPlayer, Entity pTargetEntity, CallbackInfo ci) {
		//  If we are hitting a hitbox entity, set it to the parent as most calculations that would take place doesn't on the hitbox entity
		if (pTargetEntity instanceof HitboxEntity<?> hitboxEntity) {
			pTargetEntity = hitboxEntity.getParent();   //  TODO Doesnt work?
		}
	}
}

 */
