package it.crystalnest.goblin_fabrications;

import it.crystalnest.goblin_fabrications.config.ModConfig;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Mod loader.
 */
@ApiStatus.Internal
public final class ModLoader implements ModInitializer {
  @Override
  public void onInitialize() {
    CommonModLoader.init();
    BiomeModifications.addSpawn(
      context -> ModConfig.getBiomes().contains(context.getBiomeKey().location().toString()),
      MobCategory.CREATURE,
      EntityRegistry.GOBLIN_EXPLORER.get(),
      ModConfig.getSpawnWeight(),
      ModConfig.getMinSpawnSize(),
      ModConfig.getMaxSpawnSize()
    );
  }
}
