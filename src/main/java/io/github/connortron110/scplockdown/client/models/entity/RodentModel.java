package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.RodentEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class RodentModel<T extends RodentEntity> extends EntityModel<T> {
	private final ModelPart Body;
	private final ModelPart MouseTail;
	private final ModelPart MouseTailTip;
	private final ModelPart RatTail;
	private final ModelPart RatTailTip;
	private final ModelPart Head;
	private final ModelPart RightEar;
	private final ModelPart LeftEar;
	private final ModelPart RightHand;
	private final ModelPart RightFoot;
	private final ModelPart LeftFoot;
	private final ModelPart LeftHand;
	private final ModelPart RatBody;

	/**
	 * @param isBig Determines if the Rat model should show instead
	 */
	public RodentModel(ModelPart root, boolean isBig) {
		this.Body = root.getChild("Body");
		this.MouseTail = this.Body.getChild("MouseTail");
		this.MouseTailTip = this.MouseTail.getChild("MouseTailTip");
		this.RatTail = this.Body.getChild("RatTail");
		this.RatTailTip = this.RatTail.getChild("RatTailTip");
		this.Head = this.Body.getChild("Head");
		this.RightEar = this.Head.getChild("RightEar");
		this.LeftEar = this.Head.getChild("LeftEar");
		this.RightHand = this.Body.getChild("RightHand");
		this.RightFoot = this.Body.getChild("RightFoot");
		this.LeftFoot = this.Body.getChild("LeftFoot");
		this.LeftHand = this.Body.getChild("LeftHand");
		this.RatBody = this.Body.getChild("RatBody");

		this.RatTail.visible = isBig;
		this.RatBody.visible = isBig;
		this.MouseTail.visible = !isBig;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.6F, -4.6F));

		PartDefinition MouseTail = Body.addOrReplaceChild("MouseTail", CubeListBuilder.create().texOffs(20, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 6.5F, -0.3643F, 0.0F, 0.0F));

		PartDefinition MouseTailTip = MouseTail.addOrReplaceChild("MouseTailTip", CubeListBuilder.create().texOffs(20, 5).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition RatTail = Body.addOrReplaceChild("RatTail", CubeListBuilder.create().texOffs(16, 10).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.5F, -0.3187F, 0.0F, 0.0F));

		PartDefinition RatTailTip = RatTail.addOrReplaceChild("RatTailTip", CubeListBuilder.create().texOffs(16, 16).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.182F, 0.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.1F, 0.2F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(6, 23).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -1.7F, -1.4F, 0.0F, 0.2276F, -0.0911F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(6, 25).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, -1.7F, -1.4F, 0.0F, -0.2276F, 0.0911F));

		PartDefinition RightHand = Body.addOrReplaceChild("RightHand", CubeListBuilder.create().texOffs(10, 18).addBox(-0.5F, -0.1F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.5F, 0.6F));

		PartDefinition RightFoot = Body.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(10, 22).addBox(-0.5F, 0.0F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.3F, 1.4F, 6.0F));

		PartDefinition LeftFoot = Body.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(18, 22).addBox(-0.5F, 0.0F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.3F, 1.4F, 6.0F));

		PartDefinition LeftHand = Body.addOrReplaceChild("LeftHand", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -0.1F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.5F, 0.6F));

		PartDefinition RatBody = Body.addOrReplaceChild("RatBody", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.1F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(RodentEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.xRot = (float) Math.toRadians(headPitch);
		this.Head.yRot = (float) Math.toRadians(netHeadYaw);

		this.RightFoot.xRot = Mth.cos(limbSwing * 3 + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.LeftFoot.xRot = Mth.cos(limbSwing * 3) * 2.0F * limbSwingAmount * 0.5F;

		this.LeftHand.xRot = Mth.cos(limbSwing * 3 + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.RightHand.xRot = Mth.cos(limbSwing * 3) * 2.0F * limbSwingAmount * 0.5F;

		float tail = Mth.cos(ageInTicks / 5) / 4;
		float tailTip = Mth.cos((ageInTicks - 2) / 5) / 5;

		this.RatTail.yRot = tail;
		this.RatTailTip.yRot = tailTip;

		this.MouseTail.yRot = tail;
		this.MouseTailTip.yRot = tailTip;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}