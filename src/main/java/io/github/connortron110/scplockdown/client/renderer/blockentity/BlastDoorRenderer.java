package io.github.connortron110.scplockdown.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.BlastDoorModel;
import io.github.connortron110.scplockdown.level.blockentity.BlastDoorBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.BlastDoorBlock;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

//	FIXME Does not render when player dies, but comes back on reload of block?!??!! (issue from 1.16.5)
public class BlastDoorRenderer implements BlockEntityRenderer<BlastDoorBlockEntity> {

	public static final Material CLOSED_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/blastdoor/blast_door_closed"));
	public static final Material OPEN_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/blastdoor/blast_door_open"));
	public static final Material OPENING_MATERIAL = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/blastdoor/blast_door_opening"));

	private final BlastDoorModel doorModel;

	public BlastDoorRenderer(BlockEntityRendererProvider.Context pContext) {
		this.doorModel = new BlastDoorModel(pContext.bakeLayer(SCPLayerDefinitions.BLAST_DOOR));
	}

	private static final Quaternionf AXIS_SWAP = Axis.YP.rotationDegrees(-90);

	@Override
	public void render(BlastDoorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) {
			this.doorModel.renderToBuffer(pPoseStack, CLOSED_MATERIAL.buffer(pBuffer, RenderType::entitySolid), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
			return;
		}

		pPoseStack.pushPose();
		ClientUtils.blockEntityRotation(pPoseStack, 1.0D);
		if (pBlockEntity.getBlockState().getValue(BlastDoorBlock.HORIZONTAL_AXIS).equals(Direction.Axis.X))
			pPoseStack.mulPose(AXIS_SWAP);
		float position = (pBlockEntity.getOpenProgress()) * 15F / (BlastDoorBlockEntity.MAX_OPEN);

		this.doorModel.DoorRight.setPos(position, 24, 0);
		this.doorModel.DoorLeft.setPos(-position, 24, 0);

		Material material;
		if (pBlockEntity.getOpenProgress() == 0) {
			material = CLOSED_MATERIAL;
		} else if (pBlockEntity.getOpenProgress() == BlastDoorBlockEntity.MAX_OPEN) {
			material = OPEN_MATERIAL;
		} else {
			material = OPENING_MATERIAL;
		}

		this.doorModel.renderToBuffer(pPoseStack, material.buffer(pBuffer, RenderType::entitySolid), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
		pPoseStack.popPose();
	}

	@Override
	public boolean shouldRenderOffScreen(BlastDoorBlockEntity te) {
		return true;
	}
}
