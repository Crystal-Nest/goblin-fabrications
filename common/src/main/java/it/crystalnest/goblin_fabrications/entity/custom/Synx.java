package it.crystalnest.goblin_fabrications.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public abstract class Synx extends Animal {
  public Synx(EntityType<? extends Animal> pEntityType, Level pLevel) {
    super(pEntityType, pLevel);
  }
  private static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(Synx.class, EntityDataSerializers.BOOLEAN);

  protected PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
    if (this.isFleeing()) {
      geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("flee", Animation.LoopType.LOOP));
    } else {
      if (geoAnimatableAnimationState.isMoving()) {
        geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));

      } else {
        geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
      }
    }
    return PlayState.CONTINUE;
  }
  public void isFleeing(boolean isFleeing) {
    this.entityData.set(FLEEING, isFleeing);
  }
  public boolean isFleeing() {
    return this.entityData.get(FLEEING);
  }
  @Override
  protected void defineSynchedData(SynchedEntityData.@NotNull Builder pBuild) {
    super.defineSynchedData(pBuild);
    this.entityData.set(FLEEING, false);
  }

}
