package exercises.old.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    public static void main(String[] args) {
        // Input: strs = ["eat","tea","tan","ate","nat","bat"]
        // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
    }

    public static List<List<String>> groupAnagrams(final String[] strs) {
        if (strs.length < 1) {
            return Collections.emptyList();
        }

        final var cache = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            final var word = strs[i];
            final var chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            cache.compute(key, (k,v) -> {
                final var value = v == null ? new ArrayList<String>() : v;
                value.add(word);
                return value;
            });
        }

        return new ArrayList<>(cache.values());
    }
}
