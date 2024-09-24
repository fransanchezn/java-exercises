package org.fransanchez.deprecated.array;

import java.util.Arrays;

public class RemoveTriplicatesSortedArray {
    public static void main(String[] args) {
        var nums = new int[] {1,1,1,2,2,2,3};

        var result = removeDuplicates2(nums);

        System.out.println("result: " + result);
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, 0, result)));
    }

    // Non optimal solution
    // 1,1,1,2,2,2,3
    public static int removeDuplicates1(int[] nums) {
        var comparatorNumber = nums[0];
        var occurrences = 0;
        var pointerInsert = 0;

        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] == comparatorNumber) {
                occurrences++;
            } else {
                // Reset
                comparatorNumber = nums[i];
                occurrences = 1;
            }

            if (occurrences < 3) {
                nums[pointerInsert] = nums[i];
                pointerInsert++;
            }
        }

        return pointerInsert;
    }

    // Optimal solution
    // 1,1,1,2,2,2,3
    public static int removeDuplicates2(int[] nums) {
        var insertPointer = 1;
        for (int i = 1; i < nums.length; i++) {
            if (insertPointer == 1 || nums[i] != nums[insertPointer - 2]) {
                nums[insertPointer++] = nums[i];
            }
        }

        return insertPointer;
    }
}
