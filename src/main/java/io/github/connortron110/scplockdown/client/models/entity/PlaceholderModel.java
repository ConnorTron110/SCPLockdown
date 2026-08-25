/*package io.github.connortron110.scplockdown.client.models.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;

public class PlaceholderModel<E extends Entity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer left_arm_r1;
	private final ModelRenderer right_arm_r1;
	private final ModelRenderer Head;

	public PlaceholderModel() {
		texWidth = 16;
		texHeight = 16;

		Body = new ModelRenderer(this);
		Body.setPos(1.0F, 4.0F, 0.0F);
		Body.texOffs(4, 4).addBox(-5.0F, -4.0F, -2.0F, 8.0F, 24.0F, 4.0F, 0.0F, false);

		left_arm_r1 = new ModelRenderer(this);
		left_arm_r1.setPos(5.0F, -2.0F, 0.0F);
		Body.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, 0.0F, 0.0F, -1.5708F);
		left_arm_r1.texOffs(8, 12).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_arm_r1 = new ModelRenderer(this);
		right_arm_r1.setPos(-7.0F, -2.0F, 0.0F);
		Body.addChild(right_arm_r1);
		setRotationAngle(right_arm_r1, 0.0F, 0.0F, 1.5708F);
		right_arm_r1.texOffs(10, 4).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 0.0F, 0.0F);
		Head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}*/