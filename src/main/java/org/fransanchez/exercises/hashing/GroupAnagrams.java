package org.fransanchez.exercises.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 49. Group Anagrams
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(final String[] strs) {
        final var hash = new HashMap<String, List<String>>();
        for (var word : strs) {
            final var countKey = calculateCount(word);
            final var group = hash.getOrDefault(countKey, new ArrayList<>());
            group.add(word);
            hash.put(countKey, group);
        }

        return hash.values().stream().toList();
    }

    private String calculateCount(final String word) {
        final var dictionary = new int[26];
        for (var letter : word.toCharArray()) {
            dictionary[letter - 'a']++;
        }

        return Arrays.toString(dictionary);
    }

    public static void main(String[] args) {
        final var sut = new GroupAnagrams();
        final var result = sut.groupAnagrams(new String[] { "eat","tea","tan","ate","nat","bat" });
        System.out.println(result);
    }
}
