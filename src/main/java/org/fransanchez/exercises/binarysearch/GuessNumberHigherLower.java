package org.fransanchez.exercises.binarysearch;

public class GuessNumberHigherLower {
    private final int pickedNumber;

    public GuessNumberHigherLower(final int pickedNumber) {
        this.pickedNumber = pickedNumber;
    }

    public int guessNumber(final int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            final int mid = (int) (Long.sum(low, high) / 2);

            if (guess(mid) > 0) {
                low = mid + 1;
            } else if (guess(mid) < 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    // Given function
    private int guess(final int num) {
        return Integer.compare(pickedNumber, num);
    }

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherLower(1702766719).guessNumber(2126753390));
    }
}
