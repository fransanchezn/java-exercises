package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {
    public static void main(String[] args) {
        List<Integer> q1 = new ArrayList<>(Arrays.asList(1,1));
        List<Integer> q2 = new ArrayList<>(Arrays.asList(2,2));
        List<Integer> q3 = new ArrayList<>(Arrays.asList(3,2));
        List<Integer> q4 = new ArrayList<>(Arrays.asList(1,1));
        List<Integer> q5 = new ArrayList<>(Arrays.asList(1,1));
        List<Integer> q6 = new ArrayList<>(Arrays.asList(2,1));
        List<Integer> q7 = new ArrayList<>(Arrays.asList(3,2));

        List<List<Integer>> queries = new ArrayList<>();
        queries.add(q1);
        queries.add(q2);
        queries.add(q3);
        queries.add(q4);
        queries.add(q5);
        queries.add(q6);
        queries.add(q7);

        System.out.println(freqQuery(queries));
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> result = new HashMap<>();
        List<Integer> results = new ArrayList<>();
        for (List<Integer> query : queries) {
            Integer op = query.get(0);
            Integer num = query.get(1);

            switch (op) {
                case 1:
                    int add = result.get(num) != null ? result.get(num) + 1 : 1;
                    result.put(num, add);
                    break;
                case 2:
                    int rm = result.get(num) != null ? result.get(num) - 1 : 0;
                    if (rm == 0) {
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
