package com.manuelluvuvamo.portfolio.common;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public final class Slugs {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]+");
    private static final Pattern DASHES = Pattern.compile("-{2,}");
    private static final Pattern EDGE_DASHES = Pattern.compile("(^-+)|(-+$)");

    private Slugs() {
    }

    /** "Ambiente Virtual de Ensino à Distância" -> "ambiente-virtual-de-ensino-a-distancia" */
    public static String of(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        String noWhitespace = WHITESPACE.matcher(input.trim()).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String slug = NON_LATIN.matcher(normalized).replaceAll("-").toLowerCase(Locale.ROOT);
        slug = DASHES.matcher(slug).replaceAll("-");
        return EDGE_DASHES.matcher(slug).replaceAll("");
    }
}
