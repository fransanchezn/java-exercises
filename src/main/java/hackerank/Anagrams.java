package hackerank;

public class Anagrams {

    public static void main(String[] args) {
        System.out.println(anagrams("Mango", "goman"));
    }

    static boolean anagrams(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        for (char c : a.toCharArray()) {
            if (b.isEmpty()) {
                return false;
            }

            int l = b.length();

            b = b.replaceFirst(c+"","");

            if (l == b.length()) {
                return false;
            }
        }

        if (b.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
