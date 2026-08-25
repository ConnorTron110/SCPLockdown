package io.github.connortron110.scplockdown.level.items.biocontainer;

import io.github.connortron110.scplockdown.registration.SCPTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IBioRefill {
	/**
	 * Used to determine if the conditions are correct to refill a Bio Container
	 *
	 * @param bioContainer   The stack that contains the bio container to refill
	 * @param otherHandStack The stack that is on the other hand of the player (can be empty)
	 * @param level          The level in which the player is in
	 * @param player         The player trying to refill the bio container
	 * @return if the container should be refilled
	 */
	boolean canRefill(ItemStack bioContainer, ItemStack otherHandStack, Level level, Player player);

	@Nullable
	TagKey<Item> getRefillItemTag();

	//"bioContainer" is gaunted to be a bioContainer Item, also we don't need to verify the container if it's the correct content.
	IBioRefill EMPTY = new IBioRefill() { //Empty Containers can always be refilled
		@Override
		public boolean canRefill(ItemStack bioContainer, ItemStack otherHandStack, Level level, Player player) {
			return true;
		}

		@Nullable
		@Override
		public TagKey<Item> getRefillItemTag() {
			return null;
		}
	};

	IBioRefill SCP008 = new IBioRefill() {
		@Override
		public boolean canRefill(ItemStack bioContainer, ItemStack otherHandStack, Level level, Player player) {
			return false; //this.getRefillItemTag().contains(otherHandStack.getItem());
		}

		@Nonnull
		@Override
		public TagKey<Item> getRefillItemTag() {
			return SCPTags.Items.VIAL_008_REFILLABLE;
		}
	};
}
