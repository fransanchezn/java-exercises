package org.fransanchez.exercises.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignTwitter {
    final Map<Integer, HashSet<Integer>> relationships;
    final Map<Integer, LinkedList<Tweet>> tweetsPerUser;
    final int maxTweets;
    long counter;

    public DesignTwitter() {
        relationships = new HashMap<>();
        tweetsPerUser = new HashMap<>();
        maxTweets = 10;
        counter = 0;
    }

    public void postTweet(final int userId, final int tweetId) {
        final var tweets = tweetsPerUser.getOrDefault(userId, new LinkedList<>());
        final var prevTweet = tweets.isEmpty() ? null : tweets.getFirst();
        tweets.addFirst(new Tweet(tweetId, counter++, prevTweet));
        tweetsPerUser.put(userId, tweets);
    }

    public List<Integer> getNewsFeed(final int userId) {
        final var newsFee = new ArrayList<Integer>();

        final var followees = relationships.getOrDefault(userId, new HashSet<>());
        followees.add(userId);

        final var heap = new PriorityQueue<Tweet>((a, b) -> Long.compare(b.timestamp, a.timestamp));
        for (var followeeId: followees) {
            final var tweets = tweetsPerUser.getOrDefault(followeeId, new LinkedList<>());
            if (!tweets.isEmpty()) {
                heap.add(tweets.getFirst());
            }
        }

        int counter = 0;
        while (!heap.isEmpty() && counter < 10) {
            final var tweet = heap.poll();
            newsFee.add(tweet.tweetId);
            if (tweet.next != null) {
                heap.add(tweet.next);
            }
            counter++;
        }

        return newsFee;
    }

    public void follow(final int followerId, final int followeeId) {
        final var followees = relationships.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);  // O(1)
        relationships.put(followerId, followees);  // O(1)
    }

    public void unfollow(final int followerId, final int followeeId) {
        final var followees = relationships.getOrDefault(followerId, new HashSet<>());
        followees.remove(followeeId); // O(1)
        relationships.put(followerId, followees);  // O(1)
    }

    private record Tweet(int tweetId, long timestamp, Tweet next) {}

    public static void main(String[] args) {
        final var twitter = new DesignTwitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));

        twitter.unfollow(1, 2);
        twitter.getNewsFeed(1);
    }
}
