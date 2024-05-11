package it.crystalnest.goblin_fabrications;

import it.crystalnest.goblin_fabrications.config.ModConfig;

/**
 * Common mod loader.
 */
public final class CommonModLoader {
  private CommonModLoader() {}

  /**
   * Initialize common operations across loaders.
   */
  public static void init() {
    ModConfig.CONFIG.register();
  }
}
