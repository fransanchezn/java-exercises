package exercises.binarysearch;

import java.util.Arrays;

// 2389. Longest Subsequence With Limited Sum
public class LongestSubsequenceWithLimitedSum {
    public int[] answerQueries(final int[] nums, final int[] queries) {
        Arrays.sort(nums);

        final var prefixSum = new int[nums.length];
        var sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        final var result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            final var found = search(prefixSum, queries[i]);
            result[i] = found;
        }

        return result;
    }

    // [1,3,7,12]
    public int search(final int[] arr, final int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            final var mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (left >= arr.length) {
            return arr.length;
        }

        return arr[left] > target ? left : left + 1;
    }
}
