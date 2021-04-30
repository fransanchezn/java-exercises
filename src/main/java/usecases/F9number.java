package usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class F9number {

    public static void main(String[] args) {
        // Input: List<String> CODES: TWLO, AAWS, FCBK.
        // Output: List<String> Numbers that match.
        List<String> codes = new ArrayList<>();
        codes.add("TWLO");
        codes.add("FCBK");
        codes.add("APPL");
        codes.add("AMZN");

        List<String> numbers = new ArrayList<>();
        numbers.add("02342312");
        numbers.add("02322512"); //FCBK
        numbers.add("02327752"); //APPL
        numbers.add("02321752");
        numbers.add("02326962"); //AMZN
        numbers.add("02316962");
        numbers.add("08956962"); //TWLO
        numbers.add("89562696"); //TWLO&AMZN

        System.out.println(t9Mapper(codes, numbers));
    }

    static List<String> t9Mapper(List<String> codes, List<String> numbers) {

        //     private static final char[] DIGITS = (
        //                 // ABC     DEF
        //       "0" + "1" + "222" + "333" +
        //        // GHI      JKL     MNO
        //          "444"  + "555" + "666" +
        //        // PQRS     TUV     WXYZ
        //          "7777" + "888" + "9999").toCharArray();
        Map<String, Integer> keymap = new HashMap<>();
        keymap.put("A", 2);keymap.put("B", 2);keymap.put("C", 2);
        keymap.put("D", 3);keymap.put("E", 3);keymap.put("F", 3);
        keymap.put("G", 4);keymap.put("H", 4);keymap.put("I", 4);
        keymap.put("J", 5);keymap.put("K", 5);keymap.put("L", 5);
        keymap.put("M", 6);keymap.put("N", 6);keymap.put("O", 6);
        keymap.put("P", 7);keymap.put("Q", 7);keymap.put("R", 7);keymap.put("S", 7);
        keymap.put("T", 8);keymap.put("U", 8);keymap.put("V", 8);
        keymap.put("W", 9);keymap.put("X", 9);keymap.put("Y", 9);keymap.put("Z", 9);

        List<String> result = new ArrayList<>();
        for (String code : codes) {
            String number = "";
            for (char c: code.toCharArray()) {
                number += keymap.get(c+"");
            }
            System.out.println(number);

            for (String num : numbers) {
                if (num.contains(number)) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}
