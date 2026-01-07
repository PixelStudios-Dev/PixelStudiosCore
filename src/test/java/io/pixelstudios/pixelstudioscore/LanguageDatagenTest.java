package io.pixelstudios.pixelstudioscore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.pixelstudios.pixelstudioscore.api.DataGen;
import io.pixelstudios.pixelstudioscore.api.block.BlockFactory;
import io.pixelstudios.pixelstudioscore.api.item.ItemFactory;
import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager;
import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager.*;
import io.pixelstudios.pixelstudioscore.impl.registry.ModelRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LanguageDatagenTest {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @BeforeAll
    static void bootstrap() {
        SharedConstants.createGameVersion();
        Bootstrap.initialize();
    }

    @BeforeEach
    void resetState() {
        ModelRegistry.clear();
        LanguageManager.clear();
    }

    @Test
    void testLangFilesIsGenerated(@TempDir Path tempDir) throws Exception {
        PixelStudiosCore.initCore("pixelstudioscore");
        BlockFactory.create("test_block")
                .setTranslatedName(Map.of(
                        Languages.SPANISH, "Bloque de prueba",
                        Languages.ENGLISH, "Test Block"
                ))
                .build();

        ModContainer modContainer = FabricLoader.getInstance()
                .getModContainer("pixelstudioscore")
                .orElseThrow();

        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture = CompletableFuture.completedFuture(BuiltinRegistries.createWrapperLookup());
        FabricDataGenerator generator = new FabricDataGenerator(
                tempDir,
                modContainer,
                false,
                registriesFuture
        );

        FabricDataGenerator.Pack pack = generator.createPack();

        DataGen.gen(pack, generator);
        generator.run();

        Path esLangFile = tempDir.resolve("assets/pixelstudioscore/lang/es_es.json");
        Path enLangFile = tempDir.resolve("assets/pixelstudioscore/lang/en_us.json");
        Assertions.assertTrue(Files.exists(esLangFile));
        Assertions.assertTrue(Files.exists(enLangFile));

        JsonObject esJson = GSON.fromJson(Files.readString(esLangFile), JsonObject.class);
        Assertions.assertEquals("Bloque de prueba", esJson.get("block.pixelstudioscore.test_block").getAsString());

        JsonObject enJson = GSON.fromJson(Files.readString(enLangFile), JsonObject.class);
        Assertions.assertEquals("Test Block", enJson.get("block.pixelstudioscore.test_block").getAsString());
    }

    @Test
    void testLangFileKeysAreCorrect(@TempDir Path tempDir) throws Exception {
        PixelStudiosCore.initCore("pixelstudioscore");
        BlockFactory.create("test_block")
                .setTranslatedName(Map.of(
                        Languages.SPANISH, "Bloque de prueba",
                        Languages.ENGLISH, "Test Block"
                ))
                .build();

        ItemFactory.create("test_item")
                .addTranslatedName(Map.of(
                        Languages.SPANISH, "Item de prueba",
                        Languages.ENGLISH, "Test Item"
                ))
                .build();

        ModContainer modContainer = FabricLoader.getInstance()
                .getModContainer("pixelstudioscore")
                .orElseThrow();

        CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture = CompletableFuture.completedFuture(BuiltinRegistries.createWrapperLookup());
        FabricDataGenerator generator = new FabricDataGenerator(
                tempDir,
                modContainer,
                false,
                registriesFuture
        );

        FabricDataGenerator.Pack pack = generator.createPack();
        DataGen.gen(pack, generator);
        generator.run();

        Path esLangFile = tempDir.resolve("assets/pixelstudioscore/lang/es_es.json");
        JsonObject esJson = GSON.fromJson(Files.readString(esLangFile), JsonObject.class);

        Path enLangFile = tempDir.resolve("assets/pixelstudioscore/lang/en_us.json");
        JsonObject enJson = GSON.fromJson(Files.readString(enLangFile), JsonObject.class);

        for (String key : esJson.keySet()) {
            Assertions.assertTrue(
                    key.startsWith("block.pixelstudioscore.")
                            || key.startsWith("item.pixelstudioscore."),
                    "Clave inválida encontrada: " + key
            );
        }

        for (String key : enJson.keySet()) {
            Assertions.assertTrue(
                    key.startsWith("block.pixelstudioscore.")
                            || key.startsWith("item.pixelstudioscore."),
                    "Clave inválida encontrada: " + key
            );
        }

        Assertions.assertTrue(esJson.has("block.pixelstudioscore.test_block"), "Falta traducción del bloque");
        Assertions.assertTrue(esJson.has("item.pixelstudioscore.test_item"), "Falta traducción del item");
        Assertions.assertFalse(esJson.has("block.pixelstudioscore.test_item"), "ERROR: Item registrado como bloque");

        Assertions.assertTrue(enJson.has("block.pixelstudioscore.test_block"), "Falta traducción del bloque");
        Assertions.assertTrue(enJson.has("item.pixelstudioscore.test_item"), "Falta traducción del item");
        Assertions.assertFalse(enJson.has("block.pixelstudioscore.test_item"), "ERROR: Item registrado como bloque");
    }
}
