package array;

import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        // We save in the map, the value that we need to sum to match the target!
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cache.put(target - nums[i], i);
            if (cache.containsKey(nums[i])) {
                return new int[]{cache.get(nums[i]), i};
            }
        }

        return null;
    }

}
