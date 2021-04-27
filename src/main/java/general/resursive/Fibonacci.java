package general.resursive;

public class Fibonacci {

    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233
    public static int fibonacciRec(final int ite) { // O(2^n)
        if (ite < 2) {
            return ite;
        }

        return fibonacciRec(ite - 1) + fibonacciRec(ite - 2);
    }

    public static int fibonacciIte(final int ite) { // O(n)
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

    public static void main(String[] args) {
        System.out.println(fibonacciRec(43));
        System.out.println(fibonacciIte(43));
    }
}
