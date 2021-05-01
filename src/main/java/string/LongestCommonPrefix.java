package string;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        final var input = new String[]{"flower","flow","flight"};
        //final var input = new String[]{"flower", "", "","car"};
        System.out.println(longestCommonPrefix(input));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        var commonPrefix = "";
        for (int i = 0; i < strs[0].length(); i++) {
            var charToCompare = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || charToCompare != strs[j].charAt(i)) {
                    return commonPrefix;
                }
            }
            commonPrefix += charToCompare;
        }

        return commonPrefix;
    }
}
