package exercises.recursion;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Fibonacci {

    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233
    public static long fibonacciRec(final int ite) { // O(2^n)
        if (ite < 2) {
            return ite;
        }

        return fibonacciRec(ite - 1) + fibonacciRec(ite - 2);
    }

    public static long fibonacciIte(final int ite) { // O(n)
        final var prev = new int[2]; // O(2)
        for (int i = 0; i < ite; i++) { // O(n)
            if (i < 2) {
                prev[i] = i;
            } else {
                final var next = prev[0] + prev[1];
                prev[0] = prev[1];
                prev[1] = next;
            }
        }

        return prev[0] + prev[1];
    }

    private static Map<Integer, Long> cache = new HashMap<>();
    public static long fibonacciDynProg(final int ite) { // O(n)
        if (cache.containsKey(ite)) {
            return cache.get(ite);
        }

        if (ite < 2) {
            return ite;
        }
        cache.put(ite, fibonacciDynProg(ite - 1) + fibonacciDynProg(ite - 2));
        return cache.get(ite);
    }

    public static void main(String[] args) {
        //System.out.println(fibonacciRec(43));
        //System.out.println(fibonacciIte(43));
        //final var start1 = Instant.now();
        //System.out.println(fibonacciRec(50));
        //System.out.println("time: " + (Duration.between(start1, Instant.now()).toMillis()));
        
        final var start2 = Instant.now();
        System.out.println(fibonacciDynProg(50));
        System.out.println("time: " + (Duration.between(start2, Instant.now()).toMillis()));
    }
}
