package exercises.hashmap;

import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(38));
    }

    public static boolean isHappy(final int n) {
        final var occurrences = new HashSet<Integer>();

        var number = n;
        while (number != 1 && !occurrences.contains(number)) {
            occurrences.add(number);
            number = squareSum(number);
        }

        return number == 1;
    }

    private static int squareSum(final int number) {
        var remaining = number;
        var squareSum = 0;
        while (remaining > 0) {
            var rest = remaining % 10;
            squareSum += rest * rest;
            remaining /= 10;
        }

        return squareSum;
    }
}
