package io.github.connortron110.scplockdown.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.connortron110.scplockdown.client.renderer.ItemModelRenderer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Injector for {@link ItemModelRenderer}
 */
@Mixin(BlockEntityWithoutLevelRenderer.class)
public abstract class ClientMixinBlockEntityWithoutLevelRenderer {

	@Unique private static ItemModelRenderer SCPLockdown$RendererInstance;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void ClientMixinBlockEntityWithoutLevelRenderer_init(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet, CallbackInfo ci) {
		SCPLockdown$RendererInstance = new ItemModelRenderer(pBlockEntityRenderDispatcher, pEntityModelSet);
	}

	@Inject(method = "onResourceManagerReload", at = @At("TAIL"))
	private void onResourceManagerReload(ResourceManager pResourceManager, CallbackInfo ci) {
		SCPLockdown$RendererInstance.onResourceManagerReload(pResourceManager);
	}

	/**
	 * Annoyingly, Vanilla decides that if the block isn't on the if chain it has, it terminates the call, so we have to run our stuff before vanilla.
	 * There is a Forge approach, but it requires having a custom item that uses {@link IClientItemExtensions#getCustomRenderer()}, and I don't really want to have to make a custom item PER thing I want to render.
	 * Sure I can do a base item that all the custom rendering links to, but it's more overhead when adding these types of items.
	 * This cuts the whole man in the middle problem and is a lot more straightfoward to implement.
	 */
	@Inject(method = "renderByItem", at = @At("HEAD"))
	private void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, CallbackInfo ci) {
		SCPLockdown$RendererInstance.renderByItem(pStack, pDisplayContext, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
	}
}
