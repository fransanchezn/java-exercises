package org.fransanchez.exercises.graphs.adjmatrix;

import java.util.*;

// 207. Course Schedule
public class CourseSchedule {
    // Map each course to its prerequisites
    private Map<Integer, List<Integer>> preMap = new HashMap<>();
    // Store all courses along the current DFS path
    private Set<Integer> visited = new HashSet<>();

    public boolean canFinish(final int numCourses, final int[][] prerequisites) {
        for (int[] prerequisite : prerequisites) {
            final var prerequisiteList = preMap.getOrDefault(prerequisite[0], new ArrayList<>());
            prerequisiteList.add(prerequisite[1]);
            preMap.put(prerequisite[0], prerequisiteList);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(final int course) {
        if (visited.contains(course)) {
            // Cycle detected
            return false;
        }

        if (preMap.get(course).isEmpty()) {
            return true;
        }

        visited.add(course);
        for (int pre : preMap.get(course)) {
            if (!dfs(pre)) {
                return false;
            }
        }

        visited.remove(course);
        preMap.put(course, new ArrayList<>());
        return true;
    }

    public static void main(String[] args) {
        final var sut = new CourseSchedule();
        sut.canFinish(2, new int[][] {{0, 1}});
    }
}
