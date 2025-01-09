package org.fransanchez.exercises.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 981. Time Based Key-Value Store
public class TimeBasedStore {
    private final Map<String, List<Entry>> store;

    public TimeBasedStore() {
        store = new HashMap<>();
    }

    // O(1) - assumed timestamps comes sorted
    public void set(final String key, final String value, final int timestamp) {
        final var list = store.getOrDefault(key, new ArrayList<>());
        list.add(new Entry(value, timestamp));
        store.put(key, list);
    }

    // O(log n)
    public String get(final String key, final int timestamp) {
        final var list = store.getOrDefault(key, new ArrayList<>());
        final var closestEntry = binarySearch(list, timestamp);
        return closestEntry == null ? "" : closestEntry.value;
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
