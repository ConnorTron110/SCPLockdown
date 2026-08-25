/*package io.github.connortron110.scplockdown.client.renderer.debug;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;

public class LockdownDebugRenderer {
    public final LureDebugRenderer lureDebugRenderer;

    public LockdownDebugRenderer(Minecraft minecraft) {
        this.lureDebugRenderer = new LureDebugRenderer(minecraft);
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource.BufferSource pBuffer, double pCamX, double pCamY, double pCamZ) {
        lureDebugRenderer.render(pMatrixStack, pBuffer, pCamX, pCamY, pCamZ);
    }

    public void clear() {
        lureDebugRenderer.clear();
    }

    public static void renderFilledBlockPos(PoseStack poseStack, MultiBufferSource buffer, BlockPos pos, float red, float green, float blue, float opacity) {
        DebugRenderer.renderFilledBox(poseStack, buffer, pos, 0.05F, red, green, blue, opacity);
    }
}

 */
