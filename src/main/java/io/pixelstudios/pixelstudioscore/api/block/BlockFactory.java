package io.pixelstudios.pixelstudioscore.api.block;

import io.pixelstudios.pixelstudioscore.impl.registry.ModBlockRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.ModModelRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.ModRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

/**
 *
 * Crea un bloque y lo registra, tanto en el registro de minecraft como en el datagen, de una manera sencilla.
 *
 */
public final class BlockFactory {

    private final Block block;
    private final String id;

    private RegistryKey<ItemGroup> group;
    private Map<String, String> translations;

    private boolean defaultModel;

    private BlockFactory(String id, Block block) {

        this.id = id;
        this.block = block;

        this.defaultModel = true;

    }

    private BlockFactory(String id, AbstractBlock.Settings settings) {

        this(id, new Block(settings));

    }

    private BlockFactory(String id) {

        this(id, new Block(AbstractBlock.Settings.create()));

    }

    public static BlockFactory create(String id, Block block) {
        return new BlockFactory(id, block);
    }

    public BlockFactory setTranslatedName(Map<String, String> translations) {

        this.translations = translations;

        return this;

    }

    public BlockFactory setBlockCategory(RegistryKey<ItemGroup> group) {

        this.group = group;

        return this;

    }

    public BlockFactory withoutDefaultModel() {

        this.defaultModel = false;

        return this;

    }

    public Block build() {

        ModBlockRegistry<Block> blockRegistrySettings = ModBlockRegistry.fromCustomSettings()
                .asBlockItem(new BlockItem(block, new Item.Settings()))
                .inCreativeTab(group)
                .addTranslatedName(translations);

        if (defaultModel)
            ModModelRegistry.getDefaultBlockModelList().add(block);

        return ModRegistry.of(id, block)
                .withCategory(Registries.BLOCK)
                .applySettings(blockRegistrySettings)
                .register();

    }

}
