package org.fransanchez.deprecated.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        final var numbers = new ArrayList<>(Arrays.asList(1, 2, 5, 6));
        int target = 7;
        Collections.sort(numbers);

        final var result = new ArrayList<List<Integer>>();
        comSum(numbers, 0, target, new ArrayList<>(), result);

        System.out.println(result);
    }

    /**
     * Given an ORDERED collection of numbers, return all the possible combinations
     * that sum is equals to the provided target
     */
    static void comSum(List<Integer> numbers, int index, int target, List<Integer> current, List<List<Integer>> result) {

        // Base case 1
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base case 1
        if (target < 0) {
            return;
        }

        for (int i = index; i < numbers.size(); i++) {
            // Not with itself
            // Not duplicated
            if (i == index || !numbers.get(i).equals(numbers.get(i - 1))) {
                current.add(numbers.get(i));
                comSum(numbers, i + 1, target - numbers.get(i), current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
