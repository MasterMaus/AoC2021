package aoc2021.day17;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;
import java.util.Arrays;

public class Day17 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day 17:");

        String input[] = InputLoader.asString("input/day17.txt","").split(" ");
        //ArrayList<String> input = InputLoader.toStringList("input/dayX.txt");

        String xs[] = input[2].replace(",", "").replace("x=", "").split("\\.\\.");
        String ys[] = input[3].replace(",", "").replace("y=", "").split("\\.\\.");


        // ranges target on x-axis
        int targetX1 = Integer.parseInt(xs[0]);
        int targetX2 = Integer.parseInt(xs[1]);
        // ranges target on Y-axis
        int targetY1 = Integer.parseInt(ys[1]);
        int targetY2 = Integer.parseInt(ys[0]);

        int target[] = {targetX1,targetX2,targetY1,targetY2};


        int velocity[] = new int[2]; //{x,y}

        //int velocity[] = {248,-56};

        for (int x = 0; x<=targetX2; x++) {
            for (int y = targetY2; y<(targetY2*-1); y++) {
                velocity[0] = x;
                velocity[1] = y;
                if (shoot(velocity, target)) {
                    res2++;
                }
            }
        }

        // Since X has no impact on the maximum height, we dont care about this value. We can always take an X where the x
        // Y will always come back at position (?,0) before y becomes negative
        // To reach the hightest value of Y, we want to have the highest possible speed on its way down, while still barely hitting the target
        // highest speed possible on way down is the same as the lower boundary of the target. so velocity = {0,target[3]}
        // To get to this speed, add 1 to target[3]. then remove the sign token.
        // maximum height is (n(n+1))/2 where n is the starting velocity
        //
        int n = (target[3]+1)*-1;
        System.out.println("part 1: " + (n*(n+1))/2);
        System.out.println("part 2: " + res2);
    }

    public static boolean shoot(int velocity[], int target[]) {
        if (velocity.length != 2 || target.length != 4) {
            return false; // wrong parameter input!
        }

        int location[] = {0,0};

        // If location falls below target or gets too far at the right of target, it cancels the loop and returns false
        while (location[1] >= target[3] && location[0] <= target[1]) { // while location(Y) >= lower boundary target && location(X) <= further boundary target
            location[0] += velocity[0];
            location[1] += velocity[1];

            //update the velocity on the X-axis
            if (velocity[0] > 0) {
                velocity[0] --;
            } else if (velocity[0] == 0) {
                // do nothing
            } else {
                velocity[0]++;
            }

            velocity[1] --; //update the velocity on the Y-axis

            if (location[1] >= target[3] && location[1] <= target[2]){ // if true, probe is on the same height as target
                if (location[0] >= target[0] && location[0] <= target[1]){ // if true, probe is also on the same distance as target
                    return true; //Found a valid starting velocity
                }
            }


        }
        return false;
    }
}
