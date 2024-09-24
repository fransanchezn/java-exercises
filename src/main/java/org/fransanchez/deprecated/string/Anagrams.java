package org.fransanchez.deprecated.string;

import java.util.Arrays;

public class Anagrams {

    public static void main(String[] args) {
        System.out.println(anagrams("Mango", "goman"));
    }

    // O(n) - time complexity
    // O(n) - space complexity
    public static boolean anagrams(String a, String b) {
        final var aArray = a.toLowerCase().toCharArray(); // O(n) TC, O(n) SC
        Arrays.sort(aArray); // O(n log n)

        final var bArray = b.toLowerCase().toCharArray(); // O(n), O(n) SC
        Arrays.sort(bArray); // O(n log n)

        return new String(bArray).equals(new String(aArray)); // O(1)
    }

    // O(n) - time complexity
    // O(1) - space complexity
    public static boolean anagrams2(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        for (char c : a.toCharArray()) { // O(n)
            if (b.isEmpty()) {
                return false;
            }

            int l = b.length();

            b = b.replaceFirst(c+"",""); // O(n)

            if (l == b.length()) {
                return false;
            }
        }

        if (b.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
