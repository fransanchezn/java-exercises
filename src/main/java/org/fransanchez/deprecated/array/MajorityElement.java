package org.fransanchez.deprecated.array;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MajorityElement {
    public static void main(String[] args) {
        var nums = new int[] { 2,2,1,1,1,2,2 };
        var result = majorityElement2(nums);

        System.out.println(result);
    }

    // HashMap Solution
    private static int majorityElement1(int[] nums) {
        var size = nums.length;
        var majority = size / 2;

        final var occurrences =
                Arrays.stream(nums)
                        .boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var majorityElement = occurrences.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > majority)
                .findFirst()
                .map(Map.Entry::getKey);

        return majorityElement.get();

    }

    // Sorted array Solution
    private static int majorityElement2(int[] nums) {
        var size = nums.length;
        var majority = size / 2;

        Arrays.sort(nums);

        return nums[majority];
    }
}
