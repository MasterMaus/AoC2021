package aoc2021.day12;

import aoc2021.utilities.InputLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day12 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day 12:");

        ArrayList<String> input = InputLoader.toStringList("input/day12.txt");

        ArrayList<Cave> nodes = new ArrayList<>();

        for (String line : input) {
            String[] path = line.split("-");
            Cave c1 = new Cave(path[0]);
            Cave c2 = new Cave(path[1]);
            if(nodes.contains(c1)) {
                c1 = nodes.get(nodes.indexOf(c1));
            } else {
                nodes.add(c1);
            }
            if(nodes.contains(c2)) {
                c2 = nodes.get(nodes.indexOf(c2));
            } else {
                nodes.add(c2);
            }

            c1.addEdge(c2);
            c2.addEdge(c1);
        }

        Cave start = null;
        Cave end = null;

        //Find the start and end nodes
        for (Cave c : nodes) {
            if (c.getID().equals("start")) {
                start = c;
            } else if (c.getID().equals("end")) {
                end = c;
            }
            if (start != null && end != null) {
                break;
            }
        }
        HashSet<ArrayDeque<Cave>> possiblePaths = findPathsv2(start, end, null, null);
        HashSet<ArrayDeque<Cave>> possiblePathsV2 = new HashSet<>();
        for (Cave c : nodes) {
            if(!c.equals(start) && !c.equals(end)) {
                possiblePathsV2.addAll(findPathsv2(start, end, null, c)); //TODO: find fix. I found out that the ArrayDeque is does not have an equals method. Therefore, addAll adds duplicates
            }
        }

//        for (ArrayDeque<Cave> path : possiblePathsV2) {
//            System.out.println(path);
//        }

        System.out.println(nodes.size());

        int duplicates = nodes.size() - 3; //start and end node do not count towards "exceptions". We have to keep 1 of the duplicates
        System.out.println("part 1: " + possiblePaths.size());
        System.out.println("part 2: " + (possiblePathsV2.size() - (possiblePaths.size()*duplicates)));
    }

    private static HashSet<ArrayDeque<Cave>> findPaths(Cave currentCave, Cave endCave, HashSet<Cave> visited) {
        //System.out.println("Currently exploring cave: " + currentCave.getID());

        if (visited == null) { //This is the very first time that findPaths is called. Need to create a new visited set
            visited = new HashSet<>();
        } else {
            visited = new HashSet<>(visited); //The function becomes recursive, only the nodes below this method need to know a place is visited. the method calls from up may still go there
        }

        if(!currentCave.isBig()) { // You may always go back to big caves
            visited.add(currentCave);
        }

        HashSet<ArrayDeque<Cave>> result = new HashSet<>();
        HashSet<Cave> edges = currentCave.getEdges();

        for (Cave c : edges) {
            if (c.equals(endCave)) {
                ArrayDeque<Cave> path = new ArrayDeque<>(); // Create a new path
                path.add(c); //Path ends at the endCave

                result.add(path); // Add this path to the result set
            } else if (!visited.contains(c)){ //If cave has not been visited (or cave is big) find all paths to end
                result.addAll(findPaths(c, endCave, visited));
            }
        }

        for (ArrayDeque<Cave> path : result) {
            path.addFirst(currentCave);
        }

        return result;
    }

    private static HashSet<ArrayDeque<Cave>> findPathsv2(Cave currentCave, Cave endCave, ArrayList<Cave> visited, Cave exception) {
        //System.out.println("Currently exploring cave: " + currentCave.getID());

        if (visited == null) { //This is the very first time that findPaths is called. Need to create a new visited set
            visited = new ArrayList<>();
        } else {
            visited = new ArrayList<>(visited); //The function becomes recursive, only the nodes below this method need to know a place is visited. the method calls from up may still go there
        }

        if(!currentCave.isBig()) { // You may always go back to big caves
            visited.add(currentCave);
        }

        HashSet<ArrayDeque<Cave>> result = new HashSet<>();
        HashSet<Cave> edges = currentCave.getEdges();

        for (Cave c : edges) {
            if (c.equals(endCave)) {
                ArrayDeque<Cave> path = new ArrayDeque<>(); // Create a new path
                path.add(c); //Path ends at the endCave

                result.add(path); // Add this path to the result set
            } else if (!visited.contains(c)){ //If cave has not been visited (or cave is big) find all paths to end
                result.addAll(findPathsv2(c, endCave, visited, exception));
            } else if (c.equals(exception)) { //Cave has been visited, but if cave is exception, it may go again. Cave is removed from being exception
                result.addAll(findPathsv2(c,endCave,visited,null));
            }
        }

        for (ArrayDeque<Cave> path : result) {
            path.addFirst(currentCave);
        }

        return result;
    }
}
