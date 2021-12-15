package aoc2021.day15;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

public class Node {
    private int id;
    private HashMap<Node, Integer> vertices;

    public Node(int id) {
        this.id = id;
        vertices = new HashMap<>();
    }

    public int getId() {
        return id;
    }


    public HashMap<Node, Integer> getVertices() {
        return vertices;
    }

    public void addVertex(Node node, int distance) {
        vertices.put(node, distance);
    }

    public boolean hasVertex(Node node) {
        return (vertices.keySet().contains(node));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return id == node.getId();
    }

    @Override
    public String toString() {
        return "Node id: " + id + ", vertices: " + vertices.size();
    }
}
