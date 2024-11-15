package it.crystalnest.goblin_fabrications.item;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public final class ItemRegistry {
  private static final CobwebRegister<Item> ITEMS = CobwebRegistry.ofItems(Constants.MOD_ID);

  public static final CobwebEntry<Item> GOBLIN_EXPLORER_SPAWN_EGG = ITEMS.register("goblin_explorer_spawn_egg", () -> new SpawnEggItem(EntityRegistry.GOBLIN_EXPLORER.get(), 0xDD4477, 0x909733, new Item.Properties()));

  private ItemRegistry() {}

  public static void register() {}
}
