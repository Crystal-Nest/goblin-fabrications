package it.crystalnest.goblin_fabrications.platform.services;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public interface SpawnHelper {
  /**
   * Registers the spawn rules for the entity in the specified biomes.
   *
   * @param entityType The entity type to register.
   * @param category The mob category of the entity.
   * @param weight The spawn weight (chance to spawn).
   * @param minGroupSize The minimum group size for spawning.
   * @param maxGroupSize The maximum group size for spawning.
   * @param biomes The list of biomes where the entity can spawn.
   */
  public void registerSpawn(EntityType<?> entityType, MobCategory category, int weight, int minGroupSize, int maxGroupSize, List<ResourceKey<Biome>> biomes);
}
