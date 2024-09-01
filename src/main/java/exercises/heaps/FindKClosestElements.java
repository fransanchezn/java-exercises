package exercises.heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 658. Find K Closest Elements
public class FindKClosestElements {
    public List<Integer> findClosestElements(final int[] arr, final int k, final int x) {
        final var heap = new PriorityQueue<Integer>((n1, n2) -> {
            final var n1Diff = Math.abs(n1 - x);
            final var n2Diff = Math.abs(n2 - x);

            if (n1Diff == n2Diff) {
                return n2 - n1;
            }

            return n2Diff - n1Diff;
        });

        for (int num: arr) { // O(n)
            heap.add(num); // O(log K)
            if (heap.size() > k) {
                heap.remove(); // O(log k)
            }
        }

        final var result = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) { // O(k)
            result.add(heap.remove()); // O(1)
        }

        Collections.sort(result); // O(n log n)
        return result;
    }

    public static void main(final String[] args) {
        final var sut = new FindKClosestElements();
        final var result = sut.findClosestElements(new int[] { 1,2,3,4 }, 4 ,3);

        System.out.println(result);

    }
}
