package io.github.connortron110.scplockdown.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class SCP035MaskModel extends Model {

	private final ModelPart MaskBase;
	private final ModelPart Mask1;
	private final ModelPart Mask2;
	private final ModelPart Mask3R;
	private final ModelPart Mask4R;
	private final ModelPart RightFaceTar1;
	private final ModelPart MouthTarRight;
	private final ModelPart Mask31R;
	private final ModelPart Mask3L;
	private final ModelPart Mask4L;
	private final ModelPart LeftFaceTar1;
	private final ModelPart MouthTarLeft;
	private final ModelPart Mask31L;
	private final ModelPart Mask5;
	private final ModelPart Mask6;
	private final ModelPart Mask7M;
	private final ModelPart LeftEyeTop;
	private final ModelPart LeftEyeBottom;
	private final ModelPart RightEyeBottom;
	private final ModelPart RightEyeTop;
	private final ModelPart bone;
	private final ModelPart Nose2;
	private final ModelPart RightEyeTar;
	private final ModelPart LeftEyeTar;
	private final ModelPart Mask7R;
	private final ModelPart Mask7L;
	private final ModelPart Mask8;
	private final ModelPart Mask9;
	private final ModelPart MaskTop1;
	private final ModelPart MaskTop2;
	private final ModelPart LeftEyebrow;
	private final ModelPart RightEyebrow;
	private final ModelPart RightFaceTar3;
	private final ModelPart LeftFaceTar3;
	private final ModelPart RightCheek;
	private final ModelPart LeftCheek;
	private final ModelPart Expression;
	private final ModelPart Nose1;
	private final ModelPart RightFaceTar2;
	private final ModelPart LeftFaceTar2;
	private final ModelPart MouthTar;
	private final ModelPart Shin;

	public SCP035MaskModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.MaskBase = root.getChild("MaskBase");
		this.Mask1 = this.MaskBase.getChild("Mask1");
		this.Mask2 = this.Mask1.getChild("Mask2");
		this.Mask3R = this.Mask2.getChild("Mask3R");
		this.Mask4R = this.Mask3R.getChild("Mask4R");
		this.RightFaceTar1 = this.Mask4R.getChild("RightFaceTar1");
		this.MouthTarRight = this.Mask3R.getChild("MouthTarRight");
		this.Mask31R = this.Mask3R.getChild("Mask31R");
		this.Mask3L = this.Mask2.getChild("Mask3L");
		this.Mask4L = this.Mask3L.getChild("Mask4L");
		this.LeftFaceTar1 = this.Mask4L.getChild("LeftFaceTar1");
		this.MouthTarLeft = this.Mask3L.getChild("MouthTarLeft");
		this.Mask31L = this.Mask3L.getChild("Mask31L");
		this.Mask5 = this.Mask2.getChild("Mask5");
		this.Mask6 = this.Mask5.getChild("Mask6");
		this.Mask7M = this.Mask6.getChild("Mask7M");
		this.LeftEyeTop = this.Mask7M.getChild("LeftEyeTop");
		this.LeftEyeBottom = this.Mask7M.getChild("LeftEyeBottom");
		this.RightEyeBottom = this.Mask7M.getChild("RightEyeBottom");
		this.RightEyeTop = this.Mask7M.getChild("RightEyeTop");
		this.bone = this.RightEyeTop.getChild("bone");
		this.Nose2 = this.Mask7M.getChild("Nose2");
		this.RightEyeTar = this.Mask7M.getChild("RightEyeTar");
		this.LeftEyeTar = this.Mask7M.getChild("LeftEyeTar");
		this.Mask7R = this.Mask6.getChild("Mask7R");
		this.Mask7L = this.Mask6.getChild("Mask7L");
		this.Mask8 = this.Mask6.getChild("Mask8");
		this.Mask9 = this.Mask8.getChild("Mask9");
		this.MaskTop1 = this.Mask9.getChild("MaskTop1");
		this.MaskTop2 = this.Mask9.getChild("MaskTop2");
		this.LeftEyebrow = this.Mask9.getChild("LeftEyebrow");
		this.RightEyebrow = this.Mask9.getChild("RightEyebrow");
		this.RightFaceTar3 = this.Mask6.getChild("RightFaceTar3");
		this.LeftFaceTar3 = this.Mask6.getChild("LeftFaceTar3");
		this.RightCheek = this.Mask5.getChild("RightCheek");
		this.LeftCheek = this.Mask5.getChild("LeftCheek");
		this.Expression = this.Mask5.getChild("Expression");
		this.Nose1 = this.Mask5.getChild("Nose1");
		this.RightFaceTar2 = this.Mask5.getChild("RightFaceTar2");
		this.LeftFaceTar2 = this.Mask5.getChild("LeftFaceTar2");
		this.MouthTar = this.Mask2.getChild("MouthTar");
		this.Shin = this.MaskBase.getChild("Shin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition MaskBase = partdefinition.addOrReplaceChild("MaskBase", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-1.5F, -1.0F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Mask1 = MaskBase.addOrReplaceChild("Mask1", CubeListBuilder.create().texOffs(29, 0).mirror().addBox(-2.5F, -1.0F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Mask2 = Mask1.addOrReplaceChild("Mask2", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(-3.0F, -1.0F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Mask3R = Mask2.addOrReplaceChild("Mask3R", CubeListBuilder.create().texOffs(58, 0).addBox(-0.7F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -0.7F, 0.02F));

		PartDefinition Mask4R = Mask3R.addOrReplaceChild("Mask4R", CubeListBuilder.create().texOffs(58, 3).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.7F, -0.7F, -0.01F));

		PartDefinition RightFaceTar1 = Mask4R.addOrReplaceChild("RightFaceTar1", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, -1.0F, -0.1F));

		PartDefinition MouthTarRight = Mask3R.addOrReplaceChild("MouthTarRight", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.7F, -0.5F, -0.1F));

		PartDefinition Mask31R = Mask3R.addOrReplaceChild("Mask31R", CubeListBuilder.create().texOffs(58, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Mask3L = Mask2.addOrReplaceChild("Mask3L", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-0.3F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -0.7F, 0.02F));

		PartDefinition Mask4L = Mask3L.addOrReplaceChild("Mask4L", CubeListBuilder.create().texOffs(58, 3).mirror().addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.7F, -0.7F, -0.01F));

		PartDefinition LeftFaceTar1 = Mask4L.addOrReplaceChild("LeftFaceTar1", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.1F, -1.0F, -0.1F));

		PartDefinition MouthTarLeft = Mask3L.addOrReplaceChild("MouthTarLeft", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.7F, -0.5F, -0.1F));

		PartDefinition Mask31L = Mask3L.addOrReplaceChild("Mask31L", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Mask5 = Mask2.addOrReplaceChild("Mask5", CubeListBuilder.create().texOffs(39, 3).mirror().addBox(-4.0F, -2.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -3.4F, 0.0F));

		PartDefinition Mask6 = Mask5.addOrReplaceChild("Mask6", CubeListBuilder.create().texOffs(14, 3).mirror().addBox(-3.5F, -1.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.5F, 0.01F));

		PartDefinition Mask7M = Mask6.addOrReplaceChild("Mask7M", CubeListBuilder.create().texOffs(35, 7).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition LeftEyeTop = Mask7M.addOrReplaceChild("LeftEyeTop", CubeListBuilder.create().texOffs(25, 13).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.6F, -1.7F, 0.01F, 0.0F, 0.0F, 0.4554F));

		PartDefinition LeftEyeBottom = Mask7M.addOrReplaceChild("LeftEyeBottom", CubeListBuilder.create().texOffs(17, 13).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.1F, 0.6F, 0.01F, 0.0F, 0.0F, 0.2731F));

		PartDefinition RightEyeBottom = Mask7M.addOrReplaceChild("RightEyeBottom", CubeListBuilder.create().texOffs(17, 13).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 0.6F, 0.01F, 0.0F, 0.0F, -0.2731F));

		PartDefinition RightEyeTop = Mask7M.addOrReplaceChild("RightEyeTop", CubeListBuilder.create().texOffs(25, 13).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -1.6F, 0.01F, 0.0F, 0.0F, -0.4554F));

		PartDefinition bone = RightEyeTop.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Nose2 = Mask7M.addOrReplaceChild("Nose2", CubeListBuilder.create().texOffs(7, 5).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.7F, -0.02F));

		PartDefinition RightEyeTar = Mask7M.addOrReplaceChild("RightEyeTar", CubeListBuilder.create().texOffs(6, 9).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.6F, 0.0F, 0.05F));

		PartDefinition LeftEyeTar = Mask7M.addOrReplaceChild("LeftEyeTar", CubeListBuilder.create().texOffs(6, 9).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.6F, 0.0F, 0.05F));

		PartDefinition Mask7R = Mask6.addOrReplaceChild("Mask7R", CubeListBuilder.create().texOffs(29, 7).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 0.0F));

		PartDefinition Mask7L = Mask6.addOrReplaceChild("Mask7L", CubeListBuilder.create().texOffs(29, 7).mirror().addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, -1.0F, 0.0F));

		PartDefinition Mask8 = Mask6.addOrReplaceChild("Mask8", CubeListBuilder.create().texOffs(44, 7).mirror().addBox(-4.0F, -1.0F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition Mask9 = Mask8.addOrReplaceChild("Mask9", CubeListBuilder.create().texOffs(17, 10).mirror().addBox(-3.5F, -1.0F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition MaskTop1 = Mask9.addOrReplaceChild("MaskTop1", CubeListBuilder.create().texOffs(35, 10).mirror().addBox(-4.5F, -1.0F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, -0.25F));

		PartDefinition MaskTop2 = Mask9.addOrReplaceChild("MaskTop2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -1.0F, -0.5F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 0.25F));

		PartDefinition LeftEyebrow = Mask9.addOrReplaceChild("LeftEyebrow", CubeListBuilder.create().texOffs(13, 6).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.1F, 0.4F, 0.01F, 0.5009F, 0.0911F, 0.2731F));

		PartDefinition RightEyebrow = Mask9.addOrReplaceChild("RightEyebrow", CubeListBuilder.create().texOffs(13, 6).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 0.4F, 0.01F, 0.5009F, -0.0911F, -0.2731F));

		PartDefinition RightFaceTar3 = Mask6.addOrReplaceChild("RightFaceTar3", CubeListBuilder.create().texOffs(0, 3).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.55F, -0.08F));

		PartDefinition LeftFaceTar3 = Mask6.addOrReplaceChild("LeftFaceTar3", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -1.55F, -0.08F));

		PartDefinition RightCheek = Mask5.addOrReplaceChild("RightCheek", CubeListBuilder.create().texOffs(31, 3).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.15F, 0.0F, -0.25F));

		PartDefinition LeftCheek = Mask5.addOrReplaceChild("LeftCheek", CubeListBuilder.create().texOffs(31, 3).mirror().addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.15F, 0.0F, -0.25F));

		PartDefinition Expression = Mask5.addOrReplaceChild("Expression", CubeListBuilder.create().texOffs(55, 10).mirror().addBox(-1.0F, 0.7F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(55, 10).mirror().addBox(1.0F, -1.65F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(55, 10).addBox(-2.0F, -1.65F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.15F, 0.02F));

		PartDefinition Nose1 = Mask5.addOrReplaceChild("Nose1", CubeListBuilder.create().texOffs(6, 3).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.3F, 0.0F, -0.5463F, 0.0F, 0.0F));

		PartDefinition RightFaceTar2 = Mask5.addOrReplaceChild("RightFaceTar2", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.4F, -1.9F, -0.09F));

		PartDefinition LeftFaceTar2 = Mask5.addOrReplaceChild("LeftFaceTar2", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.4F, -1.9F, -0.09F));

		PartDefinition MouthTar = Mask2.addOrReplaceChild("MouthTar", CubeListBuilder.create().texOffs(5, 12).mirror().addBox(-2.0F, -3.0F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -0.9F, 0.03F));

		PartDefinition Shin = MaskBase.addOrReplaceChild("Shin", CubeListBuilder.create().texOffs(22, 6).mirror().addBox(-1.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.5F, -0.22F));

		return LayerDefinition.create(meshdefinition, 66, 18);
	}

	public ModelPart getMaskBase() {
//		setRotationAngle(MaskBase, 0, (float) Math.PI, 0);
		return MaskBase;
	}
	
	public ModelPart tragedy() {
		RightEyebrow.resetPose();
		RightEyeTop.resetPose();
		RightEyeBottom.resetPose();

		LeftEyebrow.resetPose();
		LeftEyeTop.resetPose();
		LeftEyeBottom.resetPose();

		Expression.resetPose();
		return MaskBase;
	}

	public ModelPart comedy() {
		RightEyebrow.loadPose(PartPose.offsetAndRotation(-1.9F, 0.4F, 0.01F, 0.5009F, 0.0911F, 0.2731F));
		RightEyeTop.loadPose(PartPose.offsetAndRotation(-0.9F, -1.7F, 0.01F, 0.0F, 0.0F, 0.4554F));
		RightEyeBottom.loadPose(PartPose.offsetAndRotation(-2.3F, 0.6F, 0.01F, 0.0F, 0.0F, 0.3604F));

		LeftEyebrow.loadPose(PartPose.offsetAndRotation(1.9F, 0.4F, 0.01F, 0.5009F, -0.0911F, -0.2731F));
		LeftEyeTop.loadPose(PartPose.offsetAndRotation(0.9F, -1.7F, 0.01F, 0.0F, 0.0F, -0.4554F));
		LeftEyeBottom.loadPose(PartPose.offsetAndRotation(2.3F, 0.6F, 0.01F, 0.0F, 0.0F, -0.3604F)); // 15+5 deg on Z

		Expression.loadPose(PartPose.offsetAndRotation(0.0F, 1.15F, 0.02F, (float) Math.PI, 0, 0));
		return MaskBase;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		MaskBase.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}