package io.github.connortron110.scplockdown.level.items;

import io.github.connortron110.scplockdown.SCPLockdown;
import io.github.connortron110.scplockdown.level.SCPDamageTypes;
import io.github.connortron110.scplockdown.utils.RandProbabilityHelper;
import io.github.connortron110.scplockdown.utils.nbt.NBTWrapper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.function.Consumer;

public class SCP035MaskItem extends ArmorItem {
	public static final String COMEDY_KEY = "IsComedy";
	public static final String COMEDY_TIME_KEY = "ComedyTime";
	public static final String ATTACHED_TIME = "AttachedTime";

	//TODO Custom Item entity that changes expression and does not de-spawn (And lures entities??)

	public SCP035MaskItem(Properties pProperties) {
		//super(ArmorMaterials.DIAMOND, Type.HELMET, pProperties.setISTER(DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> () -> ItemModelRenderer::new)));   //  TODO: Probably best to redo the ISTER part
		super(ArmorMaterials.DIAMOND, Type.HELMET, pProperties);
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
		boolean isWearing = false; //Check if the player is wearing 035
		Iterator<ItemStack> iter = pEntity.getArmorSlots().iterator();
		ItemStack wearingStack = ItemStack.EMPTY; //Used to store the stack on the head to avoid shrinking multiple stacks
		while (!isWearing && iter.hasNext()) {
			ItemStack slot = iter.next();
			if (slot.getItem() instanceof ArmorItem && ((ArmorItem) slot.getItem()).getType() == Type.HELMET) {
				isWearing = ItemStack.isSameItemSameTags(pStack, slot);
				wearingStack = slot;
			}
		}

		if (pEntity instanceof Player player) {
			if (!player.getAbilities().invulnerable) {
				if (!isWearing && !player.isDeadOrDying()) {
					//If they are not wearing Telekill, swap with what ever helmet they are currently wearing
					if (!SCP148ArmorItem.isWearingTelekill(player)) {
						pEntity.setItemSlot(EquipmentSlot.HEAD, pStack.copy());
						pStack.shrink(1);
						player.addItem(wearingStack);
					}
				}

				if (isWearing) {
					//Bind so player cannot take off
					if (!wearingStack.isEnchanted()) {
						wearingStack.enchant(Enchantments.BINDING_CURSE, 1);
					}

					pEntity.hurt(SCPDamageTypes.source(pLevel, SCPDamageTypes.SCP035MASK), 8F);
					if (player.isDeadOrDying()) {
						wearingStack.removeTagKey("Enchantments"); //Remove Curse of Binding Enchantment

						//Spawn a new instance of SCP-035
						//SCP035VictimEntity victim = SCPEntities.SCP035_VICTIM.get().create(pLevel);
						//victim.setPos(pEntity.getX(), pEntity.getY(), pEntity.getZ());
						//victim.setYHeadRot(pEntity.getYHeadRot());
						//victim.setYBodyRot((player).yBodyRot);
						//victim.setItemSlot(EquipmentSlot.HEAD, wearingStack.copy());
						//pLevel.addFreshEntity(victim);

						wearingStack.shrink(1); //Before death, remove the item from the inventory (to avoid dupe)
					}
				}
			} else {
				//Player is invunerable, if the stack has binding, remove it
				if (pStack.isEnchanted()) {
					pStack.removeTagKey("Enchantments");
				}
			}
		}

		if (!pLevel.isClientSide && !isWearing) {
			if (shouldChange(pStack)) {
				changeExpression(pStack); //TODO Check if this is synced when on a server
			}
		}
	}

	//{@link io.github.connortron110.scplockdown.events.SCPRelatedForgeEvents#scp035(LivingEvent.LivingUpdateEvent)} BAD LINK

	/**
	 * Expression Changes happen on <LINK> As expression should change on any entity.
	 *
	 * @param stack The stack to change the expression on
	 */
	public static void changeExpression(ItemStack stack) {
		//Safety incase something called this with the incorrect stack
		if (stack.getItem() instanceof SCP035MaskItem) {
			NBTWrapper nbt = NBTWrapper.getNBT(stack);
			nbt.setBoolean(COMEDY_KEY, !nbt.getOrCreateKey(COMEDY_KEY, false));
			nbt.setInt(COMEDY_TIME_KEY, 0);
			nbt.save();
		}
	}

	public static boolean isComedy(ItemStack stack) {
		if (!(stack.getItem() instanceof SCP035MaskItem)) return false;
		NBTWrapper nbt = NBTWrapper.getNBT(stack);
		boolean ret = nbt.getOrCreateKey(COMEDY_KEY, false);
		nbt.save();
		return ret;
	}

	public static boolean setComedy(ItemStack stack, boolean value) {
		if (!(stack.getItem() instanceof SCP035MaskItem)) return false;
		NBTWrapper nbt = NBTWrapper.getNBT(stack);
		boolean flag = nbt.getOrCreateKey(COMEDY_KEY, value);
		nbt.setBoolean(COMEDY_KEY, flag);
		nbt.save();
		return flag;
	}

	@Override
	public boolean isFoil(ItemStack pStack) {
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack pStack) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}

    /*
    @Override
    public void fillItemCategory(ItemGroup pGroup, NonNullList<ItemStack> pItems) {
        ItemStack stack = getDefaultInstance();
        if (this.allowdedIn(pGroup)) {
            pItems.add(stack);
        }
    }

     */

	@Override
	public ItemStack getDefaultInstance() {
		ItemStack stack = new ItemStack(this);
		NBTWrapper nbt = NBTWrapper.getNBT(stack);
		nbt.getOrCreateKey(COMEDY_KEY, false);
		nbt.getOrCreateKey(COMEDY_TIME_KEY, 0);
		nbt.getOrCreateKey(ATTACHED_TIME, 0);
		nbt.save();
		return stack;
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return 0;
	}

	@Override
	public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
		return false;
	}

    /*
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
        return (A) DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> new SCP035ArmorModel(1F, isComedy(itemStack)));
    }

     */

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return SCPLockdown.MOD_ID + ":textures/entity/scp035.png";
	}

	public static boolean shouldChange(ItemStack stack) {
		if (!(stack.getItem() instanceof SCP035MaskItem))
			return false; //Safety incase something called this with the incorrect stack
		NBTWrapper nbt = NBTWrapper.getNBT(stack);
		int time = nbt.getOrCreateKey(COMEDY_TIME_KEY, 0);
		//Will change at least once per minecraft day, initially has a 1 in 100,000 chance but increases as the day goes on
		boolean result = maskChangeProbability(time++);
		nbt.setInt(COMEDY_TIME_KEY, time);
		nbt.save();
		return result;
	}

	public static boolean maskChangeProbability(int x) {
		//TODO Revert once change has been confirmed server side
		return RandProbabilityHelper.inverseParabolicProbability(x, 200, 200); //RandProbabilityHelper.inverseParabolicProbability(x, 100000, 240000);
	}
}
