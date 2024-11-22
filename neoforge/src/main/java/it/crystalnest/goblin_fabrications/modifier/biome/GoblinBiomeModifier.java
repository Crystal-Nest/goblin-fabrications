package it.crystalnest.goblin_fabrications.modifier.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.config.ModConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.random.Weight;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static it.crystalnest.goblin_fabrications.entity.EntityRegistry.GOBLIN_EXPLORER;

public record GoblinBiomeModifier(HolderSet<Biome> biomes, int value) implements BiomeModifier {

  @Override
  public void modify(@NotNull Holder<Biome> biome, @NotNull Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    // Check if the modification phase is ADD and the biome is in the target list
    if (phase == Phase.ADD && ModConfig.getBiomes().contains(biome)) {
      builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(GOBLIN_EXPLORER.get(), ModConfig.getSpawnWeight(), ModConfig.getMinSpawnSize(), ModConfig.getMaxSpawnSize()));
    }
  }

  @Override
  public MapCodec<? extends BiomeModifier> codec() {
    return GOBLIN_BIOME_MODIFIER.value();
  }
  public static final MapCodec<GoblinBiomeModifier> GOBLIN_BIOME_MODIFIER_CODEC = RecordCodecBuilder.mapCodec(instance ->
    instance.group(
      Biome.LIST_CODEC.fieldOf("biomes").forGetter(GoblinBiomeModifier::biomes),
      Codec.INT.fieldOf("value").forGetter(GoblinBiomeModifier::value)
    ).apply(instance, GoblinBiomeModifier::new)
  );

  public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS =
    DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.MOD_ID);

  public static final Holder<MapCodec<? extends BiomeModifier>> GOBLIN_BIOME_MODIFIER =
    BIOME_MODIFIERS.register("goblin_biome_modifier", () -> GOBLIN_BIOME_MODIFIER_CODEC);
}
