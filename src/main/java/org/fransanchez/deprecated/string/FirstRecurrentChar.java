package org.fransanchez.deprecated.string;

import java.util.HashMap;

public class FirstRecurrentChar {

    public static void main(String[] args) {
        final var a = "asdfghakl";

        final var result = FirstRecurrentChar.firstRecurrentChat(a);
        System.out.println(result);
    }

    // O(n) - time complexity
    // O(n) - space complexity
    public static String firstRecurrentChat(final String a) {
        final var map = new HashMap<>();
        final var c = a.split("");

        for (int i = 0; i < c.length; i++) { // O(n)
            if (map.get(c[i]) != null) { // O(1) -> O(n)
                return c[i];
            }

            map.put(c[i], 1); // O(1) -> O(n)
        }

        return null;
    }
}
