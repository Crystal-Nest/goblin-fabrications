package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.platform.services.SpawnHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;


import java.util.List;
import java.util.function.Predicate;

public class FabricSpawnHelper implements SpawnHelper {
  @Override
  public void registerSpawn(EntityType<?> entityType, MobCategory category, int weight, int minGroupSize, int maxGroupSize, List<ResourceKey<Biome>> biomes) {
    Predicate<BiomeSelectionContext> biomePredicate = context -> biomes.contains(context.getBiomeKey());

    for (ResourceKey<Biome> biomeKey : biomes) {
      // Register spawn rule with the predicate filtering biomes
      BiomeModifications.addSpawn(
        biomePredicate, // Predicate to check if biome matches
        category, // Mob category (e.g., CREATURE)
        entityType, // Entity type to spawn
        weight, // Spawn weight
        minGroupSize, // Min group size
        maxGroupSize // Max group size
      );
    }
  }
}