package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        // Input: nums = [-1,0,1,2,-1,-4]
        // Output: [[-1,-1,2],[-1,0,1]]

        final var nums = new int[]{-1,0,1,2,-1,-4};
        //final var result = threeSum(nums);
        final var result = threeSumIte(nums);
        System.out.println(result);
    }

    /**
     * Returns all the list of 3 int which sum is 0.
     */
    public static List<List<Integer>> threeSum(final int[] nums) {
        final var result = new ArrayList<List<Integer>>();
        threeSumRec(nums, 0, 0, new ArrayList<>(), result);

        return result;
    }

    private static void threeSumRec(final int[] numbers,
                                    final int index, final int target,
                                    final List<Integer> current, final List<List<Integer>> result) {

        // Base case 0
        if (current.size() == 3 && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base case 1
        if (current.size() >= 3 && target != 0) {
            return;
        }

        for (int i = index ; i < numbers.length; i++) {
            current.add(numbers[i]);
            threeSumRec(numbers, i + 1, target - numbers[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    public static List<List<Integer>> threeSumIte(final int[] nums) {
        final var results = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            final var cache = new HashMap<Integer, List<Integer>>();
            for (int j = i + 1; j < nums.length; j++) {
                if (cache.containsKey(nums[j]) && cache.get(nums[j]).size() == 2) {
                    final var array = new ArrayList<>(cache.get(nums[j]));
                    array.add(nums[j]);
                    results.add(array);
                } else {
                    cache.put((0 - (nums[i] + nums[j])), List.of(nums[i], nums[j]));
                }
            }
        }

        return results;
    }
}
