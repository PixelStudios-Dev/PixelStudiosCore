package io.pixelstudios.pixelstudioscore.impl.registry;

import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager;
import io.pixelstudios.pixelstudioscore.api.lang.LanguageManager.Languages;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

public final class ItemRegistry<T extends Item> implements IRegistryAppliable<T> {

    private RegistryKey<ItemGroup> group;

    private Map<Languages, String> translations;

    private ItemRegistry() {}

    public static ItemRegistry<Item> fromCustomSettings() {
        return new ItemRegistry<>();
    }

    public ItemRegistry<T> addTranslatedName(Map<Languages, String> translations) {

        this.translations = translations;

        return this;

    }

    public ItemRegistry<T> inCreativeTab(RegistryKey<ItemGroup> group) {

        this.group = group;

        return this;

    }

    @Override
    public void apply(CoreRegistry<T> registry) {

        if (translations != null) {

            translations.forEach((k, v) -> {

                LanguageManager.addTranslation("block", registry.path, v, k);

            });

        }

        if (group != null)
            ItemGroupEvents.modifyEntriesEvent(group).register(t -> t.add(registry.entry));

    }

}
