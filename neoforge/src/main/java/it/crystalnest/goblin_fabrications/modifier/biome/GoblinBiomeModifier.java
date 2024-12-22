package it.crystalnest.goblin_fabrications.modifier.biome;

import com.mojang.serialization.MapCodec;
import it.crystalnest.goblin_fabrications.config.ModConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import org.jetbrains.annotations.NotNull;

import static it.crystalnest.goblin_fabrications.entity.EntityRegistry.GOBLIN_EXPLORER;

/**
 * Goblin biome modifier.
 */
public class GoblinBiomeModifier implements BiomeModifier {
  /**
   * Singleton instance.
   */
  private static final GoblinBiomeModifier INSTANCE = new GoblinBiomeModifier();

  /**
   * Codec.
   */
  public static final MapCodec<GoblinBiomeModifier> CODEC = MapCodec.unit(INSTANCE);

  @Override
  public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, ModifiableBiomeInfo.BiomeInfo.@NotNull Builder builder) {
    // Check if the modification phase is ADD and the biome is in the target list
    if (phase == Phase.ADD && ModConfig.getBiomes().stream().anyMatch(location -> biome.is(ResourceLocation.parse(location)))) {
      builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(GOBLIN_EXPLORER.get(), ModConfig.getSpawnWeight(), ModConfig.getMinSpawnSize(), ModConfig.getMaxSpawnSize()));
    }
  }

  @NotNull
  @Override
  public MapCodec<? extends BiomeModifier> codec() {
    return CODEC;
  }
}
