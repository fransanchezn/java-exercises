package org.fransanchez.exercises.binarysearch;

public class FirstBadVersion {
    private final int badVersion;

    public FirstBadVersion(final int badVersion) {
        this.badVersion = badVersion;
    }

    // 1 2 3 4 5 6 7
    public int firstBadVersion(final int n) {
        var low = 1;
        var high = n;

        while (low < high) {
            final var mid = (int) (Long.sum(low, high) / 2);

            final var badVersion = isBadVersion(mid);
            if (badVersion) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean isBadVersion(final int version) {
        return version == badVersion;
    }

    public static void main(String[] args) {
        new FirstBadVersion(4).firstBadVersion(5);
    }
}
