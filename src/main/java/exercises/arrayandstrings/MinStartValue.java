package exercises.arrayandstrings;

// 1413. Minimum Value to Get Positive Step by Step Sum
public class MinStartValue {
    /*
Input: nums =[-3,2,-3,4,2]
Output: 5
Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
step by step sum
startValue = 4 | startValue = 5 | nums
  (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
  (1 +2 ) = 3  | (2 +2 ) = 4    |   2
  (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
  (0 +4 ) = 4  | (1 +4 ) = 5    |   4
  (4 +2 ) = 6  | (5 +2 ) = 7    |   2

     */
    public int minStartValue(final int[] nums) {
        var sum = 0;
        var minStartValue = 0;

        for (int num : nums) {
            sum += num;
            minStartValue = Math.min(minStartValue, sum);
        }

        return 1 - minStartValue;
    }

    public static void main(String[] args) {
        final var msv = new MinStartValue();
        final var result = msv.minStartValue(new int[] { -3,2,-3,4,2 });

        System.out.println(result);

    }
}
