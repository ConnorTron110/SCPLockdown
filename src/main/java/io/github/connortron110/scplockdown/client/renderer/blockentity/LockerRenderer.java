package io.github.connortron110.scplockdown.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.SCPLayerDefinitions;
import io.github.connortron110.scplockdown.client.models.LockerModel;
import io.github.connortron110.scplockdown.level.blockentity.LockerBlockEntity;
import io.github.connortron110.scplockdown.level.blocks.LockerBlock;
import io.github.connortron110.scplockdown.utils.ClientUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LockerRenderer implements BlockEntityRenderer<LockerBlockEntity> {

	public static final List<Material> LOCKER_MATERIALS = Stream.of(LockerBlock.Type.values())
			.map(type -> new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/block/locker/locker_" + type.name))).collect(Collectors.toList());

	private final LockerModel model;

	public LockerRenderer(BlockEntityRendererProvider.Context pContext) {
		this.model = new LockerModel(pContext.bakeLayer(SCPLayerDefinitions.LOCKER));
	}

	@Override
	public void render(LockerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
		if (!pBlockEntity.hasLevel()) return;
		Direction direction = Direction.NORTH;
		Material renderMaterial = LOCKER_MATERIALS.get(0);

		BlockState blockstate = pBlockEntity.getBlockState();
		if (blockstate.getBlock() instanceof LockerBlock) {
			direction = blockstate.getValue(HorizontalDirectionalBlock.FACING);
			renderMaterial = LOCKER_MATERIALS.get(blockstate.getValue(LockerBlock.TYPE));
		}

		pPoseStack.pushPose();
		ClientUtils.tileEntityRotation(pPoseStack, 1.0D);
		Quaternionf rotation = new Quaternionf(new AxisAngle4d(Math.toRadians(direction.getOpposite().toYRot()), new Vector3f(0, 1, 0)));
		rotation.mul(rotation);
		pPoseStack.mulPose(rotation);
		float doorOpenness = pBlockEntity.getOpenNess(pPartialTick);
		doorOpenness = 1.0F - doorOpenness;
		doorOpenness = 1.0F - doorOpenness * doorOpenness * doorOpenness;
		model.setDoorRot(-(doorOpenness * ((float) Math.PI / 2F)));
		this.model.renderToBuffer(pPoseStack, renderMaterial.buffer(pBuffer, RenderType::entityCutoutNoCull), pPackedLight, pPackedOverlay, 1, 1, 1, 1);
		pPoseStack.popPose();
	}

	@Override
	public boolean shouldRenderOffScreen(LockerBlockEntity te) {
		return true;
	}
}
