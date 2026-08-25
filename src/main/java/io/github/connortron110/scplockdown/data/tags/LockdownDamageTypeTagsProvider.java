package io.github.connortron110.scplockdown.data.tags;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LockdownDamageTypeTagsProvider extends DamageTypeTagsProvider {
	public LockdownDamageTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(pOutput, pLookupProvider, SCPLockdown.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
		this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
				SCPDamageTypes.SCP002CONSUME,
				SCPDamageTypes.SCP012CURSE,
				SCPDamageTypes.SCP035MASK,
				SCPDamageTypes.SCP053HEARTATTACK,
				SCPDamageTypes.SCP822TOXIN
		);
		this.tag(DamageTypeTags.IS_EXPLOSION).add(
				SCPDamageTypes.SCP822EXPLODE
		);
	}
}
