package io.pixelstudios.pixelstudioscore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PixelStudiosCore {

    public static final String VERSION = "0.0.1";

    public static final Logger LOGGER = LoggerFactory.getLogger("PixelStudiosCore");
    public static String MOD_ID;

    private PixelStudiosCore() {}

    public static void initCore(String modId) {

        MOD_ID = modId;

    }

}
