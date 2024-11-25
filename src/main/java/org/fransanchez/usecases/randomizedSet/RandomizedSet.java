package org.fransanchez.usecases.randomizedSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomizedSet {
    final List<Integer> values;
    final Map<Integer, Integer> valueMap;

    public RandomizedSet() {
        values = new ArrayList<>();
        valueMap = new HashMap<>();
    }

    public boolean insert(final int value) {
        if (valueMap.containsKey(value)) {
            return false;
        }

        values.add(value);
        valueMap.put(value, values.size() - 1);
        return true;
    }

    public boolean remove(final int value) {
        if (!valueMap.containsKey(value)) {
            return false;
        }

        final var valueIndex = valueMap.get(value);
        if (valueIndex == values.size() - 1) {
            values.removeLast();
            valueMap.remove(value);
            return true;
        }

        final var lastValue = values.getLast();
        values.set(valueIndex, lastValue);
        values.removeLast();
        valueMap.remove(value);
        valueMap.put(lastValue, valueIndex);

        return true;
    }

    public int getRandom() {
        if (values.isEmpty()) {
            return -1;
        }

        return values.get((int) Math.floor(Math.random() * values.size()));
    }

    public static void main(final String[] args) {
        final var sut = new RandomizedSet();
        sut.insert(2);
        sut.insert(4);
        sut.insert(5);
        sut.remove(4);
        System.out.println(sut.getRandom());
    }
}
