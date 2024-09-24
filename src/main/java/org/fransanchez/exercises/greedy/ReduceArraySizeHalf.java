package org.fransanchez.exercises.greedy;

import java.util.HashMap;
import java.util.PriorityQueue;

// 1338. Reduce Array Size to The Half
public class ReduceArraySizeHalf {
    public int minSetSize(final int[] arr) {
        final var occurrences = new HashMap<Integer, Integer>();
        final var heap = new PriorityQueue<Integer>((o1, o2) -> occurrences.getOrDefault(o2, 0) - occurrences.getOrDefault(o1, 0));

        for (int num: arr) { // O(n)
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1); // O(1)
        }

        heap.addAll(occurrences.keySet()); // O(n log n)

        var resultSize = arr.length;
        final var target = arr.length / 2;
        var result = 0;
        while(resultSize > target) { // O(n)
            final var current = heap.remove();
            final var occ = occurrences.get(current);
            resultSize -= occ;
            result++;
        }

        return result;
    }
}
