package hackerrank;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String pal) {
        String palFiltered = pal.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        String reversed = "";
        for (char i : palFiltered.toCharArray()) {
            reversed = i + reversed;
        }

        return palFiltered.equals(reversed);
    }
}