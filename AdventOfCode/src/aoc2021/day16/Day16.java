package aoc2021.day16;

import aoc2021.utilities.InputLoader;

import java.util.ArrayList;

public class Day16 {

    public static void run() {
        int res1 = 0;
        int res2 = 0;

        System.out.println("Solutions day x:");

        ArrayList<Packet> packets = new ArrayList<>();

        String input = InputLoader.asString("input/test.txt", "");

        while (!input.isEmpty()) {
            int readingPos = 1; //Start looking for "real data" at the second byte in the string
            //Parse version and type
            //always read the first 2 bytes in string
            int versionType = Integer.parseInt(input.substring(0,2),16);
            int version = (versionType & 0b11100000) >> 5; // The first 3 bits of the 8 bits versionType represents the version
            int typeID = (versionType & 0b00011100) >> 2; // The next 3 bits of the 8 bits versionType represents the typeID

            res1 += version;

            if (typeID == 4) { //typeID == 4 --> literal
                int signalbit = 1; // starts at 1, decrements with 1. always mod 4.
                int group = Integer.parseInt(input.substring(readingPos, readingPos+2),16);
                readingPos ++;
                while(hasNextGroup(group, signalbit)) {
                    signalbit --;
                    if (signalbit < 0) {
                        signalbit = 3;
                    }

                    group = Integer.parseInt(input.substring(readingPos, readingPos+2),16);
                    readingPos ++;
                    // Do stuff to actually read the group
                }
                // Read the last group.
                System.out.println(input.substring(0,readingPos+1));
                input = input.substring(readingPos+1); //Remove the entire packet from input. read next.
                } else {
                //This packet will be an operator
            }
        }

        System.out.println(input);



        System.out.println("part 1: " + res1);
        System.out.println("part 2: " + res2);
    }

    /**
     * determines if there is going to be a next group
     * @param group 8 bit integer number
     * @param i number between 0 and 3. represents what bit in the second byte is the signal bit (3210 xxxx)
     * @return true if the signalbit is 1, false if the signalbit is 0
     */
    private static boolean hasNextGroup(int group, int i) {
        int res = group >> i+4;
        return (res & 0b1) == 1;
    }
}
