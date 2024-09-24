package org.fransanchez.exercises.stacksandqueues.queue;

/*

MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3

 */
public class MovingAverage {
    private final int[] queue;
    private final int size;
    private int tail;

    private int windowSum;
    private int count;

    public MovingAverage(final int size) {
        queue = new int[size];
        this.size = size;
        this.windowSum = 0;
        this.count = 0;
        this. tail = 0;
    }

    public double next(final int val) {
        if (count < size) {
            count++;
        }

        // De-Queueing from head
        int head = (tail + 1) % size;
        windowSum = windowSum - queue[head] + val; // dequeue from head

        // Moving tail to head
        tail = head;
        queue[tail] = val;  // Updating tail value
        return windowSum * 1.0 / count;
    }

    public static void main(String[] args) {
        final var movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // return 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // return 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // return 6.0 = (10 + 3 + 5) / 3
    }
}
