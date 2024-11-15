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

import java.util.function.Supplier;

public final class EntityRegistry {

  private static final CobwebRegister<EntityType<?>> ENTITY_TYPES = CobwebRegistry.of(Registries.ENTITY_TYPE, Constants.MOD_ID);

  public static final Supplier<EntityType<GoblinEntity>> GOBLIN_EXPLORER = ENTITY_TYPES.register(
    "goblin_explorer",
    () -> EntityType.Builder.of(GoblinEntity::new, MobCategory.MONSTER)
      .sized(0.5f, 2)
      .build(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "goblin_explorer").toString())
  );

  private EntityRegistry() {}

  public static void register() {
    Services.ENTITY.registerEntityAttributes(GOBLIN_EXPLORER, GoblinEntity.buildAttributes());

    // Registering the entity attributes
    // FabricDefaultAttributeRegistry.register(GOBLIN, GoblinEntity.buildAttributes());

    // Register spawn rules
    registerEntitySpawns();
  }

  public static void registerEntitySpawns() {
    BiomeModifications.addSpawn(
      // FIXED BIOMAN FOR NOW
      BiomeSelectors.includeByKey(Biomes.PLAINS),
      MobCategory.CREATURE,
      GOBLIN,
      ModConfig.getSpawnWeight(), // Configurable spawn weight
      ModConfig.getMinSpawnSize(), // Configurable minimum spawn group size
      ModConfig.getMaxSpawnSize()  // Configurable maximum spawn group size
    );

    // Optional: Set spawn placement rules (e.g., ground spawns)
    //SpawnPlacements.register(GOBLIN, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoblinEntity::checkGoblinSpawnRules);
  }
}