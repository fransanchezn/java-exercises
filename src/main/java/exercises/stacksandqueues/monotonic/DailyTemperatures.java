package exercises.stacksandqueues.monotonic;

import java.util.Arrays;
import java.util.Stack;

// 739. Daily Temperatures
public class DailyTemperatures {

    // Input: temperatures = [73,74,75,71,69,72,76,73]
    // Output: [1,1,4,2,1,1,0,0]
    public int[] dailyTemperatures(final int[] temperatures) {
        final var stack = new Stack<Integer>();
        final var answer = new int[temperatures.length];

        for (int day = 0; day < temperatures.length; day++) {
            final var temperature = temperatures[day];

            while (!stack.empty() && temperatures[stack.peek()] < temperature) {
                final var prevDay = stack.pop();
                answer[prevDay] = day - prevDay;
            }

            stack.push(day);
        }

        return answer;
    }

    public static void main(String[] args) {
        final var sut = new DailyTemperatures();
        final var result = sut.dailyTemperatures(new int[] { 73,74,75,71,69,72,76,73 });

        System.out.println(Arrays.toString(result));
    }

}
