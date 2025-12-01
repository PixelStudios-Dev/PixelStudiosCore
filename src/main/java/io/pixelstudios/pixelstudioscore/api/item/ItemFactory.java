package io.pixelstudios.pixelstudioscore.api.item;

import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager.Languages;
import io.pixelstudios.pixelstudioscore.impl.registry.CoreRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.ItemRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.ModelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

/**
 *
 * Crea un item y lo registra, tanto en el registro de minecraft como en el datagen, de una manera sencilla.
 *
 */
public final class ItemFactory {

    private final Item item;
    private final String id;

    private RegistryKey<ItemGroup> group;

    private boolean defaultModel;
    private boolean isEgg;

    private Map<Languages, String> translations;

    private ItemFactory(String id, Item item) {

        this.id = id;
        this.item = item;

        this.defaultModel = true;
        this.isEgg = false;

    }

    private ItemFactory(String id, Item.Settings settings) {

        this(id, new Item(settings));

    }

    private ItemFactory(String id) {

        this(id, new Item(new Item.Settings()));

    }

    public static ItemFactory create(String id, Item item) {
        return new ItemFactory(id, item);
    }

    public static ItemFactory create(String id, Item.Settings settings) {
        return new ItemFactory(id, settings);
    }

    public static ItemFactory create(String id) {
        return new ItemFactory(id);
    }

    public ItemFactory addTranslatedName(Map<Languages, String> translations) {

        this.translations = translations;

        return this;

    }

    public ItemFactory setItemCategory(RegistryKey<ItemGroup> group) {

        this.group = group;

        return this;

    }

    public ItemFactory withSpawnEggTemplate() {

        this.defaultModel = false;

        this.isEgg = true;

        return this;
    }

    public ItemFactory withoutDefaultModel() {

        this.defaultModel = false;

        return this;

    }

    public Item build() {

        ItemRegistry<Item> itemRegistrySettings = ItemRegistry.fromCustomSettings()
                .addTranslatedName(translations)
                .inCreativeTab(group);

        if (defaultModel)
            ModelRegistry.getDefaultItemModelList().add(item);

        if (isEgg)
            ModelRegistry.getSpawnEggModelList().add(item);

        // - `ModRegistry` es un registrador universal
        // - Aplica tanto para Bloques, Items, Entidades, etc.
        // - Utilización de Patron Builder
        return CoreRegistry.of(id, item)
                .withCategory(Registries.ITEM)
                .applySettings(itemRegistrySettings)
                .register();

    }

}

