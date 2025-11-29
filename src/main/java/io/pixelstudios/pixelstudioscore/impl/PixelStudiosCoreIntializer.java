package io.pixelstudios.pixelstudioscore.impl;

import net.fabricmc.api.ModInitializer;

import static com.mojang.text2speech.Narrator.LOGGER;
import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.VERSION;

public class PixelStudiosCoreIntializer implements ModInitializer {

    @Override
    public void onInitialize() {

        LOGGER.info("Loading PixelStudiosCore v{}", VERSION);
        LOGGER.info("Initializing systems...");

    }

}
