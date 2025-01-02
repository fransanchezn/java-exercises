package org.fransanchez.exercises.arrayandstrings.twopointer;

// 125. Valid Palindrome
public class ValidPalindrome {
    public boolean isPalindrome(final String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            final var leftChar = Character.toLowerCase(s.charAt(left));
            final var rightChar = Character.toLowerCase(s.charAt(right));

            if (!isAlphanumeric(leftChar)) {
                left++;
            } else if (!isAlphanumeric(rightChar)) {
                right--;
            } else if (leftChar != rightChar) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    private boolean isAlphanumeric(final char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }
}
