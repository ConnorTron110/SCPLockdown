package io.github.connortron110.scplockdown;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import org.apache.commons.lang3.tuple.Pair;

public class LockdownConfig {
	public static class Common {

		public final BooleanValue creativeHandUnlocksCardReaders;

		private Common(Builder builder) {
			builder.comment("Common Config")
					.push("common");

			creativeHandUnlocksCardReaders = builder
					.comment("Set this to false to disallow creative players from unlocking card readers.")
					.translation("scplockdown.config.creativeHandUnlocksCardReaders")
					.define("creativeHandUnlocksCardReaders", true);

			builder.pop();
		}

		public final boolean canCreativeHandUnlockCardReader() {
			return COMMON_SPEC.isLoaded() ? creativeHandUnlocksCardReaders.get() : creativeHandUnlocksCardReaders.getDefault();
		}
	}

	public static class Client {

		public final BooleanValue scp914Tips;

		private Client(Builder builder) {
			builder.comment("Client Config")
					.push("client");

			scp914Tips = builder
					.comment("Set this to false to diable SCP-914 from giving tips when interacting with it.")
					.translation("scplockdown.config.scp914Tips")
					.define("scp914Tips", true);

			builder.pop();
		}

		public final boolean shouldSCP914ShowTips() {
			return CLIENT_SPEC.isLoaded() ? scp914Tips.get() : scp914Tips.getDefault();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final LockdownConfig.Common COMMON;
	public static final LockdownConfig.Client CLIENT;

	static {
		final Pair<LockdownConfig.Common, ForgeConfigSpec> commPair = new ForgeConfigSpec.Builder().configure(LockdownConfig.Common::new);
		COMMON_SPEC = commPair.getRight();
		COMMON = commPair.getLeft();

		final Pair<LockdownConfig.Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(LockdownConfig.Client::new);
		CLIENT_SPEC = clientPair.getRight();
		CLIENT = clientPair.getLeft();
	}
}
