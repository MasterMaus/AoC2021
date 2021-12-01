package aoc2021.utilities;

import java.util.Collection;
import java.util.Collections;

public class CommonFunctions {

    // get min/ max values from array or collection
    public static long getMax(long[] input) {
        long res = input[0];
        for(long l : input) {
            if (l>res) {
                res = l;
            }
        }
        return res;
    }
    public static long getMin(long[] input) {
        long res = input[0];
        for(long l : input) {
            if (l<res) {
                res = l;
            }
        }
        return res;
    }
    public static int getMax(int[] input) {
        int res = input[0];
        for(int i : input) {
            if (i>res) {
                res = i;
            }
        }
        return res;
    }
    public static int getMin(int[] input) {
        int res = input[0];
        for(int i : input) {
            if (i<res) {
                res = i;
            }
        }
        return res;
    }
    public static int getMax(Collection<Integer> input) {
        if(input != null) {
            return Collections.max(input);
        } else {
            return 0;
        }
    }
    public static int getMin(Collection<Integer> input) {
        if(input != null) {
            return Collections.min(input);
        } else {
            return 0;
        }
    }

    // calculate manhattan distance
    public static int getManhattanDistance(int x, int y) {
        if (x < 0) {
            x *= -1;
        }
        if (y < 0) {
            y *= -1;
        }
        return x + y;
    }
    public static double getManhattanDistance(double x, double y) {
        if (x < 0) {
            x *= -1;
        }
        if (y < 0) {
            y *= -1;
        }
        return x + y;
    }
    public static long getManhattanDistance(long x, long y) {
        if (x < 0) {
            x *= -1;
        }
        if (y < 0) {
            y *= -1;
        }
        return x + y;
    }

    public static boolean inRange(int val, int min, int max) {
        if(val >= min && val <= max) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean inRange(long val, long min, long max) {
        if(val >= min && val <= max) {
            return true;
        } else {
            return false;
        }
    }
}
