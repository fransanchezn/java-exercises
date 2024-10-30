package org.fransanchez.exercises.arrayandstrings;

import java.util.Arrays;

// 1929. Concatenation of Array
public class ConcatenationArray {
    public int[] getConcatenation(final int[] nums) {
        final var concatSize = nums.length * 2;
        final var result = new int[concatSize];

        for (int i = 0 ; i < concatSize; i++) {
            result[i] = nums[i % nums.length];
        }

        return result;
    }

    public static void main(String[] args) {
        final var result = new ConcatenationArray().getConcatenation(new int[] { 1,3,2,1 });
        System.out.println(Arrays.toString(result));
    }
}
