package hackerrank;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadReadWriter {

    SortedMap<Integer, List<String>> map = new TreeMap<>();
    Lock lock = new ReentrantLock();

    public void write(String message, int priority) {
        List<String> messages = map.getOrDefault(priority, new LinkedList<>());
        messages.add(message);
        map.put(priority, messages);
        System.out.println(map);
    }

    public String read(long timeout, TimeUnit unit) {
        try {
            lock.tryLock(timeout, unit);

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
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultiThreadReadWriter mtrw = new MultiThreadReadWriter();
        mtrw.write("you", 3);
        mtrw.write("how", 2);
        mtrw.write("Hello", 1);
        mtrw.write("are", 2);

        System.out.println(mtrw.read(0l, TimeUnit.SECONDS));
        System.out.println(mtrw.read(0l, TimeUnit.SECONDS));
        System.out.println(mtrw.read(0l, TimeUnit.SECONDS));
        System.out.println(mtrw.read(0l, TimeUnit.SECONDS));
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        IntStream.range(0, 4).forEach(i -> executor.submit(() -> {
//            try {
//                System.out.println("Thread" + Thread.currentThread().getId() + " - " +mtrw.read(1l, TimeUnit.SECONDS));
//            } catch (Exception e) {
//                System.out.println("Thread" + Thread.currentThread().getId() + " error.");
//            }
//        }));
    }
}
