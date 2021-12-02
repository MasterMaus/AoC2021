package aoc2021.day2;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day2 {

    public static void run() {
        int posX = 0;
        int posY = 0;

        int aim = 0;

        System.out.println("Solutions day 2:");

        ArrayList<String> input = InputLoader.toStringList("input/day2.txt");

        for(String s : input) {
            if (s.contains("forward")) {
                int val = Integer.parseInt(s.substring(8));
                posX += val;
            } else if (s.contains("down")) {
                int val = Integer.parseInt(s.substring(5));
                posY += val;
            } else if (s.contains("up")) {
                int val = Integer.parseInt(s.substring(3));
                posY -= val;
            }
        }

        System.out.println("first answer: " + posX*posY);

        posX = 0;
        posY = 0;

        for(String s : input) {
            if (s.contains("forward")) {
                int val = Integer.parseInt(s.substring(8));
                posX += val;
                posY += aim*val;
            } else if (s.contains("down")) {
                int val = Integer.parseInt(s.substring(5));
                aim += val;
            } else if (s.contains("up")) {
                int val = Integer.parseInt(s.substring(3));
                aim -= val;
            }
        }
        System.out.println("second answer: " + posX*posY);
    }
}
