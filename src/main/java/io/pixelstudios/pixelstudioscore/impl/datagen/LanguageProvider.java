package io.pixelstudios.pixelstudioscore.impl.datagen;

import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LanguageProvider extends FabricLanguageProvider {

    public String langCode;

    public LanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup, String langCode) {

        super(dataOutput, langCode, registryLookup);

        this.langCode = langCode;

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        LanguageManager.getTranslationMap().forEach((k, v) -> {

            int index = k.indexOf("#");

            if (index == -1) return;

            String code = k.substring(0, index);

            if (!code.equals(langCode)) return;

            String key = k.substring(index + 1);

            translationBuilder.add(key, v);

        });

    }

}
