package org.fransanchez.exercises.arrayandstrings.slidingwindow.fix;

import java.util.Arrays;

// 567. Permutation in String
public class PermutationInString {
    // s1 = "ab", s2 = "eidbaooo"
    // true // s2 contains one permutation of s1 ("ba")
    public boolean checkInclusion(final String s1, final String s2) {
        final var sortedS1 = s1.toCharArray();
        Arrays.sort(sortedS1);

        final var window = new StringBuilder();

        for (int right = 0; right < s2.length(); right++) {
            if (window.length() >= s1.length()) {
                window.deleteCharAt(0);
            }

            window.append(s2.charAt(right));

            if (window.length() == s1.length()) {
                final var sortedWindow = window.toString().toCharArray();
                Arrays.sort(sortedWindow);
                if (Arrays.equals(sortedS1, sortedWindow)) {
                    return true;
                }
            }
        }

        return false;
    }
}
