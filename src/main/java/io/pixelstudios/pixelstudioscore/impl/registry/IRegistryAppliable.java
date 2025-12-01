package io.pixelstudios.pixelstudioscore.impl.registry;

public interface IRegistryAppliable<T> {

    void apply(CoreRegistry<T> registry);

}
