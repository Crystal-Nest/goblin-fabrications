package it.crystalnest.goblin_fabrications.platform.services;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;

/**
 * Entity specific helper.
 */
public interface EntityHelper {
  /**
   * Register entity attributes.
   *
   * @param entityType entity type to register the attributes for.
   * @param attributesBuilder builder of the attributes to register.
   */
  void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributesBuilder);
}
