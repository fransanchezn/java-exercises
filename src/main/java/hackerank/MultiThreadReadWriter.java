package hackerank;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class MultiThreadReadWriter {

    SortedMap<Integer, List<String>> map = new TreeMap<>();
    Lock lock = new ReentrantLock();

    public void write(String message, int priority) {
        List<String> messages = map.getOrDefault(priority, new LinkedList<>());
        messages.add(message);
        map.put(priority, messages);
    }

    public String read(long timeout, TimeUnit unit) {
        try {
            long time = System.currentTimeMillis();
            System.out.println("read: " + Thread.currentThread().getId());
            lock.tryLock(timeout, unit);
            System.out.println("read lock: " + Thread.currentThread().getId());

            Thread.sleep(2000l);
            if (map.isEmpty()) {
                throw new RuntimeException("Empty map");
            }

            int key = map.firstKey();
            List<String> messages = map.get(key);
            String message = messages.remove(0);

            if (messages.isEmpty()) {
                map.remove(key);
            } else {
                map.put(key, messages);
            }

            return message;
        } catch (InterruptedException e) {
            throw new RuntimeException("timeout");
        } finally {
            System.out.println("read unLock: " + Thread.currentThread().getId());
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultiThreadReadWriter mtrw = new MultiThreadReadWriter();
        mtrw.write("you", 3);
        mtrw.write("how", 2);
        mtrw.write("Hello", 1);
        mtrw.write("are", 2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 4).forEach(i -> executor.submit(() -> {
            try {
                System.out.println("Thread" + Thread.currentThread().getId() + " - " +mtrw.read(1l, TimeUnit.SECONDS));
            } catch (Exception e) {
                System.out.println("Thread" + Thread.currentThread().getId() + " error.");
            }
        }));
    }
}
