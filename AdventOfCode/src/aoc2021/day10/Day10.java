package aoc2021.day10;

import aoc2021.utilities.InputLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    public static void run() {
        int res1 = 0;
        long res2 = 0;

        ArrayList<String> corrupt = new ArrayList<>();
        ArrayList<String> incomplete = new ArrayList<>();
        ArrayList<Long> fixCost = new ArrayList<>();

        System.out.println("Solutions day 10:");

        ArrayList<String> input = InputLoader.toStringList("input/day10.txt");

        for (String line : input) {
            // check for corruption here!
            ArrayDeque<Character> validationQueue = new ArrayDeque<>();
            for (char operator : line.toCharArray()) { // if operator is opening bracket
                if (operator == '(' || operator == '[' || operator == '{' || operator == '<') {
                    validationQueue.add(operator);
                } else { //operator is closing bracket
                    if(operator == ')') {
                        if (validationQueue.peekLast() == '(') {
                            validationQueue.removeLast();
                        } else {
                            //invalid operator found!
                            res1 += 3; //this operator relates to 3 points
                            corrupt.add(line);
                            break;
                        }
                    } else if (operator == ']') {
                        if (validationQueue.peekLast() == '[') {
                            validationQueue.removeLast();
                        } else {
                            //invalid operator found!
                            res1 += 57; //this operator relates to 57 points
                            corrupt.add(line);
                            break;
                        }

                    } else if (operator == '}') {
                        if (validationQueue.peekLast() == '{') {
                            validationQueue.removeLast();
                        } else {
                            //invalid operator found!
                            res1 += 1197; //this operator relates to 1197 points
                            corrupt.add(line);
                            break;
                        }
                    } else if (operator == '>') {
                        if (validationQueue.peekLast() == '<') {
                            validationQueue.removeLast();
                        } else {
                            //invalid operator found!
                            res1 += 25137; //this operator relates to 25137 points
                            corrupt.add(line);
                            break;
                        }
                    } else {
                        System.out.println("ERROR WITH PARSING LINE. UNKNOWN OPERATOR");
                    }

                }
            }
            if (!corrupt.contains(line)) {
                incomplete.add(line);
                long score = 0;
                while (!validationQueue.isEmpty()) {
                    score *= 5;
                    char expectation = validationQueue.pollLast();
                    if (expectation == '(') {
                        score += 1;
                    } else if (expectation == '[') {
                        score += 2;
                    } else if (expectation == '{') {
                        score += 3;
                    } else if (expectation == '<') {
                        score += 4;
                    } else {
                        System.out.println("ERROR WITH PARSING LINE. UNKNOWN OPERATOR");
                    }
                }
            fixCost.add(score);
            }
        }

        System.out.println("part 1: " + res1);
        Collections.sort(fixCost); // sort arraylist
        res2 = fixCost.get(fixCost.size()/2); //get the median value
        System.out.println("part 2: " + res2);


    }
}
