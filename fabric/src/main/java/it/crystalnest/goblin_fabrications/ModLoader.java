package it.crystalnest.goblin_fabrications;

import net.fabricmc.api.ModInitializer;

/**
 * Mod loader.
 */
public class ModLoader implements ModInitializer {
  @Override
  public void onInitialize() {
    CommonModLoader.init();
  }
}
