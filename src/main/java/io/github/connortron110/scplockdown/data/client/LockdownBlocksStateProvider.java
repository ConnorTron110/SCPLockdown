package io.github.connortron110.scplockdown.data.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.blocks.*;
import io.github.connortron110.scplockdown.level.blocks.pipes.*;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.registration.holders.StairSlabWallTriple;
import io.github.connortron110.scplockdown.utils.VoxelShapeHelper;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LockdownBlocksStateProvider extends BlockStateProvider {

	public LockdownBlocksStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
		super(packOutput, SCPLockdown.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(SCPBlocks.SCP002_METAL.get());
		simpleBlock(SCPBlocks.SCP002_FLESH_A.get());
		simpleBlock(SCPBlocks.SCP002_FLESH_B.get());
		simpleBlock(SCPBlocks.SCP002_FLESH_C.get());
		simpleBlock(SCPBlocks.SCP002_FLESH_D.get());
		horizontalAxis(SCPBlocks.SCP002_COFFEE_TABLE.get(), models().getExistingFile(blockRl("scp002_coffee_table")));
		simpleBlock(SCPBlocks.SCP002_PLANT_POT.get(), models().getExistingFile(blockRl("scp002_plant_pot")));
		horizontalBlock(SCPBlocks.SCP002_TV.get(), models().getExistingFile(blockRl("scp002_tv")));
		doorBlock(SCPBlocks.SCP002_DOOR.get(), blockRl("scp002_door_bottom"), blockRl("scp002_door_top"));
		getVariantBuilder(SCPBlocks.SCP002_LAMP.get())
				.partialState().with(LockdownDoubleTallBlock.HALF, DoubleBlockHalf.UPPER).modelForState().modelFile(models().getExistingFile(blockRl("scp002_lamp_top"))).addModel()
				.partialState().with(LockdownDoubleTallBlock.HALF, DoubleBlockHalf.LOWER).modelForState().modelFile(models().getExistingFile(blockRl("scp002_lamp_bottom"))).addModel();
		horizontalBlock(SCPBlocks.SCP002_ARM_CHAIR.get(), models().getExistingFile(blockRl("scp002_arm_chair")));
		horizontalBlock(SCPBlocks.SCP002_TABLE.get(), models().getExistingFile(blockRl("scp002_table")));

		//blockParticlesOnly(SCPFluids.SCP006_FOUNTAIN.getBlock(), "item/bucket/scp006_fountain_bucket");

		simpleBlock(SCPBlocks.SCP009.get(), models().cubeAll(ForgeRegistries.BLOCKS.getKey(SCPBlocks.SCP009.get()).getPath(), blockTexture(SCPBlocks.SCP009.get())).renderType(ResourceLocation.parse("translucent"), ResourceLocation.parse("solid")));

		horizontalBlock(SCPBlocks.SCP012.get(), models().getExistingFile(blockRl("scp012")));

		{   //SCP-015 Pipe
			MultiPartBlockStateBuilder builder = getMultipartBuilder(SCPBlocks.SCP015_PIPE.get())
					.part().modelFile(models().getExistingFile(blockRl("scp015_pipe_center"))).addModel().end();

			PipeBlock.PROPERTY_BY_DIRECTION.forEach((dir, value) -> {
				if (!dir.getAxis().isHorizontal()) {
					builder.part().modelFile(models().getExistingFile(blockRl("scp015_pipe_side"))).rotationX(((180 * dir.get3DDataValue()) + 90) % 360).uvLock(true).addModel()
							.condition(value, true);
				} else {
					builder.part().modelFile(models().getExistingFile(blockRl("scp015_pipe_side"))).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).addModel()
							.condition(value, true);
				}
			});
		}
		simpleBlock(SCPBlocks.SCP015_BLOCK.get());

		getVariantBuilder(SCPBlocks.SCP019.get())
				.forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models().getExistingFile(blockRl("scp019_" + (state.getValue(LockdownDoubleTallBlock.HALF) == DoubleBlockHalf.LOWER ? "bottom" : "top"))))
						.rotationY(state.getValue(SCP019Block.AXIS).choose(90, 0, 0))
						.build());

		getVariantBuilder(SCPBlocks.SCP035_GLASS_CASE.get())
				.forAllStates(state -> ConfiguredModel.builder()
						.modelFile(models().getBuilder(getPath(SCPBlocks.SCP035_GLASS_CASE) + "_" + state.getValue(SCP035GlassCaseBlock.DAMAGE))
								.parent(models().getExistingFile(blockRl("scp035_glass_case")))
								.texture("0", blockRl("scp035_glass_case_" + state.getValue(SCP035GlassCaseBlock.DAMAGE))))
						.rotationY((int) state.getValue(HorizontalDirectionalBlock.FACING).getOpposite().toYRot())
						.build());

		simpleBlock(SCPBlocks.SCP124.get());

		axisBlock(SCPBlocks.SCP143_LOG.get(), blockRl("scp143_log"));
		simpleBlock(SCPBlocks.SCP143_LEAVES.get());
		simpleBlock(SCPBlocks.SCP143_PLANKS.get());
		simpleBlock(SCPBlocks.SCP143_SAPLING.get(), models().cross(getPath(SCPBlocks.SCP143_SAPLING), blockRl("scp143_sapling")).renderType(ResourceLocation.parse("cutout")));

		simpleBlock(SCPBlocks.SCP148_ORE.get());
		simpleBlock(SCPBlocks.SCP148_BLOCK.get());

		simpleBlock(SCPBlocks.SCP330.get(), models().getExistingFile(blockRl("scp330")));

		horizontalBlock(SCPBlocks.SCP458.get(), models().getExistingFile(blockRl("scp458")));

		simpleBlock(SCPBlocks.SCP822.get(), models().getExistingFile(blockRl("scp822")));

		getVariantBuilder(SCPBlocks.SCP902.get()).forAllStates(state -> ConfiguredModel.builder().rotationY((int) state.getValue(HorizontalDirectionalBlock.FACING).getOpposite().toYRot()).modelFile(models().getExistingFile(blockRl("scp902_" + (state.getValue(SCP902Block.OPEN) ? "open" : "closed")))).build());

		horizontalBlock(SCPBlocks.SCP914.get(), models().orientable(getPath(SCPBlocks.SCP914), blockRl("scp914_metal"), blockRl("scp914_panel"), blockRl("scp914_metal")));
		simpleBlock(SCPBlocks.SCP914_METAL.get());
		simpleBlock(SCPBlocks.SCP914_GEARS.get());

		//Functional Blocks\\

		getVariantBuilder(SCPBlocks.CARD_READER.get()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
			builder.rotationY((int) state.getValue(HorizontalDirectionalBlock.FACING).getOpposite().toYRot());

			if (state.getValue(CardReaderBlock.PROG)) {
				builder.modelFile(models().getBuilder(getPath(SCPBlocks.CARD_READER) + "_prog")
						.parent(models().getExistingFile(blockRl("keycard_reader")))
						.texture("status", blockRl("yellow_flashing")));
			} else {
				//  On-Off only
				builder.modelFile(models().getBuilder(getPath(SCPBlocks.CARD_READER) + (state.getValue(CardReaderBlock.POWERED) ? "_on" : "_off"))
						.parent(models().getExistingFile(blockRl("keycard_reader")))
						.texture("status", blockRl(state.getValue(CardReaderBlock.POWERED) ? "green" : "red")));
			}

			return builder.build();
		});
		horizontalFaceBlock(SCPBlocks.HEAVY_BUTTON.get(), state -> models().getBuilder(getPath(SCPBlocks.HEAVY_BUTTON) + (state.getValue(HeavyButtonBlock.POWERED) ? "_on" : "_off"))
				.parent(models().getExistingFile(blockRl("heavy_button")))
				.texture("upper", blockRl("heavy_button_upper_" + (state.getValue(HeavyButtonBlock.POWERED) ? "on" : "off")))
				.texture("lower", blockRl("heavy_button_lower_" + (!state.getValue(HeavyButtonBlock.POWERED) ? "on" : "off"))));

		horizontalBlock(SCPBlocks.TOILET.get(), models().getExistingFile(blockRl("toilet")));
		horizontalBlock(SCPBlocks.CHAIR.get(), models().getExistingFile(blockRl("chair")));
		horizontalBlock(SCPBlocks.OFFICE_CHAIR.get(), models().getExistingFile(blockRl("office_chair")));
		simpleBlock(SCPBlocks.TABLE.get(), models().getExistingFile(blockRl("table")));
		trapdoorBlock(SCPBlocks.OPAQUE_TRAPDOOR.get(), blockRl(getPath(SCPBlocks.OPAQUE_TRAPDOOR)), true);
		simpleBlock(SCPBlocks.CEILING_LAMP.get(), models().getExistingFile(blockRl("ceiling_lamp")));
		horizontalFaceBlock(SCPBlocks.SMALL_LAMP.get(), models().getExistingFile(blockRl("small_lamp")));
		getVariantBuilder(SCPBlocks.CEILING_LIGHT.get())
				.forAllStates(state -> {
					ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
					builder.rotationY((int) state.getValue(HorizontalDirectionalBlock.FACING).getOpposite().toYRot());

					if (state.getValue(CeilingLightBlock.LIGHT_MODE).equals(CeilingLightBlock.CeilingLightMode.DAMAGED)) {
						builder.modelFile(models().getBuilder("damaged_ceiling_light")
								.parent(models().getExistingFile(blockRl("ceiling_light")))
								.texture("bars", blockRl("damaged_ceiling_light_bars")));
					} else {
						builder.modelFile(models().getExistingFile(blockRl("ceiling_light")));
					}

					return builder.build();
				});

		simpleBlock(SCPBlocks.WOOD_CRATE.get());
		simpleBlock(SCPBlocks.DARK_WOOD_CRATE.get());

		horizontalBlock(SCPBlocks.PERSONAL_COMPUTER.get(), models().orientable(getPath(SCPBlocks.PERSONAL_COMPUTER), blockRl("personal_computer_side"), blockRl("personal_computer_screen"), blockRl("personal_computer_side")));
		horizontalBlock(SCPBlocks.COMPUTER.get(), models().getExistingFile(blockRl("computer")));

		blockParticlesOnly(SCPBlocks.LOCKER, "entity/block/locker/locker_white"); // TODO correct particles for the respective correct type
		blockParticlesOnly(SCPBlocks.CONTAINMENT_DOOR, "entity/block/slidingdoor/containment");
		blockParticlesOnly(SCPBlocks.SLIDING_DOOR, "entity/block/slidingdoor/sliding");
		blockParticlesOnly(SCPBlocks.MAGNETIZED_DOOR, "entity/block/slidingdoor/magnetized");
		blockParticlesOnly(SCPBlocks.BLAST_DOOR, "entity/block/blastdoor/blast_door_closed");

		//Normal Blocks\\

		simpleBlock(SCPBlocks.CEILING.get());
		simpleBlock(SCPBlocks.CEILING_TILE.get());
		simpleBlock(SCPBlocks.CEILING_GRATE.get());

		simpleBlock(SCPBlocks.CONTAINMENT_FLOOR.get());
		simpleBlock(SCPBlocks.FLOOR_A.get());
		simpleBlock(SCPBlocks.FLOOR_B.get());
		simpleBlock(SCPBlocks.MESH_FLOOR.get(), models().getExistingFile(blockRl("mesh_floor")));

		simpleBlock(SCPBlocks.PLASTER_WALL.get());
		simpleBlock(SCPBlocks.VENT.get());
		simpleBlock(SCPBlocks.VENTILATION.get());

		simpleBlock(SCPBlocks.WALL_A.get());
		simpleColumn(SCPBlocks.WALL_B, blockRl(getPath(SCPBlocks.WALL_B)), blockRl(getPath(SCPBlocks.WALL_A)));
		simpleColumn(SCPBlocks.WALL_C, blockRl(getPath(SCPBlocks.WALL_C)), blockRl(getPath(SCPBlocks.WALL_A)));
		simpleColumn(SCPBlocks.WALL_D, blockRl(getPath(SCPBlocks.WALL_D)), blockRl(getPath(SCPBlocks.WALL_A)));
		simpleColumn(SCPBlocks.WALL_E, blockRl(getPath(SCPBlocks.WALL_E)), blockRl(getPath(SCPBlocks.WALL_A)));
		simpleColumn(SCPBlocks.WALL_F, blockRl(getPath(SCPBlocks.WALL_F)), blockRl(getPath(SCPBlocks.WALL_A)));
		simpleColumn(SCPBlocks.WALL_G, blockRl(getPath(SCPBlocks.WALL_G)), blockRl(getPath(SCPBlocks.WALL_A)));

		simpleBlock(SCPBlocks.WHITE_WALL.get());
		simpleBlock(SCPBlocks.OLD_WHITE_WALL.get());

		simpleBlock(SCPBlocks.REINFORCED_IRON.get());
		simpleBlock(SCPBlocks.STEEL.get());
		simpleBlock(SCPBlocks.REINFORCED_WALL_A.get());
		simpleBlock(SCPBlocks.REINFORCED_WALL_B.get());
		simpleBlock(SCPBlocks.REINFORCED_WALL_C.get());

		simpleBlock(SCPBlocks.EXTERIOR_BOTTOM.get());
		simpleColumn(SCPBlocks.EXTERIOR_MIDDLE, blockRl(getPath(SCPBlocks.EXTERIOR_MIDDLE)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.EXTERIOR_TOP, blockRl(getPath(SCPBlocks.EXTERIOR_TOP)), blockRl(getPath(SCPBlocks.WHITE_WALL)));

		simpleColumn(SCPBlocks.MEDICAL_BOTTOM, blockRl(getPath(SCPBlocks.MEDICAL_BOTTOM)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.MEDICAL_TOP, blockRl(getPath(SCPBlocks.MEDICAL_TOP)), blockRl(getPath(SCPBlocks.WHITE_WALL)));

		simpleColumn(SCPBlocks.OFFICE_BOTTOM, blockRl(getPath(SCPBlocks.OFFICE_BOTTOM)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.OFFICE_TOP, blockRl(getPath(SCPBlocks.OFFICE_TOP)), blockRl(getPath(SCPBlocks.WHITE_WALL)));

		simpleBlock(SCPBlocks.PIPE_WALL.get());

		simpleBlock(SCPBlocks.SUBLEVEL_WALL_A.get());
		simpleBlock(SCPBlocks.SUBLEVEL_WALL_B.get());
		simpleBlock(SCPBlocks.SUBLEVEL_WALL_C.get());
		simpleBlock(SCPBlocks.REINFORCED_SUBLEVEL_WALL_A.get());
		simpleBlock(SCPBlocks.REINFORCED_SUBLEVEL_WALL_B.get());
		simpleBlock(SCPBlocks.REINFORCED_SUBLEVEL_WALL_C.get());
		simpleBlock(SCPBlocks.METAL_SUBLEVEL.get());

		simpleBlock(SCPBlocks.GRATE.get());
		simpleBlock(SCPBlocks.BLAST_RESISTANT_GLASS.get(), models().cubeAll(ForgeRegistries.BLOCKS.getKey(SCPBlocks.BLAST_RESISTANT_GLASS.get()).getPath(), blockTexture(SCPBlocks.BLAST_RESISTANT_GLASS.get())).renderType(ResourceLocation.parse("cutout")));


		simpleColumn(SCPBlocks.ARMORY_BOTTOM_A, blockRl(getPath(SCPBlocks.ARMORY_BOTTOM_A)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.ARMORY_TOP_A, blockRl(getPath(SCPBlocks.ARMORY_TOP_A)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.ARMORY_BOTTOM_B, blockRl(getPath(SCPBlocks.ARMORY_BOTTOM_B)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.ARMORY_TOP_B, blockRl(getPath(SCPBlocks.ARMORY_TOP_B)), blockRl(getPath(SCPBlocks.WHITE_WALL)));

		simpleColumn(SCPBlocks.LABORATORY_BOTTOM_A, blockRl(getPath(SCPBlocks.LABORATORY_BOTTOM_A)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.LABORATORY_TOP_A, blockRl(getPath(SCPBlocks.LABORATORY_TOP_A)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.LABORATORY_BOTTOM_B, blockRl(getPath(SCPBlocks.LABORATORY_BOTTOM_B)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		simpleColumn(SCPBlocks.LABORATORY_TOP_B, blockRl(getPath(SCPBlocks.LABORATORY_TOP_B)), blockRl(getPath(SCPBlocks.WHITE_WALL)));

		simpleBlock(SCPBlocks.METAL_PANEL_A.get());
		simpleBlock(SCPBlocks.METAL_PANEL_B.get());
		simpleBlock(SCPBlocks.METAL_PANEL_C.get());

		simpleBlock(SCPBlocks.REINFORCED_GRANITE.get());
		simpleBlock(SCPBlocks.REINFORCED_QUARTZ.get());

		simpleColumn(SCPBlocks.GREEN_PANEL_A, blockRl(getPath(SCPBlocks.GREEN_PANEL_A)), blockRl(getPath(SCPBlocks.GREEN_PANEL_C)));
		simpleColumn(SCPBlocks.GREEN_PANEL_B, blockRl(getPath(SCPBlocks.GREEN_PANEL_B)), blockRl(getPath(SCPBlocks.GREEN_PANEL_C)));
		simpleBlock(SCPBlocks.GREEN_PANEL_C.get());
		simpleColumn(SCPBlocks.GREEN_PANEL_D, blockRl(getPath(SCPBlocks.GREEN_PANEL_D)), blockRl(getPath(SCPBlocks.GREEN_PANEL_C)));

		simpleBlock(SCPBlocks.METAL_FLOOR_A.get());
		simpleBlock(SCPBlocks.METAL_FLOOR_B.get());

		simpleBlock(SCPBlocks.METAL_WALL_A.get());
		simpleBlock(SCPBlocks.METAL_WALL_B.get());

		simpleBlock(SCPBlocks.STEEL_FLOOR.get());

		simpleBlock(SCPBlocks.HAZARD.get());

		addStairSlabWallTriple(SCPBlocks.REINFORCED_IRON_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.REINFORCED_IRON)));
		addStairSlabWallTriple(SCPBlocks.STEEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.STEEL)));
		addStairSlabWallTriple(SCPBlocks.SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.SUBLEVEL_WALL_A)));
		addStairSlabWallTriple(SCPBlocks.REINFORCED_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.REINFORCED_SUBLEVEL_WALL_A)));
		addStairSlabWallTriple(SCPBlocks.METAL_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.METAL_SUBLEVEL)));
		addStairSlabWallTriple(SCPBlocks.METAL_WALL_A_SUBLEVEL_STAIR_SLAB_WALL_TRIPLE, blockRl(getPath(SCPBlocks.METAL_WALL_A)));

		stairsBlock(SCPBlocks.WALL_STAIRS.get(), blockRl(getPath(SCPBlocks.WALL_A)));
		stairsBlock(SCPBlocks.FLOOR_A_STAIRS.get(), blockRl(getPath(SCPBlocks.FLOOR_A)));
		stairsBlock(SCPBlocks.FLOOR_B_STAIRS.get(), blockRl(getPath(SCPBlocks.FLOOR_B)));
		stairsBlock(SCPBlocks.WHITE_STAIRS.get(), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		stairsBlock(SCPBlocks.REINFORCED_WALL_A_STAIRS.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_A)));
		stairsBlock(SCPBlocks.REINFORCED_WALL_B_STAIRS.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_B)));
		stairsBlock(SCPBlocks.REINFORCED_WALL_C_STAIRS.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_C)));

		slabBlock(SCPBlocks.WALL_SLAB.get(), blockRl(getPath(SCPBlocks.WALL_A)), blockRl(getPath(SCPBlocks.WALL_A)));
		slabBlock(SCPBlocks.FLOOR_A_SLAB.get(), blockRl(getPath(SCPBlocks.FLOOR_A)), blockRl(getPath(SCPBlocks.FLOOR_A)));
		slabBlock(SCPBlocks.FLOOR_B_SLAB.get(), blockRl(getPath(SCPBlocks.FLOOR_B)), blockRl(getPath(SCPBlocks.FLOOR_B)));
		slabBlock(SCPBlocks.WHITE_SLAB.get(), blockRl(getPath(SCPBlocks.WHITE_WALL)), blockRl(getPath(SCPBlocks.WHITE_WALL)));
		slabBlock(SCPBlocks.REINFORCED_WALL_A_SLAB.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_A)), blockRl(getPath(SCPBlocks.REINFORCED_WALL_A)));
		slabBlock(SCPBlocks.REINFORCED_WALL_B_SLAB.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_B)), blockRl(getPath(SCPBlocks.REINFORCED_WALL_B)));
		slabBlock(SCPBlocks.REINFORCED_WALL_C_SLAB.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_C)), blockRl(getPath(SCPBlocks.REINFORCED_WALL_C)));

		wallBlock(SCPBlocks.REINFORCED_WALL_C_WALL.get(), blockRl(getPath(SCPBlocks.REINFORCED_WALL_C)));
		wallBlock(SCPBlocks.EXTERIOR_BOTTOM_WALL.get(), blockRl(getPath(SCPBlocks.EXTERIOR_BOTTOM)));
		wallBlock(SCPBlocks.EXTERIOR_MIDDLE_WALL.get(), blockRl(getPath(SCPBlocks.EXTERIOR_MIDDLE)));
		wallBlock(SCPBlocks.EXTERIOR_TOP_WALL.get(), blockRl(getPath(SCPBlocks.EXTERIOR_TOP)));
		wallBlock(SCPBlocks.QUARTZ_WALL.get(), ResourceLocation.parse("block/quartz_block_side"));
		wallBlock(SCPBlocks.PIPE_WALL_WALL.get(), blockRl(getPath(SCPBlocks.PIPE_WALL)));
		wallBlock(SCPBlocks.METAL_WALL_B_WALL.get(), blockRl(getPath(SCPBlocks.METAL_WALL_B)));
		wallBlock(SCPBlocks.SMOOTH_QUARTZ_WALL.get(), ResourceLocation.parse("block/quartz_block_bottom"));

		SCPBlocks.STRAIGHT_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(models().getExistingFile(blockRl("pipe_straight")))
				.rotationY(state.getValue(StraightPipeBlock.AXIS) == Direction.Axis.X ? 90 : 0)
				.rotationX(state.getValue(StraightPipeBlock.AXIS) == Direction.Axis.Y ? 90 : 0)
				.build()));

		SCPBlocks.CORNER_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(models().getExistingFile(blockRl("pipe_corner")))
				.rotationY((state.getValue(CornerPipeBlock.FACING).get2DDataValue() + 1 % 3) * 90)
				.rotationX(state.getValue(CornerPipeBlock.AXIS) == Direction.NORTH ? 0 : (state.getValue(CornerPipeBlock.AXIS) == Direction.UP ? 270 : 90))
				.build()));

		SCPBlocks.JUNC3_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
			builder.modelFile(models().getExistingFile(blockRl("pipe_3junc")));

			Direction facing = state.getValue(Junc3PipeBlock.FACING);

			if (state.getValue(Junc3PipeBlock.AXIS) == Direction.UP) {
				builder.rotationY((int) facing.getOpposite().toYRot());
			} else {
				builder.rotationX(180);
				builder.rotationY((int) facing.toYRot() - 90);
			}

			return builder.build();
		}));

		SCPBlocks.TJUNC_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

			if (!TJuncPipeBlock.isValidState(state)) {
				builder.modelFile(models().getExistingFile(blockRl("error")));
				return builder.build();
			}

			Direction.Axis axis = state.getValue(TJuncPipeBlock.AXIS);
			Direction facing = state.getValue(TJuncPipeBlock.FACING);

			if (axis == Direction.Axis.Y) {
				builder.modelFile(models().getExistingFile(blockRl("pipe_tjunc_vertical")));
				builder.rotationY((int) facing.getOpposite().toYRot());
				return builder.build();
			}

			builder.modelFile(models().getExistingFile(blockRl("pipe_tjunc")));

			if (facing.getAxis() == Direction.Axis.Y) {
				builder.rotationX(facing.getAxisDirection() == Direction.AxisDirection.POSITIVE ? 270 : 90);
				builder.rotationY(axis == Direction.Axis.X ? 0 : 90);
			} else {
				builder.rotationY((int) facing.getOpposite().toYRot());
			}

			return builder.build();
		}));

		SCPBlocks.JUNC4X_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
			builder.modelFile(models().getExistingFile(blockRl("pipe_4xjunc")));

			if (state.getValue(Junc4XPipeBlock.AXIS) != Direction.Axis.Y) {
				builder.rotationX(90);
				builder.rotationY(state.getValue(Junc4XPipeBlock.AXIS) == Direction.Axis.X ? 90 : 0);
			}

			return builder.build();
		}));

		SCPBlocks.JUNC4_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();

			if (!Junc4PipeBlock.isValidState(state)) {
				builder.modelFile(models().getExistingFile(blockRl("error")));
				return builder.build();
			}

			Direction.Axis axis = state.getValue(Junc4PipeBlock.AXIS);
			Direction facing = state.getValue(Junc4PipeBlock.FACING);

			if (axis == Direction.Axis.Y) {
				builder.modelFile(models().getExistingFile(blockRl("pipe_4junc_vertical")));
				builder.rotationY((int) facing.getOpposite().toYRot());
				return builder.build();
			}

			builder.modelFile(models().getExistingFile(blockRl("pipe_4junc")));
			builder.rotationX(VoxelShapeHelper.getXZIndex(facing) * 90);

			if (axis == Direction.Axis.Z) {
				builder.rotationY(270);
			}

			return builder.build();
		}));

		SCPBlocks.JUNC5_PIPES.getPairs().forEach(pair -> getVariantBuilder(pair.getObject()).forAllStates(state -> {
			ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
			builder.modelFile(models().getExistingFile(blockRl("pipe_5junc")));
			switch (state.getValue(Junc5PipeBlock.FACING)) {
				case UP:
					builder.rotationX(90);
					break;
				case DOWN:
					builder.rotationX(270);
					break;
				default:
					builder.rotationY((int) state.getValue(Junc5PipeBlock.FACING).toYRot());
			}

			return builder.build();
		}));

		SCPBlocks.JUNC6_PIPES.getPairs().forEach(pair -> simpleBlock(pair.getObject(), models().getExistingFile(blockRl("pipe_6junc"))));

		//Vanilla Extended Building Blocks
		stairsBlock(SCPBlocks.SMOOTH_STONE_STAIRS.get(), ResourceLocation.parse("block/smooth_stone"));

		addStairSlabWallTriple(SCPBlocks.WHITE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/white_terracotta"));
		addStairSlabWallTriple(SCPBlocks.ORANGE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/orange_terracotta"));
		addStairSlabWallTriple(SCPBlocks.MAGENTA_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/magenta_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIGHT_BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/light_blue_terracotta"));
		addStairSlabWallTriple(SCPBlocks.YELLOW_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/yellow_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIME_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/lime_terracotta"));
		addStairSlabWallTriple(SCPBlocks.PINK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/pink_terracotta"));
		addStairSlabWallTriple(SCPBlocks.GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/gray_terracotta"));
		addStairSlabWallTriple(SCPBlocks.LIGHT_GRAY_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/light_gray_terracotta"));
		addStairSlabWallTriple(SCPBlocks.CYAN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/cyan_terracotta"));
		addStairSlabWallTriple(SCPBlocks.PURPLE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/purple_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BLUE_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/blue_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BROWN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/brown_terracotta"));
		addStairSlabWallTriple(SCPBlocks.GREEN_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/green_terracotta"));
		addStairSlabWallTriple(SCPBlocks.RED_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/red_terracotta"));
		addStairSlabWallTriple(SCPBlocks.BLACK_TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/black_terracotta"));
		addStairSlabWallTriple(SCPBlocks.TERRACOTTA_STAIR_SLAB_WALL_TRIPLE, ResourceLocation.parse("block/terracotta"));

		wallBlock(SCPBlocks.STONE_WALL.get(), ResourceLocation.parse("block/stone"));
		wallBlock(SCPBlocks.POLISHED_GRANITE_WALL.get(), ResourceLocation.parse("block/polished_granite"));
		wallBlock(SCPBlocks.POLISHED_DIORITE_WALL.get(), ResourceLocation.parse("block/polished_diorite"));
		wallBlock(SCPBlocks.POLISHED_ANDESITE_WALL.get(), ResourceLocation.parse("block/polished_andesite"));
	}

	protected void addStairSlabWallTriple(StairSlabWallTriple stairSlabWall, ResourceLocation baseTexture) {
		stairsBlock(stairSlabWall.getStairs().get(), baseTexture);
		slabBlock(stairSlabWall.getSlab().get(), baseTexture, baseTexture);
		wallBlock(stairSlabWall.getWall().get(), baseTexture);
	}

	private void horizontalAxis(Block block, ModelFile model) {
		getVariantBuilder(block)
				.partialState().with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.X)
				.modelForState().modelFile(model).rotationY(90).addModel()
				.partialState().with(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z)
				.modelForState().modelFile(model).addModel();
	}

	private void blockParticlesOnly(RegistryObject<? extends Block> reg, String particleTexture) {
		simpleBlock(reg.get(), ConfiguredModel.builder().modelFile(
				models().getBuilder(getPath(reg))
						.parent(models().getExistingFile(blockRl("empty_particles")))
						.texture("particle", ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, particleTexture))).build());
	}

	private void simpleColumn(RegistryObject<? extends Block> reg, ResourceLocation side, ResourceLocation end) {
		simpleBlock(reg.get(), models().cubeColumn(getPath(reg), side, end));
	}

	private ResourceLocation blockRl(String loc) {
		return ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "block/" + loc);
	}

	private String getPath(RegistryObject<?> reg) {
		return reg.getId().getPath();
	}
}
