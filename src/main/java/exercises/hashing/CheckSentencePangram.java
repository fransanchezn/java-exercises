package exercises.hashing;

import java.util.HashSet;

// 1832. Check if the Sentence Is Pangram
public class CheckSentencePangram {
    public boolean checkIfPangram(final String sentence) {
        final var letters = new HashSet<Character>();
        for (int i = 0; i < sentence.length(); i++) {
            letters.add(sentence.charAt(i));
        }

        return letters.size() == 26;
    }

    public static void main(String[] args) {
        final var sut = new CheckSentencePangram();
        final var result = sut.checkIfPangram("thequickbrownfoxjumpsoverthelazydog");

        System.out.println(result);
    }
}
