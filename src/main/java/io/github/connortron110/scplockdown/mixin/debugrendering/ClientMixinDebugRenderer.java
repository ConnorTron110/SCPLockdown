/*
package io.github.connortron110.scplockdown.mixin.debugrendering;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.client.renderer.debug.LockdownDebugRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRenderer.class)
public abstract class ClientMixinDebugRenderer {

    @Shadow public abstract void clear();

    @Unique private LockdownDebugRenderer SCP_Lockdown$lockdownDebugRenderer;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void initDebugRenderer(Minecraft minecraft, CallbackInfo ci) {
        SCP_Lockdown$lockdownDebugRenderer = new LockdownDebugRenderer(minecraft);
    }

    @Inject(at = @At("TAIL"), method = "render")
    public void render(PoseStack pMatrixStack, MultiBufferSource.BufferSource pBuffer, double pCamX, double pCamY, double pCamZ, CallbackInfo callbackInfo) {
        if (Minecraft.getInstance().options.renderDebug) {
            SCP_Lockdown$lockdownDebugRenderer.render(pMatrixStack, pBuffer, pCamX, pCamY, pCamZ);
        } else {
            clear();
        }
    }

    @Inject(at = @At("TAIL"), method = "clear()V")
    public void clear(CallbackInfo ci) {
        SCP_Lockdown$lockdownDebugRenderer.clear();
    }
}

 */
