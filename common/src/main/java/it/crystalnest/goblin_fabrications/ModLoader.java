package it.crystalnest.goblin_fabrications;

import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;

/**
 * Mod loader.
 */
public class ModLoader implements ModInitializer {
  public static final CobwebRegistry REGISTER_PROVIDER = CobwebRegistry(Constants.MOD_ID);

  @Override
  public void onInitialize() {
    CommonModLoader.init();
    EntityRegistry.register();
    ItemRegistry.register();
  }
}
