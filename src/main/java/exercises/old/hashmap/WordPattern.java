package exercises.old.hashmap;

import java.util.HashMap;

public class WordPattern {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog dog dog dog"));
    }

    // Input: pattern = "abba", s = "dog cat cat dog"
    // Output: true

    // Input: pattern = "abba", s = "dog dog dog dog"
    // Output: false
    public static boolean wordPattern(final String pattern, final String s) {
        var textSplit = s.split(" ");
        if (pattern.length() != textSplit.length) {
            return false;
        }

        var letterDictionary = new HashMap<Character, String>();
        var wordDictionary = new HashMap<String, Character>();
        for (int i = 0; i < pattern.length(); i++) {
            var letter = pattern.charAt(i);
            var word = textSplit[i];

            letterDictionary.putIfAbsent(letter, word);
            wordDictionary.putIfAbsent(word, letter);

            if (!word.equals(letterDictionary.get(letter)) || letter != wordDictionary.get(word)) {
                return false;
            }
        }

        return true;
    }
}
