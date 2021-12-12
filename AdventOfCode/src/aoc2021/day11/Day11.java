package aoc2021.day11;

import aoc2021.utilities.Grid;
import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day11 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;



        System.out.println("Solutions day 11:");

        ArrayList<String> input = InputLoader.toStringList("input/day11.txt");
        int height = input.size();
        int length = input.get(0).length();
        Grid octopussies = new Grid(length, height);
        //fill octopussies grid

        for (int y = 0; y<input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length(); x++) {
                octopussies.set(x, y, Integer.parseInt(line.substring(x,x+1)));
            }
        }

        for (int round = 0; round < 100; round++) {
            octopussies = nextRound(octopussies);
            res1 += octopussies.count(0);
        }

        while (octopussies.count(0) != 100) {
            octopussies = nextRound(octopussies);
            res2++;
        }




        System.out.println("part 1: " + res1);
        System.out.println("part 2: " + (res2 + 100)); //The first 100 steps were already computed in part 1
    }

    public static Grid nextRound(Grid octopussies) {
        Grid res = new Grid(octopussies);

        //step 1: Increase all energy levels by 1.
        res.incrementAll(1);

        //step 2: Increase all neighbors by 1 if they did not flash already. Set all number 9 to 0 to indicate that they flashed
        while (res.contains(10)) {
            for (int id = 0; id < res.size(); id++) { // todo hardcoded id limit?!
                if (res.get(id) == 10) {
                    res.set(id, 0);
                    ArrayList<Integer> toIncrement = res.getNeighbors(id);
                    for(int neighbor : toIncrement) {
                        if(res.get(neighbor) != 0 && res.get(neighbor) != 10) {
                            res.increment(neighbor, 1);
                        }
                    }
                }
            }
        }

        return res;
    }
}
