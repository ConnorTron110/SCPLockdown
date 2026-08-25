package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP019Entity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SCP019Model<T extends SCP019Entity> extends EntityModel<T> {
	private final ModelPart upperBack;
	private final ModelPart lowerBack;
	private final ModelPart rightLeg;
	private final ModelPart rightFoot;
	private final ModelPart leftLeg;
	private final ModelPart leftFoot;
	private final ModelPart leftArm;
	private final ModelPart leftHand;
	private final ModelPart rightArm;
	private final ModelPart rightHand;
	private final ModelPart head;

	public SCP019Model(ModelPart root) {
		this.upperBack = root.getChild("upperBack");
		this.lowerBack = this.upperBack.getChild("lowerBack");
		this.rightLeg = this.lowerBack.getChild("rightLeg");
		this.rightFoot = this.rightLeg.getChild("rightFoot");
		this.leftLeg = this.lowerBack.getChild("leftLeg");
		this.leftFoot = this.leftLeg.getChild("leftFoot");
		this.leftArm = this.upperBack.getChild("leftArm");
		this.leftHand = this.leftArm.getChild("leftHand");
		this.rightArm = this.upperBack.getChild("rightArm");
		this.rightHand = this.rightArm.getChild("rightHand");
		this.head = this.upperBack.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition upperBack = partdefinition.addOrReplaceChild("upperBack", CubeListBuilder.create().texOffs(0, 5).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.3F, 0.0F, 0.3187F, 0.0F, 0.0F));

		PartDefinition lowerBack = upperBack.addOrReplaceChild("lowerBack", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5009F, 0.0F, 0.0F));

		PartDefinition rightLeg = lowerBack.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(22, 4).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 0.5F, 3.0F, -0.3643F, -0.5463F, 0.0F));

		PartDefinition rightFoot = rightLeg.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(29, 4).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.9F, -0.6981F, 0.0F, 0.0F));

		PartDefinition leftLeg = lowerBack.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(22, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.5F, 3.0F, -0.3643F, 0.5463F, 0.0F));

		PartDefinition leftFoot = leftLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(29, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.9F, -0.6981F, 0.0F, 0.0F));

		PartDefinition leftArm = upperBack.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(36, 0).addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2F, 0.5F, -1.5F, 0.5463F, 0.0F, 0.5463F));

		PartDefinition leftHand = leftArm.addOrReplaceChild("leftHand", CubeListBuilder.create().texOffs(36, 3).addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.3643F));

		PartDefinition rightArm = upperBack.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(43, 0).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, 0.5F, -1.5F, 0.5463F, 0.0F, -0.5463F));

		PartDefinition rightHand = rightArm.addOrReplaceChild("rightHand", CubeListBuilder.create().texOffs(44, 3).addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, -1.0472F, -0.3643F));

		PartDefinition head = upperBack.addOrReplaceChild("head", CubeListBuilder.create().texOffs(11, 0).addBox(-1.5F, -1.6F, -1.6F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.3F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(SCP019Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean flag = entity.getFallFlyingTicks() > 4;
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		if (flag) {
			this.head.xRot = (-(float) Math.PI / 4F);
		} else {
			this.head.xRot = headPitch * ((float) Math.PI / 180F);
		}

		float f = 1.0F;
		if (flag) {
			f = (float) entity.getDeltaMovement().lengthSqr();
			f = f / 0.2F;
			f = f * f * f;
		}

		if (f < 1.0F) {
			f = 1.0F;
		}

		this.rightArm.xRot = 1 + Mth.cos(limbSwing * 3 + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.leftArm.xRot = 1 + Mth.cos(limbSwing * 3) * 2.0F * limbSwingAmount * 0.5F / f;

		this.rightLeg.yRot = (float) (-0.5 + Mth.cos(limbSwing * 3 - ((float) Math.PI / 2F)) * limbSwingAmount * 0.5F / f);
		this.rightFoot.xRot = (float) (-0.5 + Mth.cos(limbSwing * 3 + ((float) Math.PI / 2F)) * limbSwingAmount * 0.5F / f);
		this.leftLeg.yRot = (float) (0.5 + Mth.cos(limbSwing * 3 + ((float) Math.PI / 2F)) * limbSwingAmount * 0.5F / f);
		this.leftFoot.xRot = (float) (-0.5 + Mth.cos(limbSwing * 3 - ((float) Math.PI / 2F)) * limbSwingAmount * 0.5F / f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		upperBack.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
