package org.fransanchez.exercises.binarysearch;

// 35. Search Insert Position
public class SearchInsertPosition {
    public int searchInsert(final int[] nums, final int target) {
        var left = 0;
        var right = nums.length;

        while (left < right) {
            final var mid = (right + left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
