package org.fransanchez.exercises.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

// 1962. Remove Stones to Minimize the Total
public class RemoveStonesMinimizeTotal {
    public int minStoneSum(final int[] piles, final int k) {
        final var heap = new PriorityQueue<Integer>(Comparator.reverseOrder());

        long totalcount = 0;
        for (int pile : piles) { // O(n)
            heap.add(pile);
            totalcount += pile;
        }

        for (int i = 0; i < k; i++) { // O(k)
            final var pile = heap.remove(); // O(log n)
            final var remaining = removeStones(pile);

            totalcount = totalcount - pile + remaining;
            if (remaining != 0) {
                heap.add(remaining); // O(log n)
            }
        }

        return (int) totalcount;
    }

    private int removeStones(final int stones) {
        return (int) Math.ceil((double) stones / 2);
    }

    public static void main(String[] args) {
        final var input = new int[] {5,4,9};

        //5,4,9
        //5,4,4
        //2,4,4
        final var sut = new RemoveStonesMinimizeTotal();
        final var result = sut.minStoneSum(input, 2);

        System.out.println(result);
    }
}
