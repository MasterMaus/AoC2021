package aoc2021.day15;

import aoc2021.utilities.Grid;
import aoc2021.utilities.InputLoader;

import java.util.*;

public class Day15 {

    public static void run() {

        ArrayList<String> input = InputLoader.toStringList("input/day15.txt");

        int height = input.size();
        int length = input.get(0).length();

        Grid dangerMap1 = new Grid(length, height);
        Grid dangerMap2 = new Grid(length*5, height*5);

        for (int y = 0; y<input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x<line.length(); x++) {
                int val = Integer.parseInt(line.substring(x,x+1));
                dangerMap1.set(x, y, val);
                for (int i = 0; i<5; i++) {
                    dangerMap2.set(x + (length*i), y, val); //fill the map for part 2, but then times
                    val++;
                    if (val == 10) {
                        val = 1;
                    }
                }
            }
        }

        // copy the existing dangermap 5 times below, and add 1 every time. its for part 2
        for(int y = length; y < height*5; y++) {
            for (int x = 0; x < length*5; x++) {
                int val = dangerMap2.get(x,y-height);
                val++;
                if (val == 10) {
                    val = 1;
                }
                dangerMap2.set(x,y,val);
            }
        }
        TreeMap<Integer, Node> idToNode1 = getNodeMap(dangerMap1); //part1
        TreeMap<Integer, Node> idToNode2 = getNodeMap(dangerMap2); //part1


        System.out.println("Solutions day 15:");
        System.out.println("part 1: " + computeDijkstra(idToNode1.get(0), idToNode1.get(dangerMap1.size()-1)));
        System.out.println("part 2: " + computeDijkstra(idToNode2.get(0), idToNode2.get(dangerMap2.size()-1)));
    }

    private static TreeMap<Integer, Node> getNodeMap(Grid dangerMap) {

        TreeMap<Integer, Node> res = new TreeMap<>();

        for (int id = 0; id<dangerMap.size(); id++) {
            Node currentNode = res.get(id);
            if (currentNode == null) { // ensure that node exists, if not create one and add to map
                currentNode = new Node(id);
                res.put(id, currentNode);
            }
            for (int neighborID : dangerMap.getOrthogonalNeighbors(id)) { // ensure that node exists, if not create one and add to map
                Node neighborNode = res.get(neighborID);
                if (neighborNode == null) {
                    neighborNode = new Node(neighborID);
                    res.put(neighborID, neighborNode);
                }
                neighborNode.addVertex(currentNode, dangerMap.get(id)); //Add vertex from neighbor to current node, with distance of the danger in currentNode
            }
        }
        return res;
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
