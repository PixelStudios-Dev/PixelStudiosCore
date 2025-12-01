package io.pixelstudios.pixelstudioscore.api.block;

import io.pixelstudios.pixelstudioscore.impl.registry.BlockRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.ModelRegistry;
import io.pixelstudios.pixelstudioscore.impl.registry.CoreRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

import static io.pixelstudios.pixelstudioscore.api.lang.LanguageManager.*;

/**
 *
 * Crea un bloque y lo registra, tanto en el registro de minecraft como en el datagen, de una manera sencilla.
 *
 */
public final class BlockFactory {

    private final Block block;
    private final String id;

    private RegistryKey<ItemGroup> group;
    private Map<Languages, String> translations;

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

    public static BlockFactory create(String id, AbstractBlock.Settings settings) {
        return new BlockFactory(id, settings);
    }

    public static BlockFactory create(String id) {
        return new BlockFactory(id);
    }

    public BlockFactory setTranslatedName(Map<Languages, String> translations) {

        this.translations = translations;

        return this;

    }

    public BlockFactory setBlockCategory(RegistryKey<ItemGroup> group) {

        this.group = group;

        return this;

    }

    /**
     *
     * <p>Evita que el datagen genere automaticamente el modelo predeterminado de bloque.
     *
     * <p>Util para bloques personalizados.
     *
     * @return Este mismo objeto
     *
     */
    public BlockFactory withoutDefaultModel() {

        this.defaultModel = false;

        return this;

    }

    public Block build() {

        BlockRegistry<Block> blockRegistrySettings = BlockRegistry.fromCustomSettings()
                .asBlockItem(new BlockItem(block, new Item.Settings()))
                .inCreativeTab(group)
                .addTranslatedName(translations);

        if (defaultModel)
            ModelRegistry.getDefaultBlockModelList().add(block);

        return CoreRegistry.of(id, block)
                .withCategory(Registries.BLOCK)
                .applySettings(blockRegistrySettings)
                .register();

    }

}
