package it.crystalnest.goblin_fabrications.goals.custom;

import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;
import net.minecraft.world.level.pathfinder.Path;


public class GoblinFleeGoal<T extends LivingEntity> extends Goal {
  protected final PathfinderMob mob;
  private final double walkSpeedModifier;
  private final double sprintSpeedModifier;
  @Nullable
  protected T toAvoid;
  protected final float maxDist;
  @Nullable
  protected Path path;
  protected final PathNavigation pathNav;
  protected final Class<T> avoidClass;
  protected final Predicate<LivingEntity> avoidPredicate;
  protected final Predicate<LivingEntity> predicateOnAvoidEntity;
  private final TargetingConditions avoidEntityTargeting;
  private int timeWithoutLineOfSight = 0; // Initialize the counter

  public GoblinFleeGoal(PathfinderMob pathfinderMob, Class<T> class_, float f, double d, double e) {

    this(pathfinderMob, class_, (livingEntity) -> {
      return true;
    }, f, d, e,  Objects.requireNonNull(EntitySelector.NO_CREATIVE_OR_SPECTATOR)::test);
  }

  public GoblinFleeGoal(PathfinderMob pathfinderMob, Class<T> class_, Predicate<LivingEntity> predicate, float f, double d, double e, Predicate<LivingEntity> predicate2) {
    this.mob = pathfinderMob;
    this.avoidClass = class_;
    this.avoidPredicate = predicate;
    this.maxDist = f;
    this.walkSpeedModifier = d;
    this.sprintSpeedModifier = e;
    this.predicateOnAvoidEntity = predicate2;
    this.pathNav = pathfinderMob.getNavigation();
    this.setFlags(EnumSet.of(Flag.MOVE));
    this.avoidEntityTargeting = TargetingConditions.forCombat().range((double)f).selector(predicate2.and(predicate));
  }

  public GoblinFleeGoal(PathfinderMob pathfinderMob, Class<T> class_, float f, double d, double e, Predicate<LivingEntity> predicate) {
    this(pathfinderMob, class_, (livingEntity) -> {
      return true;
    }, f, d, e, predicate);
  }

  @Override
  public boolean canUse() {
     if (this.toAvoid == null) {
       this.toAvoid = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.avoidClass, this.mob.getBoundingBox().inflate(maxDist, 15.0, maxDist), avoidPredicate), this.avoidEntityTargeting, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
       return false;
    }

    Vec3 avoidDirection = this.mob.position().subtract(this.toAvoid.position()).normalize().scale(16.0);
    Vec3 avoidPosition = this.mob.position().add(avoidDirection);

    this.path = this.pathNav.createPath(avoidPosition.x, avoidPosition.y, avoidPosition.z, 0);
    return this.path != null;
  }

  public boolean canContinueToUse() {
    return !this.pathNav.isDone();
  }

  public void start() {
    this.pathNav.moveTo(this.path, this.walkSpeedModifier);

      ((GoblinEntity) this.mob).isFleeing(true);
  }

  public void stop() {
    //this.toAvoid = null;
    ((GoblinEntity) this.mob).isFleeing(false);
   // this.mob.discard();
  }

  @Override
  public void tick() {
    if (this.toAvoid == null) {
      return;
    }


      this.mob.getNavigation().setSpeedModifier(this.sprintSpeedModifier);


    // Continuously adjust path to keep moving in the opposite direction
    Vec3 avoidDirection = this.mob.position().subtract(this.toAvoid.position()).normalize().scale(16.0);
    Vec3 avoidPosition = this.mob.position().add(avoidDirection);

    this.path = this.pathNav.createPath(avoidPosition.x, avoidPosition.y, avoidPosition.z, 0);
    // Check if the Goblin still has line of sight to the player
    if (this.mob.hasLineOfSight(this.toAvoid)) {
      this.timeWithoutLineOfSight = 0; // Reset the counter if the player is in sight
    } else {
      this.timeWithoutLineOfSight++; // Increment the counter if the player is out of sight
    }

    // Check if the mob should despawn after 15 seconds out of sight (300 ticks)
    if (this.timeWithoutLineOfSight > 300) {
      this.mob.discard(); // Despawn the goblin
      System.out.println("Goblin despawn");
    }
  }

}
