package org.fransanchez.exercises.arrayandstrings;

// 26. Remove Duplicates from Sorted Array
public class RemoveDuplicatesSortedArray {
    public static int removeDuplicates(final int[] numbers) {
        var lastNonDuplicated = 0;
        for (int i = 1; i < numbers.length; i++) {
            final var current = numbers[i];
            final var nonDuplicated = numbers[lastNonDuplicated];
            if (current != nonDuplicated) {
                lastNonDuplicated++;
                numbers[lastNonDuplicated] = current;
            }
        }

        return lastNonDuplicated + 1;
    }

    public static void main(String[] args) {
        final var result = RemoveDuplicatesSortedArray.removeDuplicates(new int[] { 1, 1, 2});
        System.out.println(result);
    }
}

// 1,1,2