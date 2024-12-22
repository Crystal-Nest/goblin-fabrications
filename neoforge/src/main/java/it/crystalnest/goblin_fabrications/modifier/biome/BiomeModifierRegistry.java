package it.crystalnest.goblin_fabrications.modifier.biome;

import com.mojang.serialization.MapCodec;
import it.crystalnest.goblin_fabrications.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class BiomeModifierRegistry {
  private static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Constants.MOD_ID);

  public static final Supplier<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_CODEC = BIOME_MODIFIERS.register("goblin_biome_modifier", () -> GoblinBiomeModifier.CODEC);

  private BiomeModifierRegistry() {}

  public static void register(IEventBus bus) {
    BIOME_MODIFIERS.register(bus);
  }


}
