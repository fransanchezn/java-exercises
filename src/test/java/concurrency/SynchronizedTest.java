package concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SynchronizedTest {

    private Synchronized sync = new Synchronized();

    @Test
    public void count__singleThread__rightCount() {
        for (int i = 0; i < 1000; i++) {
            sync.count();
        }

        assertEquals(1000, sync.getCounter());
    }

    @Test
    public void count__multiThread__wrongCount() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000).forEach((c) -> service.submit(() -> sync.count()));
        service.shutdown();

        assertEquals(1000, sync.getCounter());
    }

    @Test
    public void syncCount__multiThread__rightCount() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach((c) -> service.submit(() -> sync.syncCount()));
        service.shutdown();

        assertEquals(1000, sync.getCounter());
    }

    @Test
    public void syncBlockCount__multiThread__rightCount() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach((c) -> service.submit(() -> sync.syncBlockCount()));
        service.shutdown();

        assertEquals(1000, sync.getCounter());
    }

    @Test
    public void staticSyncBlockCount__multiThread__rightCount() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach((c) -> service.submit(() -> Synchronized.staticSyncBlockCount()));
        service.shutdown();

        assertEquals(1000, Synchronized.getStaticCounter());
    }

}