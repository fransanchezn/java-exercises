package exercises.arrayandstrings.prefixsum;

public class FindPivotIndex {
    public int pivotIndex(final int[] nums) {
        var total = 0;
        for (int num : nums) {
            total += num;
        }

        var leftTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            var rightTotal = total - leftTotal - nums[i];
            if (leftTotal == rightTotal) {
                return i;
            }
            leftTotal += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        final var sut = new FindPivotIndex();
        final var result = sut.pivotIndex(new int[] { 1, 1, 2, 1, 1 });

        System.out.println(result);
    }
}
