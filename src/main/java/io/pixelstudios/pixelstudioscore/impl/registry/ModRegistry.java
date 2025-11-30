package io.pixelstudios.pixelstudioscore.impl.registry;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.MOD_ID;
import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.LOGGER;

/**
 *
 * <p>
 * Clase cuya funcion es servir como nucleo para todos los registros del mod.
 * </p>
 *
 * Siguiendo un patron builder, sera de utilidad servirse en ejemplos como los dado en la clase BlockFactory.
 *
 * <p>
 * ModRegistry es un registrador universal
 * Aplica tanto para Bloques, Items, Entidades, etc.
 * Utiliza Patron Builder
 * </p>
 *
 * @param <T> Cualquier objeto admitido por el registro de minecraft
 *
 */
public final class ModRegistry<T> {

    final String path;
    final T entry;

    private Registry<? super T> category;

    private ModRegistry(String path, T entry) {

        this.path = path;

        this.entry = entry;

    }

    /**
     *
     * Inicializa el registro con esto, creara un nuevo registro y te permitira acceder
     * a todos los parametros de esta misma clase
     *
     * @param path El identificador tipo cadena del objeto a registrar
     * @param entry El objeto en sí
     * @return El objeto a registrar
     * @param <T> El tipo de objeto
     */
    public static <T> ModRegistry<T> of(String path, T entry) {
        return new ModRegistry<>(path, entry);
    }

    /**
     *
     * Establece la categoria a la que esra registrado el objeto
     *
     * @param registry La categoria dada por Registries
     * @return El objeto a registrar
     */
    public ModRegistry<T> withCategory(Registry<? super T> registry) {

        this.category = registry;

        return this;

    }

    /**
     *
     * Aplica las opciones desde un registro aplicable
     *
     * @param registryAppliable El registro aplicable Ej. ModBlockRegistry
     * @return El objeto a registrar
     */
    public ModRegistry<T> applySettings(IRegistryAppliable<T> registryAppliable) {

        if (this.category == null)
            throw new IllegalStateException("Category has not been set yet");

        registryAppliable.apply(this);

        return this;

    }

    /**
     *
     * Finalmente registra el objeto en el registro de minecraft, con los parametros dados
     *
     * @return El objeto a registrar
     */
    public T register() {

        if (category == null)
            throw new IllegalStateException("Registry category must be set before registering " + path);

        LOGGER.info("Registering {} as {}...", path, entry.getClass().getSimpleName());

        return Registry.register(category, Identifier.of(MOD_ID, path), entry);

    }

}
