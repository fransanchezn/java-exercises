package exercises.concurrency;

import java.util.concurrent.Semaphore;

class PrintAlternately {
    private final Semaphore fooLock = new Semaphore(1);
    private final Semaphore barLock = new Semaphore(0);

    private final int n;
    public PrintAlternately(final int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooLock.acquire();
            printFoo.run();
            barLock.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barLock.acquire();
            printBar.run();
            fooLock.release();
        }
    }

    public static void main(String[] args) {
        final var PrintAlternately = new PrintAlternately(10);
        final var t1 = new Thread(() -> {
            try {
                PrintAlternately.foo(() -> System.out.print("foo"));
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final var t2 = new Thread(() -> {
            try {
                PrintAlternately.bar(() -> System.out.print("bar"));
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t2.start();
        t1.start();
    }
}