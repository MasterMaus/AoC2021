package aoc2021.day9;

import aoc2021.utilities.Grid;
import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day9Improved {

    //TODO Improve day9 because unclean code

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 9:");
        ArrayList<String> input = InputLoader.toStringList("input/day9.txt");

        int height = input.size();
        int length = input.get(0).length();

        Grid grid = new Grid(length, height);

        for (int y = 0; y<input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length();x++) {
                grid.set(x,y,(Integer.parseInt(line.substring(x,x+1))));
            }
        }



    }
}
