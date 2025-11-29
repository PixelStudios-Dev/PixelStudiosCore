package io.pixelstudios.pixelstudioscore.impl.client;

import io.pixelstudios.pixelstudioscore.impl.datagen.ModEnglishLangProvider;
import io.pixelstudios.pixelstudioscore.impl.datagen.ModModelProvider;
import io.pixelstudios.pixelstudioscore.impl.datagen.ModSpanishLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PixelstudioscoreDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModSpanishLangProvider::new);
        pack.addProvider(ModEnglishLangProvider::new);


    }

}
