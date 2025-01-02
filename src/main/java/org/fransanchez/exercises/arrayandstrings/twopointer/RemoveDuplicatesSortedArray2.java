package org.fransanchez.exercises.arrayandstrings.twopointer;

import java.util.Arrays;

// 80. Remove Duplicates from Sorted Array II
public class RemoveDuplicatesSortedArray2 {
    // 1,1,1,1,2,2,3
    public int removeDuplicates(int[] nums) {
        int index = 1;
        int duplicateCounter = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[index - 1]) {
                duplicateCounter++;
                if (duplicateCounter < 2) {
                    nums[index] = nums[i];
                    index++;
                }
            } else {
                duplicateCounter = 0;
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        final var sut = new RemoveDuplicatesSortedArray2();
        final var arr = new int[] {1,1,1,2,2,3};
        sut.removeDuplicates(arr);

        System.out.println(Arrays.toString(arr));

    }
}
