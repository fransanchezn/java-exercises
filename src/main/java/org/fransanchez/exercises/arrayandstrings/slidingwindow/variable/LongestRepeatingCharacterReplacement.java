package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

// 424. Longest Repeating Character Replacement
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(final String s, final int k) {
        final var distinctChars = new HashMap<Character, Integer>();

        int left = 0;
        int length = -1;

        for (int right = 0; right < s.length(); right++) {
            final var rightChar = s.charAt(right);
            final var rightCharCount = distinctChars.getOrDefault(rightChar, 0) + 1;
            distinctChars.put(rightChar, rightCharCount);

            while ((right - left + 1) - mostFrequentCount(distinctChars) > k) {
                final var leftChar = s.charAt(left);
                final var leftCharCount = distinctChars.getOrDefault(leftChar, 0) - 1;
                if (leftCharCount <= 0) {
                    distinctChars.remove(leftChar);
                } else {
                    distinctChars.put(leftChar, leftCharCount);
                }

                left++;
            }

            length = Math.max(length, right - left + 1);
        }

        return length == -1 ? 0 : length;
    }

    private int mostFrequentCount(final Map<Character, Integer> counters) {
        return counters.values().stream().max(Integer::compareTo).orElse(0);
    }

    public static void main(String[] args) {
        final var sut = new LongestRepeatingCharacterReplacement();
        final var result = sut.characterReplacement("ABAB", 2);
        System.out.println(result);
    }
}


// AABABBA