package org.fransanchez.exercises.arrayandstrings.slidingwindow.fix;

import java.util.HashSet;
import java.util.Set;

// 1456. Maximum Number of Vowels in a Substring of Given Length
// Fix sliding window
public class MaxVowelsSubstring {
    private final Set<Character> vowels = new HashSet<>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};

    public int maxVowels(final String s, final int k) {
        var count = 0;
        var characters = s.toCharArray();

        for (int right = 0; right < k; right++) {
            if (isVowel(characters[right])) {
                count++;
            }
        }

        var result = count;
        for (int right = k; right < characters.length; right++) {
            if (isVowel(characters[right - k])) {
                count--;
            }

            if (isVowel(characters[right])) {
                count++;
            }

            result = Math.max(result, count);
        }

        return result;
    }

    private boolean isVowel(final char c) {
        return vowels.contains(c);
    }

    public static void main(String[] args) {
        final var sut = new MaxVowelsSubstring();
        final var result = sut.maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33);

        System.out.println(result);
    }
}
