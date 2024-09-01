package exercises.heaps;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// 347. Top K Frequent Elements
public class TopKFrequentElements {
    public int[] topKFrequent(final int[] nums, int k) {
        final var frequency = new HashMap<Integer, Integer>();

        for (var num : nums) { // O(n)
            final var freq = frequency.getOrDefault(num, 0);  // O(1)
            frequency.put(num, freq + 1); // O(1)
        }

        // (n1, n2) -> counts.get(n1) - counts.get(n2)
        final var heap = new PriorityQueue<Integer>(Comparator.comparingInt(frequency::get));
        for (int num : frequency.keySet()) {
            heap.add(num); // O(log k)
            if (heap.size() > k) {
                heap.remove(); // O(log k)
            }
        }

        final int[] result = new int[k];
        for (int i = 0; i < k; i++) {  // O(k)
            result[i] = heap.remove();  // O(log k)
        }

        return result;
    }
}
