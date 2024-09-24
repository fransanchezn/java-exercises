package org.fransanchez.deprecated.recursion;

public class Factorial {

    public static int factorialRec(final int number) {
        if (number == 1) {
            return number;
        }

        return number * factorialRec(number - 1);
    }

    public static int factorialIte(final int number) {
        var result = 1;
        for (int i = number; i > 0; i--) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(factorialRec(5));
        System.out.println(factorialIte(5));
    }
}
