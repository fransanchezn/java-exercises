package exercises.arrayandstrings.prefixsum;

import java.util.Arrays;

public class KRadiusSubarrayAverages {

    /*
Input: nums = [7,4,3,9,1,8,5,2,6], k = 3
Output: [-1,-1,-1,5,4,4,-1,-1,-1]
Explanation:
- avg[0], avg[1], and avg[2] are -1 because there are less than k elements before each index.
- The sum of the subarray centered at index 3 with radius 3 is: 7 + 4 + 3 + 9 + 1 + 8 + 5 = 37.
  Using integer division, avg[3] = 37 / 7 = 5.
- For the subarray centered at index 4, avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4.
- For the subarray centered at index 5, avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4.
- avg[6], avg[7], and avg[8] are -1 because there are less than k elements after each index.
     */
    public int[] getAverages(final int[] nums, final int k) {
        final var result = new int[nums.length];

        long sum = 0;
        final var prefixSum = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        for (int i = 0; i < result.length; i++) {
            if (i < k || i + k >= result.length) {
                result[i] = -1;
            } else {
                final long sumStart = prefixSum[i - k];
                final long sumEnd = prefixSum[i + k];
                final var avg = (sumEnd - sumStart + nums[i - k]) / (k * 2 + 1);
                result[i] = (int) avg;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new KRadiusSubarrayAverages();
        final var input = new int[] { Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1,Integer.MAX_VALUE - 1 };
        final var result = sut.getAverages(input, 2);

        System.out.println(Arrays.toString(result));
    }
}
