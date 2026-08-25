package io.github.connortron110.scplockdown.client.renderer;

/*import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.blockentity.LockerBlockEntity;
import io.github.connortron110.scplockdown.level.items.SCP035MaskItem;
import io.github.connortron110.scplockdown.level.tileentity.LockerTileEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.util.Lazy;

public class ItemModelRenderer extends BlockEntityWithoutLevelRenderer {
    public static final ItemModelRenderer INSTANCE = new ItemModelRenderer();

    public static final Material SCP035MATERIAL = ForgeHooksClient.getBlockMaterial(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "entity/scp035")); //FIXME Texture needs to be square

    private final SCP035MaskModel scp035Mask = new SCP035MaskModel();

    private final Lazy<BlockEntity> locker = Lazy.of(LockerBlockEntity::new);

    public ItemModelRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 0.5D, 0.5D);
        pPoseStack.scale(0.75F, 0.75F, 0.75F);
        pPoseStack.mulPose(new Quaternion(Vector3f.YP, 180, true));
        if (item instanceof SCP035MaskItem) render035Mask(pStack, pDisplayContext, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        else if (item instanceof BlockItem) {
            TileEntityRendererDispatcher.instance.renderItem(locker.get(), pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        }




        pPoseStack.popPose();
    }


    private void render035Mask(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (pDisplayContext == ItemCameraTransforms.TransformType.GUI) {
            pPoseStack.translate(0.0D, -0.5D, 0.0D);
            pPoseStack.scale(1.4F, 1.4F, 1.4F);
            pPoseStack.mulPose(new Quaternion(new Vector3f(-1 ,1, -0.2F), 30, true));
        }

        if (pDisplayContext == ItemCameraTransforms.TransformType.FIXED) {
            pPoseStack.translate(0.0D, -0.35D, 0.0D);
            pPoseStack.mulPose(new Quaternion(Vector3f.YP, 180, true));
        }

        if (pDisplayContext == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND || pDisplayContext == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND) {
            boolean flag = pDisplayContext == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND;
            pPoseStack.translate(0.0D, -0.25D, -0.2D);
            pPoseStack.mulPose(new Quaternion(new Vector3f(0, flag ? 1 : -1, 0), 90, true));
        }

        if (pDisplayContext == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || pDisplayContext == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            boolean flag = pDisplayContext == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
            pPoseStack.scale(0.8F, 0.8F, 0.8F);
            pPoseStack.translate(flag ? 0.25D : -0.25D, 0.0D, 0.0D);
            pPoseStack.mulPose(new Quaternion(new Vector3f(0, flag ? 1 : -1, 0), 30, true));
        }

        RenderMaterial rendermaterial = SCP035MATERIAL;
        IVertexBuilder ivertexbuilder = rendermaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.scp035Mask.renderType(rendermaterial.atlasLocation()), true, pStack.hasFoil()));
        if (SCP035MaskItem.isComedy(pStack)) this.scp035Mask.comedy();
        else this.scp035Mask.tragedy();
        this.scp035Mask.getMaskBase().render(pPoseStack, ivertexbuilder, pPackedLight, pPackedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}*/