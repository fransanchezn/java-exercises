package array;

public class RotateArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5 ,6 ,7};

        rotateArray(arr, 3);

        for(int i : arr) {
            System.out.print(i + ", ");
        }

    }

    // O(n^2) - time complexity
    // O(1) - space complexity
    public static void rotateArray(final int[] nums, final int k) {
        final var rot = k % nums.length;

        int temp, previous;
        for (int i = 0; i < rot; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    public static void rotateArray2(final int[] nums, final int k) {
        final var rot = k % nums.length;

        for (int i = 0; i < nums.length; i++) {

        }
    }
}
