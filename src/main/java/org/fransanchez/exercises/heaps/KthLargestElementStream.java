package org.fransanchez.exercises.heaps;

import java.util.PriorityQueue;
import java.util.Queue;

// 703. Kth Largest Element in a Stream
public class KthLargestElementStream {
    private final Queue<Integer> heap;
    private final int maxSize;

    public KthLargestElementStream(int k, int[] nums) {
        maxSize = k;
        heap = new PriorityQueue<>();

        for (int num: nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (heap.size() < maxSize) {
            heap.add(val);
        } else {
            final var min = heap.peek();
            if (min < val) {
                swapMin(val);
            }
        }

        return heap.peek();
    }

    private void swapMin(int val) {
        heap.remove();
        heap.add(val);
    }

    public static void main(final String[] args) {
        final var sut = new KthLargestElementStream(3, new int[] { 4, 5, 8, 2 });
        System.out.println(sut.add(3));
        System.out.println(sut.add(5));
        System.out.println(sut.add(10));
        System.out.println(sut.add(9));
        System.out.println(sut.add(4));
    }
}
