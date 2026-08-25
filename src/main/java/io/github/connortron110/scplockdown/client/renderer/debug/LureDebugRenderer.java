/*package io.github.connortron110.scplockdown.client.renderer.debug;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class LureDebugRenderer implements DebugRenderer.SimpleDebugRenderer {

    private final Minecraft minecraft;

    private static final List<BlockPos> blockLurePositions = new ArrayList<>();

    public static void addBlockLurePositions(List<BlockPos> positions) {
        blockLurePositions.clear();
        blockLurePositions.addAll(positions);
    }

    protected LureDebugRenderer(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, double pCamX, double pCamY, double pCamZ) {

        poseStack.pushPose();
        RenderSystem.enableBlend(); //Enables transparency
        RenderSystem.depthMask(false);
        //RenderSystem.disableTexture(); //Prevents them being invisible (since they have no texture UV)
        RenderSystem.lineWidth(4);

        //Draw boxes around all blocklure instances
        for (BlockPos blockLurePos : blockLurePositions) {

            LockdownDebugRenderer.renderFilledBlockPos(poseStack, buffer, blockLurePos, 1, 0, 0, 0.5F);


            for (Entity entity : minecraft.level.getEntities(null, new AABB(blockLurePos).inflate(20))) {
                Vec3 projectedView = minecraft.gameRenderer.getMainCamera().getPosition().reverse();
                Vec3 blockPos = Vec3.atCenterOf(new Vec3i(blockLurePos.getX(), blockLurePos.getY(), blockLurePos.getZ()));
                Vec3 entityPosition = entity.getPosition(minecraft.getFrameTime()).add(0, entity.getBbHeight()/2, 0);
                if (entityPosition.distanceTo(blockPos) >= 10) continue;

                drawLine(blockPos.add(projectedView), entityPosition.add(projectedView), 255, 0, 0);
            }


        }

        //  Hacky method to show all effected entities, gaurenteed to not be accurate
//        LureTracker.blockLuredEntities.forEach((entity, lurePos) -> {
//            Vector3d projectedView = minecraft.gameRenderer.getMainCamera().getPosition().reverse();
//            Vector3d blockPos = Vector3d.atCenterOf(new Vector3i(lurePos.getX(), lurePos.getY(), lurePos.getZ()));
//            Vector3d entityPosition = entity.getPosition(minecraft.getFrameTime()).add(0, entity.getBbHeight()/2, 0);
//            drawLine(blockPos.add(projectedView), entityPosition.add(projectedView), 255, 0, 0);
//        });

        //RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        poseStack.popPose();
    }

    private void drawLine(Vec3 start, Vec3 end, int r, int g, int b) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuilder();
        bufferBuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(start.x, start.y, start.z).color(r, g, b, 255).endVertex();
        bufferBuilder.vertex(end.x, end.y, end.z).color(r, g, b, 255).endVertex();
        tessellator.end();
    }

    private void renderHitOutline(MatrixStack pMatrixStack, IVertexBuilder pBuffer, Entity pEntity, double pX, double pY, double pZ, BlockPos pBlockPos, BlockState pBlockState, World level) {
        renderShape(pMatrixStack, pBuffer, pBlockState.getShape(level, pBlockPos, ISelectionContext.of(pEntity)), (double)pBlockPos.getX() - pX, (double)pBlockPos.getY() - pY, (double)pBlockPos.getZ() - pZ, 0.0F, 0.0F, 0.0F, 0.4F);
    }

    private static void renderShape(MatrixStack pMatrixStack, IVertexBuilder pBuffer, VoxelShape pShape, double pX, double pY, double pZ, float pRed, float pGreen, float pBlue, float pAlpha) {
        Matrix4f matrix4f = pMatrixStack.last().pose();
        pShape.forAllEdges((p_230013_12_, p_230013_14_, p_230013_16_, p_230013_18_, p_230013_20_, p_230013_22_) -> {
            pBuffer.vertex(matrix4f, (float)(p_230013_12_ + pX), (float)(p_230013_14_ + pY), (float)(p_230013_16_ + pZ)).color(pRed, pGreen, pBlue, pAlpha).endVertex();
            pBuffer.vertex(matrix4f, (float)(p_230013_18_ + pX), (float)(p_230013_20_ + pY), (float)(p_230013_22_ + pZ)).color(pRed, pGreen, pBlue, pAlpha).endVertex();
        });
    }


    public static void renderFloatingText(String text, int centerOfX, int centerOfY, int centerOfZ, int colorInt) {
        renderFloatingText(text, (double)centerOfX + 0.5D, (double)centerOfY + 0.5D, (double)centerOfZ + 0.5D, colorInt, 0.02F);
    }

    public static void renderFloatingText(String text, double x, double y, double z, int colorInt, float pScale) {
        renderFloatingText(text, x, y, z, colorInt, pScale, true, 0.0F, false);
    }

    public static void renderFloatingText(String text, double x, double y, double z, int pColor, float pScale, boolean p_217734_9_, float p_217734_10_, boolean renderOnTop) {
        Minecraft minecraft = Minecraft.getInstance();
        ActiveRenderInfo activerenderinfo = minecraft.gameRenderer.getMainCamera();
        if (activerenderinfo.isInitialized() && minecraft.getEntityRenderDispatcher().options != null) {
            FontRenderer fontrenderer = minecraft.font;
            double d0 = activerenderinfo.getPosition().x;
            double d1 = activerenderinfo.getPosition().y;
            double d2 = activerenderinfo.getPosition().z;
            RenderSystem.pushMatrix();
            RenderSystem.translatef((float)(x - d0), (float)(y - d1) + 0.07F, (float)(z - d2));
            RenderSystem.normal3f(0.0F, 1.0F, 0.0F);
            RenderSystem.multMatrix(new Matrix4f(activerenderinfo.rotation()));
            RenderSystem.scalef(pScale, -pScale, pScale);
            RenderSystem.enableTexture();
            if (renderOnTop) {
                RenderSystem.disableDepthTest();
            } else {
                RenderSystem.enableDepthTest();
            }

            RenderSystem.depthMask(true);
            RenderSystem.scalef(-1.0F, 1.0F, 1.0F);
            float f = p_217734_9_ ? (float)(-fontrenderer.width(text)) / 2.0F : 0.0F;
            f = f - p_217734_10_ / pScale;
            RenderSystem.enableAlphaTest();
            IRenderTypeBuffer.Impl irendertypebuffer$impl = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            fontrenderer.drawInBatch(text, f, 0.0F, pColor, false, TransformationMatrix.identity().getMatrix(), irendertypebuffer$impl, renderOnTop, 0, 15728880);
            irendertypebuffer$impl.endBatch();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableDepthTest();
            RenderSystem.popMatrix();
        }
    }

    //Clears Memory in preparation for new world
    public void clear() {

    }
}
 */
