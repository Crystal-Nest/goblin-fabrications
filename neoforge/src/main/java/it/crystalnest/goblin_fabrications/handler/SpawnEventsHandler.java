package it.crystalnest.goblin_fabrications.handler;


import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

/**
 * Handles spawn-related events.
 */
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class SpawnEventsHandler {

  private SpawnEventsHandler() {}

  @SubscribeEvent
  private static void handle(RegisterSpawnPlacementsEvent event) {
    event.register(EntityRegistry.GOBLIN_EXPLORER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,(entityType, serverLevelAccessor, spawnReason, pos, randomSource)->true, RegisterSpawnPlacementsEvent.Operation.AND );
  }
}
