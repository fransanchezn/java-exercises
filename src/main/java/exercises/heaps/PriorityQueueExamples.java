package exercises.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExamples {
    public static void main(String[] args) {
        System.out.println("############ MIN HEAP");
        final var minHeap = new PriorityQueue<Integer>();
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);

        System.out.println(minHeap.peek()); // Peek 1
        System.out.println(minHeap.remove()); // Remove 1
        System.out.println(minHeap.size());

        System.out.println("############ MAX HEAP");
        final var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);

        System.out.println(maxHeap.peek()); // Peek 3
        System.out.println(maxHeap.remove()); // Remove 3
        System.out.println(maxHeap.size());

    }
}
