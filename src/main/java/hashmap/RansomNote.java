package hashmap;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(final String[] args) {
        System.out.println(canConstruct("aaa", "aaab"));
    }

    private static boolean canConstruct(
            final String ransomNote,
            final String magazine) {
        final var magazineMap = stringToMap(magazine);

        for (int i = 0; i < ransomNote.length(); i++) {
            var letter = ransomNote.charAt(i);
            var letterCount = magazineMap.getOrDefault(letter, 0);
            if (letterCount == 0) {
                return false;
            }

            magazineMap.put(letter, --letterCount);
        }

        return true;
    }

    private static Map<Character, Integer> stringToMap(final String magazine) {
        final var hashmap = new HashMap<Character, Integer>();
        for (int i = 0; i < magazine.length(); i++) {
            var count = hashmap.getOrDefault(magazine.charAt(i), 0);
            hashmap.put(magazine.charAt(i), ++count);
        }

        return hashmap;
    }
}
