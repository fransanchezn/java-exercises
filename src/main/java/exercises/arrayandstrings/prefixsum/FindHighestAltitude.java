package exercises.arrayandstrings.prefixsum;

// 1732. Find the Highest Altitude
public class FindHighestAltitude {

    public int largestAltitude(final int[] gain) {
        var currentAltitude = 0;
        var maxAltitude = currentAltitude;

        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i];
            maxAltitude = Math.max(currentAltitude, maxAltitude);
        }

        return maxAltitude;
    }

    // -5,1,5,0,-7
    public static void main(String[] args) {
        final var sut = new FindHighestAltitude();
        final var result = sut.largestAltitude(new int[] { -4,-3,-2,-1,4,3,2 });
        System.out.println(result);

    }
}
