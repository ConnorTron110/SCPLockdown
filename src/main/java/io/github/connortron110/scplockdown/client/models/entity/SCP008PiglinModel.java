/*package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008Entity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class SCP008PiglinModel extends EntityModel<SCP008Entity> {
    private final ModelRenderer Body;
    private final ModelRenderer head;
    private final ModelRenderer leftear;
    private final ModelRenderer rightear;
    private final ModelRenderer hat;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftArm_r1;
    private final ModelRenderer bone;
    private final ModelRenderer lefthand_r1;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;

    public SCP008PiglinModel() {
        texWidth = 64;
        texHeight = 64;

        Body = new ModelRenderer(this);
        Body.setPos(0.0F, 0.0F, 0.0F);
        Body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        setRotationAngle(head, 0.5603F, -0.0934F, 0.1476F);
        head.texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, -0.02F, false);
        head.texOffs(31, 1).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        head.texOffs(2, 4).addBox(2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(2, 0).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        leftear = new ModelRenderer(this);
        leftear.setPos(5.0F, -6.0F, 0.0F);
        head.addChild(leftear);
        setRotationAngle(leftear, 0.0F, 0.0F, -0.5236F);
        leftear.texOffs(51, 6).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

        rightear = new ModelRenderer(this);
        rightear.setPos(-5.0F, -6.0F, 0.0F);
        head.addChild(rightear);
        setRotationAngle(rightear, 0.0F, 0.0F, 0.5236F);
        rightear.texOffs(39, 6).addBox(0.0F, 0.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        hat = new ModelRenderer(this);
        hat.setPos(0.0F, 0.0F, 0.0F);
        head.addChild(hat);


        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        setRotationAngle(RightArm, -1.4399F, 0.0F, 0.0F);
        RightArm.texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, 0.0F);


        LeftArm_r1 = new ModelRenderer(this);
        LeftArm_r1.setPos(-5.0F, 22.0F, 0.0F);
        LeftArm.addChild(LeftArm_r1);
        setRotationAngle(LeftArm_r1, -0.0436F, 0.0F, 0.0F);
        LeftArm_r1.texOffs(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setPos(2.0F, 2.0F, 0.0F);
        LeftArm.addChild(bone);
        setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
        bone.texOffs(55, 52).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        lefthand_r1 = new ModelRenderer(this);
        lefthand_r1.setPos(1.0F, 7.0F, -1.0F);
        bone.addChild(lefthand_r1);
        setRotationAngle(lefthand_r1, -0.0436F, 0.0F, 0.0F);
        lefthand_r1.texOffs(32, 52).addBox(-4.0F, -5.0F, 0.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-1.9F, 12.0F, 0.0F);
        RightLeg.texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(1.9F, 12.0F, 0.0F);
        LeftLeg.texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
        LeftLeg.texOffs(55, 53).addBox(-0.9F, 5.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(SCP008Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float HeadPitch) {
        boolean flag = entity.getFallFlyingTicks() > 4;
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.head.xRot = (-(float)Math.PI / 4F);
        } else {
            this.head.xRot = HeadPitch * ((float)Math.PI / 180F);
        }

        float f = 1.0F;
        if (flag) {
            f = (float)entity.getDeltaMovement().lengthSqr();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.RightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.RightArm.zRot = 0.0F;
        this.LeftArm.zRot = 0.0F;
        this.RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.RightLeg.yRot = 0.0F;
        this.LeftLeg.yRot = 0.0F;
        this.RightLeg.zRot = 0.0F;
        this.LeftLeg.zRot = 0.0F;
        if (this.riding) {
            this.RightArm.xRot += (-(float)Math.PI / 5F);
            this.LeftArm.xRot += (-(float)Math.PI / 5F);
            this.RightLeg.xRot = -1.4137167F;
            this.RightLeg.yRot = ((float)Math.PI / 10F);
            this.RightLeg.zRot = 0.07853982F;
            this.LeftLeg.xRot = -1.4137167F;
            this.LeftLeg.yRot = (-(float)Math.PI / 10F);
            this.LeftLeg.zRot = -0.07853982F;
        }

        this.RightArm.yRot = 0.0F;
        this.LeftArm.yRot = 0.0F;
        this.setupAttackAnimation(entity, ageInTicks);
    }

    protected void setupAttackAnimation(SCP008Entity entity, float ageInTicks) {
        if (!(this.attackTime <= 0.0F)) {
            HandSide handside = this.getAttackArm(entity);
            ModelRenderer modelrenderer = this.getArm(handside);
            float f = this.attackTime;
            this.Body.yRot = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.Body.yRot *= -1.0F;
            }

            this.RightArm.yRot += this.Body.yRot;
            this.LeftArm.yRot += this.Body.yRot;
            this.LeftArm.xRot += this.Body.yRot;
            f = 1.0F - this.attackTime;
            f = f * f;
            f = f * f;
            f = 1.0F - f;
            float f1 = MathHelper.sin(f * (float)Math.PI);
            float f2 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            modelrenderer.xRot = (float)((double)modelrenderer.xRot - ((double)f1 * 1.2D + (double)f2));
            modelrenderer.yRot += this.Body.yRot * 2.0F;
            modelrenderer.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.4F;
        }
    }

    protected HandSide getAttackArm(SCP008Entity pEntity) {
        HandSide handside = pEntity.getMainArm();
        return pEntity.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
    }

    protected ModelRenderer getArm(HandSide pSide) {
        return pSide == HandSide.LEFT ? this.LeftArm : this.RightArm;
    }
    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}*/