package deprecated.sorting;

import java.util.ArrayList;
import java.util.List;

public class DefaultSort {
    public static void main(String[] args) {
        final var input = new ArrayList<>(List.of(2, 65, 34, 2, 1, 7, 8));
        insertionSort(input);

        System.out.println(input);
    }

    public static void insertionSort(final List<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            final var currentValue = input.get(i);
            for (int j = i; j >= 0; j--) {
                final var compareValue = input.get(j);
                if (currentValue < compareValue) {
                    input.remove(i);
                    input.add(j, currentValue);
                    i--;
                }
            }
        }
    }
}
