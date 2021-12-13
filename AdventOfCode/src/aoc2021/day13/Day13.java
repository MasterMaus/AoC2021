package aoc2021.day13;

import aoc2021.utilities.Grid;
import aoc2021.utilities.InputLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Day13 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day 13:");

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day13.txt");
        ArrayList<String> input = InputLoader.toStringList("input/day13.txt");

        Grid g = new Grid(1309,895);
        ArrayDeque<String> foldInstructions = new ArrayDeque<>();

        int max = 0;

        for (String line : input) {

            if(line.matches("\\d{1,4},\\d{1,4}")) { //read positional input
                String[] pos = line.split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                g.set(x,y,1);
            } else if (!line.isEmpty()) {
                String[] instruction = line.split(" ");
//                System.out.println(Arrays.toString(instruction));
                foldInstructions.addLast(instruction[2]);
            }
        }

        String foldInstruction = foldInstructions.removeFirst();
        g = fold(g, foldInstruction);
        System.out.println(g.count(1));

        while(!foldInstructions.isEmpty()) {
            foldInstruction = foldInstructions.removeFirst();
            g = fold(g,foldInstruction);
        }

        System.out.println(g.toAsciiImage());
    }

    private static Grid fold(Grid grid, String foldInstruction) {
        //calculate the new gridsize
        int length = grid.getLength();
        int height = grid.getHeight();
        String instruction[] = foldInstruction.split("=");
        String foldAxis = instruction[0];
        int foldLine = Integer.parseInt(instruction[1]);
        if (foldAxis.equals("x")) {
            length = foldLine;
        } else {
            height = foldLine;
        }

        Grid res = new Grid(length, height);



        for(int id : grid.getPositionsWithValue(1)) {
            int[] pos = grid.idToPos(id);
            int x = pos[0];
            int y = pos[1];

            if(foldAxis.equals("x")) {
                if (x > foldLine) {
                    x = foldLine - (x - foldLine);
                }
            } else {
                if (y > foldLine) {
                    y = foldLine - (y - foldLine);
                }
            }

            res.set(x, y, 1);
        }


        return res;
    }
}
