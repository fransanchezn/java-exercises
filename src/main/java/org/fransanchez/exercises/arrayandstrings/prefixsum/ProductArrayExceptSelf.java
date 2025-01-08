package org.fransanchez.exercises.arrayandstrings.prefixsum;

// 238. Product of Array Except Self
// Not done
public class ProductArrayExceptSelf {
    // [1,2,3,4]
    public int[] productExceptSelf(final int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        // [1,1,2,6]

        int postfix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= postfix;
            postfix *= nums[i];
        }

        return res;
    }
}
