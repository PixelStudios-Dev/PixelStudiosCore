package io.pixelstudios.pixelstudioscore.impl.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.HashSet;

public class ModModelRegistry {

    private static final HashSet<Block> defaultBlockModelList = new HashSet<>();

    private static final HashSet<Item> defaultItemModelList = new HashSet<>();

    private static final HashSet<Item> spawnEggModelList = new HashSet<>();

    public static HashSet<Block> getDefaultBlockModelList() {
        return defaultBlockModelList;
    }

    public static HashSet<Item> getDefaultItemModelList() {
        return defaultItemModelList;
    }

    public static HashSet<Item> getSpawnEggModelList() {
        return spawnEggModelList;
    }

}
