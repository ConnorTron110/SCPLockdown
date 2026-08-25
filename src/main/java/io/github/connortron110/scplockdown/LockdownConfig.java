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

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final LockdownConfig.Common COMMON;

	static {
		final Pair<LockdownConfig.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(LockdownConfig.Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}

}
