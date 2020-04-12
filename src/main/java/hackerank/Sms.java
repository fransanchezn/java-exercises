package hackerank;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Sms {
    public static void main(String[] args) {
        // Input: String
        // List<String> Spliited string

//        String text = "hola que tal?, yo estoy bien! Estoy viendo a ver como puedo programar este programa de SMS que se encarga de dividir en multiples SMS basado en un numero maximo de caracteres y añade que part del mensaje es al final con un espacio excepto en el ultimo que no lo hace y me complica un poco el algoritmo.";
        String text = "hola que tal?, yo estoy bien! Estoy viendo a ver como puedo programar este programa de SMS que se encarga de dividir en multiples SMS basado en un numero maximo de caracteres y añade que part del mensaje es al final con un espacio excepto en el ultimo que no lo hace y me complica un poco el algoritmo adaas.";
        System.out.println(splitSms(text));


    }

    static List<String> splitSms(String s) {
        int maxChar = 160;
        int reservedChar = 5;
        int txtLength = maxChar - reservedChar;

        List<String> sms = new ArrayList<>();
        if (s.length() <= maxChar) {
            sms.add(s);
            return sms;
        }

        StringJoiner sb = new StringJoiner(" ");
        int counter = 0;
        String[] words = s.split(" ");
        for (String word : words) {

            if (counter == words.length) {
                txtLength = txtLength - 1;
            }

            if (sb.length() + word.length() + 1 >= txtLength) {
                sms.add(sb.toString());
                sb = new StringJoiner(" ");
            }

            sb.add(word);
            counter++;
        }

        if (sb.length() > 0) {
            sms.add(sb.toString());
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < sms.size(); i++) {
            String msg = sms.get(i);

            if (i < sms.size() - 1) {
                msg += " ";
            }

            msg += "("+(i+1)+"/"+sms.size()+")";
            result.add(msg);
        }

        return result;
    }
}
