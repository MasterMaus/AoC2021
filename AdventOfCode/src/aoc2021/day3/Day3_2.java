package aoc2021.day3;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day3_2 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 3:");

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day2.txt");
        ArrayList<String> input = InputLoader.toStringList("input/day3.txt");

        ArrayList<String> oxy = new ArrayList<>(input);
        ArrayList<String> co2 = new ArrayList<>(input);

        int len = input.get(0).length();


        for (int i = 0; i < len; i++) {
            int zeroes = 0;
            int ones = 0;
            for(String s : oxy) {
                if (s.charAt(i) == '0') {
                    zeroes ++;
                } else {
                    ones ++;
                }
            }
            if (zeroes > ones) {
                ArrayList<String> temp = new ArrayList<>();
                for (String s : oxy) {
                    if (s.charAt(i) == '0') {
                        temp.add(s);
                    }
                }
                oxy = temp;
            } else {
                ArrayList<String> temp = new ArrayList<>();
                for (String s : oxy) {
                    if (s.charAt(i) == '1') {
                        temp.add(s);
                    }
                }
                oxy = temp;
            }
            if (oxy.size() == 1) {
                break;
            }
        }

        for (int i = 0; i < len; i++) {
            int zeroes = 0;
            int ones = 0;
            for(String s : co2) {
                if (s.charAt(i) == '0') {
                    zeroes ++;
                } else {
                    ones ++;
                }
            }

            if (zeroes > ones) {
                ArrayList<String> temp = new ArrayList<>();
                for (String s : co2) {
                    if (s.charAt(i) == '1') {
                        temp.add(s);
                    }
                }
                co2 = temp;
            } else {
                ArrayList<String> temp = new ArrayList<>();
                for (String s : co2) {
                    if (s.charAt(i) == '0') {
                        temp.add(s);
                    }
                }
                co2 = temp;
            }
            if (co2.size() == 1) {
                break;
            }
        }

        int valOxy = Integer.parseInt(oxy.get(0), 2);
        int valCo2 = Integer.parseInt(co2.get(0), 2);
        System.out.println(valOxy);
        System.out.println(valOxy * valCo2);



    }
}
