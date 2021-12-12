package aoc2021.day12;

import java.util.HashSet;
import java.util.Objects;

public class Cave {

    private String id;
    private boolean isBig;
    private HashSet<Cave> edges;

    public Cave(String id) {
        this.id = id;
        isBig = id.equals(id.toUpperCase());
        edges = new HashSet<>();
    }

    public void addEdge(Cave cave){
        edges.add(cave);
    }

    public HashSet<Cave> getEdges() {
        return edges;
    }

    public boolean isBig() {
        return isBig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cave)) return false;
        Cave cave = (Cave) o;
        return isBig == cave.isBig &&
                Objects.equals(id, cave.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isBig);
    }

    @Override
    public String toString() {
        return id;
    }

    public String getID() {
        return id;
    }
}
