package aoc2021.day16;

import java.util.ArrayList;

public class Packet {

    int version;
    int typeID;
    boolean lengthID;
    int subPackets;
    ArrayList<Packet> subPacketList = new ArrayList<>();

    public Packet (String hex) {

    }

    public Packet (int number) {

    }
}
