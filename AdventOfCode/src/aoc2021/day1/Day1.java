package aoc2021.day1;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day1 {

    public static void run() {
        int res = 0;
        System.out.println("Solutions day 1:");
        ArrayList<Integer> input = InputLoader.toIntegerList("input/day1.txt");

        for (int i = 1; i<input.size(); i++) {
            if (input.get(i) > input.get(i-1)) {
                res ++;
            }
        }

        System.out.println(res);

        ArrayList<Integer> slidingInput = new ArrayList<>();

        for (int i = 2; i<input.size(); i++) {
            int slide = input.get(i) + input.get(i-1) + input.get(i-2);
            slidingInput.add(slide);
        }

        int res2 = 0;

        for (int i = 1; i<slidingInput.size(); i++) {
            if (slidingInput.get(i) > slidingInput.get(i-1)) {
                res2 ++;
            }
        }

        System.out.println(res2);
    }
}
