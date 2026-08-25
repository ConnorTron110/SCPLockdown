package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP053Entity;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SCP053Model<T extends SCP053Entity> extends EntityModel<T> {
	private final ModelPart Body;
	private final ModelPart LeftArm;
	private final ModelPart SkirtBackRight;
	private final ModelPart LeftLeg;
	private final ModelPart SkirtRight;
	private final ModelPart SkirtFrontLeft;
	private final ModelPart Head;
	private final ModelPart RightArm;
	private final ModelPart RightLeg;
	private final ModelPart SkirtFrontRight;
	private final ModelPart SkirtBackLeft;
	private final ModelPart SkirtLeft;

	public SCP053Model(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LeftArm = this.Body.getChild("LeftArm");
		this.SkirtBackRight = this.Body.getChild("SkirtBackRight");
		this.LeftLeg = this.Body.getChild("LeftLeg");
		this.SkirtRight = this.Body.getChild("SkirtRight");
		this.SkirtFrontLeft = this.Body.getChild("SkirtFrontLeft");
		this.Head = this.Body.getChild("Head");
		this.RightArm = this.Body.getChild("RightArm");
		this.RightLeg = this.Body.getChild("RightLeg");
		this.SkirtFrontRight = this.Body.getChild("SkirtFrontRight");
		this.SkirtBackLeft = this.Body.getChild("SkirtBackLeft");
		this.SkirtLeft = this.Body.getChild("SkirtLeft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, -3.1416F, 0.0F));

		PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(10, 19).addBox(-2.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -0.5F, 0.0F));

		PartDefinition SkirtBackRight = Body.addOrReplaceChild("SkirtBackRight", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, -4.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.8F, -1.1F, -0.0456F, 0.0F, 0.0F));

		PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -6.0F, 0.0F));

		PartDefinition SkirtRight = Body.addOrReplaceChild("SkirtRight", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -5.8F, -1.0F, 0.0F, 0.0F, -0.0456F));

		PartDefinition SkirtFrontLeft = Body.addOrReplaceChild("SkirtFrontLeft", CubeListBuilder.create().texOffs(40, 8).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.8F, 1.1F, 0.0456F, 0.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(10, 9).addBox(0.0F, -5.5F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -0.5F, 0.0F));

		PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, 0.0F));

		PartDefinition SkirtFrontRight = Body.addOrReplaceChild("SkirtFrontRight", CubeListBuilder.create().texOffs(31, 8).addBox(0.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.8F, 1.1F, 0.0456F, 0.0F, 0.0F));

		PartDefinition SkirtBackLeft = Body.addOrReplaceChild("SkirtBackLeft", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -4.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.8F, -1.1F, -0.0456F, 0.0F, 0.0F));

		PartDefinition SkirtLeft = Body.addOrReplaceChild("SkirtLeft", CubeListBuilder.create().texOffs(21, 9).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, -5.8F, -1.0F, 0.0F, 0.0F, 0.0456F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(SCP053Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientUtils.humanoidAnim(entity, this, this.Head, this.Body, this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, -headPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
