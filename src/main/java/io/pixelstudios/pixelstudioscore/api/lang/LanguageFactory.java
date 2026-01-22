package io.pixelstudios.pixelstudioscore.api.lang;

import java.util.HashMap;
import java.util.Map;

public class LanguageFactory {

    private final String key;

    private final Map<String, String> translations = new HashMap<>();

    private LanguageFactory(String key) {

        this.key = key;

    }

    public static LanguageFactory create(String key) {

        return new LanguageFactory(key);

    }

    public LanguageFactory spanish(String text) {

        return custom(LanguageManager.Languages.SPANISH.getLang(), text);

    }

    public LanguageFactory english(String text) {

        return custom(LanguageManager.Languages.ENGLISH.getLang(), text);

    }

    /**
     * Sirve para agregar una clave personalizada compatible con cualquier idioma.
     * Ejemplo:
     * <pre>{@code
     * LanguageFactory.create("item.sword.name")
     *     .spanish("Espada")
     *     .english("Sword")
     *     .custom("fr_fr", "Épée")        // Francés
     *     .custom("pt_br", "Espada")      // Portugués
     *     .custom("ru_ru", "Меч")         // Ruso
     *     .build();
     * }</pre>
     *
     * @param langCode El código del idioma (ej. "fr_fr").
     * @param text El texto traducido.
     * @return Esta instancia de LanguageFactory.
     */
    public LanguageFactory custom(String langCode, String text) {

        if (text != null)
            this.translations.put(langCode, text);

        return this;

    }

    public void build() {

        translations.forEach((langCode, text) -> LanguageManager.addRawTranslation(key, text, langCode));

    }
}