package io.github.connortron110.scplockdown.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public final class Utils {

	public static VoxelShape[] makeHorizontalShapes(VoxelShape northShape) {
		return new VoxelShape[]{rotateShape(Direction.SOUTH, northShape), rotateShape(Direction.WEST, northShape), northShape, rotateShape(Direction.EAST, northShape)};
	}

	public static VoxelShape[] makeHorizontalAxisShapes(VoxelShape northShape) {
		return new VoxelShape[]{northShape, rotateShapeY(northShape, 90)};
	}

	private static VoxelShape rotateShape(Direction to, VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
		int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] =
					Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	public static VoxelShape rotateShapeY(VoxelShape shape, double angle) {
		VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
		int times = Mth.floor(angle / 90.0D);
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] =
					Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	//  Client Only, Shouldn't cause an issue
	public static void createToolTip(List<Component> tooltip, String key, int amount) {
		if (!Screen.hasShiftDown()) {
			tooltip.add(Component.literal("+Shift").withStyle(ChatFormatting.YELLOW));
		} else {
			for (int i = 0; i < amount; i++) {
				String translation = "tooltip." + key + '.' + i;
				tooltip.add(Component.translatable(translation).withStyle(ChatFormatting.AQUA));
			}
		}
	}

	/**
	 * @return List of positions that are inside a given AABB
	 */
	public static List<BlockPos> boundingBoxToPositions(AABB box) {
		List<BlockPos> positions = new ArrayList<>();
		for (double x = box.minX; x <= box.maxX; x++) {
			for (double y = box.minY; y <= box.maxY; y++) {
				for (double z = box.minZ; z <= box.maxZ; z++) {
					positions.add(new BlockPos((int) x, (int) y, (int) z)); //  TODO make sure that the values round up or down and it works correctly
				}
			}
		}
		return positions;
	}

	public static void copyEntityRotationsToEntity(LivingEntity toCopy, LivingEntity toSet) {
		toSet.setYBodyRot(toCopy.yBodyRot);
		toSet.setYHeadRot(toCopy.yHeadRot);
	}

	/**
	 * @param targetEntity
	 * @param observerEntity
	 * @return True if view is obstructed
	 */
	public static boolean isViewBetweenEntitiesObstructed(Entity targetEntity, Entity observerEntity, boolean includeFluids) {
		Vec3 targetVector = targetEntity.getEyePosition(1).add(0, targetEntity.getDimensions(Pose.STANDING).height / 2, 0);
		BlockHitResult result = targetEntity.level().clip(new ClipContext(observerEntity.getEyePosition(1), targetVector, ClipContext.Block.OUTLINE, includeFluids ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE, targetEntity));
		return result.getType() == HitResult.Type.BLOCK;
	}

	/**
	 * Returns the angle that the observer is looking at in relation to the target.
	 * <p>
	 * There is a slight approximation when calculating if we are looking at the face of the target, however to explain this we need to be aware of two angles.
	 * <br> * The angle from the observer looking head on at the face of the Target
	 * <br> * The angle in relation from the targets face direction in relation to the observers position
	 * <p>
	 * The angle of the observer looking at the face of the target is absolute and linear. However, the position of the observer in relation to the target
	 * can change the outcome drastically and does not provide an accurate representation of how much the observer is looking at the face of the target.
	 * <p>
	 * We know as a fact that once the observer is 90 degrees to the direction of the targets face then the observer CANNOT see the face of the target.
	 * <p>
	 * The angle of the position in relation to the observer and target is on an exponential curve, and normalizes at 90 degrees.
	 * <br>The behavior of this angle behaves that when the angle is less than 90, the contribution it makes to the overall angle is smaller.
	 * <br>But when the angle is greater than 90, the contribution to the angle is significantly higher.
	 * <p>
	 * This in turn better reflects what the observer is actually looking at, otherwise if we were to put the angle in as is,
	 * then the observer can look directly at the face of the target, be 45 degrees to the face and given a tolerance of 50 degrees,
	 * the observer would "not be looking at the face"
	 * <p>
	 * For the face angle, generally if generally looking at the face is needed, 30 degrees is sufficient, as an observer looking at the target from 45 degrees directly at the face will be 30.
	 * It will never say if you are DIRECTLY looking at the face, but it will give an approximation for the overall angle.
	 *
	 * @param targetEntity   The target that the observer is looking at
	 * @param observerEntity The observer observing the target
	 * @param requireFace    Determines if we get the angle in relation to if the observer is looking at the targets face
	 * @return The angle in relation to the observer (either face or center of mass) looking at the target
	 */
	public static float getLookingAtAngle(Entity targetEntity, Entity observerEntity, boolean requireFace) {
		Vec3 observerLookAngle = observerEntity.getLookAngle();

		if (requireFace) {

			Vec3 targetLookAngle = targetEntity.getLookAngle().reverse();   //We get the reverse as we want the vectors to be parallel in the same direction if both are face on

			//Gets the observers relative position to the face of the target, when dot product found with targetLookAngle,
			// if the observers look angle is parallel and in the same direction with targets, the angle is 180,
			// whereas looking head on with the target will return 0
			Vec3 observersPositionInRelationToFace = targetEntity.position()
					.add(targetEntity.getLookAngle().scale(targetEntity.getDimensions(Pose.STANDING).width / 2))
					.subtract(observerEntity.position())
					.normalize();

			Vec3 targetEyesPos = targetEntity.getEyePosition(1)
					.add(targetEntity.getLookAngle().scale(targetEntity.getDimensions(Pose.STANDING).width / 2))
					.subtract(observerEntity.getEyePosition(1))
					.normalize();

			float angleInRelationToFace = (float) Math.toDegrees(Math.acos(observersPositionInRelationToFace.dot(targetLookAngle)));
			if (angleInRelationToFace > 90)
				return 90F;  //Once past 90, there is no need to calculate the rest as its guaranteed that the face cannot be seen

			//https://www.desmos.com/calculator/cpo39rljmx Red explains the curve we are using (curveAmplifier = s).
			// Originally used was green curve, but it wasn't enough. Blue was considered, however not super performance friendly.
			final float curveAmplifier = 0.25F;
			float parabolicCurveModifier = curveAmplifier * (angleInRelationToFace - 90) * (angleInRelationToFace - 90);
			angleInRelationToFace = 90 - parabolicCurveModifier;
			if (angleInRelationToFace < 0)
				angleInRelationToFace = 0;   //Cut off at 0, as curve can give negative values, which is bad

			return (float) (Math.toDegrees(Math.acos(observerLookAngle.dot(targetEyesPos))) + angleInRelationToFace);
		} else {

			Vec3 targetPos = targetEntity.position().add(0, targetEntity.getDimensions(Pose.STANDING).height / 2, 0)
					.subtract(observerEntity.getEyePosition(1)).normalize();

			return (float) Math.toDegrees(Math.acos(observerLookAngle.dot(targetPos)));
		}
	}
}
