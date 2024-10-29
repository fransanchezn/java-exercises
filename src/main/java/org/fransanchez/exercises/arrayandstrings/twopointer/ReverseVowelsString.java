package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.HashSet;
import java.util.Set;

// 345. Reverse Vowels of a String
public class ReverseVowelsString {
    private static final Set<Character> VOWELS = new HashSet<>() {{
        add('a');add('A');
        add('e');add('E');
        add('i');add('I');
        add('o');add('O');
        add('u');add('U');
    }};

    public String reverseVowels(final String s) {
        int left = 0;
        int right = s.length() - 1;
        final var input = s.toCharArray();

        while (left < right) {
            final var leftChar = s.charAt(left);
            final var rightChar = s.charAt(right);

            if (VOWELS.contains(leftChar) && VOWELS.contains(rightChar)) {
                input[left] = rightChar;
                input[right] = leftChar;
                left++;
                right--;
            } else {
                if (!VOWELS.contains(leftChar)) {
                    left++;
                }

                if (!VOWELS.contains(rightChar)) {
                    right--;
                }
            }
        }

        return new String(input);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseVowelsString().reverseVowels("IceCreAm"));
    }
}
