package org.fransanchez.exercises.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesignBrowserHistoryTest {

    @Test
    public void test() {
        final DesignBrowserHistory history = new DesignBrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");

        assertEquals("facebook.com", history.back(1));
        assertEquals("google.com", history.back(1));
        assertEquals("facebook.com", history.forward(1));
        history.visit("linkedin.com");
        assertEquals("linkedin.com", history.forward(2));
        assertEquals("google.com", history.back(2));
        assertEquals("leetcode.com", history.back(7));
    }
}