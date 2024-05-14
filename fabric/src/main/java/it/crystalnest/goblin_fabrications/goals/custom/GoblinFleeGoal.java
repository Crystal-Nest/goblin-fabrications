package it.crystalnest.goblin_fabrications.goals.custom;

import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

import java.util.function.Predicate;

public class GoblinFleeGoal<T extends LivingEntity> extends AvoidEntityGoal {
    public GoblinFleeGoal(GoblinEntity pathfinderMob, Class class_, float f, double d, double e) {
        super(pathfinderMob, class_, f, d, e);
    }

    public GoblinFleeGoal(PathfinderMob pathfinderMob, Class class_, float f, double d, double e) {
        super(pathfinderMob, class_, f, d, e);
    }

    public GoblinFleeGoal(PathfinderMob pathfinderMob, Class class_, Predicate predicate, float f, double d, double e, Predicate predicate2) {
        super(pathfinderMob, class_, predicate, f, d, e, predicate2);
    }

    public GoblinFleeGoal(PathfinderMob pathfinderMob, Class class_, float f, double d, double e, Predicate predicate) {
        super(pathfinderMob, class_, f, d, e, predicate);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.mob.distanceToSqr(this.toAvoid) < 49.0) {
            if(this.mob.getClass().equals(GoblinEntity.class))
                ((GoblinEntity) this.mob).isFleeing = true;
        } else {
            if(this.mob.getClass().equals(GoblinEntity.class))
                ((GoblinEntity) this.mob).isFleeing = false;
        }
        System.out.println("Mob value :" + ((GoblinEntity) this.mob).isFleeing);
    }

}
