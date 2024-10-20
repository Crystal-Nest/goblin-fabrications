package it.crystalnest.goblin_fabrications.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public abstract class synx extends Animal {
    public synx(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

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
}
