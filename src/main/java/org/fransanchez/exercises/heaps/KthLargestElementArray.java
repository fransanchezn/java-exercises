package org.fransanchez.exercises.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array
public class KthLargestElementArray {
    private final Queue<Integer> heap = new PriorityQueue<>();

    public int findKthLargest(final int[] nums, final int k) {
        Arrays.stream(nums).forEach(i -> add(i, k));
        return heap.peek();
    }

    private int add(final int val, final int k) {
        heap.add(val);
        if (heap.size() > k) {
            heap.remove();
        }

        return heap.peek();
    }

    public static void main(String[] args) {
        final var sut = new KthLargestElementArray();
        sut.findKthLargest(new int[] {3,2,1,5,6,4}, 2);
    }
}
