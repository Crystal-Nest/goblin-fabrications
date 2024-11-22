package it.crystalnest.goblin_fabrications.entity;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import it.crystalnest.goblin_fabrications.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;

import java.util.function.Supplier;

public final class EntityRegistry {

  private static final CobwebRegister<EntityType<?>> ENTITY_TYPES = CobwebRegistry.of(Registries.ENTITY_TYPE, Constants.MOD_ID);
  public static final Supplier<EntityType<GoblinEntity>> GOBLIN_EXPLORER = ENTITY_TYPES.register(
    "goblin_explorer",
    () -> EntityType.Builder.of(GoblinEntity::new, MobCategory.CREATURE)
      .sized(0.5f, 0.9f)
      .build(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, Constants.GOBLIN_EXPLORER_ID).toString())
  );

  public static void register() {
    Services.ENTITY.registerEntityAttributes(GOBLIN_EXPLORER, GoblinEntity.buildAttributes());
  }
}
