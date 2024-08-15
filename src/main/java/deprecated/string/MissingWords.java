package deprecated.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MissingWords {
    public static void main(String[] args) {
        System.out.println(missingWords3("Python is an easy to learn powerful programming language It has efficient high-level data structures and a simple but effective approach to objectoriented programming Python elegant syntax and dynamic typing",
                "programming Python elegant syntax and dynamic typing"));
    }

    // LinkedList are faster to remove head objects than ArrayLists
    public static String missingWords3(final String s, final String t) {
        final var textSplit = new ArrayList<>(Arrays.asList(s.split(" ")));
        final var wordSplit = new LinkedList<>(Arrays.asList(t.split(" ")));

        for (int i = 0; i < textSplit.size(); i++) {
            if (wordSplit.isEmpty()) {
                return String.join(" ", textSplit);
            }

            if (textSplit.get(i).equals(wordSplit.getFirst())) {
                wordSplit.removeFirst();
                textSplit.remove(i);
                i--;
            }
        }

        return String.join(" ", textSplit);
    }

    public static List<String> missingWords(final String s, final String t) {
        final var words = new ArrayList<>(Arrays.asList(s.split(" ")));
        final var rmWords = new ArrayList<>(Arrays.asList(t.split(" ")));

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

    public static List<String> missingWords2(final String s, final String t) {
        List<String> words = new ArrayList<>(List.of(s.split(" ")));
        final var rmWords = t.split(" ");
        final var result = new ArrayList<String>();

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
