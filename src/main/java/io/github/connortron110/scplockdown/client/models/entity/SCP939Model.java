package io.github.connortron110.scplockdown.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.connortron110.scplockdown.level.entity.SCP939Entity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SCP939Model<T extends SCP939Entity> extends EntityModel<T> {
	private final ModelPart hips;
	private final ModelPart body_1;
	private final ModelPart body_2;
	private final ModelPart arm_upper_left;
	private final ModelPart elbow_left;
	private final ModelPart arm_lower_left;
	private final ModelPart hand_left;
	private final ModelPart finger_left_1;
	private final ModelPart claw_front_left_1;
	private final ModelPart finger_left_3;
	private final ModelPart claw_front_left_3;
	private final ModelPart finger_left_2;
	private final ModelPart claw_front_left_2;
	private final ModelPart arm_upper_right;
	private final ModelPart elbow_right;
	private final ModelPart arm_lower_right;
	private final ModelPart hand_right;
	private final ModelPart finger_right_3;
	private final ModelPart claw_front_right_3;
	private final ModelPart finger_right_1;
	private final ModelPart claw_front_right_1;
	private final ModelPart finger_right_2;
	private final ModelPart claw_front_right_2;
	private final ModelPart back_2;
	private final ModelPart sensors_inner_2;
	private final ModelPart neck_1;
	private final ModelPart neck_2;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart jaw_lower_1;
	private final ModelPart jaw_lower_2;
	private final ModelPart jaw_lower_3;
	private final ModelPart teeth_lower_31;
	private final ModelPart teeth_lower_32;
	private final ModelPart teeth_lower_33;
	private final ModelPart teeth_lower_34;
	private final ModelPart teeth_lower_2;
	private final ModelPart teeth_lower_1;
	private final ModelPart cheeks_left;
	private final ModelPart cheeks_right;
	private final ModelPart jaw_upper_1;
	private final ModelPart jaw_upper_2;
	private final ModelPart jaw_upper_3;
	private final ModelPart snout_2;
	private final ModelPart teeth_upper_31;
	private final ModelPart teeth_upper_32;
	private final ModelPart teeth_upper_33;
	private final ModelPart teeth_upper_34;
	private final ModelPart teeth_upper_35;
	private final ModelPart snout_1;
	private final ModelPart teeth_upper_1;
	private final ModelPart back_3;
	private final ModelPart sensors_inner_1;
	private final ModelPart back_1;
	private final ModelPart sensors_inner_3;
	private final ModelPart leg_left;
	private final ModelPart knee_left;
	private final ModelPart ankle_left;
	private final ModelPart foot_left;
	private final ModelPart toe_left_1;
	private final ModelPart claw_back_left_1;
	private final ModelPart toe_left_3;
	private final ModelPart claw_back_left_3;
	private final ModelPart toe_left_2;
	private final ModelPart claw_back_left_2;
	private final ModelPart leg_right;
	private final ModelPart knee_right;
	private final ModelPart ankle_right;
	private final ModelPart foot_right;
	private final ModelPart toe_right_3;
	private final ModelPart claw_back_right_3;
	private final ModelPart toe_right_1;
	private final ModelPart claw_back_right_1;
	private final ModelPart toe_right_2;
	private final ModelPart claw_back_right_2;
	private final ModelPart tail_1;
	private final ModelPart tail_2;
	private final ModelPart tail_3;
	private final ModelPart sensors_inner_4;

	public SCP939Model(ModelPart root) {
		this.hips = root.getChild("hips");
		this.body_1 = this.hips.getChild("body_1");
		this.body_2 = this.body_1.getChild("body_2");
		this.arm_upper_left = this.body_2.getChild("arm_upper_left");
		this.elbow_left = this.arm_upper_left.getChild("elbow_left");
		this.arm_lower_left = this.elbow_left.getChild("arm_lower_left");
		this.hand_left = this.arm_lower_left.getChild("hand_left");
		this.finger_left_1 = this.hand_left.getChild("finger_left_1");
		this.claw_front_left_1 = this.finger_left_1.getChild("claw_front_left_1");
		this.finger_left_3 = this.hand_left.getChild("finger_left_3");
		this.claw_front_left_3 = this.finger_left_3.getChild("claw_front_left_3");
		this.finger_left_2 = this.hand_left.getChild("finger_left_2");
		this.claw_front_left_2 = this.finger_left_2.getChild("claw_front_left_2");
		this.arm_upper_right = this.body_2.getChild("arm_upper_right");
		this.elbow_right = this.arm_upper_right.getChild("elbow_right");
		this.arm_lower_right = this.elbow_right.getChild("arm_lower_right");
		this.hand_right = this.arm_lower_right.getChild("hand_right");
		this.finger_right_3 = this.hand_right.getChild("finger_right_3");
		this.claw_front_right_3 = this.finger_right_3.getChild("claw_front_right_3");
		this.finger_right_1 = this.hand_right.getChild("finger_right_1");
		this.claw_front_right_1 = this.finger_right_1.getChild("claw_front_right_1");
		this.finger_right_2 = this.hand_right.getChild("finger_right_2");
		this.claw_front_right_2 = this.finger_right_2.getChild("claw_front_right_2");
		this.back_2 = this.body_2.getChild("back_2");
		this.sensors_inner_2 = this.back_2.getChild("sensors_inner_2");
		this.neck_1 = this.body_2.getChild("neck_1");
		this.neck_2 = this.neck_1.getChild("neck_2");
		this.head = this.neck_2.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.jaw_lower_1 = this.jaw.getChild("jaw_lower_1");
		this.jaw_lower_2 = this.jaw_lower_1.getChild("jaw_lower_2");
		this.jaw_lower_3 = this.jaw_lower_2.getChild("jaw_lower_3");
		this.teeth_lower_31 = this.jaw_lower_3.getChild("teeth_lower_31");
		this.teeth_lower_32 = this.jaw_lower_3.getChild("teeth_lower_32");
		this.teeth_lower_33 = this.jaw_lower_3.getChild("teeth_lower_33");
		this.teeth_lower_34 = this.jaw_lower_2.getChild("teeth_lower_34");
		this.teeth_lower_2 = this.jaw_lower_2.getChild("teeth_lower_2");
		this.teeth_lower_1 = this.jaw_lower_1.getChild("teeth_lower_1");
		this.cheeks_left = this.jaw.getChild("cheeks_left");
		this.cheeks_right = this.jaw.getChild("cheeks_right");
		this.jaw_upper_1 = this.head.getChild("jaw_upper_1");
		this.jaw_upper_2 = this.jaw_upper_1.getChild("jaw_upper_2");
		this.jaw_upper_3 = this.jaw_upper_2.getChild("jaw_upper_3");
		this.snout_2 = this.jaw_upper_3.getChild("snout_2");
		this.teeth_upper_31 = this.jaw_upper_3.getChild("teeth_upper_31");
		this.teeth_upper_32 = this.jaw_upper_3.getChild("teeth_upper_32");
		this.teeth_upper_33 = this.jaw_upper_3.getChild("teeth_upper_33");
		this.teeth_upper_34 = this.jaw_upper_2.getChild("teeth_upper_34");
		this.teeth_upper_35 = this.jaw_upper_2.getChild("teeth_upper_35");
		this.snout_1 = this.jaw_upper_1.getChild("snout_1");
		this.teeth_upper_1 = this.jaw_upper_1.getChild("teeth_upper_1");
		this.back_3 = this.neck_1.getChild("back_3");
		this.sensors_inner_1 = this.back_3.getChild("sensors_inner_1");
		this.back_1 = this.body_1.getChild("back_1");
		this.sensors_inner_3 = this.back_1.getChild("sensors_inner_3");
		this.leg_left = this.hips.getChild("leg_left");
		this.knee_left = this.leg_left.getChild("knee_left");
		this.ankle_left = this.knee_left.getChild("ankle_left");
		this.foot_left = this.ankle_left.getChild("foot_left");
		this.toe_left_1 = this.foot_left.getChild("toe_left_1");
		this.claw_back_left_1 = this.toe_left_1.getChild("claw_back_left_1");
		this.toe_left_3 = this.foot_left.getChild("toe_left_3");
		this.claw_back_left_3 = this.toe_left_3.getChild("claw_back_left_3");
		this.toe_left_2 = this.foot_left.getChild("toe_left_2");
		this.claw_back_left_2 = this.toe_left_2.getChild("claw_back_left_2");
		this.leg_right = this.hips.getChild("leg_right");
		this.knee_right = this.leg_right.getChild("knee_right");
		this.ankle_right = this.knee_right.getChild("ankle_right");
		this.foot_right = this.ankle_right.getChild("foot_right");
		this.toe_right_3 = this.foot_right.getChild("toe_right_3");
		this.claw_back_right_3 = this.toe_right_3.getChild("claw_back_right_3");
		this.toe_right_1 = this.foot_right.getChild("toe_right_1");
		this.claw_back_right_1 = this.toe_right_1.getChild("claw_back_right_1");
		this.toe_right_2 = this.foot_right.getChild("toe_right_2");
		this.claw_back_right_2 = this.toe_right_2.getChild("claw_back_right_2");
		this.tail_1 = this.hips.getChild("tail_1");
		this.tail_2 = this.tail_1.getChild("tail_2");
		this.tail_3 = this.tail_2.getChild("tail_3");
		this.sensors_inner_4 = this.hips.getChild("sensors_inner_4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hips = partdefinition.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, 0.0F, 7.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 6.7F, -0.5009F, 0.0F, 0.0F));

		PartDefinition body_1 = hips.addOrReplaceChild("body_1", CubeListBuilder.create().texOffs(0, 17).addBox(-4.5F, -3.5F, -9.0F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 1.8F, 0.2731F, 0.0F, 0.0F));

		PartDefinition body_2 = body_1.addOrReplaceChild("body_2", CubeListBuilder.create().texOffs(0, 35).addBox(-5.0F, -4.0F, -9.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, -8.0F, 0.182F, 0.0F, 0.0F));

		PartDefinition arm_upper_left = body_2.addOrReplaceChild("arm_upper_left", CubeListBuilder.create().texOffs(42, 26).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -1.93F, -6.43F, 0.3187F, 0.207F, -0.1665F));

		PartDefinition elbow_left = arm_upper_left.addOrReplaceChild("elbow_left", CubeListBuilder.create().texOffs(44, 42).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, 10.6F, 1.1F, -1.2292F, 0.0F, 0.1412F));

		PartDefinition arm_lower_left = elbow_left.addOrReplaceChild("arm_lower_left", CubeListBuilder.create().texOffs(33, 55).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.02F, 8.1F, 0.3F, 0.7741F, -0.0456F, 0.0456F));

		PartDefinition hand_left = arm_lower_left.addOrReplaceChild("hand_left", CubeListBuilder.create().texOffs(48, 56).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, 6.7F, -1.6F, 0.2731F, 0.0F, 0.0F));

		PartDefinition finger_left_1 = hand_left.addOrReplaceChild("finger_left_1", CubeListBuilder.create().texOffs(50, 63).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.2F, 1.0F, -0.0911F, 0.2276F, 0.0F));

		PartDefinition claw_front_left_1 = finger_left_1.addOrReplaceChild("claw_front_left_1", CubeListBuilder.create().texOffs(45, 62).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition finger_left_3 = hand_left.addOrReplaceChild("finger_left_3", CubeListBuilder.create().texOffs(50, 69).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.2F, 1.0F, -0.0911F, -0.3187F, 0.0F));

		PartDefinition claw_front_left_3 = finger_left_3.addOrReplaceChild("claw_front_left_3", CubeListBuilder.create().texOffs(41, 71).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition finger_left_2 = hand_left.addOrReplaceChild("finger_left_2", CubeListBuilder.create().texOffs(31, 67).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.2F, 1.0F, -0.0911F, 0.0F, 0.0F));

		PartDefinition claw_front_left_2 = finger_left_2.addOrReplaceChild("claw_front_left_2", CubeListBuilder.create().texOffs(33, 72).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition arm_upper_right = body_2.addOrReplaceChild("arm_upper_right", CubeListBuilder.create().texOffs(61, 23).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -1.93F, -6.43F, 0.3187F, -0.207F, 0.1665F));

		PartDefinition elbow_right = arm_upper_right.addOrReplaceChild("elbow_right", CubeListBuilder.create().texOffs(62, 39).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, 10.6F, 1.1F, -1.2292F, 0.0F, -0.1412F));

		PartDefinition arm_lower_right = elbow_right.addOrReplaceChild("arm_lower_right", CubeListBuilder.create().texOffs(62, 53).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 8.1F, 0.3F, 0.7741F, 0.0456F, -0.0456F));

		PartDefinition hand_right = arm_lower_right.addOrReplaceChild("hand_right", CubeListBuilder.create().texOffs(64, 66).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, 6.7F, -1.6F, 0.2731F, 0.0F, 0.0F));

		PartDefinition finger_right_3 = hand_right.addOrReplaceChild("finger_right_3", CubeListBuilder.create().texOffs(50, 69).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.2F, 1.0F, -0.0911F, 0.2276F, 0.0F));

		PartDefinition claw_front_right_3 = finger_right_3.addOrReplaceChild("claw_front_right_3", CubeListBuilder.create().texOffs(41, 71).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition finger_right_1 = hand_right.addOrReplaceChild("finger_right_1", CubeListBuilder.create().texOffs(50, 63).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.2F, 1.0F, -0.0911F, -0.3187F, 0.0F));

		PartDefinition claw_front_right_1 = finger_right_1.addOrReplaceChild("claw_front_right_1", CubeListBuilder.create().texOffs(45, 62).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition finger_right_2 = hand_right.addOrReplaceChild("finger_right_2", CubeListBuilder.create().texOffs(31, 67).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.2F, 1.0F, -0.0911F, 0.0F, 0.0F));

		PartDefinition claw_front_right_2 = finger_right_2.addOrReplaceChild("claw_front_right_2", CubeListBuilder.create().texOffs(33, 72).addBox(-0.5F, 0.0F, -0.3F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, 1.6F, 0.6829F, 0.0F, 0.0F));

		PartDefinition back_2 = body_2.addOrReplaceChild("back_2", CubeListBuilder.create().texOffs(0, 80).addBox(-3.0F, -3.0F, -4.5F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.6F, -4.4F, 0.0175F, 0.0F, 0.0F));

		PartDefinition sensors_inner_2 = back_2.addOrReplaceChild("sensors_inner_2", CubeListBuilder.create().texOffs(107, 29).addBox(0.0F, -8.0F, -4.5F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.02F, -2.8F, 0.2F));

		PartDefinition neck_1 = body_2.addOrReplaceChild("neck_1", CubeListBuilder.create().texOffs(0, 53).addBox(-4.0F, -3.5F, -5.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.03F, -8.76F, 0.2731F, 0.0F, 0.0F));

		PartDefinition neck_2 = neck_1.addOrReplaceChild("neck_2", CubeListBuilder.create().texOffs(0, 102).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.2F, -5.0F, -0.1367F, 0.0F, 0.0F));

		PartDefinition head = neck_2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(37, 0).addBox(-3.5F, -2.0F, -4.0F, 7.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -5.8F, 0.182F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(37, 10).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.0456F, 0.0F, 0.0F));

		PartDefinition jaw_lower_1 = jaw.addOrReplaceChild("jaw_lower_1", CubeListBuilder.create().texOffs(63, 10).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.05F, -4.7F, 0.1274F, 0.0F, 0.0F));

		PartDefinition jaw_lower_2 = jaw_lower_1.addOrReplaceChild("jaw_lower_2", CubeListBuilder.create().texOffs(85, 8).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.6F, -0.182F, 0.0F, 0.0F));

		PartDefinition jaw_lower_3 = jaw_lower_2.addOrReplaceChild("jaw_lower_3", CubeListBuilder.create().texOffs(91, 14).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.1F, -0.0456F, 0.0F, 0.0F));

		PartDefinition teeth_lower_31 = jaw_lower_3.addOrReplaceChild("teeth_lower_31", CubeListBuilder.create().texOffs(119, 12).addBox(0.0F, -3.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.55F, 0.1F, -1.5F, 0.0F, 0.0F, 0.2276F));

		PartDefinition teeth_lower_32 = jaw_lower_3.addOrReplaceChild("teeth_lower_32", CubeListBuilder.create().texOffs(119, 16).addBox(0.0F, -3.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.55F, 0.1F, -1.5F, 0.0F, 0.0F, -0.2276F));

		PartDefinition teeth_lower_33 = jaw_lower_3.addOrReplaceChild("teeth_lower_33", CubeListBuilder.create().texOffs(110, 14).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, -2.95F, 0.2276F, 0.0F, 0.0F));

		PartDefinition teeth_lower_34 = jaw_lower_2.addOrReplaceChild("teeth_lower_34", CubeListBuilder.create().texOffs(119, 4).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.05F, 0.1F, -2.0F, 0.0F, 0.0F, 0.2276F));

		PartDefinition teeth_lower_2 = jaw_lower_2.addOrReplaceChild("teeth_lower_2", CubeListBuilder.create().texOffs(119, 8).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.95F, 0.1F, -2.1F, 0.0F, 0.0F, -0.2276F));

		PartDefinition teeth_lower_1 = jaw_lower_1.addOrReplaceChild("teeth_lower_1", CubeListBuilder.create().texOffs(30, 20).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.7F));

		PartDefinition cheeks_left = jaw.addOrReplaceChild("cheeks_left", CubeListBuilder.create().texOffs(66, 14).addBox(0.0F, -5.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, 1.0F, -5.3F, -0.3821F, -0.1367F, 0.0F));

		PartDefinition cheeks_right = jaw.addOrReplaceChild("cheeks_right", CubeListBuilder.create().texOffs(76, 14).addBox(0.0F, -5.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, 1.0F, -5.3F, -0.3821F, 0.1367F, 0.0F));

		PartDefinition jaw_upper_1 = head.addOrReplaceChild("jaw_upper_1", CubeListBuilder.create().texOffs(61, 0).addBox(-2.5F, -0.5F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.6F, -3.2F, 0.1274F, 0.0F, 0.0F));

		PartDefinition jaw_upper_2 = jaw_upper_1.addOrReplaceChild("jaw_upper_2", CubeListBuilder.create().texOffs(84, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6F, -4.1F, -0.182F, 0.0F, 0.0F));

		PartDefinition jaw_upper_3 = jaw_upper_2.addOrReplaceChild("jaw_upper_3", CubeListBuilder.create().texOffs(103, 8).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9F, -3.4F, -0.0456F, 0.0F, 0.0F));

		PartDefinition snout_2 = jaw_upper_3.addOrReplaceChild("snout_2", CubeListBuilder.create().texOffs(117, 2).addBox(-1.5F, -0.5F, -2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.02F, -0.85F, -0.58F, 0.7007F, 0.0F, 0.0F));

		PartDefinition teeth_upper_31 = jaw_upper_3.addOrReplaceChild("teeth_upper_31", CubeListBuilder.create().texOffs(0, -2).addBox(0.0F, 0.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 2.0F, -0.95F, 0.0F, 0.0F, -0.2276F));

		PartDefinition teeth_upper_32 = jaw_upper_3.addOrReplaceChild("teeth_upper_32", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, 0.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.0F, -0.95F, 0.0F, 0.0F, 0.2276F));

		PartDefinition teeth_upper_33 = jaw_upper_3.addOrReplaceChild("teeth_upper_33", CubeListBuilder.create().texOffs(110, 18).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.5F, -0.2276F, 0.0F, 0.0F));

		PartDefinition teeth_upper_34 = jaw_upper_2.addOrReplaceChild("teeth_upper_34", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -2.05F, 0.0F, 0.0F, -0.2276F));

		PartDefinition teeth_upper_35 = jaw_upper_2.addOrReplaceChild("teeth_upper_35", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.0F, -2.05F, 0.0F, 0.0F, 0.2276F));

		PartDefinition snout_1 = jaw_upper_1.addOrReplaceChild("snout_1", CubeListBuilder.create().texOffs(102, 0).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.05F, -4.28F, -0.0087F, 0.0F, 0.0F));

		PartDefinition teeth_upper_1 = jaw_upper_1.addOrReplaceChild("teeth_upper_1", CubeListBuilder.create().texOffs(46, 19).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -2.9F));

		PartDefinition back_3 = neck_1.addOrReplaceChild("back_3", CubeListBuilder.create().texOffs(0, 93).addBox(-2.5F, -3.5F, -6.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.95F, 2.4F, 0.3168F, 0.0F, 0.0F));

		PartDefinition sensors_inner_1 = back_3.addOrReplaceChild("sensors_inner_1", CubeListBuilder.create().texOffs(109, 19).addBox(0.0F, -9.0F, -3.5F, 0.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, -2.3F, -3.4F, -0.4098F, 0.0F, 0.0F));

		PartDefinition back_1 = body_1.addOrReplaceChild("back_1", CubeListBuilder.create().texOffs(2, 67).addBox(-2.5F, -3.0F, 0.0F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, -2.5F, -9.4F, -0.2276F, 0.0F, 0.0F));

		PartDefinition sensors_inner_3 = back_1.addOrReplaceChild("sensors_inner_3", CubeListBuilder.create().texOffs(107, 39).addBox(0.0F, -9.0F, -4.5F, 0.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, -1.8F, 4.8F, 0.2731F, 0.0F, 0.0F));

		PartDefinition leg_left = hips.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(91, 22).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -0.9F, 6.4F, 0.2276F, -0.1367F, -0.2276F));

		PartDefinition knee_left = leg_left.addOrReplaceChild("knee_left", CubeListBuilder.create().texOffs(92, 38).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3F, 9.8F, -2.4F, 0.9849F, 0.1021F, 0.236F));

		PartDefinition ankle_left = knee_left.addOrReplaceChild("ankle_left", CubeListBuilder.create().texOffs(92, 53).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.6F, 2.8F, -0.7741F, 0.0F, 0.0F));

		PartDefinition foot_left = ankle_left.addOrReplaceChild("foot_left", CubeListBuilder.create().texOffs(76, 27).addBox(-1.5F, 0.0F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.3F, -0.7F, 0.0911F, -0.0456F, -0.0456F));

		PartDefinition toe_left_1 = foot_left.addOrReplaceChild("toe_left_1", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -3.1F, -0.182F, -0.4554F, 0.0F));

		PartDefinition claw_back_left_1 = toe_left_1.addOrReplaceChild("claw_back_left_1", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition toe_left_3 = foot_left.addOrReplaceChild("toe_left_3", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -3.0F, -0.182F, 0.4554F, 0.0F));

		PartDefinition claw_back_left_3 = toe_left_3.addOrReplaceChild("claw_back_left_3", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition toe_left_2 = foot_left.addOrReplaceChild("toe_left_2", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.1F, -0.182F, 0.0F, 0.0F));

		PartDefinition claw_back_left_2 = toe_left_2.addOrReplaceChild("claw_back_left_2", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.02F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition leg_right = hips.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(106, 66).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -0.9F, 6.4F, 0.2276F, 0.1367F, 0.2276F));

		PartDefinition knee_right = leg_right.addOrReplaceChild("knee_right", CubeListBuilder.create().texOffs(90, 68).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3F, 9.8F, -2.4F, 0.9849F, -0.1021F, -0.236F));

		PartDefinition ankle_right = knee_right.addOrReplaceChild("ankle_right", CubeListBuilder.create().texOffs(77, 70).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.6F, 2.8F, -0.7741F, 0.0F, 0.0F));

		PartDefinition foot_right = ankle_right.addOrReplaceChild("foot_right", CubeListBuilder.create().texOffs(57, 73).addBox(-1.5F, 0.0F, -3.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.3F, -0.7F, 0.0911F, 0.0456F, 0.0456F));

		PartDefinition toe_right_3 = foot_right.addOrReplaceChild("toe_right_3", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -3.1F, -0.182F, -0.4554F, 0.0F));

		PartDefinition claw_back_right_3 = toe_right_3.addOrReplaceChild("claw_back_right_3", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition toe_right_1 = foot_right.addOrReplaceChild("toe_right_1", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -3.0F, -0.182F, 0.4554F, 0.0F));

		PartDefinition claw_back_right_1 = toe_right_1.addOrReplaceChild("claw_back_right_1", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition toe_right_2 = foot_right.addOrReplaceChild("toe_right_2", CubeListBuilder.create().texOffs(113, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.1F, -0.182F, 0.0F, 0.0F));

		PartDefinition claw_back_right_2 = toe_right_2.addOrReplaceChild("claw_back_right_2", CubeListBuilder.create().texOffs(104, 82).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.4098F, 0.0F, 0.0F));

		PartDefinition tail_1 = hips.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(0, 117).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.4F, 8.0F, -0.6829F, 0.0F, 0.0F));

		PartDefinition tail_2 = tail_1.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(18, 118).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.8F, -0.1367F, 0.0F, 0.0F));

		PartDefinition tail_3 = tail_2.addOrReplaceChild("tail_3", CubeListBuilder.create().texOffs(14, 117).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.8F, 0.0911F, 0.0F, 0.0F));

		PartDefinition sensors_inner_4 = hips.addOrReplaceChild("sensors_inner_4", CubeListBuilder.create().texOffs(109, 52).addBox(0.0F, -5.0F, -3.5F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.02F, -2.9F, 4.2F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(SCP939Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float frontUpperLegResting = (float) Math.toRadians(18.26);
		float frontElbowLegResting = (float) Math.toRadians(-70.43);
		float backUpperLegResting = (float) Math.toRadians(13.04);
		float backLowerLegResting = (float) Math.toRadians(56.43);

		float neck2RestingX = (float) Math.toRadians(7.83);
		float headRestingX = (float) Math.toRadians(-10.43);
		float jawRestingX = (float) Math.toRadians(2.61);

		//  18 Degrees back and forth
		float frontUpperLegSwing = (float) Math.toRadians(25 * Mth.sin(limbSwing));
		float frontLowerLegSwing = (float) Math.toRadians(20 * Mth.cos(limbSwing));

		//  phase shift the back legs by a very small amount
		float backUpperLegSwing = (float) Math.toRadians(50 * Mth.sin((float) (limbSwing + (Math.PI / 2))));
		float backLowerLegSwing = (float) Math.toRadians(20 * Mth.cos((float) (limbSwing + (Math.PI / 2))));

		//  Front legs
		arm_upper_left.xRot = frontUpperLegResting + (frontUpperLegSwing * limbSwingAmount);
		arm_upper_right.xRot = frontUpperLegResting - (frontUpperLegSwing * limbSwingAmount);

		elbow_left.xRot = frontElbowLegResting - (frontLowerLegSwing * limbSwingAmount);
		elbow_right.xRot = frontElbowLegResting + (frontLowerLegSwing * limbSwingAmount);

		//  Back legs
		leg_right.xRot = backUpperLegResting - (backUpperLegSwing * limbSwingAmount);
		leg_left.xRot = backUpperLegResting + (backUpperLegSwing * limbSwingAmount);

		knee_right.xRot = backLowerLegResting + (backLowerLegSwing * limbSwingAmount);
		knee_left.xRot = backLowerLegResting - (backLowerLegSwing * limbSwingAmount);

		//  Head
		neck_1.yRot = (float) (Math.toRadians(netHeadYaw) / 3);
		neck_2.yRot = (float) (Math.toRadians(netHeadYaw) / 3);
		head.yRot = (float) (Math.toRadians(netHeadYaw) / 3);

		neck_2.xRot = (float) (neck2RestingX + Math.toRadians(headPitch) / 2);
		head.xRot = (float) (headRestingX + Math.toRadians(headPitch) / 2);

		//  Mouth "Breathing"
		jaw.xRot = (float) (jawRestingX - Math.toRadians(5 * (Math.sin((ageInTicks / 20)) - 1)));

		//  Spines
		float spineSwayDegrees = 5;
		sensors_inner_1.zRot = (float) Math.toRadians(spineSwayDegrees * Math.sin((ageInTicks / 20)));
		sensors_inner_2.zRot = (float) Math.toRadians(spineSwayDegrees * Math.sin((ageInTicks / 20) - (Math.PI / 4)));
		sensors_inner_3.zRot = (float) Math.toRadians(spineSwayDegrees * Math.sin((ageInTicks / 20) - (2 * Math.PI / 4)));
		sensors_inner_4.zRot = (float) Math.toRadians(spineSwayDegrees * Math.sin((ageInTicks / 20) - (3 * Math.PI / 4)));
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hips.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
