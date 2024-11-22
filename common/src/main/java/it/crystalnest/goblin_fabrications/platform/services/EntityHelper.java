package it.crystalnest.goblin_fabrications.platform.services;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

/**
 * Entity specific helper.
 */
public interface EntityHelper {
  /**
   * Register entity attributes.
   *
   * @param entityType entity type to register the attributes for.
   * @param attributes builder of the attributes to register.
   */
  void registerEntityAttributes(Supplier<? extends EntityType<? extends LivingEntity>> entityType, AttributeSupplier.Builder attributes);

  //ADD registring egg and loot table
}
