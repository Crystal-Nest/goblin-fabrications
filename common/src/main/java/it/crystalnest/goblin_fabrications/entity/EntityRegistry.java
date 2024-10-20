package it.crystalnest.goblin_fabrications.entity;

import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.ModLoader;
import it.crystalnest.goblin_fabrications.config.ModConfig;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public final class EntityRegistry {

    private static final CobwebRegistry ENTITY_REGISTER = ModLoader.REGISTER_PROVIDER.of(BuiltInRegistries.ENTITY_TYPE);

    public static final EntityType<GoblinEntity> GOBLIN = FabricEntityTypeBuilder
            .create(MobCategory.MONSTER, GoblinEntity::new)
            // Rendering issue here
            .dimensions(EntityDimensions.fixed(0.5f, 2f))
            .build();

    private EntityRegistry() {}

    public static void register() {
        ENTITY_REGISTER.apply("goblin", GOBLIN);

        // Registering the entity attributes
        FabricDefaultAttributeRegistry.register(GOBLIN, GoblinEntity.setAttributers());

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