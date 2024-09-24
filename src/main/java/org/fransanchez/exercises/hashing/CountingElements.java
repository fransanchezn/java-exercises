package org.fransanchez.exercises.hashing;

import java.util.HashSet;

// 1426. Counting Elements
public class CountingElements {
    public int countElements(final int[] arr) {
        final var numbers = new HashSet<Integer>();
        for (int num: arr) {
            numbers.add(num);
        }

        var result = 0;
        for (int num: arr) {
            if (numbers.contains(num + 1)) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new CountingElements();
        final var result = sut.countElements(new int[] { 1,2,3 });

        System.out.println(result);
    }
}
