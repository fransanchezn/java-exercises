package learning.concurrency.threads;

// Not preferred way to implement a Thread use Runnable instead!
// Gives you more flexibility since it is an interface
public class MyThread extends Thread {

    @Override
    public void run() {
        final var start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 5_000L) {
            System.out.println(Thread.currentThread().getName() + " is working");
            threadSleep(1_000);
        }
    }

    private void threadSleep(final long delay) {
      try {
          Thread.sleep(delay);
      } catch (InterruptedException e) {
          throw new RuntimeException(e);
      }
    }
}
