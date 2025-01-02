package org.fransanchez.exercises.arrayandstrings.slidingwindow.variable;

import java.util.HashSet;

// 3. Longest Substring Without Repeating Characters
public class LongestSubstringNoRepeatingCharacters {
    public int lengthOfLongestSubstring(final String s) {
        final var counters = new HashSet<Character>();
        var left = 0;
        var result = 0;

        for (int right = 0; right < s.length(); right++) {
            // Increase Left
            while(counters.contains(s.charAt(right))) {
                counters.remove(s.charAt(left));
                left++;
            }
            counters.add(s.charAt(right));
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(final String[] args) {
        final var sut = new LongestSubstringNoRepeatingCharacters();
        final var result = sut.lengthOfLongestSubstring("pwwkew");

        System.out.println(result);
    }
}
