package it.crystalnest.goblin_fabrications.handler;

import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.entity.client.GoblinRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Handles entity related events.
 */
@EventBusSubscriber(modid = Constants.MOD_ID)
public final class EntityEventsHandler {
  private EntityEventsHandler() {}

  /**
   * Handles the {@link EntityRenderersEvent.RegisterRenderers RegisterRenderers} event.<br/>
   * Registers all Goblin entity renderers.
   *
   * @param event {@link EntityRenderersEvent.RegisterRenderers RegisterRenderers}.
   */
  @SubscribeEvent
  private static void handle(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(EntityRegistry.GOBLIN_EXPLORER.get(), GoblinRenderer::new);
  }
}
