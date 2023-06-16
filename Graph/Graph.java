package Graph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ASUS
 */

public class Graph <T extends Comparable<T>, N extends Comparable<N>>{
    private HashMap<Location, ArrayList<Edge>> map;
    private LinkedList<Edge> edgeList;

    Vertex1<T, N> head;
    int size;

    public Graph() {
        this.map = new HashMap<>();
        this.edgeList = new LinkedList<>();
        head = null;
        size = 0;
    }

    public void addVertex(Location v) {
        if (!map.containsKey(v)) {
            map.put(v, new ArrayList<>());
        }
    }

    public Location getVertex(String name) {
        for (Location location : map.keySet()) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }

    public void addEdge(Location source, Location destination, int weight) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        map.get(source).add(new Edge(destination, source, weight));

        if (source.getName().equals("Town Hall")) {
            edgeList.addFirst(new Edge(destination, source, weight));

        } else {
            edgeList.addLast(new Edge(destination, source, weight));
        }
    }

    public ArrayList<Edge> getEdge(Location loc) {
        if (map.containsKey(loc)) {
            return map.get(loc);
        }
        return null;
    }

    public void printGraph() {
        for (Location v : map.keySet()) {
            System.out.print(v.getName() + " --> ");
            for (Edge e : map.get(v)) {
                System.out.print(e.getTovertex().getName() + " (" + e.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public LinkedList<Edge> getEdgeList() {
        return edgeList;
    }
    
    public List<String> getNeighbors(Location vertex){
        
        if (map.containsKey(vertex)) {
            ArrayList<Edge> temp = map.get(vertex);
            List<String> neighbors = new ArrayList<>();
            
            for (int i = 0; i < temp.size(); i++) {
                neighbors.add(temp.get(i).getTovertex().getName());
            }
            return neighbors;
        }
        
        System.out.println("No key");
        return null;
    }
    
    public boolean containsCity(String cityName){
        return (this.getVertex(cityName) != null);
    }

        // For extra feature 4
        public void resetVertices() {
            Vertex1<T, N> temp = head;
            while (temp != null) {
                temp.setDistance();
                temp.setVisited();
                temp.setShortestPaths();
                temp = temp.nextVertex;
            }
        }

        public boolean hasVertex(T v) {
            if (head == null)
                return true;
            Vertex1<T, N> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0)
                    return false;
                temp = temp.nextVertex;
            }
            return true;
        }

        public Vertex1<T, N> getVertex1(T v) {
            Vertex1<T, N> temp = head;
            while (temp != null) {
                if (temp.vertexInfo.compareTo(v) == 0) {
                    return temp;
                }
                temp = temp.nextVertex1;
            }
            return null;
        }

        public N getEdgeWeight1(T source1, T destination1) {
            if (head == null)
                return null;
            if (hasVertex(source1) || hasVertex(destination1))
                return null;
            Vertex1<T, N> sourceVertex1 = head;
            while (sourceVertex1 != null) {
                if (sourceVertex1.vertexInfo.equals(source1)) {
                    // Reached source vertex, look for destination now
                    Edge<T, N> currentEdge = sourceVertex1.firstEdge;
                    while (currentEdge != null) {
                        if (currentEdge.toVertex.vertexInfo.compareTo(destination1) == 0)
                            // destination vertex found
                            return currentEdge.weight;
                        currentEdge = currentEdge.nextEdge;
                    }
                }
                sourceVertex1 = sourceVertex1.nextVertex;
            }
            return null;
        }

        public void resetEdgeWeight(T spurNode, T nextSpurNode, N maxValue) {
            Vertex1<T, N> sourceVertex1 = getVertex1(spurNode);
            if (sourceVertex1 != null) {
                Edge<T, N> currentEdge = sourceVertex1.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(nextSpurNode) == 0) {
                        currentEdge.weight = maxValue;
                        break;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
        }

        public static class Vertex1<T extends Comparable<T>, N extends Comparable<N>> {
            public T vertexInfo;
            public Edge<T, N> firstEdge;
            public Vertex1<T, N> nextVertex;
            public Graph.Vertex1<T,N> nextVertex1;

            public Vertex1(T v) {
                vertexInfo = v;
                firstEdge = null;
                nextVertex = null;
            }

            public void setDistance() {
            }

            public void setVisited() {
            }

            public void setShortestPaths() {
            }

        }

        public static class Edge<T extends Comparable<T>, N extends Comparable<N>> {
            public Vertex1<T, N> toVertex;
            public Edge<T, N> nextEdge;
            public N weight;

            public Edge(Vertex1<T, N> v, N w) {
                toVertex = v;
                nextEdge = null;
                weight = w;
            }

            public Edge(Location destination, Location source, int weight) {

            }

            public Location getTovertex() {
                return null;
            }

            public String getWeight() {
                return null;
            }
        }
    }

