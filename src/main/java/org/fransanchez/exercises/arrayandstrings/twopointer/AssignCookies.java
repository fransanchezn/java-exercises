package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.Arrays;

// 455. Assign Cookies
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int cookiesUsed = 0;

        int childIndex = g.length - 1;
        int cookieIndex = s.length - 1;

        while (childIndex >= 0 && cookieIndex >= 0) {
            final var childGreed = g[childIndex];
            final var cookieSize = s[cookieIndex];

            if (childGreed <= cookieSize) {
                cookiesUsed++;
                cookieIndex--;
            }

            childIndex--;
        }

        return cookiesUsed;
    }

    public static void main(String[] args) {
        System.out.println(new AssignCookies().findContentChildren(new int[] {1,2,3}, new int[] {1,1}));
    }
}
