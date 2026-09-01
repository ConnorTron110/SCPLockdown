package io.github.connortron110.scplockdown.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.SlidingDoorModel;
import io.github.connortron110.scplockdown.level.blockentity.SlidingDoorBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.SlidingDoorBlock;
import io.github.connortron110.scplockdown.registration.SCPBlocks;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SlidingDoorRenderer implements BlockEntityRenderer<SlidingDoorBlockEntity> {

	public static final ImmutableMap<Block, Material> SLIDING_DOOR_MATERIALS;

	static {
		ImmutableMap.Builder<Block, Material> builder = ImmutableMap.builder();
		builder.put(SCPBlocks.CONTAINMENT_DOOR.get(), new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/slidingdoor/containment")));
		builder.put(SCPBlocks.SLIDING_DOOR.get(), new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/slidingdoor/sliding")));
		builder.put(SCPBlocks.MAGNETIZED_DOOR.get(), new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/slidingdoor/magnetized")));
		SLIDING_DOOR_MATERIALS = builder.build();
	}

	private final SlidingDoorModel doorModel;

	public SlidingDoorRenderer(BlockEntityRendererProvider.Context pContext) {
		this.doorModel = new SlidingDoorModel(pContext.bakeLayer(SCPLayerDefinitions.SLIDING_DOOR));
	}

	private static final Quaternionf AXIS_SWAP = new Quaternionf(new AxisAngle4d(Math.toRadians(-90), new Vector3f(0, 1, 0)));
	private static final Quaternionf HINGE_FLIP = new Quaternionf(new AxisAngle4d(Math.toRadians(180), new Vector3f(0, 1, 0)));

	@Override
	public void render(SlidingDoorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) return;

		Material renderMaterial = SLIDING_DOOR_MATERIALS.getOrDefault(pBlockEntity.getBlockState().getBlock(), null);
		if (renderMaterial == null) return; //  In case the block in the world somehow was not a sliding door block
		Direction.Axis axis = pBlockEntity.getBlockState().getValue(SlidingDoorBlock.HORIZONTAL_AXIS);
		DoorHingeSide hinge = pBlockEntity.getBlockState().getValue(SlidingDoorBlock.HINGE);

		pPoseStack.pushPose();
		ClientUtils.blockEntityRotation(pPoseStack, 1D);
		if (axis == Direction.Axis.X) pPoseStack.mulPose(AXIS_SWAP);
		if (hinge == DoorHingeSide.RIGHT) pPoseStack.mulPose(HINGE_FLIP);
		float position = (pBlockEntity.getOpenProgress()) * 1F / (SlidingDoorBlockEntity.MAX_OPEN);
		position = Mth.clamp(position, 0, 0.99F);	//	Clamp the door so it doesn't clip into the neighboring block
		pPoseStack.translate(position, 0, 0);
		this.doorModel.renderToBuffer(pPoseStack, renderMaterial.buffer(pBuffer, RenderType::entitySolid), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
		pPoseStack.popPose();
	}

	@Override
	public boolean shouldRenderOffScreen(SlidingDoorBlockEntity te) {
		return true;
	}
}
