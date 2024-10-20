package it.crystalnest.goblin_fabrications.config;

import it.crystalnest.cobweb.api.config.CommonConfig;
import it.crystalnest.goblin_fabrications.Constants;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mod common configuration.
 */
public final class ModConfig extends CommonConfig {
  /**
   * Mod common configuration.
   */
  public static final ModConfig CONFIG = register(Constants.MOD_ID, ModConfig::new);


  private ModConfigSpec.IntValue spawnWeight;
  private ModConfigSpec.IntValue minSpawnSize;
  private ModConfigSpec.IntValue maxSpawnSize;
  private ModConfigSpec.ConfigValue<List<String>> biomes;
  //private ModConfigSpec.ConfigValue<List<Map<String, Object>>> customLootTable;

  /**
   * @param builder configuration builder.
   */
  private ModConfig(ModConfigSpec.Builder builder) {
    super(builder);
  }

  @Override
  protected void define(ModConfigSpec.Builder builder) {
    /*builder.comment("Loot Table Settings").push("loot");

    customLootTable = builder
            .comment("Custom loot table entries")
            .define("customLootTable", List.of(
                    Map.of(
                            "name", "minecraft:bone",
                            "weight", 30,
                            "min", 1,
                            "max", 5
                    ),
                    Map.of(
                            "name", "minecraft:porkchop",
                            "weight", 30,
                            "min", 1,
                            "max", 5
                    )
                    // Add more items as needed
            ));
*/
    // Define new configuration options for spawn settings
    builder.comment("Spawn Settings").push("spawning");

    spawnWeight = builder
            .comment("Goblin spawn weight (rarer with lower value)")
            .defineInRange("spawnWeight", 50, 1, Integer.MAX_VALUE);

    minSpawnSize = builder
            .comment("Minimum spawn group size for Goblins")
            .defineInRange("minSpawnSize", 1, 1, Integer.MAX_VALUE);

    maxSpawnSize = builder
            .comment("Maximum spawn group size for Goblins")
            .defineInRange("maxSpawnSize", 1, 1, Integer.MAX_VALUE);

    biomes = builder
            .comment("List of biomes where Goblins can spawn (e.g., [\"minecraft:plains\", \"minecraft:forest\"])")
            .define("biomes", List.of("minecraft:plains"));

    builder.pop();
  }
/*  public static List<Map<String, Object>> getCustomLootTable() {
    return CONFIG.customLootTable.get();
  }*
  /**
   * Returns the goblin spawn weight as read from the configuration file.
   *
   * @return the goblin spawn weight.
   */
  public static int getSpawnWeight() {
    return CONFIG.spawnWeight.get();
  }

  /**
   * Returns the minimum spawn group size as read from the configuration file.
   *
   * @return the minimum spawn group size.
   */
  public static int getMinSpawnSize() {
    return CONFIG.minSpawnSize.get();
  }

  /**
   * Returns the maximum spawn group size as read from the configuration file.
   *
   * @return the maximum spawn group size.
   */
  public static int getMaxSpawnSize() {
    return CONFIG.maxSpawnSize.get();
  }


  /**
   * Returns the list of biomes where Goblins can spawn, as read from the configuration file.
   *
   * @return the list of biome RegistryKeys.
   */
  //public static List<ResourceLocation> getBiomes() {
  //  return CONFIG.biomes.get().stream().map(ResourceLocation::new).collect(Collectors.toList());
  //}
}