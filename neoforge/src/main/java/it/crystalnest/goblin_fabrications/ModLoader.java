package it.crystalnest.goblin_fabrications;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

/**
 * Mod loader.
 */
@Mod(Constants.MOD_ID)
public class ModLoader {
  /**
   * Mod initialization.
   *
   * @param bus Event bus.
   */
  public ModLoader(IEventBus bus) {
    CommonModLoader.init();
  }
}
