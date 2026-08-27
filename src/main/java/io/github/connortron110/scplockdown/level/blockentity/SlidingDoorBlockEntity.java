package io.github.connortron110.scplockdown.level.blockentity;

import com.google.common.collect.Lists;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class SlidingDoorBlockEntity extends BlockEntity {

	/**
	 * Time it takes for a door to open in ticks.
	 */
	public static final int TIME_TO_OPEN = 23;
	/**
	 * Determines how many steps there are when opening a door.
	 * In this case 80 is chosen due to 16 "pixels" in a block. 16*5=80 so its determine this will be a smooth enough animation for most.
	 */
	public static final int MAX_OPEN = 80;

	private static final VoxelShape[] Z_DOOR_SHAPES;
	private static final VoxelShape[] X_DOOR_SHAPES;

	//	Creates the Shapes for both axis of the door opening stages
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

	/**
	 * Determines how open this door is. 0 is fully closed, 80 (or {@link MAX_OPEN}) is fully open.
	 */
	private int OpenProgress = 0;
	@Nullable private BlockPos SCP914Link = null;

	public SlidingDoorBlockEntity(BlockPos pPos, BlockState pState) {
		super(SCPBlockEntities.SLIDING_DOOR.get(), pPos, pState);
	}

	public VoxelShape getShape() {
		int index = getBlockState().getValue(SlidingDoorBlock.HINGE) == DoorHingeSide.LEFT ? 80 - OpenProgress : 80 + OpenProgress;
		if (getBlockState().getValue(SlidingDoorBlock.HORIZONTAL_AXIS) == Direction.Axis.Z) return Z_DOOR_SHAPES[index];
		else return X_DOOR_SHAPES[index];
	}

	/**
	 * Lets this door know that it's linked to an SCP-914 instance at a given location. Also configures the door to ignore redstone signals.
	 *
	 * @param scp914Pos The location of SCP-914 we want to be linked to.
	 */
	public void linkSCP914(BlockPos scp914Pos) {
		//	Sanity check to see if we are actually being linked to a 914
		if (level.getBlockEntity(scp914Pos) != null && level.getBlockEntity(scp914Pos) instanceof SCP914BlockEntity) {
			this.SCP914Link = scp914Pos;
			this.getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(SlidingDoorBlock.SIGNAL_SENSITIVE, false));
		}
	}

	/**
	 * Unlinks SCP-914 from this door. And configures the door to act as normal.
	 */
	public void unlinkSCP914() {
		//	If 914 was never linked, what are we doing here?
		if (this.SCP914Link == null) return;

		this.SCP914Link = null;
		this.getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(SlidingDoorBlock.SIGNAL_SENSITIVE, true));
	}

	/**
	 * @return True if SCP-914 has been linked to this door. False otherwise.
	 */
	public boolean isSCP914Linked() {
		return this.SCP914Link != null;
	}

	public int getOpenProgress() {
		return OpenProgress;
	}

	/**
	 * To prevent noise spam due to the sound being long, the sound only allows you to play if the door is 87.5% open/closed
	 *
	 * @return true if a sound should play
	 */
	public boolean shouldPlaySound() {
		return this.getBlockState().getValue(SlidingDoorBlock.OPEN) ? OpenProgress < 5 : OpenProgress > MAX_OPEN - 5;
	}

	/**
	 * Moves entities if they are within the doors bounding box. Prevents players from cheesing the door if it happens to close on them.
	 */
	public void checkAndPushEntities() {
		//	No point checking if an entity is in the door if its fully open
		if (OpenProgress != MAX_OPEN) {
			List<Entity> entities = level.getEntities(null, getShape().bounds().expandTowards(0, 1, 0).move(worldPosition));
			if (!entities.isEmpty()) {
				for (Entity entity : entities) {
					if (entity.getPistonPushReaction() != PushReaction.IGNORE) {

						//	Push the entity in a perpendicular direction to the door
						float x = 0, z = 0;
						if (getBlockState().getValue(SlidingDoorBlock.HORIZONTAL_AXIS) == Direction.Axis.X) {
							//	Push entity in the X Axis
							x = (entity.getX() - worldPosition.getX() > 0.5) ? 0.1F : -0.1F;
						} else {
							//	Push entity in the Z Axis
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
			OpenProgress += MAX_OPEN / TIME_TO_OPEN;
		} else {
			OpenProgress -= MAX_OPEN / TIME_TO_OPEN;
		}

		OpenProgress = Mth.clamp(OpenProgress, 0, MAX_OPEN);
		checkAndPushEntities();
	}

	private static final AABB RENDER_BOUNDS = new AABB(0, 0, 0, 1, 2, 1);

	@Override
	public AABB getRenderBoundingBox() {
		return RENDER_BOUNDS.move(worldPosition);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		OpenProgress = pTag.getInt("DoorProgress");
		if (pTag.contains("SCP914Link")) SCP914Link = BlockPos.of(pTag.getLong("SCP914Link"));
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.putInt("DoorProgress", OpenProgress);
		if (SCP914Link != null) pTag.putLong("SCP914Link", SCP914Link.asLong());
	}

	/**
	 * Allows for servers and clients to reliably sync
	 */
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = new CompoundTag();
		saveAdditional(tag);
		return tag;
	}
}
