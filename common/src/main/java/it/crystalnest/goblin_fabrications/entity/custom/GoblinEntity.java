package it.crystalnest.goblin_fabrications.entity.custom;

import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.goals.custom.GoblinFleeGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;

public class GoblinEntity extends Synx implements GeoEntity {
  private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

  public GoblinEntity(EntityType<? extends GoblinEntity> entityType, Level level) {
    super(entityType, level);
    // this.isFleeing(false);
  }

  public static AttributeSupplier.Builder createAttributes() {
    return LivingEntity.createLivingAttributes()
      .add(Attributes.MAX_HEALTH, 16.0f)
      .add(Attributes.ATTACK_DAMAGE, 4.0f)
      .add(Attributes.ATTACK_SPEED, 2.0f)
      .add(Attributes.MOVEMENT_SPEED, 0.3)
      .add(Attributes.FOLLOW_RANGE, 5.0)
      .add(Attributes.JUMP_STRENGTH, 0.5f)
      .add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1f);
  }

  @Override
  protected void registerGoals() {

    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8, 1.0000001E-5F));
    this.goalSelector.addGoal(3, new GoblinFleeGoal<>(this, Player.class, 50.0F, 1.7, 1.7));
    this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 50.0F));
    this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
  }

  @Override
  protected @NotNull ResourceKey<LootTable> getDefaultLootTable() {
    return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/goblin_explorer"));
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(new AnimationController<>(this, "controller", this::predicate));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return cache;
  }

  @Nullable
  @Override
  public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
    return null;
  }

  @Override
  public boolean isFood(@NotNull ItemStack itemStack) {
    return false;
  }

  @Override
  public void tick() {
    super.tick();

    // Check if the entity is in water
    if (this.isInWater()) {
      // Check if the mob already has Dolphin's Grace
      if (!this.hasEffect(MobEffects.DOLPHINS_GRACE)) {
        this.addEffect(new MobEffectInstance(
          MobEffects.DOLPHINS_GRACE, // The effect
          200,                     // Duration in ticks (10 seconds)
          1,                       // Amplifier (Dolphin's Grace I)
          false,                   // Ambient particles
          false                    // Show particles
        ));
      }
    } else {
      // Remove the effect when the mob is out of water
      this.removeEffect(MobEffects.DOLPHINS_GRACE);
    }
  }

/*@Override
  public void travel(Vec3 movement) {
    if (this.isInWater()) {
      this.setDeltaMovement(this.getDeltaMovement().multiply(1.2, 1.0, 1.2)); // Increase speed in water
    }
    super.travel(movement);
  }*/
  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.RABBIT_AMBIENT;
  }

  @Override
  protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
    return SoundEvents.RABBIT_HURT;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundEvents.FOX_DEATH;
  }

  @Override
  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
    this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F); // Example: Zombie step sound
  }
}


