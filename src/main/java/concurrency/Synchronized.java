package concurrency;

public class Synchronized {

    private int counter = 0;
    private static int staticCounter = 0;

    public void count() {
        System.out.println("Thread: " + Thread.currentThread().getId() + " started!");
        setCounter(getCounter() + 1);
        System.out.println("Thread: " + Thread.currentThread().getId() + " ended!");
    }

    public synchronized void syncCount() {
        count();
    }

    public void syncBlockCount() {
        synchronized(this) {
            count();
        }
    }

    public static void staticSyncBlockCount() {
        synchronized (Synchronized.class) {
            staticCounter +=1;
        }
    }

    public static int getStaticCounter() {
        return staticCounter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
