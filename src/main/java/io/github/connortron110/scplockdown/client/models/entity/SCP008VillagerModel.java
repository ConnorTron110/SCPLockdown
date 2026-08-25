/*package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008VillagerEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class SCP008VillagerModel extends EntityModel<SCP008VillagerEntity> {
	private final ModelRenderer body;
	private final ModelRenderer bodywear;
	private final ModelRenderer head;
	private final ModelRenderer headwear;
	private final ModelRenderer nose;
	private final ModelRenderer bone;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public SCP008VillagerModel() {
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);

		bodywear = new ModelRenderer(this);
		bodywear.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(bodywear);
		bodywear.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, 0.5F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		headwear = new ModelRenderer(this);
		headwear.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(headwear);
		headwear.texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.51F, false);

		nose = new ModelRenderer(this);
		nose.setPos(0.0F, -2.0F, 0.0F);
		head.addChild(nose);
		nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 2.0F, 0.0F);
		nose.addChild(bone);
		

		rightArm = new ModelRenderer(this);
		rightArm.setPos(-4.0F, 2.5F, 0.0F);
		body.addChild(rightArm);
		rightArm.texOffs(44, 22).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(4.0F, 2.5F, 0.0F);
		body.addChild(leftArm);
		leftArm.texOffs(44, 22).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(2.0F, 12.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SCP008VillagerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
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

	protected void setupAttackAnimation(SCP008VillagerEntity entity, float ageInTicks) {
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

	protected HandSide getAttackArm(SCP008VillagerEntity pEntity) {
		HandSide handside = pEntity.getMainArm();
		return pEntity.swingingArm == Hand.MAIN_HAND ? handside : handside.getOpposite();
	}

	protected ModelRenderer getArm(HandSide pSide) {
		return pSide == HandSide.LEFT ? this.leftArm : this.rightArm;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}*/