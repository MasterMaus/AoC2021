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
            String[] entry = s.split(" ");
            int val = Integer.parseInt(entry[1]);
            if (entry[0].equals("forward")) {
                posX += val;
            } else if (entry[0].equals("down")) {
                posY += val;
            } else if (entry[0].equals("up")) {
                posY -= val;
            }
        }

        System.out.println("first answer: " + posX*posY);

        posX = 0;
        posY = 0;

        for(String s : input) {
            String[] entry = s.split(" ");
            int val = Integer.parseInt(entry[1]);
            if (entry[0].equals("forward")) {
                posX += val;
                posY += aim*val;
            } else if (entry[0].equals("down")) {
                aim += val;
            } else if (entry[0].equals("up")) {
                aim -= val;
            }
        }

        System.out.println("second answer: " + posX*posY);
    }
}
