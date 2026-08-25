package io.github.connortron110.scplockdown.level.items.biocontainer;

import io.github.connortron110.scplockdown.registration.SCPItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractBioContainerItem extends Item {

	public static final String BIO_CONTENTS_KEY = "BioContents";
	public static final String BIO_AMOUNT_KEY = "BioAmount";

	public AbstractBioContainerItem(Properties pProperties) {
		super(pProperties);
	}

	@Override   //  Used by the Syringe to infect other mobs /  TODO Get samples from entities
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
		if (!player.level().isClientSide) {
			if (usedHand == InteractionHand.MAIN_HAND && isSyringe(stack)) {
				BioContents syringeContents = getBioContent(stack);
				if (syringeContents != BioContents.EMPTY) {
					syringeContents.useOnEntity(interactionTarget.level(), player, interactionTarget); //FIXME as player interacts, syringe refills at the same time
					player.setItemInHand(usedHand, decreaseContainer(stack, syringeContents));
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}

	//  TODO: Allow the player to "Drink" the contents in a vial
	@Override   //  Used for vials to fill / refill the vial itself and or a syringe
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
		if (!level.isClientSide) {
			if (usedHand == InteractionHand.OFF_HAND) {
				if (isVial(player.getItemInHand(usedHand))) {
					ItemStack vialStack = player.getOffhandItem();
					ItemStack mainHandStack = player.getMainHandItem();

					//Vial is in offhand, now do we refill or deplete?
					if (isSyringe(mainHandStack)) {
						//Main hand is syringe, we either want to deplete or add if contents match up
						if (getBioContent(mainHandStack) == BioContents.EMPTY && getBioContent(vialStack) != BioContents.EMPTY) {
							//Syringe is empty and vial has contents, we can transfer
							player.setItemInHand(InteractionHand.MAIN_HAND, increaseContainer(mainHandStack, getBioContent(vialStack)));
							player.setItemInHand(InteractionHand.OFF_HAND, decreaseContainer(vialStack, getBioContent(vialStack)));

						} else if ((getBioContent(vialStack) == getBioContent(mainHandStack) || getBioContent(vialStack) == BioContents.EMPTY) && getBioAmount(vialStack) < ((VialItem) SCPItems.VIAL.asItem()).getMaxContainerSize()) {
							//Conditions are right for the syringe to put its contents in the vial
							player.setItemInHand(InteractionHand.OFF_HAND, increaseContainer(vialStack, getBioContent(mainHandStack)));
							player.setItemInHand(InteractionHand.MAIN_HAND, clearContainer(mainHandStack));
						}
					} else if (getBioContent(vialStack).canRefill(vialStack, mainHandStack, level, player) && BioContents.getContentTypeFromStack(mainHandStack) != BioContents.EMPTY) {
						//We can refill the Vial with the item in the offhand

						vialStack = increaseContainer(vialStack, BioContents.getContentTypeFromStack(mainHandStack));

						mainHandStack.shrink(1);

						player.setItemInHand(InteractionHand.MAIN_HAND, mainHandStack);
						player.setItemInHand(InteractionHand.OFF_HAND, vialStack);
					}
				}
			}
		}

		return InteractionResultHolder.pass(player.getItemInHand(usedHand));
	}

	public static boolean isVial(ItemStack stack) {
		return SCPItems.VIAL.asItem().equals(stack.getItem());
	}

	public static boolean isSyringe(ItemStack stack) {
		return SCPItems.SYRINGE.asItem().equals(stack.getItem());
	}

	public final ItemStack increaseContainer(ItemStack containerStack, BioContents contents) {
		if (getBioContent(containerStack) != contents && getBioContent(containerStack) != BioContents.EMPTY)
			return containerStack;
		return setContentsAndAmount(containerStack, contents, getBioAmount(containerStack) + 1);
	}

	public final ItemStack decreaseContainer(ItemStack containerStack, BioContents contents) {
		if (getBioContent(containerStack) != contents && getBioContent(containerStack) != BioContents.EMPTY)
			return containerStack;
		return setContentsAndAmount(containerStack, contents, getBioAmount(containerStack) - 1);
	}

	public final ItemStack setContentsAndAmount(ItemStack containerStack, BioContents contents, int amount) {
		if (!isBioContainer(containerStack)) return containerStack; //Item was not a Bio container
		CompoundTag nbt = containerStack.getOrCreateTag();
		nbt.putInt(BIO_CONTENTS_KEY, contents.ordinal());
		nbt.putInt(BIO_AMOUNT_KEY, Mth.clamp(amount, 0, ((AbstractBioContainerItem) containerStack.getItem()).getMaxContainerSize()));
		containerStack.setTag(nbt);
		return validateBioContainer(containerStack);
	}

	public final ItemStack clearContainer(ItemStack containerStack) {
		return setContentsAndAmount(containerStack, BioContents.EMPTY, 0);
	}

	public static int getBioAmount(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		return nbt.getInt(BIO_AMOUNT_KEY);
	}

	public static BioContents getBioContent(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		return BioContents.values()[nbt.getInt(BIO_CONTENTS_KEY)];
	}

	/**
	 * Validates and corrects the passed Bio Container Stack
	 *
	 * @param stack The Stack to validate
	 * @return The Validated / Corrected Stack
	 */
	public final ItemStack validateBioContainer(ItemStack stack) {
		if (!isBioContainer(stack)) return stack; //Item was not a Bio container, no validation required
		CompoundTag nbt = stack.getOrCreateTag();
		//Container does not have required NBT, return default instance
		if (!nbt.contains(BIO_CONTENTS_KEY) || !nbt.contains(BIO_AMOUNT_KEY)) return getDefaultInstance();

		if (nbt.getInt(BIO_AMOUNT_KEY) > 0) {
			//Empty amount should always be 0
			if (BioContents.values()[nbt.getInt(BIO_CONTENTS_KEY)] == BioContents.EMPTY) {
				return getDefaultInstance();
			}

			//Amount cannot be bigger than the maximum
			if (nbt.getInt(BIO_AMOUNT_KEY) > ((AbstractBioContainerItem) stack.getItem()).getMaxContainerSize()) {
				return setContentsAndAmount(stack, BioContents.values()[nbt.getInt(BIO_CONTENTS_KEY)], ((AbstractBioContainerItem) stack.getItem()).getMaxContainerSize());
			}
		} else {
			//If container amount is 0, contents should be empty
			if (BioContents.values()[nbt.getInt(BIO_CONTENTS_KEY)] != BioContents.EMPTY) {
				return clearContainer(stack);
			}
		}

		return stack;
	}


	public static boolean isBioContainer(ItemStack stack) {
		return stack.getItem() instanceof AbstractBioContainerItem;
	}

	public abstract int getMaxContainerSize();

	@Override
	public abstract ItemStack getDefaultInstance();

	@Override
	public final String getDescriptionId(ItemStack pStack) {
		return getDescriptionId() + "." + getBioContent(pStack).getName();
	}

    /*
    @Override
    public final void fillItemCategory(ItemGroup pGroup, NonNullList<ItemStack> pItems) {
        if (this.allowdedIn(pGroup)) pItems.add(getDefaultInstance());
    }

     */
}
