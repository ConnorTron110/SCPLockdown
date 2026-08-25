package io.github.connortron110.scplockdown.client;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.client.models.BlastDoorModel;
import io.github.connortron110.scplockdown.client.models.LockerModel;
import io.github.connortron110.scplockdown.client.models.SCP914KeyKnobModel;
import io.github.connortron110.scplockdown.client.models.SlidingDoorModel;
import io.github.connortron110.scplockdown.client.models.entity.*;
import io.github.connortron110.scplockdown.client.renderer.blockentity.SCP914KeyKnobRenderer;
import io.github.connortron110.scplockdown.client.renderer.entity.RendererGenericEntity;
import io.github.connortron110.scplockdown.client.renderer.entity.SCP019EntityRenderer;
import io.github.connortron110.scplockdown.client.renderer.entity.SCP023EntityRenderer;
import io.github.connortron110.scplockdown.client.renderer.entity.SCP049PlayerEntityRenderer;
import io.github.connortron110.scplockdown.client.renderer.blockentity.BlastDoorRenderer;
import io.github.connortron110.scplockdown.client.renderer.blockentity.LockerRenderer;
import io.github.connortron110.scplockdown.client.renderer.blockentity.SlidingDoorRenderer;
import io.github.connortron110.scplockdown.level.entity.ChairEntity;
import io.github.connortron110.scplockdown.registration.SCPBlockEntities;
import io.github.connortron110.scplockdown.registration.SCPEntities;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

