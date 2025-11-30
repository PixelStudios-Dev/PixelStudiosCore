package io.pixelstudios.pixelstudioscore.impl;

import io.pixelstudios.pixelstudioscore.api.block.BlockFactory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModBlocks {

    public static final Block TEST_BLOCK;

    static {

        TEST_BLOCK = BlockFactory.create("test", new Block(AbstractBlock.Settings.create()))
                .setTranslatedName(Map.of(
                        "es", "Prueba",
                        "en", "Test"
                ))
                .setBlockCategory(ItemGroups.COMBAT)
                .build();
        //TEST_BLOCK = register(new Block(AbstractBlock.Settings.create()), "test", true);

    }

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = Identifier.of("testmod", name);

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void test() {

    }

}
