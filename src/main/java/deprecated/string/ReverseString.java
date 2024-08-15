package deprecated.string;

public class ReverseString {

    public static void main(String[] args) {
        final var input = "aloh";
        final var result = ReverseString.reverseRec(input);
        System.out.println(String.format("result: %s", result));
    }

    public static String reverseIte(final String input) {
        final var array = input.split("");
        var result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            result += array[i];
        }

        return result;
    }

    public static String reverseRec(final String input) {
        if (input.isEmpty()) {
            return input;
        }

        return reverseRec(input.substring(1)) + input.charAt(0);
    }
}
