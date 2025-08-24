package it.crystalnest.goblin_fabrications.item;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

/**
 * Item registry.
 */
public final class ItemRegistry {
  /**
   * {@link CobwebRegister} for {@link Item}s.
   */
  private static final CobwebRegister.Items ITEMS = CobwebRegistry.ofItems(Constants.MOD_ID);
  private static final ResourceLocation SPAWN_EGG_ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "goblin_explorer");



  /**
   * Goblin Explorer mob spawn egg.
   */
  public static final CobwebEntry<Item> GOBLIN_EXPLORER_SPAWN_EGG = ITEMS.registerItem(
    SPAWN_EGG_ID.getPath() + "_spawn_egg",
    properties -> new SpawnEggItem(
      EntityRegistry.GOBLIN_EXPLORER.get(),
      properties
    )
  );
  /**
   * {@link CobwebRegister} for {@link CreativeModeTab}s.
   */
  private static final CobwebRegister<CreativeModeTab> CREATIVE_TABS = CobwebRegistry.ofCreativeModeTabs(Constants.MOD_ID);

  /**
   * Goblin Fabrications creative tab.
   */
  public static final Supplier<CreativeModeTab> LEATHERED_BOOTS_TAB = CREATIVE_TABS.register(Constants.GOBLIN_FABRICATIONS_TAB, Services.ITEM.supplyTab(
    () -> GOBLIN_EXPLORER_SPAWN_EGG.get().getDefaultInstance(),
    Constants.GOBLIN_FABRICATIONS_TAB,
    output -> output.accept(GOBLIN_EXPLORER_SPAWN_EGG.get())
  ));

  private ItemRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {}
}
