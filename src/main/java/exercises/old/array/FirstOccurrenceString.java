package exercises.old.array;

public class FirstOccurrenceString {
    public static void main(String[] args) {
        System.out.println(firstOccurrence("mississippi", "issip"));
    }

    public static int firstOccurrence(final String haystack, final String needle) {
        if (needle.length() > haystack.length()) {
            return -1;
        }

        var haystackIndex = 0;
        var needleIndex = 0;
        while (haystackIndex < haystack.length() && needleIndex < needle.length()) {
            if (haystack.charAt(haystackIndex) == needle.charAt(needleIndex)) {
                needleIndex++;
            } else {
                haystackIndex -= needleIndex;
                needleIndex = 0;
            }
            haystackIndex++;
        }

        return needleIndex < needle.length() ? -1 : haystackIndex - needleIndex;
    }
}
