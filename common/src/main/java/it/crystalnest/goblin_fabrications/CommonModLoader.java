package it.crystalnest.goblin_fabrications;

import it.crystalnest.goblin_fabrications.config.ModConfig;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.item.ItemRegistry;
import it.crystalnest.goblin_fabrications.sound.SoundRegistry;

/**
 * Common mod loader.
 */
public final class CommonModLoader {
  private CommonModLoader() {}

  /**
   * Initialize common operations across loaders.
   */
  public static void init() {
    EntityRegistry.register();
    ModConfig.CONFIG.register();
    ItemRegistry.register();
    SoundRegistry.register();
  }
}
