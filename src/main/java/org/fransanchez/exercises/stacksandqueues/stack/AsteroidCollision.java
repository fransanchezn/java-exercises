package org.fransanchez.exercises.stacksandqueues.stack;

import java.util.Arrays;
import java.util.Stack;

// 735. Asteroid Collision
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        final var stack = new Stack<Integer>();

        for (final int asteroid : asteroids) {
            var exploded = false;
            if (asteroid < 0) {
                var coliding = true;
                while (!stack.empty() && !exploded && coliding) {
                    final var previousAsteroid = stack.peek();

                    if (previousAsteroid < 0) {
                        coliding = false;
                    } else {
                        final var absoluteAsteroid = Math.abs(asteroid);
                        if (previousAsteroid < absoluteAsteroid) {
                            stack.pop();
                        } else if (previousAsteroid == absoluteAsteroid) {
                            stack.pop();
                            exploded = true;
                        } else {
                            exploded = true;
                        }
                    }
                }
            }

            if (!exploded) {
                stack.push(asteroid);
            }
        }

        final var result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        final var sut = new AsteroidCollision();
        final var result = sut.asteroidCollision(new int[] { -2,-1,1,2 });

        System.out.println(Arrays.toString(result));
    }
}
