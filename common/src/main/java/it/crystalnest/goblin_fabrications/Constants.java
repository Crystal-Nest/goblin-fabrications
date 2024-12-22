package it.crystalnest.goblin_fabrications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common shared constants across all loaders.
 */
public final class Constants {
  /**
   * Mod ID.
   */
  public static final String MOD_ID = "goblin_fabrications";

  /**
   * Goblin Fabrications creative tab ID.
   */
  public static final String GOBLIN_FABRICATIONS_TAB = Constants.MOD_ID + "_tab";

  /**
   * Goblin Explorer mob ID.
   */
  public static final String GOBLIN_EXPLORER_ID = "goblin_explorer";

  /**
   * Mod logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  private Constants() {}
}
