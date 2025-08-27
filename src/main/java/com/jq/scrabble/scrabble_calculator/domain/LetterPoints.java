package com.jq.scrabble.scrabble_calculator.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class LetterPoints {

    private final Map<String, Integer> points;

    public LetterPoints() {
        Map<String, Integer> m = new HashMap<>();

        putAll(m, 1, "A","E","I","O","U","L","N","S","T","R");
        putAll(m, 2, "D","G");
        putAll(m, 3, "B","C","M","P");
        putAll(m, 4, "F","H","V","W","Y");
        putAll(m, 6, "K");
        putAll(m, 8, "J","X");
        putAll(m,10, "Q","Z");

        points = Collections.unmodifiableMap(m);
    }

    private static void putAll(Map<String,Integer> m, int value, String... letters) {
        for (String s : letters) {
            m.put(s, value);
        }
    }

    public int valueOf(String s) {
        if (s == null || s.isBlank()) return 0;
        return points.getOrDefault(s.toUpperCase(), 0);
    }
}
