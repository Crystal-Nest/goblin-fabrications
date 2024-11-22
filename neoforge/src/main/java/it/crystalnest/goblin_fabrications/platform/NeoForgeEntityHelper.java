package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.platform.services.EntityHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeEntityHelper implements EntityHelper {
  private static final List<Pair<Supplier<? extends EntityType<? extends LivingEntity>>, AttributeSupplier>> ENTITY_ATTRIBUTES = new ArrayList<>();

  @SubscribeEvent
  private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
    for (Pair<Supplier<? extends EntityType<? extends LivingEntity>>, AttributeSupplier> pair : ENTITY_ATTRIBUTES) {
      event.put(pair.getKey().get(), pair.getValue());
    }
  }

  @Override
  public void registerEntityAttributes(Supplier<? extends EntityType<? extends LivingEntity>> entityType, AttributeSupplier.Builder attributes) {
    ENTITY_ATTRIBUTES.add(Pair.of(entityType, attributes.build()));
  }
}