/**
 * Handles Everything Rendering wise for Entities
 */
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SCPLockdown.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SCPLayerDefinitions {
	//  Block Entity textures are defined in the vanilla atlas json
	public static final ModelLayerLocation LOCKER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "locker"), "main");
	public static final ModelLayerLocation SLIDING_DOOR = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "sliding_door"), "main");
	public static final ModelLayerLocation BLAST_DOOR = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "blast_door"), "main");

	public static final ModelLayerLocation SCP914_KEY_KNOB = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp914_key_knob"), "main");


	public static final ModelLayerLocation GUARD = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "guard"), "main");
	public static final ModelLayerLocation RODENT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "rodent"), "main");

	public static final ModelLayerLocation SCP019 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp019"), "main");
	public static final ModelLayerLocation SCP023 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp023"), "main");
	public static final ModelLayerLocation SCP049 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp049"), "main");
	public static final ModelLayerLocation SCP053 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp053"), "main");
	public static final ModelLayerLocation SCP939 = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SCPLockdown.MOD_ID, "scp939"), "main");

	@SubscribeEvent
	public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		//  Block Entities
		event.registerLayerDefinition(SLIDING_DOOR, SlidingDoorModel::createBodyLayer);
		event.registerLayerDefinition(BLAST_DOOR, BlastDoorModel::createBodyLayer);
		event.registerLayerDefinition(LOCKER, LockerModel::createBodyLayer);

		event.registerLayerDefinition(SCP914_KEY_KNOB, SCP914KeyKnobModel::createBodyLayer);

		//  Entities
		event.registerLayerDefinition(GUARD, GuardModel::createBodyLayer);
		event.registerLayerDefinition(RODENT, RodentModel::createBodyLayer);

		event.registerLayerDefinition(SCP019, SCP019Model::createBodyLayer);
		event.registerLayerDefinition(SCP023, SCP023Model::createBodyLayer);
		event.registerLayerDefinition(SCP049, SCP049Model::createBodyLayer);
		event.registerLayerDefinition(SCP053, SCP053Model::createBodyLayer);
		event.registerLayerDefinition(SCP939, SCP939Model::createBodyLayer);
	}

	@SubscribeEvent
	public static void setupEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		//  Block Entities
		event.registerBlockEntityRenderer(SCPBlockEntities.LOCKER.get(), LockerRenderer::new);
		event.registerBlockEntityRenderer(SCPBlockEntities.SLIDING_DOOR.get(), SlidingDoorRenderer::new);
		event.registerBlockEntityRenderer(SCPBlockEntities.BLAST_DOOR.get(), BlastDoorRenderer::new);

		event.registerBlockEntityRenderer(SCPBlockEntities.SCP914.get(), SCP914KeyKnobRenderer::new);

		//  Entities
		event.registerEntityRenderer(SCPEntities.CHAIR.get(), manager -> new EntityRenderer<>(manager) {
			@Override
			public ResourceLocation getTextureLocation(@Nonnull ChairEntity pEntity) {
				return null;
			}
		});

		event.registerEntityRenderer(SCPEntities.PETAL_ITEM.get(), ItemEntityRenderer::new);

		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.DCLASS.get(), DClassEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCIENTIST.get(), ScientistEntityRenderer::new);
		event.registerEntityRenderer(SCPEntities.GUARD.get(), context -> genericEntityRenderer(context, new GuardModel<>(context.bakeLayer(SCPLayerDefinitions.GUARD)), 0.5F, "textures/entity/staff/guard.png"));

		event.registerEntityRenderer(SCPEntities.MOUSE.get(), context -> genericEntityRenderer(context, new RodentModel<>(context.bakeLayer(SCPLayerDefinitions.RODENT), false), 0.25F, "textures/entity/mouse.png"));
		event.registerEntityRenderer(SCPEntities.RAT.get(), context -> genericEntityRenderer(context, new RodentModel<>(context.bakeLayer(SCPLayerDefinitions.RODENT), true), 0.3F, "textures/entity/rat.png"));

		//  SCPs

		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_PLAYER.get(), SCP008PlayerEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_GENERIC.get(), SCP008GenericEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_DCLASS.get(), SCP008DClassEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_SCIENTIST.get(), SCP008ScientistEntityRenderer::new);
		event.registerEntityRenderer(SCPEntities.SCP008_GUARD.get(), context -> genericEntityRenderer(context, new GuardModel<>(context.bakeLayer(SCPLayerDefinitions.GUARD)), 0.5F, "textures/entity/008/guard.png"));
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_VILLAGER.get(), SCP008VillagerEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_ILLAGER.get(), SCP008IllagerEntityRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_ENDERMAN.get(), SCP008EndermanRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_PIGLIN.get(), manager -> genericEntityRenderer(manager, new SCP008PiglinModel(), 0.5F, "textures/entity/008/piglin.png"));
		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP008_PIGLIN_BRUTE.get(), SCP008PiglinBruteRenderer::new);

		event.registerEntityRenderer(SCPEntities.SCP019.get(), SCP019EntityRenderer::new);

		event.registerEntityRenderer(SCPEntities.SCP023.get(), SCP023EntityRenderer::new);

		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP027.get(), SCP027Renderer::new);

		//RenderingRegistry.registerEntityRenderingHandler(SCPEntities.SCP035_VICTIM.get(), SCP035VictimRenderer::new);

		event.registerEntityRenderer(SCPEntities.SCP049.get(), context -> genericEntityRenderer(context, new SCP049Model<>(context.bakeLayer(SCPLayerDefinitions.SCP049)), 0.5F, "textures/entity/scp049.png"));
		event.registerEntityRenderer(SCPEntities.SCP049_PLAYER.get(), SCP049PlayerEntityRenderer::new);

		//  FIXME Model is upside down
		//event.registerEntityRenderer(SCPEntities.SCP053.get(), context -> genericEntityRenderer(context, new SCP053Model<>(context.bakeLayer(SCPLayerDefinitions.SCP053)), 0.2F, "textures/entity/scp053.png"));

		event.registerEntityRenderer(SCPEntities.SCP939.get(), context -> genericEntityRenderer(context, new SCP939Model<>(context.bakeLayer(SCPLayerDefinitions.SCP939)), 1.0F, "textures/entity/scp939.png"));
	}

	private static <E extends Mob, M extends EntityModel<E>> RendererGenericEntity<E, M> genericEntityRenderer(EntityRendererProvider.Context pContext, M model, float shadowRadius, String textureLocation) {
		return new RendererGenericEntity<>(pContext, model, shadowRadius, textureLocation);
	}
}
