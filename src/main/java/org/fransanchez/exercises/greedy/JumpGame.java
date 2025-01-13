package org.fransanchez.exercises.greedy;

// 55. Jump Game
public class JumpGame {
    public boolean canJump(final int[] nums) {
        int goal = nums.length - 1;

        for (var i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= goal ) {
                goal = i;
            }
        }

        return goal == 0;
    }
}
