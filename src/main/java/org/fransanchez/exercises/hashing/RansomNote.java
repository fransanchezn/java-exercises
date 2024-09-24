package org.fransanchez.exercises.hashing;

// 383. Ransom Note
public class RansomNote {

    public boolean canConstruct(final String ransomNote, final String magazine) {
        final var hash = new int[26];
        for (int i = 0 ; i < magazine.length(); i++) {
            hash[magazine.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            final var charValue = hash[ransomNote.charAt(i) - 'a'] -= 1;
            if (charValue < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(final String[] args) {
        final var sut = new RansomNote();
        final var result = sut.canConstruct("a", "b");

        System.out.println(result);
    }
}
