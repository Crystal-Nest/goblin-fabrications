package it.crystalnest.goblin_fabrications.platform;

import it.crystalnest.goblin_fabrications.platform.services.SpawnHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;


import java.util.List;

public class NeoForgeSpawnHelper implements SpawnHelper {


  @Override
  public void registerSpawn(EntityType<?> entityType, MobCategory category, int weight, int minGroupSize, int maxGroupSize, List<ResourceKey<Biome>> biomes) {

  }

}