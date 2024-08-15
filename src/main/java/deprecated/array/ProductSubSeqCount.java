package deprecated.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSubSeqCount {
    public static void main(String[] args) {
        final var arr = List.of(5, 10, 11, 9, 5);
        int k = 5;

        System.out.println(kSub(arr, k));
    }

    // O(n^2) - time complexity
    public static List<List<Integer>> kSub(final List<Integer> arr, final int k) {
        final var result = new ArrayList<List<Integer>>();

        for (int i = 0; i < arr.size(); i++) {
            int count = arr.get(i);

            final var ite = new ArrayList<Integer>();
            ite.add(arr.get(i));

            if (count % k == 0) {
                result.addAll(Collections.singletonList(ite));
            }

            for (int j = i + 1; j < arr.size(); j++) {
                count += arr.get(j);
                ite.add(arr.get(j));

                if (count % k == 0) {
                    result.addAll(Collections.singletonList(ite));
                }
            }
        }

        return result;
    }
}
