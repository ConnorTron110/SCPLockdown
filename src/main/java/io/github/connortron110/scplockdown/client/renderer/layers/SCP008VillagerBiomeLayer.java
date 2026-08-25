/*package io.github.connortron110.scplockdown.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.connortron110.scplockdown.level.entity.scp008.SCP008VillagerEntity;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.resources.data.VillagerMetadataSection;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SCP008VillagerBiomeLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> implements IResourceManagerReloadListener {
    private final Object2ObjectMap<VillagerType, VillagerMetadataSection.HatType> typeHatCache = new Object2ObjectOpenHashMap<>();
    private final Object2ObjectMap<VillagerProfession, VillagerMetadataSection.HatType> professionHatCache = new Object2ObjectOpenHashMap<>();

    public SCP008VillagerBiomeLayer(IEntityRenderer<T, M> p_i50955_1_, IReloadableResourceManager p_i50955_2_) {
        super(p_i50955_1_);
        p_i50955_2_.registerReloadListener(this);
    }

    public void render(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            SCP008VillagerEntity entity = (SCP008VillagerEntity) pLivingEntity;
            ResourceLocation variantLocation = entity.getVariantEnum(entity).getTextureLocation();
            renderColoredCutoutModel(this.getParentModel(), variantLocation, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
        }
    }
    public void onResourceManagerReload(IResourceManager pResourceManager) {
        this.professionHatCache.clear();
        this.typeHatCache.clear();
    }
}

 */