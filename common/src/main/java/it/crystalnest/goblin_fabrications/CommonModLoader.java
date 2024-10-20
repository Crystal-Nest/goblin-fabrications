package it.crystalnest.goblin_fabrications;

import it.crystalnest.goblin_fabrications.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;

/**
 * Common mod loader.
 */
@ApiStatus.Internal
public final class CommonModLoader {
  private CommonModLoader() {}

  /**
   * Initialize common operations across loaders.
   */
  public static void init() {
    ModConfig.CONFIG.register();
  }
}
