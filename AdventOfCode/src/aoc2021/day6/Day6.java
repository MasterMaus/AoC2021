package aoc2021.day6;

import aoc2021.utilities.InputLoader;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.TreeMap;

public class Day6 {

    public static void run() {

        System.out.println("Solutions day 6:");
        TreeMap<Integer, Long> population = new TreeMap<>(); //K -> internal clock ; V -> amount of fish with that clock

        for (int i = 0; i <= 8; i++) {
            population.put(i, (long) 0);
        }

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day6.txt");
        //ArrayList<String> input = InputLoader.toStringList("input/day6.txt");
        String input = InputLoader.asString("input/day6.txt", "");
        String[] fish = input.split(",");
        for (String s : fish) {
            int clock = Integer.parseInt(s);
            population.put(clock, population.get(clock) + 1);
        }

        //System.out.println(population.values().toString());

        for (int i = 0; i<80; i++) {
            population = simulateDay(population);
        }

        System.out.println(total(population));


        for (int i = 80; i<256; i++) {
            population = simulateDay(population);
        }

        System.out.println(total(population));


    }

    public static TreeMap<Integer, Long> simulateDay(TreeMap<Integer, Long> population) {
        TreeMap<Integer, Long> res = new TreeMap<>();
        for(int i = 1; i <= 8; i++) {
            res.put(i-1, population.get(i));
        }
        res.put(8, population.get(0));
        res.put(6, res.get(6) + population.get(0));

        return res;
    }

    public static long total(TreeMap<Integer, Long> population) {
        long res = 0;
        for(int i : population.keySet()) {
            res += population.get(i);
        }
        return res;

    }
}
