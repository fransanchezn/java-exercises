package hackerrank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        String t1 = "123456,-,ES0073906579213636759889,ES7520804425617884296583,1300,2019-1-24";
        String t2 = "654321,123456,ES0073906579213636759889,ES4404877854884229954162,300,2019-3-20";
        String[] ts = new String[2];
        ts[0] = t1;
        ts[1] = t2;
        System.out.println(test.solution(ts, 123456, "2019-4-1"));
    }

    public static int solution(String[] transactions, int customerId, String depositOpeningDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        LongAdder adder = new LongAdder();
        Arrays.stream(transactions).filter((t) -> {
            // We first filter out the transactions that we are interested in.
            // Filter by customerId (in/out) and date within 6months.
            String[] split = t.split(",");

            int destCustomerId = !split[0].equals("-") ? Integer.valueOf(split[0]) : -1;
            int origCustomerId = !split[1].equals("-") ? Integer.valueOf(split[1]) : -1;

            LocalDate date = LocalDate.parse(split[5], formatter);
            LocalDate opening = LocalDate.parse(depositOpeningDate, formatter);
            long monthDiff = ChronoUnit.MONTHS.between(date, opening);
            boolean isAfter = opening.isAfter(date);

            // Filter by customerId, after date and 6 months or less from the opening.
            return (destCustomerId == customerId || origCustomerId == customerId) && isAfter && monthDiff <= 6;
        }).forEach((t) -> {
            // Now we need to calculate the amount for that customer.
            // For this, we will subtract amount going out and add amount coming in,
            // based on the customerId being origin or destination.
            String[] split = t.split(",");

            int destCustomerId = !split[0].equals("-") ? Integer.valueOf(split[0]) : -1;
            int origCustomerId = !split[1].equals("-") ? Integer.valueOf(split[1]) : -1;

            if (destCustomerId == customerId) {
                adder.add(Long.valueOf(split[4]));
            } else if (origCustomerId == customerId) {
                adder.add(-1 * Long.valueOf(split[4]));
            }
        });

        return adder.intValue();
    }
}
