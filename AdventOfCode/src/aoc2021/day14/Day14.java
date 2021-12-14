package aoc2021.day14;

import aoc2021.utilities.InputLoader;
import aoc2021.utilities.CommonFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Day14 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day 14:");

        TreeMap<String, String> polymerMapping = new TreeMap<>();
        TreeMap<String, Long> polymerOccurence = new TreeMap<>();
        TreeMap<Character, Long> elementOccurence;

        ArrayList<String> input = InputLoader.toStringList("input/day14.txt");

        String originalPolymer = input.get(0);

        for (int i = 2; i<input.size(); i++) {
            String[] line = input.get(i).split(" -> ");
            polymerMapping.put(line[0], line[1]);
        }


        for(int i = 0; i < originalPolymer.length()-1; i++) {
            String pair = originalPolymer.substring(i, i+2);
            if(polymerOccurence.keySet().contains(pair)) {
                polymerOccurence.put(pair,polymerOccurence.get(pair)+1);
            } else {
                polymerOccurence.put(pair,(long)1);
            }
        }

        for (int i = 0; i<10; i++){ // Loop over 10 times
            polymerOccurence = insertPolymers(polymerMapping, polymerOccurence);
        }
        elementOccurence = getElementOccurence(polymerOccurence, originalPolymer);

        long maximumOccurences = Collections.max(elementOccurence.values());
        long minimumOccurences = Collections.min(elementOccurence.values());

        System.out.println("part 1: " + (maximumOccurences - minimumOccurences));

        for (int i = 0; i<30; i++){ // Loop over another 30 times for a total of 40
            polymerOccurence = insertPolymers(polymerMapping, polymerOccurence);
        }
        elementOccurence = getElementOccurence(polymerOccurence, originalPolymer);

        maximumOccurences = Collections.max(elementOccurence.values());
        minimumOccurences = Collections.min(elementOccurence.values());

        System.out.println("part 2: " + (maximumOccurences - minimumOccurences));
    }


    private static TreeMap<Character, Long> getElementOccurence(TreeMap<String, Long> polymerOccurence, String originalPolymer) {
        TreeMap<Character, Long> res = new TreeMap<>();
        res.put(originalPolymer.charAt(0), (long) 1);
        //Every existing polymer becomes 2 new polymers. (LEFT)+(INSERTION)  and (INSERTION)+(RIGHT)
        for (String polymer : polymerOccurence.keySet()) {
            long occurence = polymerOccurence.get(polymer);
            char right = polymer.charAt(1);

            if(res.containsKey(right)) {
                res.put(right, res.get(right) + occurence);
            } else {
                res.put(right, occurence);
            }
        }
        return res;
    }

    private static TreeMap<String, Long> insertPolymers(TreeMap<String, String> polymerMapping, TreeMap<String, Long> polymerOccurence) {
        TreeMap<String, Long> res = new TreeMap<>();

        //count every right element of every polymer we know, and increment the very first element of the original string. This one cannot be counted in the current implementation
        for(String polymer : polymerOccurence.keySet()) {
            long occurence = polymerOccurence.get(polymer);
            String insertionPolymer = polymerMapping.get(polymer);

            String leftPolymer = polymer.substring(0,1) + insertionPolymer;
            String rightPolymer = insertionPolymer + polymer.substring(1);

            if(res.keySet().contains(leftPolymer)) {
                res.put(leftPolymer,res.get(leftPolymer)+occurence);
            } else {
                res.put(leftPolymer,occurence);
            }

            if(res.keySet().contains(rightPolymer)) {
                res.put(rightPolymer,res.get(rightPolymer)+occurence);
            } else {
                res.put(rightPolymer,occurence);
            }
        }

        return res;
    }
}
