package deprecated.sorting;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {
        final var a = new int[]{1, 3, 4, 10};
        final var b = new int[]{1, 5, 7, 9, 12};

        final var result = MergeSortedArray.mergeSorted(a, b);
        Arrays.stream(result).forEach(System.out::println);
    }

    public static int[] mergeSorted(final int[] a, final int[] b) {
        final var result = new int[a.length + b.length];
        var indexA = 0;
        var indexB = 0;

        for (int i = 0; i < result.length - 1; i++) {

            if (indexA > a.length - 1) {
                result[i] = b[indexB];
                continue;
            }

            if (indexB > b.length - 1) {
                result[i] = b[indexB];
                continue;
            }

            final var aNum = a[indexA];
            final var bNum = b[indexB];

            if (aNum > bNum) {
                result[i] = bNum;
                indexB++;
            } else if (aNum < bNum) {
                result[i] = aNum;
                indexA++;
            } else {
                result[i] = aNum;
                indexA++;
                indexB++;
            }
        }
        return  result;
    }
}
