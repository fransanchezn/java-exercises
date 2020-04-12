package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductSubSeqCount {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[]{5, 10, 11, 9, 5}));
        int k = 5;

        System.out.println(kSub(arr, k));
    }

    static List<List<Integer>> kSub(List<Integer> arr, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            int count = arr.get(i);
            List<Integer> ite = new ArrayList<>();
            ite.add(arr.get(i));

            if (count % k == 0) {
                result.addAll(Collections.singleton(new ArrayList<>(ite)));
            }

            for (int j = i + 1; j < arr.size(); j++) {
                count += arr.get(j);
                ite.add(arr.get(j));

                if (count % k == 0) {
                    result.addAll(Collections.singleton(new ArrayList<>(ite)));
                }
            }
        }

        return result;
    }
}
