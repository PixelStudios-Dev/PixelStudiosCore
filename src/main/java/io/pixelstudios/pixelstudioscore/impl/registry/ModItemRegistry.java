package io.pixelstudios.pixelstudioscore.impl.registry;

import io.pixelstudios.pixelstudioscore.api.lang.ModLanguageManager;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

public final class ModItemRegistry<T extends Item> implements IRegistryAppliable<T> {

    private RegistryKey<ItemGroup> group;

    private Map<String, String> translations;

    private ModItemRegistry() {}

    public static ModItemRegistry<Item> fromCustomSettings() {
        return new ModItemRegistry<>();
    }

    public ModItemRegistry<T> addTranslatedName(Map<String, String> translations) {

        this.translations = translations;

        return this;

    }

    public ModItemRegistry<T> inCreativeTab(RegistryKey<ItemGroup> group) {

        this.group = group;

        return this;

    }

    @Override
    public void apply(ModRegistry<T> registry) {

        if (translations != null) {

            translations.forEach((k, v) -> {

                ModLanguageManager.addTranslation("block", registry.path, v, k);

            });

        }

        if (group != null)
            ItemGroupEvents.modifyEntriesEvent(group).register(t -> t.add(registry.entry));

    }

}
