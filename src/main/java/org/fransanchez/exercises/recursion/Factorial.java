package org.fransanchez.exercises.recursion;

public class Factorial {

    public long factorial(final int n) {
        // 0! = 1
        // 1! = 1
        if (n <= 1) {
            return 1L;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Factorial().factorial(5));
    }
}
