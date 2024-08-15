package deprecated.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistinctPairs {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 6, 7, 8, 9, 1};

        System.out.println(distinctPairsN(input, 10));
    }

    // O(n^2) double loop to compare all possible combinations
    public static List<List<Integer>> distinctPairs(int[] input, int k) {
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0 ; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] + input[j] == k) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(input[i]);
                    pair.add(input[j]);
                    results.add(pair);
                }
            }
        }

        return results;
    }

    // O(n) using a cache to store the rest (k - n) as key.
    public static List<List<Integer>> distinctPairsN(int[] input, int k) {
        final var cache = new HashMap<Integer, Integer>();
        final var results = new ArrayList<List<Integer>>();

        for (int i = 0 ; i < input.length; i++) {
            cache.put(k - input[i], input[i]);
            if (cache.containsKey(input[i])) {
                results.add(List.of(cache.get(input[i]), input[i]));
            }
        }

        return results;
    }
}
