package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class LockerModel extends Model {
	private final ModelPart lockerbody;
	private final ModelPart rightdoor;
	private final ModelPart leftdoor;

	public LockerModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.lockerbody = root.getChild("lockerbody");
		this.rightdoor = root.getChild("rightdoor");
		this.leftdoor = root.getChild("leftdoor");
	}

	public void setDoorRot(float radians) {
		rightdoor.yRot = radians;
		leftdoor.yRot = -radians;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition lockerbody = partdefinition.addOrReplaceChild("lockerbody", CubeListBuilder.create().texOffs(26, 57).addBox(-21.0F, -10.0F, 6.0F, 14.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 0).addBox(-22.0F, -10.0F, -6.0F, 1.0F, 31.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -10.0F, -6.0F, 1.0F, 31.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(-14.5F, -9.0F, -6.0F, 1.0F, 29.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, 21.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-21.0F, 21.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-9.0F, 21.0F, 4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-21.0F, 21.0F, 4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(26, 44).addBox(-21.0F, 20.0F, -6.0F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(56, 13).addBox(-21.0F, 6.0F, -4.0F, 14.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(43, 0).addBox(-21.0F, -10.0F, -6.0F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(74, 56).addBox(-21.0F, -2.0F, 0.0F, 14.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, 2.0F, 0.0F));

		PartDefinition rightdoor = partdefinition.addOrReplaceChild("rightdoor", CubeListBuilder.create().texOffs(88, 24).addBox(-6.0F, -9.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 24).addBox(-6.0F, -6.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(56, 57).addBox(-8.0F, -16.0F, -1.0F, 8.0F, 31.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 24).addBox(-6.0F, -12.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(74, 84).addBox(-7.0F, -1.5F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -6.0F));

		PartDefinition leftdoor = partdefinition.addOrReplaceChild("leftdoor", CubeListBuilder.create().texOffs(74, 84).addBox(6.0F, -1.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 24).addBox(2.0F, -5.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 24).addBox(2.0F, -8.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(88, 24).addBox(2.0F, -11.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(66, 24).addBox(0.0F, -15.5F, -1.0F, 8.0F, 31.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 7.5F, -6.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lockerbody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightdoor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftdoor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}