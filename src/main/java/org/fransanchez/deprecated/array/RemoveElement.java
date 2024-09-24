package org.fransanchez.deprecated.array;

import java.util.Arrays;

public class RemoveElement {
    public static void main(String[] args) {
        var nums = new int[] { 1, 2, 1, 1, 2, 3 };
        final var result = removeElement(nums, 1);

        System.out.println("result: " + result);
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, result)));
    }

    public static int removeElement(int[] nums, int val) {
        var insertPointer = 0;
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[insertPointer] = nums[i];
                insertPointer++;
            }
        }

        return insertPointer;
    }
}
