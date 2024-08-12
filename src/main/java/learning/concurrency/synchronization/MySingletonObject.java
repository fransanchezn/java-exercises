package learning.concurrency.synchronization;

public class MySingletonObject {
    public static MySingletonObject mySingletonObject = null;

    private MySingletonObject(final String threadName) {
        System.out.println("Creating singleton object: " + threadName);
    }

    public static MySingletonObject getMySingletonObject() {
        synchronized(MySingletonObject.class) {
            mySingletonObject = mySingletonObject == null
                    ? new MySingletonObject(Thread.currentThread().getName())
                    : mySingletonObject;
        }

        return mySingletonObject;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10_000; i++) {
            new Thread(() -> {
                var singleton = MySingletonObject.getMySingletonObject();
                System.out.println("MySingleton: " + singleton);
            }).start();
        }
    }
}
