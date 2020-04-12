package hackerank;

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

        for (int i = 0; i < words.size(); i++) {
            if (rmWords.size() < 1) {
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
}
