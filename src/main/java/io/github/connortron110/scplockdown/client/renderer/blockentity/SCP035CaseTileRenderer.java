/*
package io.github.connortron110.scplockdown.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.SCP035MaskModel;
import io.github.connortron110.scplockdown.level.blocks.SCP035GlassCaseBlock;
import io.github.connortron110.scplockdown.level.tileentity.SCP035CaseTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class SCP035CaseTileRenderer extends TileEntityRenderer<SCP035CaseTileEntity> {

    public static final RenderMaterial SCP035_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation(SCPLockdown.MOD_ID, "entity/scp035"));

    private final SCP035MaskModel model = new SCP035MaskModel();

    public SCP035CaseTileRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    private static final Quaternion MASK_FLIP = new Quaternion(Vector3f.ZP, 180, true);
    private static final Quaternion MASK_LEAN = new Quaternion(Vector3f.XP, 20, true);

    @Override
    public void render(SCP035CaseTileEntity pBlockEntity, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay) {
        if (pBlockEntity.isComedy()) {
            model.comedy();
        } else {
            model.tragedy();
        }

        BlockState state = pBlockEntity.getBlockState();
        if (!state.getValue(SCP035GlassCaseBlock.EMPTY)) {
            //Center the mask and rotate depending on facing value (North | South is swapped)
            pMatrixStack.pushPose();
            pMatrixStack.translate(0.5F, 0.1F, 0.5F);
            pMatrixStack.scale(1.0F, -1.0F, -1.0F);
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(state.getValue(SCP035GlassCaseBlock.FACING).toYRot()));
            pMatrixStack.mulPose(MASK_FLIP);

            //Offset and lean on case
            pMatrixStack.translate(0F, 0F, -0.15F);
            pMatrixStack.mulPose(MASK_LEAN);
            this.model.renderToBuffer(pMatrixStack, SCP035_MATERIAL.buffer(pBuffer, RenderType::entitySolid), pCombinedLight, pCombinedOverlay, 1, 1, 1, 1);
            pMatrixStack.popPose();
        }
    }
}


 */