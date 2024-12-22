package it.crystalnest.goblin_fabrications.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
  private static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(Synx.class, EntityDataSerializers.BOOLEAN);

  public Synx(EntityType<? extends Animal> pEntityType, Level pLevel) {
    super(pEntityType, pLevel);

  }

  protected PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
    if (this.isFleeing()) {
      geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("flee", Animation.LoopType.LOOP));

      // Play fleeing sound
      if (!this.level().isClientSide) { // Ensure the sound plays on the server
        this.level().playSound(
          null, // Player to notify, null means no specific player
          this.blockPosition(), // Position to play the sound
          SoundEvents.RABBIT_HURT, // Replace with your fleeing sound
          SoundSource.HOSTILE, // Category of sound
          1.0F, // Volume
          1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F // Pitch variation
        );
      }
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

  protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
    super.defineSynchedData(builder);
    builder.define(FLEEING, false);
  }
}
