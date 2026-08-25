package io.github.connortron110.scplockdown.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class ClientUtils {

	public static <E extends LivingEntity> void humanoidAnim(E entity, EntityModel<? extends E> model, ModelPart head, ModelPart torso, ModelPart rightArm, ModelPart leftArm, ModelPart rightLeg, ModelPart leftLeg, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
		boolean flag = entity.getFallFlyingTicks() > 4;
		head.yRot = netheadYaw * ((float) Math.PI / 180F);
		if (flag) {
			head.xRot = (-(float) Math.PI / 4F);
		} else {
			head.xRot = headPitch * ((float) Math.PI / 180F);
		}

		float swingDiv = 1.0F;
		if (flag) {
			swingDiv = (float) entity.getDeltaMovement().lengthSqr();
			swingDiv = swingDiv / 0.2F;
			swingDiv = swingDiv * swingDiv * swingDiv;
		}

		if (swingDiv < 1.0F) {
			swingDiv = 1.0F;
		}

		rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / swingDiv;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / swingDiv;
		rightArm.zRot = 0.0F;
		leftArm.zRot = 0.0F;
		rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / swingDiv;
		leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / swingDiv;
		rightLeg.yRot = 0.0F;
		leftLeg.yRot = 0.0F;
		rightLeg.zRot = 0.0F;
		leftLeg.zRot = 0.0F;
		if (model.riding) {
			rightArm.xRot += (-(float) Math.PI / 5F);
			leftArm.xRot += (-(float) Math.PI / 5F);
			rightLeg.xRot = -1.4137167F;
			rightLeg.yRot = ((float) Math.PI / 10F);
			rightLeg.zRot = 0.07853982F;
			leftLeg.xRot = -1.4137167F;
			leftLeg.yRot = (-(float) Math.PI / 10F);
			leftLeg.zRot = -0.07853982F;
		}

		rightArm.yRot = 0.0F;
		leftArm.yRot = 0.0F;

		if (!(model.attackTime <= 0.0F)) {
			HumanoidArm handside = entity.swingingArm == InteractionHand.MAIN_HAND ? entity.getMainArm() : entity.getMainArm().getOpposite();
			ModelPart modelrenderer = handside == HumanoidArm.LEFT ? leftArm : rightArm;
			float f = model.attackTime;
			torso.yRot = Mth.sin(Mth.sqrt(f) * ((float) Math.PI * 2F)) * 0.2F;
			if (handside == HumanoidArm.LEFT) {
				torso.yRot *= -1.0F;
			}

			rightArm.yRot += torso.yRot;
			leftArm.yRot += torso.yRot;
			leftArm.xRot += torso.yRot;
			f = 1.0F - model.attackTime;
			f = f * f;
			f = f * f;
			f = 1.0F - f;
			float f1 = Mth.sin(f * (float) Math.PI);
			float f2 = Mth.sin(model.attackTime * (float) Math.PI) * -(head.xRot - 0.7F) * 0.75F;
			modelrenderer.xRot = (float) ((double) modelrenderer.xRot - ((double) f1 * 1.2D + (double) f2));
			modelrenderer.yRot += torso.yRot * 2.0F;
			modelrenderer.zRot += Mth.sin(model.attackTime * (float) Math.PI) * -0.4F;
		}
	}

	/**
	 * Most tile entities seem to require the same amount of translation in order to render them correctly.
	 */
	public static void tileEntityRotation(PoseStack poseStack, double heightOffset) {
		poseStack.translate(0.5D, 0.5D, 0.5D);
		poseStack.scale(1.0F, -1.0F, -1.0F);
		poseStack.translate(0.0D, -heightOffset, 0.0D);
	}

	public static double getFOV() {
		return Minecraft.getInstance().options.fov().get();
	}
}
