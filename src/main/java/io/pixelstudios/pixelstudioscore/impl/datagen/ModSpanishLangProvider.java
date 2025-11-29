package io.pixelstudios.pixelstudioscore.impl.datagen;

import io.pixelstudios.pixelstudioscore.api.lang.ModLanguageManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModSpanishLangProvider extends FabricLanguageProvider {

    public ModSpanishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "es_es", registryLookup);
    }

    @Override

    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        ModLanguageManager.getSpanishTranslationMap().forEach(translationBuilder::add);

    }

}
