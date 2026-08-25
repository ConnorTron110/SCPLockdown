/*package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class HumanModel<E extends LivingEntity> extends EntityModel<E> {
    private final ModelRenderer Torso;
    private final ModelRenderer Head;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LowerRightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LowerLeftLeg;
    private final ModelRenderer RightArm;
    private final ModelRenderer LowerRightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LowerLeftArm;

    public HumanModel(boolean slim) {
        texWidth = 64;
        texHeight = 64;

        Torso = new ModelRenderer(this);
        Torso.setPos(0.0F, 0.0F, 0.0F);
        Torso.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(1.9F, 12.0F, 0.0F);
        RightLeg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        LowerRightLeg = new ModelRenderer(this);
        LowerRightLeg.setPos(0.0F, 5.8F, 0.0F);
        RightLeg.addChild(LowerRightLeg);
        LowerRightLeg.texOffs(0, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(-1.9F, 12.0F, 0.0F);
        LeftLeg.texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        LowerLeftLeg = new ModelRenderer(this);
        LowerLeftLeg.setPos(0.0F, 5.8F, 0.0F);
        LeftLeg.addChild(LowerLeftLeg);
        LowerLeftLeg.texOffs(16, 37).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        if (slim) {
            RightArm = new ModelRenderer(this);
            RightArm.setPos(5.0F, 2.0F, 0.0F);
            RightArm.texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);

            LowerRightArm = new ModelRenderer(this);
            LowerRightArm.setPos(0.5F, 3.8F, 0.0F);
            RightArm.addChild(LowerRightArm);
            LowerRightArm.texOffs(40, 26).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);

            LeftArm = new ModelRenderer(this);
            LeftArm.setPos(-5.0F, 2.0F, 0.0F);
            LeftArm.texOffs(32, 48).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);

            LowerLeftArm = new ModelRenderer(this);
            LowerLeftArm.setPos(-0.5F, 3.8F, 0.0F);
            LeftArm.addChild(LowerLeftArm);
            LowerLeftArm.texOffs(32, 37).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, false);
        } else {
            RightArm = new ModelRenderer(this);
            RightArm.setPos(5.0F, 2.0F, 0.0F);
            RightArm.texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

            LowerRightArm = new ModelRenderer(this);
            LowerRightArm.setPos(1.0F, 3.8F, 0.0F);
            RightArm.addChild(LowerRightArm);
            LowerRightArm.texOffs(40, 26).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

            LeftArm = new ModelRenderer(this);
            LeftArm.setPos(-5.0F, 2.0F, 0.0F);
            LeftArm.texOffs(32, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

            LowerLeftArm = new ModelRenderer(this);
            LowerLeftArm.setPos(-1.0F, 3.8F, 0.0F);
            LeftArm.addChild(LowerLeftArm);
            LowerLeftArm.texOffs(32, 37).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        }
    }

    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float HeadPitch) {
        ClientUtils.humanoidAnim(entity, this, this.Head, this.Torso, this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, HeadPitch);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}*/