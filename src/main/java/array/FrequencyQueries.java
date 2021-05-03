package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {
    // https://www.hackerrank.com/challenges/frequency-queries/problem
    public static void main(String[] args) {
        final var q1 = List.of(1, 1);
        final var q2 = List.of(2, 2);
        final var q3 = List.of(3, 2);
        final var q4 = List.of(1, 1);
        final var q5 = List.of(1, 1);
        final var q6 = List.of(2, 1);
        final var q7 = List.of(3, 2);

        final var queries = new ArrayList<>(List.of(q1, q2, q3, q4, q5, q6, q7));
        System.out.println(freqQuery(queries));
    }

    public static List<Integer> freqQuery(final List<List<Integer>> queries) {
        final var result = new HashMap<Integer, Integer>();
        final var results = new ArrayList<Integer>();

        for (List<Integer> query : queries) {
            Integer op = query.get(0);
            Integer num = query.get(1);

            switch (op) {
                case 1:
                    final var add = result.getOrDefault(num, 0) + 1;
                    result.put(num, add);
                    break;
                case 2:
                    final var rm = result.getOrDefault(num, 0) - 1;
                    if (rm <= 0) {
                        result.remove(num);
                    } else {
                        result.put(num, rm);
                    }
                    break;
                case 3:
                    if (result.containsValue(num)) {
                        results.add(1);
                    } else {
                        results.add(0);
                    }
                    break;
                default:
                    throw new RuntimeException("Operation not supported");
            }
        }

        return results;
    }
}
