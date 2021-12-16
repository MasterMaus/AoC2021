package aoc2021.day16;

import java.util.ArrayList;

public class Packet {

    private int version;
    private int type;
    private long literal = -1;
    private int subPackets = 0;
    private ArrayList<Packet> subPacketList = null;

    public Packet (int version, int type) {
        this.version = version;
        this.type = type;
        subPacketList = new ArrayList<>();

    }

    public Packet (int version, Long value) {
        this.version = version;
        type = 4;
        literal = value;
    }

    public void addPacket (Packet p) {
        if (subPacketList != null) {
            subPacketList.add(p);
            subPackets ++;
        }
    }


    public ArrayList<Packet> getSubPackets() {
        return subPacketList;
    }

    @Override
    public String toString() {
        String res = "PACKET -- Version: " + version + ", type: " + type + "\n";
        if (type == 4) { //literal
            res += "value: " + literal;
        }
        return res;
    }

    public int getSummedVersion() {
        int res;
        if (version < 0) { //This is the master packet. its only a container and therefor has no version
            res = 0;
        } else {
            res = version;
        }
        if (subPacketList != null) {
            for (Packet p: subPacketList) {
                res += p.getSummedVersion();
            }
        }

        return res;
    }

    public void calculate() {
        for(Packet p : subPacketList) {
            if (p.literal == -1) {
                p.calculate();
            }
        }
        if (type >= 0){
            for (Packet p : subPacketList) {
                if (p == null) {
                    p.calculate();
                }
            }
            switch(type) {
                case 0: //sum
                    literal = 0;
                    for (Packet p : subPacketList) {
                        literal += p.literal;
                    }
                    break;
                case 1: //product
                    literal = 1;
                    for (Packet p : subPacketList) {
                        literal *= p.literal;
                    }
                    break;
                case 2: //minimum
                    literal = subPacketList.get(0).literal;
                    for (Packet p : subPacketList)
                    {
                        if (literal > p.literal) {
                            literal = p.literal;
                        }
                    }
                    break;
                case 3: //maximum
                    literal = subPacketList.get(0).literal;
                    for (Packet p : subPacketList) {
                        if (literal < p.literal) {
                            literal = p.literal;
                        }                    }
                    break;
                case 5: //greater
                    if (subPacketList.get(0).literal > subPacketList.get(1).literal) {
                        literal = 1;
                    } else {
                        literal = 0;
                    }
                    break;
                case 6: //less then
                    if (subPacketList.get(0).literal < subPacketList.get(1).literal) {
                        literal = 1;
                    } else {
                        literal = 0;
                    }
                    break;
                case 7: //equal
                    if (subPacketList.get(0).literal == subPacketList.get(1).literal) {
                        literal = 1;
                    } else {
                        literal = 0;
                    }
                    break;
            }
        }
    }

    public long getLiteralValue() {
        return literal;
    }
}
