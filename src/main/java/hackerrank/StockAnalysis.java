package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StockAnalysis {
    public static void main(String[] args) {
        List<String> input1 = new ArrayList<>(Arrays.asList("P1:a", "P3:b", "P5:x"));
        List<String> input2 = new ArrayList<>(Arrays.asList("P1:b", "P2:q", "P5:x"));
        System.out.println(stockAnalysis(input1, input2));
    }

    static List<String> stockAnalysis(List<String> ...inputs) {
        Map<String, String> stock = new LinkedHashMap<>();
        for (List<String> input : inputs) {
            for (int i = 0 ; i < input.size(); i++) {
                String point = input.get(i);
                String[] pointSplit = point.split(":");
                stock.put(pointSplit[0], pointSplit[1]);
            }
        }

        return new ArrayList<>(stock.values());
    }
}
