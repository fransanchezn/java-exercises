package org.fransanchez.exercises.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private final Map<Integer, Integer> memoization;

    public Fibonacci() {
        memoization = new HashMap<>();
        memoization.put(0, 0);
        memoization.put(1, 1);
    }

    public int fibonacci(final int n) {
        if (memoization.containsKey(n)) {
            return memoization.get(n);
        }

        final var result = fibonacci(n - 1) + fibonacci(n - 2);
        memoization.put(n, result);

        return result;
    }

    public static void main(String[] args) {
        final var sut = new Fibonacci();
        final var result = sut.fibonacci(10);

        System.out.println(result);
    }
}
