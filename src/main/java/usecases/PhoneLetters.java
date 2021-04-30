package usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class PhoneLetters {

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList("23", "221852020", "57657645703")); // "23", "620802327", "07450608433"
        for (String num : input) {
            List<String> results = new ArrayList<>();
            List<String> numbers = new ArrayList<>(Arrays.asList(num.split("")));
            comb(numbers, 0, new ArrayList<String>(), results);
            System.out.println(results);
        }

        System.out.println("--------------");

        for (String num: input) {
            List<String> numbers = new ArrayList<>(Arrays.asList(num.split("")));
            System.out.println(comb2(numbers));
        }
    }

    // Recursive solution
    public static void comb(List<String> numbers, int index, List<String> current, List<String> results) {
        String[] k9 = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        // Base case
        if (index >= numbers.size()) {
            results.add(current.stream().collect(Collectors.joining("")));
            return;
        }

        String[] letters = k9[Integer.valueOf(numbers.get(index))].split("");
        for (int i = 0; i < letters.length; i++) {
            current.add(letters[i]);
            comb(numbers, index + 1, current, results);
            current.remove(current.size() - 1);
        }
    }

    // Iterative solution
    public static List<String> comb2(List<String> numbers) {
        String[] k9 = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> results = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        q.add("");

        while(!q.isEmpty()) {
            String comb = q.remove();

            if (comb.length() == numbers.size()) {
                results.add(comb);
            } else {
                String letters = k9[Integer.valueOf(numbers.get(comb.length()))];
                for (int i = 0; i < letters.length(); i++) {
                    q.add(comb + letters.charAt(i));
                }
            }
        }

        return results;
    }
}
