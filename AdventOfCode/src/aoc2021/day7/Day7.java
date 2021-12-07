package aoc2021.day7;

import aoc2021.utilities.CommonFunctions;
import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day7 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 7:");
        ArrayList<Integer> crabPositions = new ArrayList<>();
        ArrayList<Integer> fuelCosts = new ArrayList<>();
        ArrayList<Integer> fuelCostsV2 = new ArrayList<>();

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day7.txt");
        //ArrayList<String> input = InputLoader.toStringList("input/day7.txt");
        String input = InputLoader.asString("input/day7.txt", "");
        String[] crab = input.split(",");
        for (String s : crab) {
            int pos = Integer.parseInt(s);
            crabPositions.add(pos);
        }

        int high = CommonFunctions.getMax(crabPositions);

        for(int i = 0; i < high; i++) {
            fuelCosts.add(calculateFuel(i, crabPositions));
            fuelCostsV2.add(calculateFuelV2(i, crabPositions));
        }

        System.out.println("part 1: " + CommonFunctions.getMin(fuelCosts));
        System.out.println("part 2: " + CommonFunctions.getMin(fuelCostsV2));


    }

    private static Integer calculateFuel(int i, ArrayList<Integer> crabPositions) {
        int res = 0;

        for (int pos : crabPositions) {
            if (i > pos) {
                res += i-pos;
            } else {
                res += pos-i;
            }
        }

        return res;
    }

    private static Integer calculateFuelV2(int i, ArrayList<Integer> crabPositions) {
        int res = 0;

        for (int pos : crabPositions) {
            if (i > pos) {
                res += calculateGauss(i-pos);
            } else {
                res += calculateGauss(pos-i);
            }
        }

        return res;
    }

    private static int calculateGauss(int cost) {
        return (cost * (cost + 1))/2;
    }
}
