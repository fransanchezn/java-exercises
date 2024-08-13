package exercises.old.array;

public class LengthLastWord {

    public static void main(final String[] args) {
        System.out.println(lengthOfLastWord1("   "));
    }


    private static int lengthOfLastWord1(final String s) {
        var splitted = s.split(" ");
        return splitted[splitted.length - 1].length();
    }

    // "luffy is still joyboy"
    private static int lengthOfLastWord2(final String s) {
        var splitted = s.split(" ");
        var lastWordLength = 0;
        var index = splitted.length - 1;
        while (index >= 0 && lastWordLength == 0) {
            lastWordLength = splitted[index].length();
            index--;
        }

        return lastWordLength;
    }
}
