package exercises.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        System.out.println(summaryRanges(new int[] { -2147483648, -2147483647, 2147483647 }));
    }
    // Input: nums = [0,1,2,4,5,7]
    // Output: ["0->2","4->5","7"]


    // -2147483648,-2147483647,2147483647
    public static List<String> summaryRanges(final int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        final var ranges = new ArrayList<String>();
        var rangeStart = nums[0];
        var previousNumber = nums[0];

        for (int i = 0; i < nums.length; i++) {
            final var currentNumber = nums[i];
            final var difference = Math.abs(currentNumber - previousNumber);
            if (difference > 1) {
                ranges.add(rangeToString(rangeStart, previousNumber));
                rangeStart = currentNumber;
            }

            previousNumber = currentNumber;
        }

        ranges.add(rangeToString(rangeStart, previousNumber));

        return ranges;
    }

    private static String rangeToString(final int startRange, final int endRange) {
        if (startRange != endRange) {
            return startRange + "->" + endRange;
        } else {
            return startRange + "";
        }
    }
}
