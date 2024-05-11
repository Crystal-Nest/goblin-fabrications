package it.crystalnest.goblin_fabrications.item;

import it.crystalnest.cobweb.api.registry.Register;
import it.crystalnest.goblin_fabrications.ModLoader;
import it.crystalnest.goblin_fabrications.entity.EntityRegistry;
import it.crystalnest.goblin_fabrications.entity.custom.GoblinEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ItemRegistry {
    private static final Register<Item> ITEM_REGISTER = ModLoader.REGISTER_PROVIDER.of(BuiltInRegistries.ITEM);
    public static final Item GOBLIN_SPAWN_EGG = new SpawnEggItem(EntityRegistry.GOBLIN, 0xDD4477, 0x909733, new FabricItemSettings());

    private ItemRegistry() {}

    public static void register() {
        ITEM_REGISTER.apply("goblin_spawn_egg", GOBLIN_SPAWN_EGG);
    }
}
