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
  private static final CobwebRegister<Item> ITEMS = CobwebRegistry.ofItems(Constants.MOD_ID);
  private static final ResourceLocation SPAWN_EGG_ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "goblin_explorer_spawn_egg");
  private static final ResourceKey<Item> SPAWN_EGG_KEY = ResourceKey.create(Registries.ITEM, SPAWN_EGG_ID);


  /**
   * Goblin Explorer mob spawn egg.
   */
  public static final CobwebEntry<Item> GOBLIN_EXPLORER_SPAWN_EGG = ITEMS.register(

    SPAWN_EGG_ID.getPath(),

    () -> new SpawnEggItem(

      EntityRegistry.GOBLIN_EXPLORER.get(),

      0xDD4477,

      0x909733,

      new Item.Properties()

        .setId(SPAWN_EGG_KEY)  // Required for 1.21.2+

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
