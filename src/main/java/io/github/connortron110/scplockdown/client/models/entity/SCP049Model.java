package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP049Entity;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SCP049Model<T extends SCP049Entity> extends EntityModel<T> {
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart Head;
	private final ModelPart Hood;
	private final ModelPart Mask;
	private final ModelPart NoseBase;
	private final ModelPart NoseBase1;
	private final ModelPart MaskBottom;
	private final ModelPart NoseTip;
	private final ModelPart NoseTip1;
	private final ModelPart MaskRight;
	private final ModelPart MaskLeft;
	private final ModelPart MaskTop;
	private final ModelPart MaskCenter;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart LeftArm;

	public SCP049Model(ModelPart root) {
		this.Body = root.getChild("Body");
		this.RightArm = this.Body.getChild("RightArm");
		this.Head = this.Body.getChild("Head");
		this.Hood = this.Head.getChild("Hood");
		this.Mask = this.Head.getChild("Mask");
		this.NoseBase = this.Mask.getChild("NoseBase");
		this.NoseBase1 = this.NoseBase.getChild("NoseBase1");
		this.MaskBottom = this.Mask.getChild("MaskBottom");
		this.NoseTip = this.Mask.getChild("NoseTip");
		this.NoseTip1 = this.NoseTip.getChild("NoseTip1");
		this.MaskRight = this.Mask.getChild("MaskRight");
		this.MaskLeft = this.Mask.getChild("MaskLeft");
		this.MaskTop = this.Mask.getChild("MaskTop");
		this.MaskCenter = this.Mask.getChild("MaskCenter");
		this.LeftLeg = this.Body.getChild("LeftLeg");
		this.RightLeg = this.Body.getChild("RightLeg");
		this.LeftArm = this.Body.getChild("LeftArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Hood = Head.addOrReplaceChild("Hood", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Mask = Head.addOrReplaceChild("Mask", CubeListBuilder.create().texOffs(0, 54).addBox(-3.5F, -4.0F, -4.25F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.1F, 0.0F));

		PartDefinition NoseBase = Mask.addOrReplaceChild("NoseBase", CubeListBuilder.create().texOffs(14, 54).addBox(-1.0F, -5.0F, -5.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition NoseBase1 = NoseBase.addOrReplaceChild("NoseBase1", CubeListBuilder.create().texOffs(14, 54).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -5.0F, -5.6F, 0.1367F, 0.0F, 0.0F));

		PartDefinition MaskBottom = Mask.addOrReplaceChild("MaskBottom", CubeListBuilder.create().texOffs(45, 60).addBox(-2.5F, -3.5F, -4.25F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition NoseTip = Mask.addOrReplaceChild("NoseTip", CubeListBuilder.create().texOffs(1, 58).addBox(-0.5F, -5.2F, -8.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition NoseTip1 = NoseTip.addOrReplaceChild("NoseTip1", CubeListBuilder.create().texOffs(1, 58).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.3F, -8.0F, 0.1677F, 0.0F, 0.0F));

		PartDefinition MaskRight = Mask.addOrReplaceChild("MaskRight", CubeListBuilder.create().texOffs(32, 59).addBox(-3.7F, -5.0F, -4.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition MaskLeft = Mask.addOrReplaceChild("MaskLeft", CubeListBuilder.create().texOffs(27, 54).addBox(2.7F, -5.0F, -4.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition MaskTop = Mask.addOrReplaceChild("MaskTop", CubeListBuilder.create().texOffs(35, 54).addBox(-3.0F, -6.0F, -4.25F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition MaskCenter = Mask.addOrReplaceChild("MaskCenter", CubeListBuilder.create().texOffs(55, 54).addBox(-1.0F, -5.0F, -4.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(15, 33).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(35, 34).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SCP049Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientUtils.humanoidAnim(entity, this, this.Head, this.Body, this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
