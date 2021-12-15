package aoc2021.day15;

import aoc2021.utilities.Grid;
import aoc2021.utilities.InputLoader;

import java.util.*;

public class Day15 {

    public static void run() {


        System.out.println("Solutions day 15:");
        int res1 = part1();
        int res2 = part2();


        System.out.println("part 1: " + res1);
        System.out.println("part 2: " + res2);
    }

    private static int part1() {
        ArrayList<String> input = InputLoader.toStringList("input/day15.txt");

        int height = input.size();
        int length = input.get(0).length();
        Grid dangerMap = new Grid(length, height);
        TreeMap<Integer, Node> idToNode = new TreeMap<>();

        for (int y = 0; y<input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length(); x++) {
                dangerMap.set(x, y, Integer.parseInt(line.substring(x,x+1)));
            }
        }

        for (int id = 0; id<dangerMap.size(); id++) {
            Node currentNode = idToNode.get(id);
            if (currentNode == null) { // ensure that node exists, if not create one and add to map
                currentNode = new Node(id);
                idToNode.put(id, currentNode);
            }
            for (int neighborID : dangerMap.getOrthogonalNeighbors(id)) { // ensure that node exists, if not create one and add to map
                Node neighborNode = idToNode.get(neighborID);
                if (neighborNode == null) {
                    neighborNode = new Node(neighborID);
                    idToNode.put(neighborID, neighborNode);
                }
                neighborNode.addVertex(currentNode, dangerMap.get(id)); //Add vertex from neighbor to current node, with distance of the danger in currentNode
            }
        }


        return computeDijkstra(idToNode.get(0), idToNode.get(dangerMap.size()-1));
    }

    private static int part2() {
        ArrayList<String> input = InputLoader.toStringList("input/day15.txt");

        int height = input.size();
        int length = input.get(0).length();
        Grid dangerMap = new Grid(length*5, height*5);
        TreeMap<Integer, Node> idToNode = new TreeMap<>();


        for (int y = 0; y<input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length(); x++) {
                int val = Integer.parseInt(line.substring(x,x+1));
                for (int i = 0; i<5; i++) {
                    dangerMap.set(x + (length*i), y, val);
                    val++;
                    if (val == 10) {
                        val = 1;
                    }
                }
            }
        }

        for(int y = length; y < height*5; y++) {
            for (int x = 0; x < length*5; x++) {
                int val = dangerMap.get(x,y-height);
                val ++;
                if (val == 10) {
                    val = 1;
                }
                dangerMap.set(x,y,val);
            }
        }

        //System.out.println(dangerMap.toString());

        for (int id = 0; id<dangerMap.size(); id++) {
            Node currentNode = idToNode.get(id);
            if (currentNode == null) { // ensure that node exists, if not create one and add to map
                currentNode = new Node(id);
                idToNode.put(id, currentNode);
            }
            for (int neighborID : dangerMap.getOrthogonalNeighbors(id)) { // ensure that node exists, if not create one and add to map
                Node neighborNode = idToNode.get(neighborID);
                if (neighborNode == null) {
                    neighborNode = new Node(neighborID);
                    idToNode.put(neighborID, neighborNode);
                }
                neighborNode.addVertex(currentNode, dangerMap.get(id)); //Add vertex from neighbor to current node, with distance of the danger in currentNode
            }
        }


        return computeDijkstra(idToNode.get(0), idToNode.get(dangerMap.size()-1));
    }


    private static int computeDijkstra(Node start, Node end) {
        HashSet<Node> unvisited = new HashSet<>();
        HashSet<Node> visited = new HashSet<>();
        visited.add(start);
        HashMap<Node, Integer> distances = new HashMap<>(); //update distances with the node that is lowest distance. after place it in visited
        distances.put(start,0);
        HashMap<Node, Integer> vertices = start.getVertices(); // collect all vertices on the start node
        for (Node vertex : vertices.keySet()) { // Add all known distances
            distances.put(vertex, vertices.get(vertex));
            unvisited.add(vertex);
        }
        while(!unvisited.isEmpty()) {
            Node nextUpdate = null;
            int dist = 0x7fffff;
            for(Node n : unvisited) {
                if (nextUpdate == null) {
                    nextUpdate = n;
                    dist = distances.get(n);
                } else {
                    if (distances.get(n) < dist) {
                        nextUpdate = n;
                        dist = distances.get(n);
                    }
                }
            }
//            System.out.println("Next node to visit: " + nextUpdate);
            unvisited.remove(nextUpdate);
            update(nextUpdate, unvisited, visited, distances);
            visited.add(nextUpdate);
        }

        return distances.get(end);
    }

    private static void update(Node nodeUpdate, HashSet<Node> unvisited, HashSet<Node> visited, HashMap<Node, Integer> distances) {
        int updateDistance = distances.get(nodeUpdate);
        HashMap<Node, Integer> vertices = nodeUpdate.getVertices(); // collect all vertices on the start node

        for (Node vertex : vertices.keySet()) { // Add all known distances
            if(visited.contains(vertex)) {
                //This node is already visited!
            } else if (unvisited.contains(vertex)) {
                //This node is already "known", but its current distance may be bigger
                int distance = updateDistance + vertices.get(vertex); // The total distance from start to vertex = updateDistance + distance from updateNode to vertex
                int currentDistance = distances.get(vertex);
                if (distance < currentDistance) {
                    distances.put(vertex, distance);
                }
            } else {
                //This node is a new one!
                unvisited.add(vertex);
                int distance = updateDistance + vertices.get(vertex); // The total distance from start to vertex = updateDistance + distance from updateNode to vertex
                distances.put(vertex, distance);
            }
        }

    }

}
