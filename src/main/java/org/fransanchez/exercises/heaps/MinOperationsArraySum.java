package org.fransanchez.exercises.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

// 2208. Minimum Operations to Halve Array Sum
public class MinOperationsArraySum {
    public int halveArray(final int[] nums) {
        final var heap = new PriorityQueue<Double>(Comparator.reverseOrder());

        long originalCount = 0;
        for (int num: nums) {
            originalCount += num;
            heap.add((double) num); // O(log n)
        }
        double halvedCount = originalCount;
        final double target = originalCount/2.0d;

        var operations = 0;
        while (halvedCount > target) { // O(n)
            final var maxNumber = heap.remove(); // O(log n)
            final var maxNumberHalf = maxNumber/2.0d;
            halvedCount = halvedCount - maxNumber + maxNumberHalf;
            if (maxNumberHalf != 0) {
                heap.add(maxNumberHalf); // O(log n)
            }
            operations++;
        }

        return operations;
    }

    public static void main(final String[] args) {
        final var nums = new int[] {1};

        final var sut = new MinOperationsArraySum();
        final var result = sut.halveArray(nums);

        System.out.println(result);
    }
}
