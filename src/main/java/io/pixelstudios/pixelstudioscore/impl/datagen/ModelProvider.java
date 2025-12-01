package io.pixelstudios.pixelstudioscore.impl.datagen;

import io.pixelstudios.pixelstudioscore.impl.registry.ModelRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;

public class ModelProvider extends FabricModelProvider {

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        ModelRegistry.getDefaultBlockModelList().forEach(blockStateModelGenerator::registerSimpleCubeAll);

        ModelRegistry.getSpawnEggModelList().forEach(entry -> blockStateModelGenerator.registerParentedItemModel(entry, ModelIds.getMinecraftNamespacedItem("template_spawn_egg")));

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        ModelRegistry.getDefaultItemModelList().forEach(entry -> itemModelGenerator.register(entry, Models.GENERATED));

    }

}
