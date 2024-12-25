package org.fransanchez.exercises.hashing;

import java.util.Arrays;

// 242. Valid Anagram
public class Anagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("aaaaaa", "aaaaaa"));
    }

    public static boolean isAnagram(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }

        final var dictionary = new int[26];
        final var sLetters = s.toCharArray();
        final var tLetters = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            final var sLetter = sLetters[i];
            dictionary[sLetter - 'a'] += 1;

            final var tLetter = tLetters[i];
            dictionary[tLetter - 'a'] -= 1;
        }

        return Arrays.stream(dictionary).allMatch(i -> i == 0);
    }
}
