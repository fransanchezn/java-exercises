package org.fransanchez.exercises.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 973. K Closest Points to Origin
public class KClosestPointsOrigin {
    private final Queue<Point> heap = new PriorityQueue<>((a, b) -> Double.compare(b.distance, a.distance));

    public int[][] kClosest(final int[][] points, final int k) {
        Arrays.stream(points).forEach(p -> {
            add(new Point(p, distanceToOrigin(p)), k);
        });

        final var result = new int[k][2];
        final var heapList = heap.stream().toList();
        for (int i = 0; i < heapList.size(); i++) {
            result[i] = heapList.get(i).coordinates;
        }

        return result;
    }

    private Point add(final Point p, final int k) {
        final var size = heap.size();
        if (size < k) {
            heap.add(p);
            return heap.peek();
        }

        final var max = heap.peek();
        if (Double.compare(max.distance, p.distance) > 0) {
            heap.remove();
            heap.add(p);
        }

        return heap.peek();
    }

    private record Point(int[] coordinates, double distance) {}

    private double distanceToOrigin(final int[] coordinates) {
        // √(x1 - x2)^2 + (y1 - y2)^2
        final var xAxis = Math.pow((0d - coordinates[0]), 2);
        final var yAxis = Math.pow((0d - coordinates[1]), 2);
        return Math.sqrt(xAxis + yAxis);
    }

    public static void main(String[] args) {
        final var sut = new KClosestPointsOrigin();
        final var coordinates = new int[][] {{3,3},{5,-1},{-2,4}};
        System.out.println(Arrays.deepToString(sut.kClosest(coordinates, 2)));
    }
}
