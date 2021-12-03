package aoc2021.day3;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day3 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 3:");

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day2.txt");
        ArrayList<String> input = InputLoader.toStringList("input/day3.txt");

        int len = input.get(0).length();
        String epsilonStr = "";
        String gammaStr = "";

        for (int i = 0; i < len; i++) {
            int zeroes = 0;
            int ones = 0;
            for(String s : input) {
                if (s.charAt(i) == '0') {
                    zeroes ++;
                } else {
                    ones ++;
                }
            }
            if (zeroes > ones) {
                gammaStr += "0";
                epsilonStr += "1";
            } else {
                gammaStr += "1";
                epsilonStr += "0";
            }
        }
        System.out.println(gammaStr);
        System.out.println(epsilonStr);

        int gamma = Integer.parseInt(gammaStr, 2);
        int epsilon = Integer.parseInt(epsilonStr, 2);

        System.out.println(gamma * epsilon);



    }
}
