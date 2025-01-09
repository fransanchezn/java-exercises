package org.fransanchez.exercises.trees.binarytrees.binarysearchtree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

// 981. Time Based Key-Value Store
public class TimeBasedStoreII {
    private final Map<String, TreeMap<Integer, String>> store;

    public TimeBasedStoreII() {
        store = new HashMap<>();
    }

    // O(log n)
    public void set(final String key, final String value, final int timestamp) {
        final var treeMap = store.getOrDefault(key, new TreeMap<>());
        treeMap.put(timestamp, value);
        store.put(key, treeMap);
    }

    // O(log n)
    public String get(final String key, final int timestamp) {
        return Optional.ofNullable(store.get(key))
                .map(i -> i.floorEntry(timestamp))
                .map(Map.Entry::getValue)
                .orElse("");
    }

    private Entry binarySearch(final List<Entry> entries, final int target) {
        int left = 0;
        int right = entries.size() - 1;
        Entry closestMin = null;

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (entries.get(mid).timestamp > target) {
              right = mid - 1;
            } else if (entries.get(mid).timestamp < target) {
                left = mid + 1;
                closestMin = entries.get(mid);
            } else {
                return entries.get(mid);
            }
        }

        return closestMin;
    }

    private record Entry(String value, int timestamp) {}
}
