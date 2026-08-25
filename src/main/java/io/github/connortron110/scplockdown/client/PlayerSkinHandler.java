package io.github.connortron110.scplockdown.client;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import io.github.connortron110.scplockdown.SCPLockdown;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Currently used by {@link io.github.connortron110.scplockdown.level.entity.scp008.SCP008PlayerEntity} and {@link io.github.connortron110.scplockdown.level.entity.SCP049PlayerEntity} to grab skins of players that are currently not on the server (their skin still exists on some url).
 * This may expand to allow for more generic calls from multiple other sources where a players texture is needed.
 * <br><br>
 * Tries to not repeat code that Mojang has already created.
 */
public class PlayerSkinHandler {

	//  The Default player skin info for players that have no UUID's (should not happen but ehh)
	private static final PlayerSkinInfo DEFAULT = new PlayerSkinInfo(DefaultPlayerSkin.getDefaultSkin(), false);
	//  A map containing player information based on a UUID and is used as a cache to grab and store skins
	private static final Map<UUID, PlayerSkinInfo> PLAYER_SKIN = new HashMap<>();

	public static PlayerSkinInfo getPlayerSkinInfoFromUUID(UUID uuid) {

		//  If passed uuid is null just return steve skin
		if (uuid == null) return DEFAULT;

		//  If the skin info is not already cached, we need to grab it
		if (!PLAYER_SKIN.containsKey(uuid)) {
			//  First put a null instance in to avoid multiple calls while waiting for async function to return true data
			PLAYER_SKIN.put(uuid, null);

			//  Set up a "profile" of the user, Name does not matter, we only care about the UUID
			GameProfile profile = new GameProfile(uuid, "jeb_");

            /*
            This bit still hurts my head as 99% of it is obfuscated bs, however when we "register the profile", we call
            the api to grab a lot of information; however we are only interested in the Skin, so on the return call,
            we check for the skin, check the model type and get the newly registered skin location.
             */
			Minecraft.getInstance().getSkinManager().registerSkins(profile, (type, location, typeMeta) -> {
				if (type == MinecraftProfileTexture.Type.SKIN) {
					//  Check the model type is non-null to allow for proper creation of PlayerSkinInfo
					String skinModel = typeMeta.getMetadata("model");
					if (skinModel == null) skinModel = "default";

					PLAYER_SKIN.put(uuid, new PlayerSkinInfo(location, skinModel.equalsIgnoreCase("slim")));
					SCPLockdown.LOGGER.debug("Player Skin Stored for UUID: {}", uuid);
				}
			}, true);
		}

		PlayerSkinInfo skinInfo = PLAYER_SKIN.get(uuid);
		//  If nothing was provided, use default skin based on players UUID
		if (skinInfo == null) {
			skinInfo = new PlayerSkinInfo(DefaultPlayerSkin.getDefaultSkin(uuid), DefaultPlayerSkin.getSkinModelName(uuid).equalsIgnoreCase("slim"));
		}

		return skinInfo;
	}

	/**
	 * Holds a skins location and if a slim model should be used
	 */
	public static class PlayerSkinInfo {
		private final ResourceLocation location;
		private final boolean slim;

		public PlayerSkinInfo(ResourceLocation location, boolean slim) {
			this.location = location;
			this.slim = slim;
		}

		public ResourceLocation getTexture() {
			return location;
		}

		public boolean isSlim() {
			return slim;
		}
	}
}
