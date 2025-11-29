package io.pixelstudios.pixelstudioscore.api.lang;

import java.util.HashMap;

import static io.pixelstudios.pixelstudioscore.PixelStudiosCore.MOD_ID;

public final class ModLanguageManager {

    private static final HashMap<String, String> spanishTranslationMap = new HashMap<>();
    private static final HashMap<String, String> englishTranslationMap = new HashMap<>();

    private ModLanguageManager() {}

    public static void addTranslation(String category, String id, String translation, String language) {

        switch (language) {
            case "es" -> spanishTranslationMap.put(category+"."+MOD_ID+"."+id, translation);
            case "en" -> englishTranslationMap.put(category+"."+MOD_ID+"."+id, translation);
        }

    }

    public static HashMap<String, String> getSpanishTranslationMap() {
        return spanishTranslationMap;
    }

    public static HashMap<String, String> getEnglishTranslationMap() {
        return englishTranslationMap;
    }

}
