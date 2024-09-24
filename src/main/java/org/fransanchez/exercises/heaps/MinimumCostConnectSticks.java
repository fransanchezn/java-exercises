package org.fransanchez.exercises.heaps;

import java.util.PriorityQueue;

// Premium
public class MinimumCostConnectSticks {
    public int connectSticks(final int[] sticks) {
        final var heap = new PriorityQueue<Integer>();

        for (int stick: sticks) { // O(n)
            heap.add(stick);
        }

        var totalCost = 0;
        while (heap.size() > 1) {  // O(n)
            final var stickOne = heap.remove();  // O(log n)
            final var stickTwo = heap.remove(); // O(log n)
            final var mergeCost = stickOne + stickTwo;

            totalCost += mergeCost;

            heap.add(mergeCost); // O(log n)
        }

        return totalCost;
    }

    public static void main(String[] args) {
        final var sut = new MinimumCostConnectSticks();
        final var result = sut.connectSticks(new int[] {1,8,3,5});

        System.out.println(result);
    }
}
