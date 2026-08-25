package io.github.connortron110.scplockdown.level.blockentity;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.level.blocks.IScrewdriverInteraction;
import io.github.connortron110.scplockdown.level.blocks.SCP914Block;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class SlidingDoorBlockEntity extends BlockEntity {

	public static final int TIME_TO_OPEN = 23; //In Ticks
	public static final int MAX_OPEN = 80;
	//0 = Closed, 80 = Open (80 because 16 pixels on a block, 16x5 means 5 moves per pixel which will be smooth enough)
	private int openProgress = 0;

	private static final VoxelShape[] Z_DOOR_SHAPES;
	private static final VoxelShape[] X_DOOR_SHAPES;

	static {
		List<VoxelShape> xShapes = Lists.newArrayList();
		List<VoxelShape> zShapes = Lists.newArrayList();
		VoxelShape centerX = Block.box(0, 0, 7, 16, 16, 9);
		VoxelShape centerZ = Block.box(7, 0, 0, 9, 16, 16);
		float steps = 16F / MAX_OPEN;
		for (int i = 0; i <= MAX_OPEN * 2; i++) {
			VoxelShape xAndShape = Block.box(16 - (i * steps), 0, 7, 32 - (i * steps), 16, 9);
			xShapes.add(Shapes.join(centerX, xAndShape, BooleanOp.AND));
			VoxelShape zAndShape = Block.box(7, 0, -16 + (i * steps), 9, 16, 0 + (i * steps));
			zShapes.add(Shapes.join(centerZ, zAndShape, BooleanOp.AND));
		}
		Z_DOOR_SHAPES = xShapes.toArray(new VoxelShape[0]);
		X_DOOR_SHAPES = zShapes.toArray(new VoxelShape[0]);
	}

	public SlidingDoorBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.SLIDING_DOOR.get(), pPos, pState);
	}

	public VoxelShape getShape() {
		int index = getBlockState().getValue(SlidingDoorBlock.HINGE) == DoorHingeSide.LEFT ? 80 - openProgress : 80 + openProgress;
		if (getBlockState().getValue(SlidingDoorBlock.HORIZONTAL_AXIS) == Direction.Axis.Z) {
			return Z_DOOR_SHAPES[index];
		} else return X_DOOR_SHAPES[index];
	}

	public int getOpenProgress() {
		return openProgress;
	}

	/**
	 * To prevent noise spam due to the sound being long, the sound only allows you to play if the door is 87.5% open/closed
	 *
	 * @return true if a sound should play
	 */
	public boolean shouldPlaySound() {
		return this.getBlockState().getValue(SlidingDoorBlock.OPEN) ? openProgress < 5 : openProgress > MAX_OPEN - 5;
	}

	/**
	 * Called from block after vanilla detects player within the blocks tile, checks for player in shape and pushes them away
	 */
	public void checkAndPushEntities() {
		if (openProgress != MAX_OPEN) {
			List<Entity> entities = level.getEntities(null, getShape().bounds().expandTowards(0, 1, 0).move(worldPosition));
			if (!entities.isEmpty()) {
				for (Entity entity : entities) {
					if (entity.getPistonPushReaction() != PushReaction.IGNORE) {

						//  Push the player in a perpendicular direction to the door
						float x = 0, z = 0;
						if (getBlockState().getValue(SlidingDoorBlock.HORIZONTAL_AXIS) == Direction.Axis.X) {
							//Push Player in the X Axis
							x = (entity.getX() - worldPosition.getX() > 0.5) ? 0.1F : -0.1F;
						} else {
							//Push Player in the Z Axis
							z = (entity.getZ() - worldPosition.getZ() > 0.5) ? 0.1F : -0.1F;
						}

						entity.move(MoverType.SHULKER_BOX, new Vec3(x, 0, z));
					}
				}
			}
		}
	}

	public void tick() {
		if (getBlockState().getValue(SlidingDoorBlock.OPEN)) {
			openProgress += MAX_OPEN / TIME_TO_OPEN;
		} else {
			openProgress -= MAX_OPEN / TIME_TO_OPEN;
		}

		openProgress = Mth.clamp(openProgress, 0, MAX_OPEN);
		checkAndPushEntities();
	}

	private static final AABB RENDER_BOUNDS = new AABB(0, 0, 0, 1, 2, 1);

	@Override
	public AABB getRenderBoundingBox() {
		return RENDER_BOUNDS.move(worldPosition);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putInt("DoorProgress", openProgress);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		openProgress = pTag.getInt("DoorProgress");
	}
}
