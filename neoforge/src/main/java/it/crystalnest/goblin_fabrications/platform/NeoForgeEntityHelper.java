package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.platform.services.EntityHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NeoForgeEntityHelper implements EntityHelper {
  @Override
  public void registerEntityAttributes(EntityType<? extends LivingEntity> entityType, AttributeSupplier.Builder attributesBuilder) {

  }

  @SubscribeEvent()
  public void test(EntityAttributeCreationEvent event) {
    event.put();
  }
}
