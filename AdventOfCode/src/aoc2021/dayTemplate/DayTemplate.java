package aoc2021.dayTemplate;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class DayTemplate {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day x:");

        ArrayList<Integer> input = InputLoader.toIntegerList("input/dayX.txt");
        //ArrayList<String> input = InputLoader.toStringList("input/dayX.txt");



        System.out.println("part 1: " + res1);
        System.out.println("part 2: " + res2);
    }
}
