/*package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class SCP035ArmorModel extends BipedModel<LivingEntity> {

    private final SCP035MaskModel maskModel = new SCP035MaskModel();

    public SCP035ArmorModel(float p_i1148_1_, boolean isComedy) {
        super(p_i1148_1_);
        ModelRenderer mask = isComedy ? maskModel.comedy() : maskModel.tragedy();
        mask.zRot = (float) Math.PI;
        mask.y = 3F;
        mask.z = -4.5F;
        this.head.addChild(mask);
    }

    @Override
    public void renderToBuffer(MatrixStack pMatrixStack, IVertexBuilder pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        pMatrixStack.pushPose();
        this.head.translateAndRotate(pMatrixStack);
        maskModel.renderToBuffer(pMatrixStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
        pMatrixStack.popPose();
    }
}*/
