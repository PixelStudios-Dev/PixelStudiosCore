package io.pixelstudios.pixelstudioscore.impl.registry;

import io.pixelstudios.pixelstudioscore.api.lang.ModLanguageManager;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Map;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.MOD_ID;

public final class ModBlockRegistry<T extends Block> implements IRegistryAppliable<T> {

    private BlockItem blockItem;

    private RegistryKey<ItemGroup> group;

    private Map<String, String> translations;

    public static ModBlockRegistry<Block> fromCustomSettings() {
        return new ModBlockRegistry<>();
    }

    public ModBlockRegistry<T> asBlockItem(BlockItem blockItem) {

        this.blockItem = blockItem;

        return this;

    }

    public ModBlockRegistry<T> addTranslatedName(Map<String, String> translations) {

        this.translations = translations;

        return this;

    }

    public ModBlockRegistry<T> inCreativeTab(RegistryKey<ItemGroup> group) {

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

        if (blockItem != null)
            Registry.register(Registries.ITEM, Identifier.of(MOD_ID, registry.path), blockItem);

    }

}
