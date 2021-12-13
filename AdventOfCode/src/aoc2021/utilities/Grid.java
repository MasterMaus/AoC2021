package aoc2021.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    private int grid[];
    private int length, height;

    public Grid(int length, int height) {
        this.length = length;
        this.height = height;
        grid = new int[length * height];
    }

    public Grid(Grid copy) {
        length = copy.length;
        height = copy.height;
        grid = Arrays.copyOf(copy.grid, length*height);

    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int size() {
        return grid.length;
    }

    public int pointToId(int x, int y) {
        if(x < 0 || y < 0 || x >= length || y >= height) {
            return -1;
        }
        return y*length + x;
    }

    public int get(int id) {
        return grid[id];
    }

    public int get(int x, int y) {
        return get(pointToId(x, y));
    }

    public void set(int id, int value) {
        grid[id] = value;
    }

    public void set(int x, int y, int value){
        set(pointToId(x, y), value);
    }

    public void incrementAll(int inc) {
        for (int i = 0; i< grid.length; i++) {
            grid[i] += inc;
        }
    }

    public void increment(int id, int value) {
        grid[id] += value;
    }

    public boolean contains(int value) {
        for (int val : grid) {
            if (val == value) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getPositionsWithValue(int val) {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<grid.length; i++) {
            if (grid[i]==val) {
                res.add(i);
            }
        }
        return res;
    }

    public ArrayList<Integer> getNeighbors(int x, int y) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(pointToId(x, y-1));
        res.add(pointToId(x+1, y-1));
        res.add(pointToId(x+1, y));
        res.add(pointToId(x+1, y+1));
        res.add(pointToId(x, y+1));
        res.add(pointToId(x-1, y+1));
        res.add(pointToId(x-1, y));
        res.add(pointToId(x-1, y-1));
        while(res.remove(Integer.valueOf(-1))) {
            // keep removing values of -1
        }
        return res;
    }

    public ArrayList<Integer> getNeighbors(int id) {
        int x = id % length;
        int y = id / length; //integer division becomes floor
        return getNeighbors(x,y);
    }

    public int[] idToPos(int id) {
        int x = id % length;
        int y = id / length; //integer division becomes floor
        return new int[]{x,y};
    }

    public int count(int value) {
        int res = 0;
        for (int val : grid) {
            if (val == value) {
                res++;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String res = "";
        for (int y = 0; y<height; y++) {
            for(int x = 0; x<length; x++) {
                res = res + grid[pointToId(x,y)];
            }
            res = res + "\n";
        }
        return res;
    }

    public String toAsciiImage() {
        String res = "";
        for (int y = 0; y<height; y++) {
            for(int x = 0; x<length; x++) {
                if (grid[pointToId(x,y)] == 0) {
                    res = res + ".";
                } else if (grid[pointToId(x,y)] == 1) {
                    res = res + "#";
                } else {
                    res = res + "?";
                }
                //res = res + grid[pointToId(x,y)];
            }
            res = res + "\n";
        }
        return res;
    }
}
