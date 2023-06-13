package Graph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author ASUS
 */

public class Graph {
    private HashMap<Location, ArrayList<Edge>> map;
    private LinkedList<Edge> edgeList;

    public Graph() {
        this.map = new HashMap<>();
        this.edgeList = new LinkedList<>();
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
}
