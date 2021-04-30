package string;

import java.util.ArrayList;
import java.util.List;

public class Partitions {

    public static void main(String[] args) {
        String input = "ababcbacadefegdehijhklij";
        System.out.println(partitions(input));
    }

    static List<Integer> partitions(String input) { // O(n^n) - Not great
        List<Integer> partitions = new ArrayList<>();
        int end = 0;
        int start = 0;
        for (int i = 0; i < input.length(); i++) { // O(n)
            int lastIndex = input.lastIndexOf(input.charAt(i)); // O(n) - Should keep this store so we don't perform this action every time.
            end = end < lastIndex ? lastIndex : end;

            if (i == end) {
                partitions.add(end - start + 1);
                start = end + 1;
            }
        }

        return partitions;
    }
}
