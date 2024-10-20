package it.crystalnest.goblin_fabrications.entity.custom;

import it.crystalnest.goblin_fabrications.goals.custom.GoblinFleeGoal;

import net.minecraft.network.syncher.EntityDataAccessor;

import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;

import net.minecraft.world.entity.animal.*;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.*;


import java.util.Objects;


public class GoblinEntity extends synx implements GeoEntity{


    private static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);


    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public GoblinEntity(EntityType<? extends GoblinEntity> entityType, Level level) {
        super(entityType, level);
        this.isFleeing(false);
    }


    public boolean isFleeing(){
        return this.entityData.get(FLEEING);
    }
    public void isFleeing(boolean isFleeing){
        this.entityData.set(FLEEING, isFleeing);
    }
    public static AttributeSupplier.Builder setAttributers() {
        return Animal.createLivingAttributes()
          .add(Attributes.MAX_HEALTH, 16.0f)
          .add(Attributes.ATTACK_DAMAGE, 4.0f)
          .add(Attributes.ATTACK_SPEED, 2.0f)
          .add(Attributes.MOVEMENT_SPEED, 0.3)
          .add(Attributes.FOLLOW_RANGE, 5.0)
          .add(Attributes.JUMP_STRENGTH, 2.5f);
    }

    @Override
    protected void registerGoals() {

        //this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(1, new OcelotAvoidEntityGoal(this, Player.class, 32.0F, 1.7, 1.7));
        //this.goalSelector.addGoal(1, new PanicGoal(this, 1.5));
        //this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8, 1.0000001E-5F));
        //this.goalSelector.addGoal(12, new LookAtPlayerGoal(this, Player.class, 10.0F));

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8, 1.0000001E-5F));
        this.goalSelector.addGoal(3, new GoblinFleeGoal<Player>(this, Player.class, 50.0F, 1.7, 1.7));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 50.0F));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8, 1.0000001E-5F));

        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, "controller", this::predicate));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuild) {
        super.defineSynchedData(pBuild);
        this.entityData.set(FLEEING, false);
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    static class OcelotAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        private final GoblinEntity goblin;

        public OcelotAvoidEntityGoal(GoblinEntity goblin, Class<T> class_, float f, double d, double e) {
            super(goblin, class_, f, d, e, Objects.requireNonNull(EntitySelector.NO_CREATIVE_OR_SPECTATOR)::test);
            this.goblin = goblin;
        }

        public boolean canUse() {
            return super.canUse();
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse();
        }
    }


}
