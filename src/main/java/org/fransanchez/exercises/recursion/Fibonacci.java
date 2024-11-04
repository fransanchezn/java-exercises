package org.fransanchez.exercises.recursion;

public class Fibonacci {
    public int fibonacci(final int n) {
        // F(0) = 0
        // F(1) = 1
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibonacci(5));
    }
}
