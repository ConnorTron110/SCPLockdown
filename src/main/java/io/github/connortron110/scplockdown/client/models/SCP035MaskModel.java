/*package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SCP035MaskModel extends Model {
	private final ModelRenderer MaskBase;
	private final ModelRenderer Mask1;
	private final ModelRenderer Mask2;
	private final ModelRenderer Mask3R;
	private final ModelRenderer Mask4R;
	private final ModelRenderer RightFaceTar1;
	private final ModelRenderer MouthTarRight;
	private final ModelRenderer Mask31R;
	private final ModelRenderer Mask3L;
	private final ModelRenderer Mask4L;
	private final ModelRenderer LeftFaceTar1;
	private final ModelRenderer MouthTarLeft;
	private final ModelRenderer Mask31L;
	private final ModelRenderer Mask5;
	private final ModelRenderer Mask6;
	private final ModelRenderer Mask7M;
	private final ModelRenderer LeftEyeTop;
	private final ModelRenderer LeftEyeBottom;
	private final ModelRenderer RightEyeBottom;
	private final ModelRenderer RightEyeTop;
	private final ModelRenderer bone;
	private final ModelRenderer Nose2;
	private final ModelRenderer RightEyeTar;
	private final ModelRenderer LeftEyeTar;
	private final ModelRenderer Mask7R;
	private final ModelRenderer Mask7L;
	private final ModelRenderer Mask8;
	private final ModelRenderer Mask9;
	private final ModelRenderer MaskTop1;
	private final ModelRenderer MaskTop2;
	private final ModelRenderer LeftEyebrow;
	private final ModelRenderer RightEyebrow;
	private final ModelRenderer RightFaceTar3;
	private final ModelRenderer LeftFaceTar3;
	private final ModelRenderer RightCheek;
	private final ModelRenderer LeftCheek;
	private final ModelRenderer Expression;
	private final ModelRenderer Nose1;
	private final ModelRenderer RightFaceTar2;
	private final ModelRenderer LeftFaceTar2;
	private final ModelRenderer MouthTar;
	private final ModelRenderer Shin;

	public SCP035MaskModel() {
		super(RenderType::entitySolid);
		texWidth = 66;
		texHeight = 18;

		MaskBase = new ModelRenderer(this);
		MaskBase.setPos(0.0F, 0.0F, 0.0F);
		MaskBase.texOffs(20, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		Mask1 = new ModelRenderer(this);
		Mask1.setPos(0.0F, 1.0F, 0.0F);
		MaskBase.addChild(Mask1);
		Mask1.texOffs(29, 0).addBox(-2.5F, 0.0F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, true);

		Mask2 = new ModelRenderer(this);
		Mask2.setPos(0.0F, 1.0F, 0.0F);
		Mask1.addChild(Mask2);
		Mask2.texOffs(43, 0).addBox(-3.0F, 0.0F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F, true);

		Mask3R = new ModelRenderer(this);
		Mask3R.setPos(-2.0F, 0.7F, 0.02F);
		Mask2.addChild(Mask3R);
		Mask3R.texOffs(58, 0).addBox(-0.7F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Mask4R = new ModelRenderer(this);
		Mask4R.setPos(-0.7F, 0.7F, -0.01F);
		Mask3R.addChild(Mask4R);
		Mask4R.texOffs(58, 3).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		RightFaceTar1 = new ModelRenderer(this);
		RightFaceTar1.setPos(0.1F, 1.0F, -0.1F);
		Mask4R.addChild(RightFaceTar1);
		RightFaceTar1.texOffs(0, 7).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		MouthTarRight = new ModelRenderer(this);
		MouthTarRight.setPos(0.7F, 0.5F, -0.1F);
		Mask3R.addChild(MouthTarRight);
		MouthTarRight.texOffs(0, 12).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Mask31R = new ModelRenderer(this);
		Mask31R.setPos(0.0F, 1.0F, 0.0F);
		Mask3R.addChild(Mask31R);
		Mask31R.texOffs(58, 0).addBox(-1.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Mask3L = new ModelRenderer(this);
		Mask3L.setPos(2.0F, 0.7F, 0.02F);
		Mask2.addChild(Mask3L);
		Mask3L.texOffs(58, 0).addBox(-0.3F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Mask4L = new ModelRenderer(this);
		Mask4L.setPos(0.7F, 0.7F, -0.01F);
		Mask3L.addChild(Mask4L);
		Mask4L.texOffs(58, 3).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		LeftFaceTar1 = new ModelRenderer(this);
		LeftFaceTar1.setPos(-0.1F, 1.0F, -0.1F);
		Mask4L.addChild(LeftFaceTar1);
		LeftFaceTar1.texOffs(0, 7).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		MouthTarLeft = new ModelRenderer(this);
		MouthTarLeft.setPos(-0.7F, 0.5F, -0.1F);
		Mask3L.addChild(MouthTarLeft);
		MouthTarLeft.texOffs(0, 12).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Mask31L = new ModelRenderer(this);
		Mask31L.setPos(0.0F, 1.0F, 0.0F);
		Mask3L.addChild(Mask31L);
		Mask31L.texOffs(58, 0).addBox(0.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Mask5 = new ModelRenderer(this);
		Mask5.setPos(0.0F, 3.4F, 0.0F);
		Mask2.addChild(Mask5);
		Mask5.texOffs(39, 3).addBox(-4.0F, 0.0F, -0.5F, 8.0F, 2.0F, 1.0F, 0.0F, true);

		Mask6 = new ModelRenderer(this);
		Mask6.setPos(0.0F, 1.5F, 0.01F);
		Mask5.addChild(Mask6);
		Mask6.texOffs(14, 3).addBox(-3.5F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.0F, true);

		Mask7M = new ModelRenderer(this);
		Mask7M.setPos(0.0F, 1.0F, 0.0F);
		Mask6.addChild(Mask7M);
		Mask7M.texOffs(35, 7).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		LeftEyeTop = new ModelRenderer(this);
		LeftEyeTop.setPos(2.6F, 1.7F, 0.01F);
		Mask7M.addChild(LeftEyeTop);
		LeftEyeTop.texOffs(25, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		LeftEyeBottom = new ModelRenderer(this);
		LeftEyeBottom.setPos(1.1F, -0.6F, 0.01F);
		Mask7M.addChild(LeftEyeBottom);
		LeftEyeBottom.texOffs(17, 13).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		RightEyeBottom = new ModelRenderer(this);
		RightEyeBottom.setPos(-1.1F, -0.6F, 0.01F);
		Mask7M.addChild(RightEyeBottom);
		RightEyeBottom.texOffs(17, 13).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		RightEyeTop = new ModelRenderer(this);
		RightEyeTop.setPos(-2.6F, 1.6F, 0.01F);
		Mask7M.addChild(RightEyeTop);
		RightEyeTop.texOffs(25, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		RightEyeTop.addChild(bone);


		Nose2 = new ModelRenderer(this);
		Nose2.setPos(0.0F, 0.7F, -0.02F);
		Mask7M.addChild(Nose2);
		Nose2.texOffs(7, 5).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		RightEyeTar = new ModelRenderer(this);
		RightEyeTar.setPos(-1.6F, 0.0F, 0.05F);
		Mask7M.addChild(RightEyeTar);
		RightEyeTar.texOffs(6, 9).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		LeftEyeTar = new ModelRenderer(this);
		LeftEyeTar.setPos(1.6F, 0.0F, 0.05F);
		Mask7M.addChild(LeftEyeTar);
		LeftEyeTar.texOffs(6, 9).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		Mask7R = new ModelRenderer(this);
		Mask7R.setPos(-3.0F, 1.0F, 0.0F);
		Mask6.addChild(Mask7R);
		Mask7R.texOffs(29, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Mask7L = new ModelRenderer(this);
		Mask7L.setPos(3.0F, 1.0F, 0.0F);
		Mask6.addChild(Mask7L);
		Mask7L.texOffs(29, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Mask8 = new ModelRenderer(this);
		Mask8.setPos(0.0F, 2.0F, 0.0F);
		Mask6.addChild(Mask8);
		Mask8.texOffs(44, 7).addBox(-4.0F, 0.0F, -0.5F, 8.0F, 1.0F, 1.0F, 0.0F, true);

		Mask9 = new ModelRenderer(this);
		Mask9.setPos(0.0F, 1.0F, 0.0F);
		Mask8.addChild(Mask9);
		Mask9.texOffs(17, 10).addBox(-3.5F, 0.0F, -0.5F, 7.0F, 1.0F, 1.0F, 0.0F, true);

		MaskTop1 = new ModelRenderer(this);
		MaskTop1.setPos(0.0F, 1.0F, -0.25F);
		Mask9.addChild(MaskTop1);
		MaskTop1.texOffs(35, 10).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, true);

		MaskTop2 = new ModelRenderer(this);
		MaskTop2.setPos(0.0F, 1.0F, 0.25F);
		Mask9.addChild(MaskTop2);
		MaskTop2.texOffs(0, 0).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, true);

		LeftEyebrow = new ModelRenderer(this);
		LeftEyebrow.setPos(2.1F, -0.4F, 0.01F);
		Mask9.addChild(LeftEyebrow);
		LeftEyebrow.texOffs(13, 6).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		RightEyebrow = new ModelRenderer(this);
		RightEyebrow.setPos(-2.1F, -0.4F, 0.01F);
		Mask9.addChild(RightEyebrow);
		RightEyebrow.texOffs(13, 6).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		RightFaceTar3 = new ModelRenderer(this);
		RightFaceTar3.setPos(-2.0F, 1.55F, -0.08F);
		Mask6.addChild(RightFaceTar3);
		RightFaceTar3.texOffs(0, 3).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		LeftFaceTar3 = new ModelRenderer(this);
		LeftFaceTar3.setPos(2.0F, 1.55F, -0.08F);
		Mask6.addChild(LeftFaceTar3);
		LeftFaceTar3.texOffs(0, 3).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		RightCheek = new ModelRenderer(this);
		RightCheek.setPos(-3.15F, 0.0F, -0.25F);
		Mask5.addChild(RightCheek);
		RightCheek.texOffs(31, 3).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		LeftCheek = new ModelRenderer(this);
		LeftCheek.setPos(3.15F, 0.0F, -0.25F);
		Mask5.addChild(LeftCheek);
		LeftCheek.texOffs(31, 3).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		Expression = new ModelRenderer(this);
		Expression.setPos(0.0F, -1.15F, 0.02F);
		Mask5.addChild(Expression);
		Expression.texOffs(55, 10).addBox(-1.0F, -1.7F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Expression.texOffs(55, 10).addBox(1.0F, 0.65F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Expression.texOffs(55, 10).addBox(-2.0F, 0.65F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Nose1 = new ModelRenderer(this);
		Nose1.setPos(0.0F, 1.3F, 0.0F);
		Mask5.addChild(Nose1);
		setRotationAngle(Nose1, 0.5463F, 0.0F, 0.0F);
		Nose1.texOffs(6, 3).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		RightFaceTar2 = new ModelRenderer(this);
		RightFaceTar2.setPos(-2.4F, 1.9F, -0.09F);
		Mask5.addChild(RightFaceTar2);
		RightFaceTar2.texOffs(0, 7).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		LeftFaceTar2 = new ModelRenderer(this);
		LeftFaceTar2.setPos(2.4F, 1.9F, -0.09F);
		Mask5.addChild(LeftFaceTar2);
		LeftFaceTar2.texOffs(0, 7).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		MouthTar = new ModelRenderer(this);
		MouthTar.setPos(0.0F, 0.9F, 0.03F);
		Mask2.addChild(MouthTar);
		MouthTar.texOffs(5, 12).addBox(-2.0F, 0.0F, -0.5F, 4.0F, 3.0F, 1.0F, 0.0F, true);

		Shin = new ModelRenderer(this);
		Shin.setPos(0.0F, 1.5F, -0.22F);
		MaskBase.addChild(Shin);
		Shin.texOffs(22, 6).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);
	}

	public ModelRenderer getMaskBase() {
		//setRotationAngle(MaskBase, 0, (float) Math.PI, 0);
		return MaskBase;
	}
	
	public ModelRenderer tragedy() {
		setRotationAngle(RightEyebrow, -0.5009F, -0.0911F, 0.2731F);
		RightEyeTop.setPos(-2.6F, 1.6F, 0.01F);
		setRotationAngle(RightEyeTop, 0.0F, 0.0F, 0.4554F);
		RightEyeBottom.setPos(-1.1F, -0.6F, 0.01F);
		setRotationAngle(RightEyeBottom, 0.0F, 0.0F, 0.2731F);

		setRotationAngle(LeftEyebrow, -0.5009F, 0.0911F, -0.2731F);
		LeftEyeTop.setPos(2.6F, 1.7F, 0.01F);
		setRotationAngle(LeftEyeTop, 0.0F, 0.0F, -0.4554F);
		LeftEyeBottom.setPos(1.1F, -0.6F, 0.01F);
		setRotationAngle(LeftEyeBottom, 0.0F, 0.0F, -0.2731F);

		setRotationAngle(Expression, 0.0F, 0.0F, 0.0F);
		return MaskBase;
	}

	public ModelRenderer comedy() {
		setRotationAngle(RightEyebrow, -0.5009F, -0.0911F, -0.2731F);
		RightEyeTop.setPos(-0.9F, 1.6F, 0.01F);
		setRotationAngle(RightEyeTop, 0.0F, 0.0F, -0.4554F);
		RightEyeBottom.setPos(-2.1F, -0.6F, 0.01F);
		setRotationAngle(RightEyeBottom, 0.0F, 0.0F, -0.2731F);

		setRotationAngle(LeftEyebrow, -0.5009F, 0.0911F, 0.2731F);
		LeftEyeTop.setPos(0.9F, 1.7F, 0.01F);
		setRotationAngle(LeftEyeTop, 0.0F, 0.0F, 0.4554F);
		LeftEyeBottom.setPos(2.1F, -0.6F, 0.01F);
		setRotationAngle(LeftEyeBottom, 0.0F, 0.0F, 0.2731F);

		setRotationAngle(Expression, (float) Math.PI, 0.0F, 0.0F);
		return MaskBase;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		MaskBase.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}*/