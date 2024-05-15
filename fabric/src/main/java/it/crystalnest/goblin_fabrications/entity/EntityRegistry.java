package it.crystalnest.goblin_fabrications.entity;

import it.crystalnest.cobweb.api.registry.Register;
import it.crystalnest.goblin_fabrications.Constants;
import it.crystalnest.goblin_fabrications.ModLoader;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnGroupData;

public final class EntityRegistry {
    private static final Register<EntityType<?>> ENTITY_REGISTER = ModLoader.REGISTER_PROVIDER.of(BuiltInRegistries.ENTITY_TYPE);
    public static final EntityType<GoblinEntity> GOBLIN = FabricEntityTypeBuilder.create(MobCategory.MONSTER, GoblinEntity::new).dimensions(EntityDimensions.fixed(0.5f,0.5f)).build();


    private EntityRegistry() {}

    public static void register(){
        ENTITY_REGISTER.apply("goblin", GOBLIN);

        FabricDefaultAttributeRegistry.register(GOBLIN,GoblinEntity.setAttributers());

    }
}
