package io.pixelstudios.pixelstudioscore.impl;

import net.fabricmc.api.ModInitializer;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.VERSION;
import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.LOGGER;

public class PixelStudiosCoreIntializer implements ModInitializer {

    @Override
    public void onInitialize() {

        LOGGER.info("Loading PixelStudiosCore v{}", VERSION);
        LOGGER.info("Initializing systems...");

    }

}
