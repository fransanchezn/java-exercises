package org.fransanchez.deprecated.array;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    static final Map<Character, Integer> ROMAN_TO_INTEGER;

    static {
        ROMAN_TO_INTEGER = new HashMap<>();
        ROMAN_TO_INTEGER.put('I', 1);
        ROMAN_TO_INTEGER.put('V', 5);
        ROMAN_TO_INTEGER.put('X', 10);
        ROMAN_TO_INTEGER.put('L', 50);
        ROMAN_TO_INTEGER.put('C', 100);
        ROMAN_TO_INTEGER.put('D', 500);
        ROMAN_TO_INTEGER.put('M', 1000);
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("CMIXC"));
    }

    /*
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000


    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.

     */
    // MCMXCIV = 1000-100+1000-10+100-1+5
    // IV = -1+5
    private static int romanToInt(final String s) {
        var totalNumberValue = 0;
        var lastNumberValue = 0;

        if (s.length() < 2) {
            return ROMAN_TO_INTEGER.get(s.charAt(0));
        }

        for (int i = 0; i < s.length() - 1; i++) {
           var currentRomanNumber = s.charAt(i);
           var nextRomanNumber = s.charAt(i + 1);

           var currentNumberValue = ROMAN_TO_INTEGER.get(currentRomanNumber);
           var nextNumberValue = ROMAN_TO_INTEGER.get(nextRomanNumber);
           if (currentNumberValue >= nextNumberValue) {
               totalNumberValue += currentNumberValue;
           } else {
               totalNumberValue -= currentNumberValue;
           }

            lastNumberValue = nextNumberValue;
        }

        return totalNumberValue + lastNumberValue;
    }
}
