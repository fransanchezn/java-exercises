package deprecated.string;

public class SubString {

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
    }

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        if (haystack.equals(needle)) {
            return 0;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                final var substr = haystack.substring(i, i + needle.length());
                if (substr.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }
}


// ABCD
// BCD