package com.west2.dwasearch.data;

public final class EventCatalog {
    private static final String[] KEYWORDS = {
            "women 3m springboard",
            "women 10m platform",
            "women 3m synchronised",
            "women 10m synchronised",
            "men 3m springboard",
            "men 10m platform",
            "men 3m synchronised",
            "men 10m synchronised"
    };

    private EventCatalog() {
    }

    public static String[] keywords() {
        return KEYWORDS.clone();
    }

    public static boolean contains(String keyword) {
        for (int i = 0; i < KEYWORDS.length; i++) {
            if (KEYWORDS[i].equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public static String fileNameOf(String keyword) {
        return keyword.replace(' ', '_') + ".json";
    }
}
