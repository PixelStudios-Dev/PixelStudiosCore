package io.pixelstudios.pixelstudioscore.impl.registry;

public interface IRegistryAppliable<T> {

    void apply(ModRegistry<T> registry);

}
