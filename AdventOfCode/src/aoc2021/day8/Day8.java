package aoc2021.day8;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;
import java.util.TreeMap;

public class Day8 {

    public static void run() {
        int res = 0;

        System.out.println("Solutions day 8:");

        ArrayList<String> input = InputLoader.toStringList("input/day8.txt");

        for(String disp : input) {
            String[] out = disp.split(" \\| ")[1].split(" ");
            for (String digit : out) {
                int len = digit.length();
                if (len == 2 || len == 4 || len == 3 || len == 7) {
                    res++;
                }
            }
        }

        System.out.println(res);

        int res2 = 0;

        for (String disp : input) {
            String[] sample = disp.split(" \\| ")[0].split(" ");
            String[] out = disp.split(" \\| ")[1].split(" ");
            TreeMap<Integer, String> digits = new TreeMap<>();
            ArrayList<String> len5 = new ArrayList<>(); //ArrrayList containing all unknown values with len 5 (3,2,5)
            ArrayList<String> len6 = new ArrayList<>(); //ArrayList containing all unknown values with len 6 (9,6,0)
            for (String digit : sample) {
                if (digit.length() == 2) {
                    digits.put(1, digit); //Places the string literal of 1 into the map.
                } else if (digit.length() == 3) {
                    digits.put(7, digit); //Places the string literal of 7 into the map
                } else if (digit.length() == 4) {
                    digits.put(4, digit);
                } else if (digit.length() == 7) {
                    digits.put(8, digit);
                } else if (digit.length() == 5) {
                    len5.add(digit);
                } else if (digit.length() == 6) {
                    len6.add(digit);
                }
            }

            //1, 4, 7, 8 are known values
            //3 can be deduced because its the only 5 length digit containing both segments of 1

            char[] one = digits.get(1).toCharArray();
            for(String digit : len5) {
                if(containsAll(digit, one)) {
                    digits.put(3, digit); // number 3 has been found, add to map
                }
            }
            len5.remove(digits.get(3)); // Remove the number 3 from the unknown

            //1, 3, 4, 7, 8 are known values
            //9 can be deduced because its the only 6 length digit containing all segments of 4
            char[] four = digits.get(4).toCharArray();
            for(String digit : len6) {
                if(containsAll(digit, four)) {
                    digits.put(9, digit); // number 9 has been found, add to map
                }
            }
            len6.remove(digits.get(9)); // Remove the number 9 from the unknown

            //1, 3, 4, 7, 8, 9 are known values
            //0 can be deduced because its the only 6 length digit left containgin all segments of 1

            for(String digit : len6) {
                if(containsAll(digit, one)) {
                    digits.put(0, digit); // number 0 has been found, add to map
                }
            }
            len6.remove(digits.get(0)); // Remove the number 3 from the unknown

            //6 is now the only digit with 6 segments left, so this one can be deduced too
            digits.put(6, len6.get(0)); // Put the only entry left in len6 into the map

            //2, 5 are the only unknown values left.
            //6 contains all segments of 5, but not all segments of 2.

            if (containsAll(digits.get(6), len5.get(0).toCharArray())) { // len5[0] = 5 and len5[1] = 2;
                digits.put(5, len5.get(0));
                digits.put(2, len5.get(1));

            } else { // len5[0] = 2 and len5[1] = 5;
                digits.put(2, len5.get(0));
                digits.put(5, len5.get(1));
            }


            String outputValue = "";
            for (String digit : out) {
                if (digit.length() == 7) {
                    outputValue = outputValue + "8";
                } else if (digit.length() == 6) {
                    if (containsAll(digit, digits.get(9).toCharArray())) {
                        outputValue = outputValue + "9";
                    } else if (containsAll(digit, digits.get(6).toCharArray())) {
                        outputValue = outputValue + "6";
                    } else if (containsAll(digit, digits.get(0).toCharArray())) {
                        outputValue = outputValue + "0";
                    }
                } else if (digit.length() == 5) {
                    if (containsAll(digit, digits.get(2).toCharArray())) {
                        outputValue = outputValue + "2";
                    } else if (containsAll(digit, digits.get(3).toCharArray())) {
                        outputValue = outputValue + "3";
                    } else if (containsAll(digit, digits.get(5).toCharArray())) {
                        outputValue = outputValue + "5";
                    }
                } else if (digit.length() == 4) {
                    outputValue = outputValue + "4";
                } else if (digit.length() == 3) {
                    outputValue = outputValue + "7";
                } else if (digit.length() == 2) {
                    outputValue = outputValue + "1";
                }
            }
            res2 += Integer.parseInt(outputValue);

        }
        System.out.println(res2);

    }

    public static boolean containsAll(String message, char[] characters) {
        for (char c : characters) {
            if (!message.contains(Character.toString(c))) {
                return false;
            }
        }
        return true;
    }
}
