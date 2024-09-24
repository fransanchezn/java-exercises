package org.fransanchez.exercises.binarysearch;

// 704. Binary Search
public class BinarySearch {
    public int search(final int[] nums, final int target) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            final var mid = left + (right - left)  / 2;
            if (nums[mid] == target) {
              return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
