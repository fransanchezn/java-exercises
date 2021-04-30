package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingWords {
    public static void main(String[] args) {
        System.out.println(missingWords("Python is an easy to learn powerful programming language It has efficient high-level data structures and a simple but effective approach to objectoriented programming Python elegant syntax and dynamic typing",
                "programming Python elegant syntax and dynamic typing"));
    }

    static List<String> missingWords(String s, String t) {
        List<String> words = new ArrayList<>(Arrays.asList(s.split(" ")));
        List<String> rmWords = new ArrayList<>(Arrays.asList(t.split(" ")));

        for (int i = 0; i < words.size(); i++) { // O(n)
            if (rmWords.isEmpty()) {
                return words;
            }

            if (words.get(i).equals(rmWords.get(0))) {
                words.remove(i);
                i--;
                rmWords.remove(0);
            }
        }
        return words;
    }

    static List<String> missingWords2(String s, String t) {
        List<String> words = new ArrayList<>(Arrays.asList(s.split(" ")));
        String[] rmWords = t.split(" ");
        List<String> result = new ArrayList<>();

        for (int i = 0 ; i < rmWords.length; i++) {  // O(n)
            int lastIndex = words.indexOf(rmWords[i]); // O(n)
            if (lastIndex < 0) {
                continue;
            }

            result.addAll(new ArrayList<>(words.subList(0, lastIndex)));
            words = words.subList(lastIndex + 1, words.size());
        }

        return result;
    }
}
