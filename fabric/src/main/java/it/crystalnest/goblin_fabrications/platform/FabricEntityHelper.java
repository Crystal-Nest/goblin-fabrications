package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.platform.services.EntityHelper;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class FabricEntityHelper implements EntityHelper {
  @Override
  public void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributesBuilder) {
    FabricDefaultAttributeRegistry.register(entityType, attributesBuilder);
  }
}
