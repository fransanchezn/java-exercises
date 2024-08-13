package exercises.old.hashmap;

import java.util.HashMap;

public class ContainsNearbyDuplicate {

    public static void main(final String[] args) {
        //final var nums = new int[]{ 1,2,3,1,2,3 };
        final var nums = new int[]{ 1,2,3,1 };
        System.out.println(containsNearbyDuplicate(nums, 3));
    }

    // O(n)
    public static boolean containsNearbyDuplicate(final int[] nums, final int k) {
        final var numberPosition = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            final var closestPrevious = numberPosition.get(nums[i]);
            if (closestPrevious != null && i - closestPrevious <= k) {
                return true;
            }
            numberPosition.put(nums[i], i);
        }

        return false;
    }
}
