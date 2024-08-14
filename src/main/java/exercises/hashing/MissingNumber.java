package exercises.hashing;

// 268. Missing Number
public class MissingNumber {
    public int missingNumber(final int[] nums) {
        final var numbers = new boolean[nums.length + 1];
        for (int num : nums) {
            numbers[num] = true;
        }

        for (int i = 0; i < numbers.length + 1; i++) {
            if (!numbers[i]) {
                return i;
            }
        }

        return -1;
    }

    public static void main(final String[] args) {
        final var sut = new MissingNumber();
        final var result = sut.missingNumber(new int[] { 9,6,4,2,3,5,7,0,1 });

        System.out.println(result);
    }
}
