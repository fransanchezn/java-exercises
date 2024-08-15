package deprecated.hashmap;

public class IsomorphicStrings {
    public static void main(final String[] args) {
        System.out.println(isIsomorphic("abcdefghijklmnopqrstuvwxyzva", "abcdefghijklmnopqrstuvwxyzck"));
        // System.out.println(isIsomorphic("egg", "add"));
    }

    public static boolean isIsomorphic(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }

        var letterIndexFirstMap = new Integer[127];
        var letterIndexSecondMap = new Integer[127];

        var letterIndexFirst = 0;
        var letterIndexSecond = 0;
        for (int i = 0; i < s.length(); i++) {
            // First
            var letterFirst = s.charAt(i);
            var indexFirst = letterIndexFirstMap[letterFirst];
            if (indexFirst == null) {
                indexFirst = letterIndexFirst++;
                letterIndexFirstMap[letterFirst] = indexFirst;
            }

            // Second
            var letterSecond = t.charAt(i);
            var indexSecond = letterIndexSecondMap[letterSecond];
            if (indexSecond == null) {
                indexSecond = letterIndexSecond++;
                letterIndexSecondMap[letterSecond] = indexSecond;
            }

            if (!indexFirst.equals(indexSecond)) {
                return false;
            }
        }

        return true;
    }

    private static int[] stringToNumber(final String input) {
        var letterIndexMap = new Integer[127];
        var letterIndex = 0;
        final var number = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            var letter = input.charAt(i);
            var index = letterIndexMap[letter];
            if (index == null) {
                index = letterIndex++;
                letterIndexMap[letter] = index;
            }

            number[i] = index;
        }

        return number;
    }
}
