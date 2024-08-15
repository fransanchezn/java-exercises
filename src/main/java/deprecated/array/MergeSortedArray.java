package deprecated.array;

import java.util.Arrays;

/*
https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        final var nums1 = new int[]{ 2,0,0,0 };
        final var m = 1;

        final var nums2 = new int[]{ 1,2,3 };
        final var n = 3;

        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        var pointerNum1 = m - 1;
        var pointerNum2 = n - 1;
        var pointerInsertPosition = m + n - 1;

        while (pointerNum2 >= 0) {
            if (pointerNum1 >= 0 && nums1[pointerNum1] > nums2[pointerNum2]) {
                nums1[pointerInsertPosition] = nums1[pointerNum1];
                pointerNum1--;
            } else {
                nums1[pointerInsertPosition] = nums2[pointerNum2];
                pointerNum2--;
            }
            pointerInsertPosition--;
        }
    }
}
