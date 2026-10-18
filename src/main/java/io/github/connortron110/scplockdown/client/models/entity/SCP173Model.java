package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP173Entity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SCP173Model<T extends SCP173Entity> extends EntityModel<T> {
	private final ModelPart Butt;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart Torso;
	private final ModelPart LeftArm;
	private final ModelPart RightArm;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart Head2;
	private final ModelPart RightEye;
	private final ModelPart LeftEye;
	private final ModelPart Head3;
	private final ModelPart Head4;

	public SCP173Model(ModelPart root) {
		this.Butt = root.getChild("Butt");
		this.LeftLeg = this.Butt.getChild("Left Leg");
		this.RightLeg = this.Butt.getChild("Right Leg");
		this.Torso = this.Butt.getChild("Torso");
		this.LeftArm = this.Torso.getChild("Left Arm");
		this.RightArm = this.Torso.getChild("Right Arm");
		this.Neck = this.Torso.getChild("Neck");
		this.Head = this.Neck.getChild("Head");
		this.Head2 = this.Head.getChild("Head 2");
		this.RightEye = this.Head2.getChild("Right Eye");
		this.LeftEye = this.Head2.getChild("Left Eye");
		this.Head3 = this.Head.getChild("Head 3");
		this.Head4 = this.Head.getChild("Head 4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Butt = partdefinition.addOrReplaceChild("Butt", CubeListBuilder.create().texOffs(26, 9).mirror().addBox(-5.5F, -17.5F, -4.0F, 11.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition LeftLeg = Butt.addOrReplaceChild("Left Leg", CubeListBuilder.create().texOffs(46, 46).mirror().addBox(0.5F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = Butt.addOrReplaceChild("Right Leg", CubeListBuilder.create().texOffs(46, 46).addBox(-4.5F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Torso = Butt.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(24, 23).mirror().addBox(-6.0F, -32.5F, -5.0F, 12.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.6F));

		PartDefinition LeftArm = Torso.addOrReplaceChild("Left Arm", CubeListBuilder.create().texOffs(1, 21).mirror().addBox(5.5F, -28.5F, -12.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = Torso.addOrReplaceChild("Right Arm", CubeListBuilder.create().texOffs(1, 21).addBox(-8.5F, -28.5F, -12.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Neck = Torso.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, -35.5F, -4.5F, 11.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(-6.5F, -49.0F, -7.0F, 13.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head2 = Head.addOrReplaceChild("Head 2", CubeListBuilder.create().texOffs(0, 38).mirror().addBox(-5.5F, -48.0F, -7.5F, 11.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightEye = Head2.addOrReplaceChild("Right Eye", CubeListBuilder.create().texOffs(5, 46).addBox(-5.0F, -44.5F, -7.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftEye = Head2.addOrReplaceChild("Left Eye", CubeListBuilder.create().texOffs(5, 46).mirror().addBox(3.0F, -44.5F, -7.75F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head3 = Head.addOrReplaceChild("Head 3", CubeListBuilder.create().texOffs(4, 39).mirror().addBox(-5.5F, -49.5F, -6.0F, 11.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head4 = Head.addOrReplaceChild("Head 4", CubeListBuilder.create().texOffs(2, 40).mirror().addBox(-7.0F, -48.0F, -6.0F, 14.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SCP173Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Butt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}