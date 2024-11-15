package it.crystalnest.goblin_fabrications;

import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.entity.client.GoblinRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ClientModLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    EntityRendererRegistry.register(EntityRegistry.GOBLIN_EXPLORER.get(), GoblinRenderer::new);
  }
}
