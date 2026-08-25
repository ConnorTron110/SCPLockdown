package io.github.connortron110.scplockdown.utils;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.AxisAngle4f;
import org.joml.Math;
import org.joml.Quaternionf;
import org.joml.Vector3d;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Can and does use up a lot of computational time, only designed to run during startup and never mid-game.
 * Always store the values created by this class and never constantly call new objects.
 */
public class VoxelShapeHelper {

	public final VoxelShape northShape;
	public VoxelShape modifiedShape;


	public VoxelShapeHelper(VoxelShape northShape) {
		this.northShape = northShape;
		this.modifiedShape = northShape;
	}

	public VoxelShapeHelper base() {
		modifiedShape = northShape;
		return this;
	}

	/**
	 * @return Returns the modified shape (after all the rotation... etc...)
	 */
	public VoxelShape getModified() {
		return modifiedShape;
	}

	public VoxelShape[] createYVoxels() {
		VoxelShape[] ret = new VoxelShape[]{modifiedShape, rotateShape(modifiedShape, Direction.Axis.Y, 90), rotateShape(modifiedShape, Direction.Axis.Y, 180), rotateShape(modifiedShape, Direction.Axis.Y, 270)};
		base();
		return ret;
	}

	public VoxelShape[] createXVoxels() {
		VoxelShape[] ret = new VoxelShape[]{modifiedShape, rotateShape(modifiedShape, Direction.Axis.X, 90), rotateShape(modifiedShape, Direction.Axis.X, 180), rotateShape(modifiedShape, Direction.Axis.X, 270)};
		base();
		return ret;
	}

	public VoxelShape[] createZVoxels() {
		VoxelShape[] ret = new VoxelShape[]{modifiedShape, rotateShape(modifiedShape, Direction.Axis.Z, 270), rotateShape(modifiedShape, Direction.Axis.Z, 180), rotateShape(modifiedShape, Direction.Axis.Z, 90)};
		base();
		return ret;
	}

	public VoxelShapeHelper rotateShapeY(int angle) {
		modifiedShape = rotateShape(modifiedShape, Direction.Axis.Y, angle);
		return this;
	}

	public VoxelShapeHelper rotateShapeX(int angle) {
		modifiedShape = rotateShape(modifiedShape, Direction.Axis.X, angle);
		return this;
	}

	public VoxelShapeHelper rotateShapeZ(int angle) {
		modifiedShape = rotateShape(modifiedShape, Direction.Axis.Z, angle);
		return this;
	}

	private static VoxelShape rotateShapeY(VoxelShape shape, double angle) {
		VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
		int times = Mth.floor(angle / 90.0D);
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
					buffer[1] = Shapes.or(buffer[1], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	/**
	 * @return Horizontal Shapes in an array indexed according to the 2D value of horizontal directions
	 */
	public static VoxelShape[] createHorizontalFacingVoxels(VoxelShape shape) {
		shape = shape.optimize();
		return new VoxelShape[]{rotateShape(shape, Direction.Axis.Y, 180), rotateShape(shape, Direction.Axis.Y, 90), shape, rotateShape(shape, Direction.Axis.Y, -90)};
	}

	/**
	 * Creates all voxels in the index of the facing values
	 *
	 * @return An array of voxels based on the 3D data of the facing
	 */
	public static VoxelShape[] createFacingVoxels(VoxelShape shape) {
		return new VoxelShape[]{rotateShapeX(shape, 270), rotateShapeX(shape, 90), shape, rotateShapeY(shape, 180), rotateShapeY(shape, 270), rotateShapeY(shape, 90)};
	}

	private static VoxelShape rotateShapeX(VoxelShape shape, double angle) {
		VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
		int times = Mth.floor(angle / 90.0D);
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
					buffer[1] = Shapes.or(buffer[1], Shapes.box(minX, minZ, 1 - maxY, maxX, maxZ, 1 - minY)));

			//  Set "current" rotation to new rotation to prepare for another rotation
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];
	}

	/**
	 * Rotates a given VoxelShape around the anti-clockwise angle of the given axis
	 *
	 * @param shape The shape we want to rotate
	 * @param axis  The Axis we want to rotate about
	 * @param angle The Positive Anti-Clockwise angle about the axis according to the right hand rule
	 * @return The Rotated Shape
	 */
	private static VoxelShape rotateShape(VoxelShape shape, Direction.Axis axis, int angle) {

		//  Snap the rotation to 90 Degrees
		int snappedRotationAngle = Mth.floor(Mth.wrapDegrees(angle) / 90F) * 90;
		if (snappedRotationAngle == 0) return shape;    //  Shouldn't be needed but just in case I do something stupid

		float angleRads = Math.toRadians(snappedRotationAngle);

		//  Get the Positive Axis Rotation Vector for the Quaternion
		AxisAngle4f angleVector;
		switch (axis) {
			case X -> angleVector = new AxisAngle4f(angleRads, 1F, 0F, 0F);
			case Y -> angleVector = new AxisAngle4f(angleRads, 0F, 1F, 0F);
			default -> angleVector = new AxisAngle4f(angleRads, 0F, 0F, 1F);
		}

		Quaternionf rotationQuaternion = new Quaternionf(angleVector);

		//  Offset the shape to center it around the point of rotation and translate each box
		AtomicReference<VoxelShape> rotatedShape = new AtomicReference<>(Shapes.empty());
		shape.move(-0.5D, -0.5D, -0.5D).forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
			Vector3d minPositions = rotationQuaternion.transform(new Vector3d(minX, minY, minZ));
			Vector3d maxPosition = rotationQuaternion.transform(new Vector3d(maxX, maxY, maxZ));

			//  Join this cube and sort min-max numbers
			rotatedShape.set(Shapes.or(rotatedShape.get(), Shapes.box(Math.min(minPositions.x, maxPosition.x), Math.min(minPositions.y, maxPosition.y), Math.min(minPositions.z, maxPosition.z), Math.max(minPositions.x, maxPosition.x), Math.max(minPositions.y, maxPosition.y), Math.max(minPositions.z, maxPosition.z))));
		});

		//  Re offset the shape and return the rotated shape, no need to optimize as in theory it should be already optimized
		return rotatedShape.get().move(0.5D, 0.5D, 0.5D);
	}

	/**
	 * @return Index value counterclockwise from North in Top-Down Direction (NWSE | 0123)
	 */
	@Deprecated
	public static int getYIndex(Direction direction) {
		return direction.getOpposite().get2DDataValue();
	}

	/**
	 * @return Index value counterclockwise from North/West in X (NDSU/WDEU | 0123)
	 */
	@Deprecated
	public static int getXZIndex(Direction direction) {
		return switch (direction) {
			case DOWN -> 1;
			case SOUTH, EAST -> 2;
			case UP -> 3;
			default -> 0;
		};
	}
}
