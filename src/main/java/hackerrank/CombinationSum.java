package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 5, 6));
        int target = 7;
        Collections.sort(numbers);

        List<List<Integer>> result = new ArrayList<>();
        comSum(numbers, 0, target, new ArrayList<Integer>(), result);

        System.out.println(result);
    }

    static void comSum(List<Integer> numbers, int index, int target, List<Integer> current, List<List<Integer>> result) {

        // Base case 1
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base case 1
        if (target < 0) {
            return;
        }

        for (int i = index; i < numbers.size(); i++) {
            if (i == index || numbers.get(i) != numbers.get(i - 1)) {
                current.add(numbers.get(i));
                comSum(numbers, i + 1, target - numbers.get(i), current, result);
                current.remove(current.size() - 1);
            }
        }
    }
}
