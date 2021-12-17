package aoc2021.day16;

import aoc2021.utilities.InputLoader;

import java.math.BigInteger;

public class Day16 {

    public static void run() {

        System.out.println("Solutions day 16:");

        Packet masterPacket = new Packet(-1,-1);

        String inputHex = InputLoader.asString("input/day16.txt", "");
        String inputBin = (new BigInteger(inputHex, 16)).toString(2);

        if (inputBin.length() % 8 != 0) { // Add leading zeroes that get lost in conversion
            inputBin = "0" + inputBin;
        }

        while(!inputBin.isEmpty()) {
            int inputPointer = 0;

            inputPointer += addPacket(masterPacket, inputBin);

            while (inputPointer % 8 != 0) {
                if(inputBin.charAt(inputPointer) != '0') {
                    System.out.println("Something went wrong oops!");
                }
                inputPointer++;
            }
            inputBin = inputBin.substring(inputPointer); //Remove packet from input
        }



        System.out.println("part 1: " + masterPacket.getSummedVersion());
        masterPacket.getSubPackets().get(0).calculate();
        System.out.println("part 2: " + masterPacket.getSubPackets().get(0).getLiteralValue());
    }

    private static int createLiteral(Packet masterPacket, int version, String input) {
        int pointer = 0;
        String valueBin = "";
        while (input.charAt(pointer) == '1') {
            valueBin += input.substring(pointer +1, pointer + 5);
            pointer += 5;
        }
        valueBin = valueBin + input.substring(pointer+1, pointer +5);
        pointer += 5;

        Packet literal = new Packet(version, Long.parseLong(valueBin,2));
        masterPacket.addPacket(literal);
        return pointer;
    }

    private static int addPacket(Packet masterPacket, String inputBin) {
        int pointer = 0;
        int version = Integer.parseInt(inputBin.substring(pointer, pointer +=3),2);
        int type = Integer.parseInt(inputBin.substring(pointer, pointer += 3),2);

        if (type == 4) {
            pointer += createLiteral(masterPacket, version,inputBin.substring(pointer));
        } else {
            // if flag is true, length is packetlength. if flag is false, length is bitlength
            char flag = inputBin.charAt(pointer++);
            //Create new packet and add to masterPacket. depending on flag, add subpackets differently
            Packet packet = new Packet(version, type);
            masterPacket.addPacket(packet);
            if (flag == '1') {
                int packetSize = Integer.parseInt(inputBin.substring(pointer, pointer += 11),2);
                for (int i = 0; i < packetSize; i++) {
                    pointer += addPacket(packet, inputBin.substring(pointer));
                }
            } else {
                int stringSize = Integer.parseInt(inputBin.substring(pointer, pointer +=15),2);
                int counter = 0;
                while (counter < stringSize) {
                    counter += addPacket(packet, inputBin.substring(pointer + counter));
                }

                pointer += counter;
            }
        }

        return pointer;
    }




}
