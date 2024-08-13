package exercises.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private final int n;

    private final Semaphore zeroLock = new Semaphore(1);
    private final Semaphore evenLock = new Semaphore(0);
    private final Semaphore oddLock = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(final IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zeroLock.acquire();

            printNumber.accept(0);
            if (i % 2 == 0) {
                evenLock.release();
            } else {
                oddLock.release();
            }
        }
    }

    public void even(final IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenLock.acquire();
            //System.out.println("even: "+ nextNumber);
            printNumber.accept(i);
            zeroLock.release();
        }
    }

    public void odd(final IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= this.n; i += 2) {
            oddLock.acquire();
            //System.out.println("odd: "+ nextNumber);
            printNumber.accept(i);
            zeroLock.release();
        }
    }

    public static void main(String[] args) {
        final var zeo = new ZeroEvenOdd(2);

        new Thread(() -> {
            try {
                zeo.zero(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                zeo.even(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                zeo.odd(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
