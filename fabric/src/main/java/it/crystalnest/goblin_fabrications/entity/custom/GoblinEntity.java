package it.crystalnest.goblin_fabrications.entity.custom;

import it.crystalnest.goblin_fabrications.goals.custom.GoblinFleeGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Iterator;
import java.util.function.Predicate;

public class GoblinEntity extends Monster implements GeoEntity  {

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
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0f)
                .add(Attributes.ATTACK_DAMAGE, 4.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new GoblinFleeGoal<Wolf>(this, Wolf.class, 6.0F, 5.0, 2.2));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, "controller", this::predicate));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLEEING, false);
    }


    private PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {

        System.out.println(this.isFleeing());
        if(this.isFleeing()) {
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("flee", Animation.LoopType.LOOP));
        }else{
        if(geoAnimatableAnimationState.isMoving()){
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));

        }else{
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }}
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource source, DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(source, difficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }
    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimention) {
        return 0.74F;
    }

}
