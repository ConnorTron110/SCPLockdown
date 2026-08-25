package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class SCP914KeyKnobModel extends Model {
	public final ModelPart Center;
	public final ModelPart Key;
	public final ModelPart Knob;

	public SCP914KeyKnobModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.Center = root.getChild("Center");
		this.Key = this.Center.getChild("Key");
		this.Knob = this.Center.getChild("Knob");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Center = partdefinition.addOrReplaceChild("Center", CubeListBuilder.create().texOffs(8, 0).addBox(-2.0F, -2.0F, -8.9F, 4.0F, 4.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(0, 9).addBox(-1.0F, 3.0F, -9.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition Key = Center.addOrReplaceChild("Key", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -9.0F));

		PartDefinition cube_r1 = Key.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 9).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.8F))
				.texOffs(8, 5).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(-0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition Knob = Center.addOrReplaceChild("Knob", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.6F, -1.8F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, 0.0F, -8.8F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Center.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}