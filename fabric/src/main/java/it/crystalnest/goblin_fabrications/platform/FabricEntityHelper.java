package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.platform.services.EntityHelper;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public class FabricEntityHelper implements EntityHelper {
  @Override
  public void registerEntityAttributes(Supplier<? extends EntityType<? extends LivingEntity>> entityType, Supplier<AttributeSupplier.Builder> attributes) {
    FabricDefaultAttributeRegistry.register(entityType.get(), attributes.get());
  }
}
