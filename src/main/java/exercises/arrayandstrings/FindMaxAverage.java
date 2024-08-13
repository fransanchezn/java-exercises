package exercises.arrayandstrings;

// 643. Maximum Average Subarray I
public class FindMaxAverage {
    // Input: nums = [1,12,-5,-6,50,3], k = 4
    // Output: 12.75000
    // Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
    public double findMaxAverage(final int[] nums, final int k) {
        var count = 0;
        for (int i = 0; i < k; i++) {
            count += nums[i];
        }

        var result = count;
        for (int i = k; i < nums.length; i++) {
            final var removeValue = nums[i - k];
            final var addValue = nums[i];
            count = count - removeValue + addValue;

            result = Math.max(result, count);
        }

        return (double) result / k;
    }

    public static void main(final String[] args) {
        final var sut = new FindMaxAverage();
        final var result = sut.findMaxAverage(new int[] {0,4,0,3,2}, 1);

        System.out.println(result);
    }
}
