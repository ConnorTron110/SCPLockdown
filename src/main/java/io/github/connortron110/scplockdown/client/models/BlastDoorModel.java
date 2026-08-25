package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class BlastDoorModel extends Model {

	public final ModelPart DoorLeft;
	public final ModelPart DoorRight;
	private final ModelPart bb_main;

	public BlastDoorModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.DoorLeft = root.getChild("DoorLeft");
		this.DoorRight = root.getChild("DoorRight");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition DoorLeft = partdefinition.addOrReplaceChild("DoorLeft", CubeListBuilder.create().texOffs(91, 88).addBox(-16.0F, -32.0F, -2.0F, 16.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition DoorRight = partdefinition.addOrReplaceChild("DoorRight", CubeListBuilder.create().texOffs(54, 55).addBox(0.0F, -32.0F, -2.0F, 16.0F, 32.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(120, 27).addBox(16.0F, -40.0F, -3.0F, 2.0F, 40.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 44).addBox(-16.0F, -36.0F, -3.0F, 32.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 27).addBox(-16.0F, -40.0F, -6.0F, 32.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(95, 27).addBox(19.0F, -40.0F, -5.0F, 2.0F, 40.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(79, 119).addBox(-18.0F, -40.0F, -3.0F, 2.0F, 40.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(54, 92).addBox(-21.0F, -40.0F, -5.0F, 2.0F, 40.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-24.0F, -48.0F, -9.0F, 48.0F, 8.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(27, 55).addBox(-22.0F, -40.0F, -6.0F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 55).addBox(21.0F, -40.0F, -6.0F, 1.0F, 40.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(19, 108).addBox(-19.0F, -40.0F, -4.0F, 1.0F, 40.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 108).addBox(18.0F, -40.0F, -4.0F, 1.0F, 40.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 108).addBox(0.0F, -40.0F, 0.0F, 3.0F, 40.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.0F, 0.0F, 7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 108).addBox(-3.0F, -40.0F, 0.0F, 3.0F, 40.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.0F, 0.0F, 7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(96, 125).addBox(0.0F, -40.0F, 0.0F, 3.0F, 40.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.0F, 0.0F, -7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(103, 125).addBox(-3.0F, -40.0F, 0.0F, 3.0F, 40.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.0F, 0.0F, -7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -38.0F, 6.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -38.0F, 6.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(15, 55).addBox(-4.0F, -2.0F, 0.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -38.0F, -6.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(77, 27).addBox(0.0F, -2.0F, 0.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -38.0F, -6.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		DoorLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		DoorRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}