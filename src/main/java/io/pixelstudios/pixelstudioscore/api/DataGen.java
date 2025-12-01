package io.pixelstudios.pixelstudioscore.api;

import io.pixelstudios.pixelstudioscore.impl.datagen.LanguageProvider;
import io.pixelstudios.pixelstudioscore.impl.datagen.ModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class DataGen {

    private DataGen() {}

    public static void gen(FabricDataGenerator.Pack pack, FabricDataGenerator fabricDataGenerator) {

        pack.addProvider(ModelProvider::new);

        pack.addProvider((output, registriesFuture) ->
                new LanguageProvider(output, registriesFuture, "es_es")
        );

        pack.addProvider((output, registriesFuture) ->
                new LanguageProvider(output, registriesFuture, "en_us")
        );

    }

}
