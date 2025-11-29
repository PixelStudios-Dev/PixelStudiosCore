package io.pixelstudios.testmod.client;

import io.pixelstudios.pixelstudioscore.impl.client.PixelstudioscoreDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TestModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        new PixelstudioscoreDataGenerator().onInitializeDataGenerator(fabricDataGenerator);

    }
}
