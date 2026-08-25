/*package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.connortron110.scplockdown.level.entity.SCP027Entity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SCP027CapModel<E extends SCP027Entity> extends EntityModel<E> {

    public final ModelRenderer Cap;

    public SCP027CapModel() {
        texWidth = 32;
        texHeight = 32;

        Cap = new ModelRenderer(this);
        Cap.setPos(0.0F, 0.0F, 0.0F);
        Cap.texOffs(0, 0).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(MatrixStack poseStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Cap.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}*/