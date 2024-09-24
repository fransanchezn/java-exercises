package org.fransanchez.deprecated.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemSort {
    public static void main(String[] args) {
        List<Integer> items = new ArrayList<>(Arrays.asList(8, 5, 5, 5, 5, 1, 1, 1, 4, 4));
        System.out.println(itemSort(items));
    }

    static List<Integer> itemSort(List<Integer> items) {
        Map<Integer, Integer> inventory = new HashMap<>();
        for (int i = 0 ; i < items.size(); i++) {
            Integer count = inventory.getOrDefault(items.get(i),0);
            inventory.put(items.get(i), ++count);
        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(inventory.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int compareValue = o1.getValue().compareTo(o2.getValue());
                if (compareValue == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }

                return compareValue;
            }
        });
        System.out.println(list);

        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            for (int i = 0; i<entry.getValue();i++) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}
