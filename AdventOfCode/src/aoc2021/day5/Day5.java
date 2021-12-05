package aoc2021.day5;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day5 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day x:");

        ArrayList<String> input = InputLoader.toStringList("input/day5.txt");
        int grid[][] = new int[1000][1000];

        for (String s : input) {
            if (!s.equals("")) {
                int x1, x2, y1, y2;
                String entry[] = s.split(" -> |,");
                x1 = Integer.parseInt(entry[0]);
                y1 = Integer.parseInt(entry[1]);
                x2 = Integer.parseInt(entry[2]);
                y2 = Integer.parseInt(entry[3]);

                if (x1 == x2) {
                    for (int i = y1; i <= y2; i++) {
                        grid[x1][i] ++;
                    }
                    for (int i = y2; i <= y1; i++) {
                        grid[x1][i] ++;
                    }
                } else if (y1 == y2) {
                    for (int i = x1; i <= x2; i++) {
                        grid[i][y1] ++;
                    }
                    for (int i = x2; i <= x1; i++) {
                        grid[i][y1] ++;
                    }
                } else {
//                    System.out.println(x1+","+y1+" -> "+x2+","+y2);
                    if (x1-x2 == y1-y2) {
                        for(int i = 0; i <= (x1-x2); i++) { //diagonal right down -- left up
                            grid[x2+i][y2+i] ++;
//                            System.out.println((x2+i)+","+(y2+i));
                        }
                        for(int i = 0; i <= (x2-x1); i++) { //diagonal left up -- right down
                            grid[x1+i][y1+i] ++;
//                            System.out.println((x1+i)+","+(y1+i));
                        }
                    } else if (x2-x1 == y1-y2) {
//                        System.out.println(x1+","+y1+" -> "+x2+","+y2);
                        for(int i = 0; i <= (x1-x2); i++) { //diagonal right up -- left down
                            grid[x1-i][y1+i] ++;
//                            System.out.println((x1-i)+","+(y1+i));
                        }
                        for(int i = 0; i <= (x2-x1); i++) { //diagonal left down -- right up
                            grid[x1+i][y1-i] ++;
//                            System.out.println((x1+i)+","+(y1-i));
                        }
                    } else {
                        System.out.println("not dia");
                    }
                }
            }
        }

        for (int line[] : grid) {
            for (int point : line) {
                if (point >= 2) {
                    res ++;
                }
            }

        }
        System.out.println(res);
    }
}
