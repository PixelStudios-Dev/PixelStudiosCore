package io.pixelstudios.pixelstudioscore.api.lang;

import java.util.HashMap;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.MOD_ID;

public final class LanguageManager {

    private static final HashMap<String, String> translationMap = new HashMap<>();

    private LanguageManager() {}

    public static void addTranslation(String category, String id, String translation, Languages language) {

        translationMap.put(language.getLang()+"#"+category+"."+MOD_ID+"."+id, translation);

    }

    public static HashMap<String, String> getTranslationMap() {
        return translationMap;
    }

    public enum Languages {

        SPANISH("es_es"), ENGLISH("en_us");

        final String lang;

        Languages(String l) {
            lang = l;
        }

        public String getLang() {
            return lang;
        }

    }

}
