package learning.concurrency.synchronization;

public class Stack {
    private final int[] array;
    private int stackTop;
    private final Object lock;

    public Stack(final int capacity) {
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }

    public boolean isEmpty() {
        return stackTop < 0;
    }

    public boolean isFull() {
        return stackTop >= (array.length - 1);
    }

    public boolean push(final int item) {
        synchronized(lock) {
            if (isFull()) {
                return false;
            }

            try { Thread.sleep(1_000); } catch (final InterruptedException ignored) {}

            array[++stackTop] = item;
            return true;
        }
    }

    public synchronized int pop() {
        synchronized(lock) {
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }
            int obj = array[stackTop];
            array[stackTop] = Integer.MIN_VALUE;

            try { Thread.sleep(1_000); } catch (final InterruptedException ignored) {}

            stackTop--;
            return obj;
        }
    }
}
