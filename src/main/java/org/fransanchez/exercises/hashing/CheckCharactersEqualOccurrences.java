package org.fransanchez.exercises.hashing;

import java.util.HashMap;

// 1941. Check if All Characters Have Equal Number of Occurrences
public class CheckCharactersEqualOccurrences {
    public boolean areOccurrencesEqual(String s) {
        final var occ = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            occ.compute(s.charAt(i), (k, v) -> v == null? 0 : v + 1);
        }

        return occ.values().stream().distinct().count() == 1;
    }

    public static void main(String[] args) {
        final var sut = new CheckCharactersEqualOccurrences();
        final var result = sut.areOccurrencesEqual("abacbc");

        System.out.println(result);
    }
}
