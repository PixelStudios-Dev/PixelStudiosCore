package io.pixelstudios.pixelstudioscore.api;

import io.pixelstudios.pixelstudioscore.impl.datagen.ModEnglishLangProvider;
import io.pixelstudios.pixelstudioscore.impl.datagen.ModModelProvider;
import io.pixelstudios.pixelstudioscore.impl.datagen.ModSpanishLangProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGen {

    private DataGen() {
    }

    public static void gen(FabricDataGenerator.Pack pack) {

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModSpanishLangProvider::new);
        pack.addProvider(ModEnglishLangProvider::new);


    }

}
