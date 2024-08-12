package exercises.array;

public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        final var r = increasingTriplet(new int[]{20,100,10,21,5,13,14});
        System.out.println(r);
    }

    // [2,1,5,0,4,6]
    // (3, 4, 5) - nums[3] == 0 < nums[4] == 4 < nums[5] == 6
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        var one = Integer.MAX_VALUE;
        var two = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= one) {
                one = nums[i];
            } else if (nums[i] <= two) {
                two = nums[i];
            } else {
                return true;
            }
        }

        return false;
    }
}
