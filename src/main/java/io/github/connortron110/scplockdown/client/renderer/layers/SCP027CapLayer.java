/*package io.github.connortron110.scplockdown.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.SCP027CapModel;
import io.github.connortron110.scplockdown.level.entity.SCP027Entity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class SCP027CapLayer<T extends SCP027Entity, M extends BipedModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation CAP_LOCATION = new ResourceLocation(SCPLockdown.MOD_ID, "textures/entity/scp027_cap.png");
    private final SCP027CapModel<T> CapModel = new SCP027CapModel<>();

    public SCP027CapLayer(IEntityRenderer<T, M> p_i50939_1_) {
        super(p_i50939_1_);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStack.pushPose();
        IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(livingEntity)));
        CapModel.Cap.xRot = getParentModel().head.xRot;
        CapModel.Cap.yRot = getParentModel().head.yRot;
        CapModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        CapModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, LivingRenderer.getOverlayCoords(livingEntity, 0.0F), 1, 1, 1, 1);
        matrixStack.popPose();
    }

    @Override
    protected ResourceLocation getTextureLocation(T entity) {
        return CAP_LOCATION;
    }
}

 */