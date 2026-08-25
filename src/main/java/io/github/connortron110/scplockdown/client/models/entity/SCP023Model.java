package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP023Entity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SCP023Model<T extends SCP023Entity> extends EntityModel<T> {
	private final ModelPart NeckTuff;
	private final ModelPart Head;
	private final ModelPart RightEar;
	private final ModelPart LeftEar;
	private final ModelPart Snoot;
	private final ModelPart TopMolars;
	private final ModelPart TopCanines;
	private final ModelPart Jaw;
	private final ModelPart BottomCanines;
	private final ModelPart BottomMolars;
	private final ModelPart FrontLeftLeg;
	private final ModelPart FrontRightLeg;
	private final ModelPart Back;
	private final ModelPart BackRightLeg;
	private final ModelPart BackLeftLeg;
	private final ModelPart Tail;

	public SCP023Model(ModelPart root) {
		this.NeckTuff = root.getChild("NeckTuff");
		this.Head = this.NeckTuff.getChild("Head");
		this.RightEar = this.Head.getChild("RightEar");
		this.LeftEar = this.Head.getChild("LeftEar");
		this.Snoot = this.Head.getChild("Snoot");
		this.TopMolars = this.Snoot.getChild("TopMolars");
		this.TopCanines = this.Snoot.getChild("TopCanines");
		this.Jaw = this.Snoot.getChild("Jaw");
		this.BottomCanines = this.Jaw.getChild("BottomCanines");
		this.BottomMolars = this.Jaw.getChild("BottomMolars");
		this.FrontLeftLeg = this.NeckTuff.getChild("FrontLeftLeg");
		this.FrontRightLeg = this.NeckTuff.getChild("FrontRightLeg");
		this.Back = this.NeckTuff.getChild("Back");
		this.BackRightLeg = this.Back.getChild("BackRightLeg");
		this.BackLeftLeg = this.Back.getChild("BackLeftLeg");
		this.Tail = this.Back.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition NeckTuff = partdefinition.addOrReplaceChild("NeckTuff", CubeListBuilder.create().texOffs(21, 0).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -4.1F, 1.5708F, 0.0F, 0.0F));

		PartDefinition Head = NeckTuff.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.5F, -1.5708F, 0.0F, 0.0F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(16, 14).addBox(1.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Snoot = Head.addOrReplaceChild("Snoot", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.1F));

		PartDefinition TopMolars = Snoot.addOrReplaceChild("TopMolars", CubeListBuilder.create().texOffs(44, 22).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 24).addBox(-2.8F, 0.2F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4F, 1.2F, -4.8F));

		PartDefinition TopCanines = Snoot.addOrReplaceChild("TopCanines", CubeListBuilder.create().texOffs(44, 29).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.6F, -4.8F));

		PartDefinition Jaw = Snoot.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(47, 0).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -0.9F));

		PartDefinition BottomCanines = Jaw.addOrReplaceChild("BottomCanines", CubeListBuilder.create().texOffs(44, 15).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.4F, -3.9F));

		PartDefinition BottomMolars = Jaw.addOrReplaceChild("BottomMolars", CubeListBuilder.create().texOffs(44, 16).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 19).addBox(-2.8F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4F, -0.1F, -3.9F));

		PartDefinition FrontLeftLeg = NeckTuff.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.0F, -2.0F, -1.57F, 0.0F, 0.0F));

		PartDefinition FrontRightLeg = NeckTuff.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.0F, -2.0F, -1.57F, 0.0F, 0.0F));

		PartDefinition Back = NeckTuff.addOrReplaceChild("Back", CubeListBuilder.create().texOffs(18, 14).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 5.0F, 0.0F));

		PartDefinition BackRightLeg = Back.addOrReplaceChild("BackRightLeg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 5.0F, -2.0F, -1.57F, 0.0F, 0.0F));

		PartDefinition BackLeftLeg = Back.addOrReplaceChild("BackLeftLeg", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 5.0F, -2.0F, -1.57F, 0.0F, 0.0F));

		PartDefinition Tail = Back.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(9, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, 2.0F, -1.0016F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(SCP023Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.xRot = (float) (Math.toRadians(headPitch) - Math.PI / 2);
		this.Head.zRot = (float) Math.toRadians(-netHeadYaw);
		this.Tail.xRot = (float) Math.toRadians(-45);
		this.BackRightLeg.xRot = (float) ((Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) - Math.PI / 2);
		this.BackLeftLeg.xRot = (float) ((Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount) - Math.PI / 2);
		this.FrontLeftLeg.xRot = (float) ((Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount) - Math.PI / 2);
		this.FrontRightLeg.xRot = (float) ((Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) - Math.PI / 2);

		this.Jaw.xRot = (float) Math.toRadians((Mth.cos(ageInTicks / 20) + 1) * 5);
		this.Tail.yRot = (float) Math.toRadians((Mth.cos(ageInTicks / 5)) * 5);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		NeckTuff.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}