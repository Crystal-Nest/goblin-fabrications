package it.crystalnest.goblin_fabrications.entity;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.config.ModConfig;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import it.crystalnest.goblin_fabrications.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;

import java.util.Arrays;
import java.util.function.Supplier;

public final class EntityRegistry {

  private static final CobwebRegister<EntityType<?>> ENTITY_TYPES = CobwebRegistry.of(Registries.ENTITY_TYPE, Constants.MOD_ID);

  public static final Supplier<EntityType<GoblinEntity>> GOBLIN_EXPLORER = ENTITY_TYPES.register(
    "goblin_explorer",
    () -> EntityType.Builder.of(GoblinEntity::new, MobCategory.CREATURE)
      .sized(0.5f, 0.9f).clientTrackingRange(8)
      .build(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "goblin_explorer").toString())
  );

  private EntityRegistry() {}

  public static void register() {
    // Register entity attributes (this registers the attributes such as health, attack, etc. for GoblinExplorer)
    Services.ENTITY.registerEntityAttributes(GOBLIN_EXPLORER, GoblinEntity.buildAttributes());

    // Register spawn rules for the GoblinExplorer in specific biomes
    Services.SPAWN.registerSpawn(GOBLIN_EXPLORER.get(), MobCategory.CREATURE, 10, 1, 1, Arrays.asList(Biomes.FOREST, Biomes.PLAINS));
  }

}