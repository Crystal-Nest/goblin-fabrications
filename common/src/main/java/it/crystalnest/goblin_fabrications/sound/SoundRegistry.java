package it.crystalnest.goblin_fabrications.sound;


import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.goblin_fabrications.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {
    /**
     * {@link CobwebRegister} for {@link SoundEvent}s.
     */
    private static final CobwebRegister<SoundEvent> SOUNDS = CobwebRegistry.of(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

    /**
     * Goblin sounds.
     */
    public static final CobwebEntry<SoundEvent> GOBLIN_HURT = SOUNDS.register("goblin_hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"goblin_hurt")));
    public static final CobwebEntry<SoundEvent> GOBLIN_DEATH = SOUNDS.register("goblin_death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"goblin_death")));
    public static final CobwebEntry<SoundEvent> GOBLIN_SURPRISE = SOUNDS.register("goblin_surprise", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"goblin_surprise")));
    public static final CobwebEntry<SoundEvent> GOBLIN_IDLE = SOUNDS.register("goblin_idle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"goblin_idle")));
    public static final CobwebEntry<SoundEvent> GOBLIN_RUN = SOUNDS.register("goblin_run", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,"goblin_run")));

    /**
     * Called to load the class and register.
     */
    public static void register() {}
}
