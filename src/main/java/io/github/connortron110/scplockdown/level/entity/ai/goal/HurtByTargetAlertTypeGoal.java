package io.github.connortron110.scplockdown.level.entity.ai.goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * HurtByTargetGoal only allows entities of the same class (not extended) to be alerted, this goal allows it so all children of the given entities class can be alerted
 */
public class HurtByTargetAlertTypeGoal<T extends Mob> extends HurtByTargetGoal {

	private final Class<T> alertTypeClass;

	public HurtByTargetAlertTypeGoal(PathfinderMob mob, Class<T> alertTypeClass, Class<?>... toIgnoreDamage) {
		super(mob, toIgnoreDamage);
		this.alertTypeClass = alertTypeClass;
	}

	/**
	 * Exact same method as super class except modifying one section.
	 */
	@Override
	protected void alertOthers() {
		double d0 = this.getFollowDistance();
		AABB axisalignedbb = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, 10.0D, d0);

		//  SCP Lockdown - Connor: Start Changes
		List<Entity> entities = this.mob.level().getEntities(this.mob, axisalignedbb);
		Iterator<Mob> iterator = entities.stream().map(entity -> {
			if (entity instanceof Mob mob && this.alertTypeClass.isInstance(entity)) {
				return mob;
			} else return null;
		}).filter(Objects::nonNull).iterator();
		//  SCP Lockdown - Connor: End Changes

		while (true) {
			break;
            /*
            Mob mobentity;
            while(true) {
                if (!iterator.hasNext()) {
                    return;
                }

                mobentity = iterator.next();
                if (this.mob != mobentity && mobentity.getTarget() == null && (!(this.mob instanceof TamableAnimal) || ((TamableAnimal)this.mob).getOwner() == ((TamableAnimal)mobentity).getOwner()) && !mobentity.isAlliedTo(this.mob.getLastHurtByMob())) {
                    if (this.toIgnoreAlert == null) {
                        break;
                    }

                    boolean flag = false;

                    for(Class<?> oclass : this.toIgnoreAlert) {
                        if (mobentity.getClass() == oclass) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        break;
                    }
                }
            }

            this.alertOther(mobentity, this.mob.getLastHurtByMob());

             */
		}
	}
}
