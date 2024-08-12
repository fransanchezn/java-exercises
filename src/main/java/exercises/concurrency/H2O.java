package exercises.concurrency;

import java.util.concurrent.Semaphore;

class H2O {
    private final Semaphore oxygen;
    private final Semaphore hydrogen;

    public H2O() {
        oxygen = new Semaphore(0, true);
        hydrogen = new Semaphore(2, true);
    }

    // HHO / HOH
    public void hydrogen(final Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire(1);
        releaseHydrogen.run();
        oxygen.release(1); // Release 1/2
    }

    // HHO / HOH
    public void oxygen(final Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire(2); // Two HH required
        releaseOxygen.run();
        hydrogen.release(2);
    }
}
