package io.pixelstudios.pixelstudioscore.api.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.MOD_ID;

public class SoundFactory {

    private static SoundEvent registerSoundEvents(String name) {
        Identifier identifier = Identifier.of(MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

}
