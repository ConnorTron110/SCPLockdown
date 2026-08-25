package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class GuardModel<E extends LivingEntity> extends EntityModel<E> {
	private final ModelPart Torso;
	private final ModelPart Vest;
	private final ModelPart CrotchProtection;
	private final ModelPart LeftShoulderPad;
	private final ModelPart RightShoulderPad;
	private final ModelPart LeftArm;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart Holster;
	private final ModelPart Head;
	private final ModelPart Helmet;
	private final ModelPart RightArm;

	public GuardModel(ModelPart root) {
		super(RenderType::entityTranslucentCull);
		this.Torso = root.getChild("Torso");
		this.Vest = this.Torso.getChild("Vest");
		this.CrotchProtection = this.Vest.getChild("CrotchProtection");
		this.LeftShoulderPad = this.Vest.getChild("LeftShoulderPad");
		this.RightShoulderPad = this.Vest.getChild("RightShoulderPad");
		this.LeftArm = root.getChild("LeftArm");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.Holster = this.RightLeg.getChild("Holster");
		this.Head = root.getChild("Head");
		this.Helmet = this.Head.getChild("Helmet");
		this.RightArm = root.getChild("RightArm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(19, 34).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Vest = Torso.addOrReplaceChild("Vest", CubeListBuilder.create().texOffs(32, 19).addBox(-5.0F, 0.0F, -2.5F, 10.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.1F, 0.0F));

		PartDefinition CrotchProtection = Vest.addOrReplaceChild("CrotchProtection", CubeListBuilder.create().texOffs(2, 20).addBox(-3.5F, 0.0F, -2.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.8F, 0.0F));

		PartDefinition LeftShoulderPad = Vest.addOrReplaceChild("LeftShoulderPad", CubeListBuilder.create().texOffs(2, 32).addBox(-2.0F, 0.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.2F, -0.5F, 0.0F));

		PartDefinition RightShoulderPad = Vest.addOrReplaceChild("RightShoulderPad", CubeListBuilder.create().texOffs(2, 32).addBox(0.0F, 0.0F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.2F, -0.5F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(31, 51).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(1, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(48, 51).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition Holster = RightLeg.addOrReplaceChild("Holster", CubeListBuilder.create().texOffs(19, 56).addBox(0.0F, -1.5F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.8F, 2.6F, -0.2F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Helmet = Head.addOrReplaceChild("Helmet", CubeListBuilder.create().texOffs(35, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(46, 34).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 68, 68);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float HeadPitch) {
		ClientUtils.humanoidAnim(entity, this, this.Head, this.Torso, this.RightArm, this.LeftArm, this.RightLeg, this.LeftLeg, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, HeadPitch);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}