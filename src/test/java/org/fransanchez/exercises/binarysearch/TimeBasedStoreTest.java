package org.fransanchez.exercises.binarysearch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeBasedStoreTest {
    private final TimeBasedStore sut = new TimeBasedStore();

    @Test
    void givenMultipleInsert_whenCallingGets_returnApproximateTimestampValue() {
        sut.set("foo", "bar", 1);
        assertThat(sut.get("foo", 1)).isEqualTo("bar");
        assertThat(sut.get("foo", 3)).isEqualTo("bar");

        sut.set("foo", "bar2", 4);
        assertThat(sut.get("foo", 4)).isEqualTo("bar2");
        assertThat(sut.get("foo", 5)).isEqualTo("bar2");
    }
}