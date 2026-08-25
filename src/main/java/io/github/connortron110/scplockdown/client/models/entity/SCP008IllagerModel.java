/*package io.github.connortron110.scplockdown.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008IllagerEntity;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class SCP008IllagerModel<T extends SCP008IllagerEntity> extends SegmentedModel<T> implements IHasArm, IHasHead {
    private final ModelRenderer head;
    private final ModelRenderer hat;
    private final ModelRenderer body;
    private final ModelRenderer arms;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftArm;

    public SCP008IllagerModel(float p_i47227_1_, float p_i47227_2_, int p_i47227_3_, int p_i47227_4_) {
        this.head = (new ModelRenderer(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.head.setPos(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, p_i47227_1_);
        this.hat = (new ModelRenderer(this, 32, 0)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.hat.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, p_i47227_1_ + 0.45F);
        this.head.addChild(this.hat);
        this.hat.visible = false;
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        modelrenderer.setPos(0.0F, p_i47227_2_ - 2.0F, 0.0F);
        modelrenderer.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, p_i47227_1_);
        this.head.addChild(modelrenderer);
        this.body = (new ModelRenderer(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.body.setPos(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, p_i47227_1_);
        this.body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, p_i47227_1_ + 0.5F);
        this.arms = (new ModelRenderer(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.arms.setPos(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
        this.arms.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, p_i47227_1_);
        ModelRenderer modelrenderer1 = (new ModelRenderer(this, 44, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        modelrenderer1.mirror = true;
        modelrenderer1.addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, p_i47227_1_);
        this.arms.addChild(modelrenderer1);
        this.arms.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, p_i47227_1_);
        this.leftLeg = (new ModelRenderer(this, 0, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.leftLeg.setPos(-2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightLeg = (new ModelRenderer(this, 0, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.rightLeg.mirror = true;
        this.rightLeg.setPos(2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightArm = (new ModelRenderer(this, 40, 46)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightArm.setPos(-5.0F, 2.0F + p_i47227_2_, 0.0F);
        this.leftArm = (new ModelRenderer(this, 40, 46)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.leftArm.setPos(5.0F, 2.0F + p_i47227_2_, 0.0F);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.head, this.body, this.leftLeg, this.rightLeg, /*this.arms,*/ /*this.rightArm, this.leftArm);
    }

    /**
     * Sets this entity's model rotation angles
     *//*
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
        boolean flag = entity.getFallFlyingTicks() > 4;
        this.head.yRot = netheadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.head.xRot = (-(float)Math.PI / 4F);
        } else {
            this.head.xRot = headPitch * ((float)Math.PI / 180F);
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

        this.rightArm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
        this.rightLeg.zRot = 0.0F;
        this.leftLeg.zRot = 0.0F;
        if (this.riding) {
            this.rightArm.xRot += (-(float)Math.PI / 5F);
            this.leftArm.xRot += (-(float)Math.PI / 5F);
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        }

        this.rightArm.yRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        this.setupAttackAnimation(entity, ageInTicks);
    }

    protected void setupAttackAnimation(T entity, float ageInTicks) {
        if (!(this.attackTime <= 0.0F)) {
            HandSide handside = this.getAttackArm(entity);
            ModelRenderer modelrenderer = this.getArm(handside);
            float f = this.attackTime;
            this.body.yRot = MathHelper.sin(MathHelper.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (handside == HandSide.LEFT) {
                this.body.yRot *= -1.0F;
            }

            this.rightArm.yRot += this.body.yRot;
            this.leftArm.yRot += this.body.yRot;
            this.leftArm.xRot += this.body.yRot;
            f = 1.0F - this.attackTime;
            f = f * f;
            f = f * f;
            f = 1.0F - f;
            float f1 = MathHelper.sin(f * (float)Math.PI);
            float f2 = MathHelper.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            modelrenderer.xRot = (float)((double)modelrenderer.xRot - ((double)f1 * 1.2D + (double)f2));
            modelrenderer.yRot += this.body.yRot * 2.0F;
            modelrenderer.zRot += MathHelper.sin(this.attackTime * (float)Math.PI) * -0.4F;
        }
    }

    protected HandSide getAttackArm(T pEntity) {
        HandSide handside = pEntity.getMainArm();
        return pEntity.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
    }

    protected ModelRenderer getArm(HandSide pSide) {
        return pSide == HandSide.LEFT ? this.leftArm : this.rightArm;
    }


    public ModelRenderer getHat() {
        return this.hat;
    }

    public void translateToHand(HandSide pSide, MatrixStack pMatrixStack) {
        this.getArm(pSide).translateAndRotate(pMatrixStack);
    }

    @Override
    public ModelRenderer getHead() {
        return this.head;
    }
}*/