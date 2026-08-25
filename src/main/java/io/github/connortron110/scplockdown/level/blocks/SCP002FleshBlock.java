package io.github.connortron110.scplockdown.level.blocks;

import com.google.common.collect.ImmutableList;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.util.Lazy;

import java.util.stream.Collectors;

/**
 * Porting removed the Tile entity that the flesh had attached. <br>
 * Swords were main source of mining, it's a bit more complicated now, so I decreased strength by 10 <br>
 * Flesh now damages player every 0.5 seconds instead of every second.
 */
public class SCP002FleshBlock extends LockdownBlock {

	private static final VoxelShape BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

	public SCP002FleshBlock(Properties properties) {
		super(properties.strength(40F).sound(SoundType.SLIME_BLOCK).noOcclusion().isValidSpawn((state, level, pos, type) -> false).speedFactor(0.2F));
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.block();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return (level.getBlockState(pos.below()).getBlock().getClass() == this.getClass()) ? Shapes.empty() : BOTTOM_SHAPE;
	}

	private boolean shouldFurniturePlaceHere(Level level, BlockPos pos) {
		return (level.getBlockState(pos.below()).getBlock().getClass() == SCP002FleshBlock.class)   //Ensure we place above a flesh block (this covers placing above the furniture)
				&& pos.getY() <= level.getMaxBuildHeight(); //Ensure we are below the build height
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		entity.setSprinting(false);
		entity.fallDistance = 0F;

		//Similar to the honey sliding
		Vec3 vector3d = entity.getDeltaMovement();
		if (vector3d.y < -0.13D) {
			double d0 = -0.05D / vector3d.y;
			entity.setDeltaMovement(new Vec3(vector3d.x * d0, -0.05D, vector3d.z * d0));
		} else {
			entity.setDeltaMovement(new Vec3(vector3d.x, -0.05D, vector3d.z));
		}

		if (entity instanceof LivingEntity) {
			if (entity.hurt(SCPDamageTypes.source(level, SCPDamageTypes.SCP002CONSUME), 2F) && ((LivingEntity) entity).isDeadOrDying()) {

				//Attempt to place furniture
				BlockPos furnitureBlockPos = pos;
				while (!level.getBlockState(furnitureBlockPos).canBeReplaced()) {
					furnitureBlockPos = furnitureBlockPos.above();
					if (!shouldFurniturePlaceHere(level, furnitureBlockPos)) return;
				}

				//Choose a random furniture block and test to see if at least one works
				attemptFurniturePlacement(state, level, furnitureBlockPos);
			}
		}
	}

	private static final ImmutableList<Lazy<Block>> FURNITURE;

	static {
		ImmutableList.Builder<Lazy<Block>> builder = ImmutableList.builder();
		builder.add(Lazy.of(SCPBlocks.SCP002_COFFEE_TABLE::get));
		builder.add(Lazy.of(SCPBlocks.SCP002_PLANT_POT::get));
		builder.add(Lazy.of(SCPBlocks.SCP002_TV::get));
		builder.add(Lazy.of(SCPBlocks.SCP002_LAMP::get));
		builder.add(Lazy.of(SCPBlocks.SCP002_ARM_CHAIR::get));
		builder.add(Lazy.of(SCPBlocks.SCP002_TABLE::get));
		FURNITURE = builder.build();
	}

	private void attemptFurniturePlacement(BlockState state, Level level, BlockPos pos) {
		Block furniture = FURNITURE.get(level.random.nextInt(FURNITURE.size())).get();
		BlockState furnitureState = furniture.defaultBlockState();

		//For Horizontal based blocks
		if (furnitureState.hasProperty(HorizontalDirectionalBlock.FACING)) {
			furnitureState = furnitureState.setValue(HorizontalDirectionalBlock.FACING, HorizontalDirectionalBlock.FACING.getAllValues().collect(Collectors.toList()).get(level.random.nextInt(HorizontalDirectionalBlock.FACING.getPossibleValues().size())).value());
			for (int i = HorizontalDirectionalBlock.FACING.getPossibleValues().size(); i > 0; i--) {
				furnitureState = furnitureState.cycle(HorizontalDirectionalBlock.FACING);
				if (furniture.canSurvive(furnitureState, level, pos)) {
					level.setBlockAndUpdate(pos, furnitureState);
					furniture.setPlacedBy(level, pos, furnitureState, null, ItemStack.EMPTY);
					return;
				}
			}
		}

		//For Axis Based Blocks
		if (furnitureState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
			furnitureState = furnitureState.setValue(BlockStateProperties.HORIZONTAL_AXIS, BlockStateProperties.HORIZONTAL_AXIS.getAllValues().collect(Collectors.toList()).get(level.random.nextInt(BlockStateProperties.HORIZONTAL_AXIS.getPossibleValues().size())).value());
			for (int i = BlockStateProperties.HORIZONTAL_AXIS.getPossibleValues().size(); i > 0; i--) {
				furnitureState = furnitureState.cycle(BlockStateProperties.HORIZONTAL_AXIS);
				if (furniture.canSurvive(furnitureState, level, pos)) {
					level.setBlockAndUpdate(pos, furnitureState);
					furniture.setPlacedBy(level, pos, furnitureState, null, ItemStack.EMPTY);
					return;
				}
			}
		}

		level.setBlockAndUpdate(pos, furnitureState);
		furniture.setPlacedBy(level, pos, furnitureState, null, ItemStack.EMPTY);
	}
}
