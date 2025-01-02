package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.Arrays;

// 167. Two Sum II - Input Array Is Sorted
public class TwoSumInputArraySorted {
    // numbers=2,7,11,15 target=9 => output=[1,2]
    public int[] twoSum(final int[] numbers, final int target) {
         int left = 0;
         int right = numbers.length - 1;

         while(left < right) {
             final var sum = numbers[left] + numbers[right];

             if (sum > target) {
                 right--;
             } else if (sum < target) {
                 left++;
             } else {
                 return new int[] {left + 1, right + 1};
             }
         }

         return new int[]{};
    }

    public static void main(String[] args) {
        final var sut = new TwoSumInputArraySorted();
        final var result = sut.twoSum(new int[] {2,7,11,15}, 9);
        System.out.println(Arrays.toString(result));
    }
}
