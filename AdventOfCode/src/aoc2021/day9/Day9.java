package aoc2021.day9;

import aoc2021.utilities.InputLoader;

import java.util.*;

public class Day9 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 9:");

        //ArrayList<Integer> input = InputLoader.toIntegerList("input/day9.txt");
        ArrayList<String> input = InputLoader.toStringList("input/day9.txt");

        int[][] map = new int[input.size()][input.get(0).length()];
        TreeMap<Integer,Integer> bassins = new TreeMap<>();

        for (int y = 0; y<map.length; y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length();x++) {
                map[y][x] = (Integer.parseInt(line.substring(x,x+1)));
            }
        }

        for (int y = 0; y<map.length;y++) {
            for(int x = 0; x<map[y].length; x++) {
                int point = map[y][x];
                if (isLowest(map, y, x)) {
                    res += point + 1;
                    int[] location = {y, x};
                    bassins.put(getId(location),getBassinSize(map, location));
                }
            }
        }

        System.out.println(bassins.size());

        Collection<Integer> bassinSizes = bassins.values();
        int max1 = Collections.max(bassinSizes);
        bassinSizes.remove(max1);
        int max2 = Collections.max(bassinSizes);
        bassinSizes.remove(max2);
        int max3 = Collections.max(bassinSizes);
        bassinSizes.remove(max3);
        System.out.println("There are " + res + " low points");
        System.out.println("The product of the size of the 3 biggest bassins is: " + max1*max2*max3);

    }

    public static boolean isLowest(int[][] map, int y, int x) {
        int point = map[y][x];
        int top = 99, bot = 99, left = 99, right = 99;
        if(y>0 && y<99) {
            top = map[y-1][x];
            bot = map[y+1][x];
        } else if (y==0) {
            bot = map[y+1][x];
        } else if (y==99) {
            top = map[y-1][x];
        }

        if(x>0 && x<99) {
            left = map[y][x-1];
            right = map[y][x+1];
        } else if (x==0) {
            right = map[y][x+1];
        } else if (x==99) {
            left = map[y][x-1];
        }

        if(point < top && point < bot && point < left && point < right) { // if at least 1 neighbor is lower return false
            return true;
        } else {
            return false;
        }
    }

    public static int getBassinSize(int[][] map, int[] pos) {
        TreeMap<Integer, Integer> bassin = fillBassin(map, new TreeMap<Integer, Integer>(), pos);
        int size = 0;
        return bassin.size();
    }

    public static TreeMap<Integer, Integer> fillBassin(int[][] map, TreeMap<Integer, Integer> bassin, int[] pos) {
        int y = pos[0];
        int x = pos[1];
        bassin.put(getId(pos[0], pos[1]), map[y][x]);

        int[] top = {y-1, x};
        int[] bot = {y+1, x};
        int[] left = {y, x-1};
        int[] right = {y, x+1};

        if(y==0) {
            //dont check top
            if(map[bot[0]][bot[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(bot))) {
                    bassin = fillBassin(map, bassin, bot);
                }
            }
        } else if (y==99){
            //dont check bot
            if(map[top[0]][top[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(top))) {
                    bassin = fillBassin(map, bassin, top);
                }
            }
        } else {
            //check both top and bot
            if(map[bot[0]][bot[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(bot))) {
                    bassin = fillBassin(map, bassin, bot);
                }
            }
            if(map[top[0]][top[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(top))) {
                    bassin = fillBassin(map, bassin, top);
                }
            }
        }

        if(x==0) {
            //dont check left
            if(map[right[0]][right[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(right))) {
                    bassin = fillBassin(map, bassin, right);
                }
            }
        } else if (x==99) {
            //dont check right
            if(map[left[0]][left[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(left))) {
                    bassin = fillBassin(map, bassin, left);
                }
            }
        } else {
            //check both left and right
            if(map[right[0]][right[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(right))) {
                    bassin = fillBassin(map, bassin, right);
                }
            }
            if(map[left[0]][left[1]] < 9) {
                //This place is not a wall
                if(!bassin.containsKey(getId(left))) {
                    bassin = fillBassin(map, bassin, left);
                }
            }

        }

        return bassin;
    }

    public static int getId(int y ,int x) {
        return y * 100 + x;
    }
    public static int getId(int[] pos) {
        return pos[0]*100 + pos[1];
    }
}
